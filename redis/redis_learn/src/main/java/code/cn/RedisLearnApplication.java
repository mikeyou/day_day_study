package code.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableCaching
@MapperScan(basePackages = "code.cn.dao")
public class RedisLearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisLearnApplication.class, args);
    }
}
