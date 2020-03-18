package team.fjut.cf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 初始化Springboot
 *
 * @author axiang [2019/10/07]
 */
@SpringBootApplication
@MapperScan(basePackages = {"team.fjut.cf.mapper"})
@EnableTransactionManagement
@EnableScheduling
public class CodingFirstApplication {
    public static void main(String[] args) {
        SpringApplication.run(CodingFirstApplication.class, args);
    }
}
