package com.leyou.goods.client;

import com.leyou.common.PageResult;
import com.leyou.item.pojo.Spu;
import com.leyou.item.vo.SpuBo;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
@Component
public class GoodsClientFallBack implements GoodsClient {
    @Override
    public PageResult<SpuBo> querySpuBoByPage(String key, Boolean saleable, Integer page, Integer rows) {
        return new PageResult<SpuBo>(0L,new LinkedList<SpuBo>());
    }

    @Override
    public Spu querySpuById(Long id) {
        return new Spu();
    }
}
