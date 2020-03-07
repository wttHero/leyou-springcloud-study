package com.leyou.item.service;

import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 根据parentId获取Category集合（根据父节点id查询子节点）
     * @param pid
     * @return
     */
    public List<Category> queryCategoriesListByPid(Long pid) {
        Category category = new Category();
        category.setParentId(pid);
        return categoryMapper.select(category);
//        return categoryMapper.selectByExample(new Example(Category.class).createCriteria().andEqualTo("parentId",pid));
    }

    public List<String> queryNamesByIds(List<Long> list) {
        Example example = new Example(Category.class);
        example.createCriteria().andIn("id",list);
        return categoryMapper.selectByExample(example).stream().map(Category::getName).collect(Collectors.toList());
    }
}
