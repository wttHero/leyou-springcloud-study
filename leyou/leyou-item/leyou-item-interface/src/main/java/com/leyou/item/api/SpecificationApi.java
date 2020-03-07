package com.leyou.item.api;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
/**
 * 对开发SpecificationController模块的开发者需要提供该模块的fegin远程调用的api接口
 * 其他微服务中调用时只需要继承该接口即可,注意接口中的返回值是元返回值，不包含ResponsEntity的包裹
 * 如ResponsEntity<User>,下面的对应接口返回的应该是User
 */

public interface SpecificationApi {

    /**
     * 根据分类id查询参数组
     * @param cid 分类id
     * @return 参数组集合
     */
    @GetMapping("/groups/{cid}")
    public List<SpecGroup> queryGroupsByCid(@PathVariable("cid") Long cid);

    /**
     * 根据条件gid查询规格参数
     * @param gid 分类id
     * @return 规格参数集合
     */
    @GetMapping("/params")
    public List<SpecParam> queryParamsByCid(Long gid);
}
