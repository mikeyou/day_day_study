package code.cn.dao;

import code.cn.model.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserDao extends Mapper<User> {

    //查询所有用户
    List<User> findAllUserList();
}
