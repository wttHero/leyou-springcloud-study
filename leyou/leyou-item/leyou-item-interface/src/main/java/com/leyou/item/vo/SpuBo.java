package com.leyou.item.vo;

import com.leyou.item.pojo.Spu;

public class SpuBo extends Spu {
    
        String cname;// 商品分类名称
        
        String bname;// 品牌名称

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }
    // 略 。。
    }