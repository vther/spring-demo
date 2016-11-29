package com.vther.spring.mvc.hello.dao;

import com.vther.spring.mvc.hello.vo.QueryVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-context.xml"})
public class IUserDaoTest {
    @Test
    public void findUser() throws Exception {
        beautyPrint(userMapper.findUser(116));
    }

    @Autowired
    private IUserDao userMapper;

    @Test
    public void count() throws Exception {
        beautyPrint(userMapper.count());
    }
    @Test
    public void findUsers() throws Exception {
        QueryVO vo  = new QueryVO();
        vo.setAge(116);
        vo.setName("qq");
        beautyPrint(userMapper.findUsers(vo));
    }

    private void beautyPrint(Object o){
        System.out.println("----------------------------");
        System.out.println("----------------------------");
        System.out.println("----------------------------");
        System.out.println("----------------------------");
        System.out.println("----------------------------");
        System.out.println("----------------------------" + o);
        System.out.println("----------------------------");
        System.out.println("----------------------------");
        System.out.println("----------------------------");
        System.out.println("----------------------------");
        System.out.println("----------------------------");
    }
}