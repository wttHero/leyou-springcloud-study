package com.leyou.goods.service;

import com.leyou.goods.client.BrandClient;
import com.leyou.goods.client.CategoryClient;
import com.leyou.goods.client.GoodsClient;
import com.leyou.goods.client.SpecificationClient;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Spu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GoodsService {
    @Autowired
    private GoodsClient goodsClient;
    @Autowired
    private SpecificationClient specificationClient;
    @Autowired
    private CategoryClient categoryClient;
    @Autowired
    private BrandClient brandClient;
    public Map<String, Object> queryDataById(Long id) {
        Map<String, Object> dataMap = new HashMap<>();
        //查询spu
        Spu spu = goodsClient.querySpuById(id);
        dataMap.put("spu",spu);
        //查询分页 Map<String,Object>
        List<Long> cids = Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3());
        List<String> categoryNames = categoryClient.queryNamesByIds(cids);
        //初始化一个分页的map
        List<Map<String,Object>> categorys = new ArrayList<>();
        for (int i = 0; i < cids.size(); i++) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",cids.get(i));
            map.put("name",categoryNames.get(i));
            categorys.add(map);
        }
        dataMap.put("categorys",categorys);
        //查询品牌
        Brand brand = brandClient.queryBrandById(spu.getBrandId());
        dataMap.put("brand",brand);
        return dataMap;
    }

    public Spu querySpuById(Long id) {
        return goodsClient.querySpuById(id);
    }
}
