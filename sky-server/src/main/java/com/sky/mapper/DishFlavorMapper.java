package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    void insertBatch(List<DishFlavor> flavors);


/**
     * 根据菜品ID查询对应的口味数据
     * @param id
     * @return
     */
    @Select("select * from dish_flavor where dish_id = #{dishId}")
    List<DishFlavor> getByDishId(Long id);
/**
     * 根据菜品ID删除对应的口味数据
     * @param id
     */
    void deleteByDishId(Long id);
    /**
     * 根据ID批量删除
     * @param dishIds
     */
    void deleteByDishIds(List<Long> dishIds);
}
