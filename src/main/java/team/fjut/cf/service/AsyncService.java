package team.fjut.cf.service;

/**
 * 异步线程测试Service
 *
 * @author axiang [2019/10/14]
 */
public interface AsyncService {
    /**
     * 模拟耗时的生成报表
     */
    void generateReport();
}
