package com.leyou.item.api;

import com.leyou.common.PageResult;
import com.leyou.item.pojo.Brand;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对开发BrandController模块的开发者需要提供该模块的fegin远程调用的api接口
 * 其他微服务中调用时只需要继承该接口即可,注意接口中的返回值是元返回值，不包含ResponsEntity的包裹
 * 如ResponsEntity<User>,下面的对应接口返回的应该是User
 */
@RequestMapping("/brand")
public interface BrandApi {

    /**
     * 根据查询条件分页并排序查询品牌信息
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    @GetMapping("page")
    public PageResult<Brand> queryBrandsByPage(
            @RequestParam(value = "key",required = false)String key,
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows",defaultValue = "5")Integer rows,
            @RequestParam(value = "sortBy",required = false)String sortBy,
            @RequestParam(value = "desc",required = false)Boolean desc
    );

    @PostMapping
    public  void saveBrand(Brand brand, @RequestParam("cids")List<Long> cids);
    @GetMapping("{id}")
    public Brand queryBrandById(@PathVariable("id")Long id);

}
