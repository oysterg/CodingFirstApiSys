package team.fjut.cf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author axiang [2019/10/07]
 */
@SpringBootApplication
@MapperScan("team.fjut.cf.mapper")
@EnableTransactionManagement
public class CodingFirstApplication {
    public static void main(String[] args) {
        SpringApplication.run(CodingFirstApplication.class, args);
    }
}
