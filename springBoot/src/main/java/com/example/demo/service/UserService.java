package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;


public interface UserService {


    /** 
     *@Description: 创建用户
     *@Param: [username, name] 
     *@return: void 
     *@Author: zhangchao
     *@Date: 2019/12/14 14:49
    */
    public void createUser(String username, String name);


    /**
     *@Description: 创建用户
     *@Param: [username, name]
     *@return: void
     *@Author: zhangchao
     *@Date: 2019/12/14 14:49
     */
    public User findById(Integer id);
}
