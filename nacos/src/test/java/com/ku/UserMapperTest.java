package com.ku;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ku.config.MybatisPlusJoinTestConfig;
import com.ku.entity.User;
import com.ku.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import java.util.List;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@MybatisTest
@Import(MybatisPlusJoinTestConfig.class)
@MapperScan("com.ku.mapper")
class UserMapperTest {

    @Autowired
    private UserMapper mapper;

    @Test
    void findAll() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        List<User> users = mapper.selectList(queryWrapper);
        System.out.println(users);
    }

    @Test
    void findById() {

        User user = mapper.selectById(1L);
        System.out.println(user);
    }

    @Test
    void insert() {

        User entity = new User();
        entity.setId(4L);
        entity.setName("test");
        entity.setEmail("test@mail.com");

        mapper.insert(entity);
        System.out.println("finished");
    }

    @Test
    void findByCondition() {

        List<User> users = mapper.findByCondition();
        System.out.println(users);
    }
}
