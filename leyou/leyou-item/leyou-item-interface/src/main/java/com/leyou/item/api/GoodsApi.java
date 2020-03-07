package com.leyou.item.api;

import com.leyou.common.PageResult;
import com.leyou.item.pojo.Spu;
import com.leyou.item.vo.SpuBo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 对开发BrandController模块的开发者需要提供该模块的fegin远程调用的api接口
 * 其他微服务中调用时只需要继承该接口即可,注意接口中的返回值是元返回值，不包含ResponsEntity的包裹
 * 如ResponsEntity<User>,下面的对应接口返回的应该是User
 */
public interface GoodsApi {
    /**
     * 根据条件查询spu分页
     * @param key 关键字
     * @param saleable 上下架
     * @param page 默认页数
     * @param rows 一页的条数
     * @return
     */
    @GetMapping("spu/page")
    public PageResult<SpuBo> querySpuBoByPage(
            @RequestParam(value = "key", required = false)String key,
            @RequestParam(value = "saleable", required = false)Boolean saleable,
            @RequestParam(value = "page", defaultValue = "1")Integer page,
            @RequestParam(value = "rows", defaultValue = "5")Integer rows
    );

    @GetMapping("spu/{id}")
    public Spu querySpuById(@PathVariable("id")Long id);

}
