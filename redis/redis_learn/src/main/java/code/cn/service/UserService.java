package code.cn.service;

import code.cn.model.User;

public interface UserService {

    //初始化用户数据
    boolean initUserData();

    User findUserById(Integer id);
}
