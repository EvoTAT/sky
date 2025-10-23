package com.sky.mapper;

import com.sky.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.Map;

@Mapper
public interface UserMapper {

    /**
     * 根据openid查询用户
     * @param openid
     * @return
     */
    @Select("select * from user where openid=#{openid}")
    User getByOpenid(String openid);


    void insert(User user);

    @Select("select * from user where id=#{userId}")
    User getById(Long userId);

    Integer countByDate(LocalDate date);

    Integer countTotalByDate(LocalDate date);

    Integer countByMap(Map map);
}
