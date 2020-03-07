package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class BrandService {
    @Resource
    private BrandMapper brandMapper;

    /**
     * 根据查询条件分页并排序查询品牌信息
     *
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    public PageResult<Brand> queryBrandsByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {

        //根据name模糊查询，或者根据首字母查询
        //添加分页条件
        PageHelper.startPage(page, rows);
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        //根据name模糊查询，首字母查询
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("name", "%" + key + "%").orEqualTo("letter", key);
        }
        //添加排序条件
        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));
        }
        List<Brand> result = brandMapper.selectByExample(example);
        PageInfo<Brand> pageInfo = new PageInfo<>(result);
        return new PageResult<Brand>(pageInfo.getTotal(),pageInfo.getList());
    }

    public void saveBrand(Brand brand, List<Long> cids) {
        int i = brandMapper.insertSelective(brand);
        if(i==1){
            cids.forEach(cid ->{
                brandMapper.insertCategoryAndBrand(cid,brand.getId());
            });
        }
    }

    public Brand queryBrandsById(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }
}
