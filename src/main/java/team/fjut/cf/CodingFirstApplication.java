package team.fjut.cf;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CodingFirstApplication {
    public static void main(String[] args) {
        log.info("【CodingFirst】- 正在进行Spring初始化");
        SpringApplication.run(CodingFirstApplication.class, args);
        log.info("【CodingFirst】- Spring初始化成功");
    }
}
