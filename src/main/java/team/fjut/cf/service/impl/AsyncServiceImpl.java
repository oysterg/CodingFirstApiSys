package team.fjut.cf.service.impl;

import team.fjut.cf.service.AsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author axiang [2019/10/14]
 */
@Service
public class AsyncServiceImpl implements AsyncService {
    @Override
    @Async
    public void generateReport() {
        System.out.println("进入模拟生成报表线程");
        try {
            Thread.sleep(1000*5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("报表线程名称：" + "【" + Thread.currentThread().getName() + "】");
        System.out.println("进入模拟生成报表线程");
    }
}
