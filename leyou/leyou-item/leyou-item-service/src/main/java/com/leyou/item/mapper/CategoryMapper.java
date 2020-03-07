package com.leyou.item.mapper;

import com.leyou.item.pojo.Category;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

//说明 SelectByIdListMapper<Category,Long> 提供根据id集合去查询实体类集合
public interface CategoryMapper extends Mapper<Category>, SelectByIdListMapper<Category,Long> {
}
