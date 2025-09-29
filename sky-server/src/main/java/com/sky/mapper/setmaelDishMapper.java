package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface setmaelDishMapper {
    /**
     * 根据菜品ID查询套餐ID
     * @param dishIds 菜品ID
     * @return 套餐ID列表
     */
    List<Long> getSetmealIdsByDishId(List<Long> dishIds);
}
