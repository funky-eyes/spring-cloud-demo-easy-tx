package icu.funkye.controller;

import java.util.concurrent.ThreadLocalRandom;
import icu.funkye.easy.tx.config.annotation.GlobalTransaction;
import icu.funkye.service.ITestService;
import icu.funkye.service.SagaTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author funkye
 */
@RestController
public class TestController {
    private final static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private ITestService testService;

    @Autowired
    SagaTestService localService;

    /**
     * 简单测试提交分布式事务接口
     * 
     * @return
     */
    @GetMapping(value = "commit")
    @GlobalTransaction(retry = false)
    public Object commit() {
        testService.commit();
        return true;
    }

    @GetMapping(value = "commit1")
    @GlobalTransaction(retry = false)
    public Object commit(int id) {
        localService.commitLocal(id);
        return true;
    }

    public Object rollback(int id) {
        if (!ThreadLocalRandom.current().nextBoolean()) {
            throw new RuntimeException();
        }
        logger.info("rollback id: {}", id);
        return true;
    }

    @GetMapping(value = "retryCommit")
    @GlobalTransaction(retry = true)
    public Object retryCommit(int id) {
        localService.commitLocal(id);
        localService.commitLocal2(id);
        return true;
    }

}
