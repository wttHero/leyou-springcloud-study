package com.leyou.item.api;

import com.leyou.item.pojo.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
/**
 * 对开发CategoryController模块的开发者需要提供该模块的fegin远程调用的api接口
 * 其他微服务中调用时只需要继承该接口即可,注意接口中的返回值是元返回值，不包含ResponsEntity的包裹
 * 如ResponsEntity<User>,下面的对应接口返回的应该是User
 */
@RequestMapping("category")
public interface CategoryApi {

    @GetMapping("list")
    public List<Category>queryCategoriesByPid(@RequestParam(value = "pid", defaultValue = "0") Long pid);

    /**
     * 根据名称查询商品分类名称
     * @param ids
     * @return
     */
    @GetMapping
    public List<String> queryNamesByIds(@RequestParam("ids") List<Long> ids);
}
