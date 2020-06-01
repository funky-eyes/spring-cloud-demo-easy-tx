package icu.funkye.controller;

import icu.funkye.easy.tx.config.annotation.GlobalTransaction;
import icu.funkye.service.ITestService;
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

    /**
     * 简单测试提交分布式事务接口
     * 
     * @return
     */
    @GetMapping(value = "commit")
    @GlobalTransaction
    public Object commit() {
        testService.commit();
        return true;
    }

}
