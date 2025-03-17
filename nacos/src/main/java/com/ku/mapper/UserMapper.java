package com.ku.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yulichang.base.MPJBaseMapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.ku.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends MPJBaseMapper<User> {


    default List<User> findByCondition() {

        MPJLambdaWrapper<User> wrapper = new MPJLambdaWrapper<User>()
                .selectAll(User.class);
        return selectJoinList(wrapper);
    }

//    @Select("SELECT * FROM user WHERE id = #{id}")
//    User findById(Long id);
//
//    @Select("SELECT * FROM user")
//    List<User> findAll();
//
//    @Insert("INSERT INTO user(name, email) VALUES(#{name}, #{email})")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
//    void insert(User user);
//
//    @Update("UPDATE user SET name = #{name}, email = #{email} WHERE id = #{id}")
//    void update(User user);
//
//    @Delete("DELETE FROM user WHERE id = #{id}")
//    void deletedlete(Long id);
}
