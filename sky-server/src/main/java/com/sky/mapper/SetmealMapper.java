package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.vo.DishItemVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealMapper {

    /**
     * 根据分类id查询套餐的数量
     * @param setmealId
     * @return
     */
    @Select("select sd.name, sd.copies, d.image, d.description " +
            "from setmeal_dish sd left join dish d on sd.dish_id = d.id " +
            "where sd.setmeal_id = #{setmealId}")
    List<DishItemVO> getDishItemBySetmealId(Long setmealId);
/**
     * 插入数据
     * @param setmeal
     */
    void insert(Setmeal setmeal);
/**
     * 根据分类id查询套餐的数量
     * @param id
     * @return
     */
    @Delete("select count(1) from setmeal where category_id = #{id}")
    Integer countByCategoryId(Long id);
/**
     * 根据条件查询
     * @param setmeal
     * @return
     */
    List<Setmeal> list(Setmeal setmeal);
/**
     * 分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    Page<Setmeal> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);
/**
     * 根据id查询
     * @param id
     * @return
     */
    @Select("select * from setmeal where id = #{id}")
    Setmeal getById(Long id);
/**
     * 根据ID删除数据
     * @param setmealId
     */
    @Delete("delete from setmeal where id = #{setmealId}")
    void deleteById(Long setmealId);

    void update(Setmeal setmeal);
}
