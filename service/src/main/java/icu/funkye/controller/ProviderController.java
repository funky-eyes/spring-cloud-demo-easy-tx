package icu.funkye.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import icu.funkye.easy.tx.config.annotation.GlobalTransaction;
import icu.funkye.service.ITestService;

@RestController
public class ProviderController {
    private final static Logger logger = LoggerFactory.getLogger(ProviderController.class);
    @Autowired
    ITestService testService;

    @RequestMapping("/commit")
    @GlobalTransaction
    @Transactional
    public Object commit() {
        testService.Commit(2);
        testService.Commit(1);
        return true;
    }

}
