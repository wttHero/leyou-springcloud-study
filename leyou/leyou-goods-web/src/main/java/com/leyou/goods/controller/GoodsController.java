package com.leyou.goods.controller;

import com.leyou.goods.service.GoodsService;
import com.leyou.item.pojo.Spu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/item/{id}.html")
    public String toItemPage(@PathVariable("id") Long id){
        Map<String,Object> data = goodsService.queryDataById(id);
        ModelMap model = new ModelMap();
        model.addAttribute("data",data);
        return "item";
    }

    //测试查询消息队列是否会查询时打入消息进入队列的方法
    @GetMapping("/item/{id}")
    @ResponseBody
    public ResponseEntity<Spu> fet(@PathVariable("id") Long id){
        Spu spu = goodsService.querySpuById(id);
        return ResponseEntity.ok(spu);
    }
}
