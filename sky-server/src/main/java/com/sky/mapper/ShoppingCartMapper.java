package com.sky.mapper;

import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    List<ShoppingCart> list(ShoppingCart shoppingCart);
/**
     * 更新数量
     * @param shoppingCart
     */
    @Update("update shopping_cart set number = #{number} where id = #{id}")
    void updataNumber(ShoppingCart shoppingCart);
/**
     * 插入数据
     * @param shoppingCart
     */
    @Insert("insert into shopping_cart (name,image,user_id,dish_id,setmeal_id,dish_flavor,number,amount,create_time)"
            + "values (#{name},#{image},#{userId},#{dishId},#{setmealId},#{dishFlavor},#{number},#{amount},#{createTime})")
    void insert(ShoppingCart shoppingCart);
/**
     * 批量删除
     * @param userId
     */
    @Delete("delete from shopping_cart where user_id = #{userId}")
    void deletegetByIds(Long userId);

    void insertBatch(List<ShoppingCart> shoppingCartList);
}
