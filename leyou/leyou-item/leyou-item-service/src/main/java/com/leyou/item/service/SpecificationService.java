package com.leyou.item.service;

import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SpecificationService {

    @Resource
    private SpecGroupMapper specGroupMapper;
    @Resource
    private SpecParamMapper specParamMapper;
    /**
     * 根据分类id查询参数组
     * @param cid 分类id
     * @return 参数组集合
     */
    public List<SpecGroup> queryGroupsByCid(Long cid) {
        SpecGroup group = new SpecGroup();
        group.setCid(cid);
        return specGroupMapper.select(group);
    }
    /**
     * 根据条件gid查询规格参数
     * @param gid 分类id
     * @return 规格参数集合
     */
    public List<SpecParam> queryParamsByGid(Long gid) {
        SpecParam group = new SpecParam();
        group.setGroupId(gid);
        return specParamMapper.select(group);
    }
}
