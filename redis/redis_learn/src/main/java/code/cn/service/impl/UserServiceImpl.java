package code.cn.service.impl;

import code.cn.dao.UserDao;
import code.cn.model.User;
import code.cn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private static final String USER_CACHE = "user::";

    @Override
    public boolean initUserData() {
        for (int i = 1; i <= 10; ++i) {
            User user = new User();
            String account = "Account_" + i;
            user.setAccount(account);
            String password = "Password_" + i;
            user.setPassword(password);
            user.setCreateAt(LocalDateTime.now());
            int index = this.userDao.insert(user);
            if (index > 0) {
                this.redisTemplate.opsForValue().set(USER_CACHE + user.getUserId(), user);
            }
        }
        return true;
    }

    @Override
    @Cacheable(cacheNames = USER_CACHE, key = "#id")
    public User findUserById(Integer id) {
        return this.userDao.selectByPrimaryKey(id);
    }
}
