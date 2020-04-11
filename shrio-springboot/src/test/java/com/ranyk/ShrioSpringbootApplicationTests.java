package com.ranyk;

import com.ranyk.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShrioSpringbootApplicationTests {

    @Autowired
    private UserService userService;


    @Test
    void contextLoads() {
        System.out.println(userService.queryUserByName("张三"));
    }

}
