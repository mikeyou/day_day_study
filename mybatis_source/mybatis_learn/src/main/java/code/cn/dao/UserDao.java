package code.cn.dao;

import code.cn.model.User;

import java.util.List;

public interface UserDao {

    //查询所有用户
    List<User> findAllUserList();
}
