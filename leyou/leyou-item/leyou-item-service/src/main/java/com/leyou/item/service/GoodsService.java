package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.mapper.SpuMapper;
import com.leyou.item.pojo.Spu;
import com.leyou.item.vo.SpuBo;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GoodsService {
    @Resource
    private SpuMapper spuMapper;
    @Autowired
    private CategoryService categoryService;
    @Resource
    private BrandMapper brandMapper;
    //注入rabbitmq的消息模板
    @Resource
    private AmqpTemplate amqpTemplate;

    public PageResult<SpuBo> querySpuBoByPage(String key, Boolean saleable, Integer page, Integer rows) {
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        //关键字查询
        if(StringUtils.isNotBlank(key)){
            criteria.andLike("title","%"+key+"%");
        }
        spuMapper.selectByExample(example);
        if (saleable != null) {
            criteria.andEqualTo("saleable", saleable);
        }

        // 分页条件
        PageHelper.startPage(page, rows);

        // 执行查询
        List<Spu> spus = this.spuMapper.selectByExample(example);
        PageInfo<Spu> pageInfo = new PageInfo<>(spus);

        List<SpuBo> spuBos = new ArrayList<>();
        spus.forEach(spu->{
            SpuBo spuBo = new SpuBo();
            // copy共同属性的值到新的对象
            BeanUtils.copyProperties(spu, spuBo);
            // 查询分类名称
            List<String> names = this.categoryService.queryNamesByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
            spuBo.setCname(StringUtils.join(names, "/"));

            // 查询品牌的名称
            spuBo.setBname(this.brandMapper.selectByPrimaryKey(spu.getBrandId()).getName());

            spuBos.add(spuBo);
        });
        return new PageResult<>(pageInfo.getTotal(), spuBos);
    }


    public Spu querySpuById(Long id) {
        //此处利用查询时模拟查询发送队列消息到默认的交换机（application.yml里配置的LEYOU.ITEM.EXCHANGE），（其实应该是插入数据和删除数据以及修改数据是发送通知）
        //当然下面的amqpTemplate.convertAndSend()也可以用三个参数的方法 1：交换机名称，2.routingKey（可以理解为消息的分类标签），3:msg 消息内容
        try {
            //此方法可以指定交换机名称，到时监听队列的时候也是监听此交换机即可
//            this.amqpTemplate.convertAndSend("LEYOU.ITEM.EXCHANGE","item.select",id);
            //此方法未指定交换机名称，则会使用配置文件application.yml中默认配置的交换机
            this.amqpTemplate.convertAndSend("item.select",id);
        } catch (AmqpException e) {
            e.printStackTrace();
        }finally {
            //确保防止发生异常，也要返回查询结果
            return spuMapper.selectByPrimaryKey(id);
        }
    }
}
