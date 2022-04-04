package icu.funkye.controller;

import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import icu.funkye.easy.tx.config.annotation.GlobalTransaction;
import icu.funkye.easy.tx.config.annotation.SagaTransaction;
import icu.funkye.service.ITestService;
import icu.funkye.service.SagaTestService;

/**
 * @author funkye
 */
@RestController
public class TestController {
    private final static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private ITestService testService;

    @Autowired SagaTestService localService;

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
    @SagaTransaction(confirm = "commit", clazz = TestController.class, cancel = "rollback")
    public Object commit(int id) {
        if (!ThreadLocalRandom.current().nextBoolean()) {
            throw new RuntimeException();
        }
        logger.info("commit id: {}", id);
        localService.commitLocal(2);
        return true;
    }

    public Object rollback(int id) {
        if (!ThreadLocalRandom.current().nextBoolean()) {
            throw new RuntimeException();
        }
        logger.info("rollback id: {}", id);
        return false;
    }

}
