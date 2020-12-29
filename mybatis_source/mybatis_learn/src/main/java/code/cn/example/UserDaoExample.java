package code.cn.example;

import code.cn.dao.UserDao;
import code.cn.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserDaoExample {

    private static final Logger log = LoggerFactory.getLogger(UserDaoExample.class);

    private static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        String resource = "mybatis-configuration.xml";  //mybatis的配置文件
        try {
            InputStream inputStream;
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(resource));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlSessionFactory;
    }

    //1.初步搭建的demo
    public static void testFindUserList() {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> userList = userDao.findAllUserList();
        log.info("userList >>>>> {}", userList);
        sqlSession.close();
    }

    public static void main(String[] args) {
        testFindUserList();
    }
}
