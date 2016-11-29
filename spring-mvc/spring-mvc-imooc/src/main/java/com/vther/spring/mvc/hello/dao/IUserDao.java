package com.vther.spring.mvc.hello.dao;


import com.vther.spring.mvc.hello.vo.QueryVO;
import com.vther.spring.mvc.hello.vo.UserVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IUserDao {
    List<UserVO> findUsers(QueryVO queryVO);

    Long count();

    @Select(value = "SELECT id AS age,job_name AS name from job_info1 WHERE id = #{age}")
    UserVO findUser(int age);
}
