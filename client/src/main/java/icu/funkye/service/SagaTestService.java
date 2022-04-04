package icu.funkye.service;

import java.util.concurrent.ThreadLocalRandom;
import icu.funkye.easy.tx.config.annotation.SagaTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SagaTestService {

    private final static Logger logger = LoggerFactory.getLogger(SagaTestService.class);

    @SagaTransaction(confirm = "commitLocal", clazz = SagaTestService.class, cancel = "rollbackLocal")
    public Object commitLocal(int id) {
        if (ThreadLocalRandom.current().nextInt(10)<2) {
            throw new RuntimeException(" commitLocal 模拟失败");
        }
        logger.info("commitLocal: {}", id);
        return true;
    }

    @SagaTransaction(confirm = "commitLocal2", clazz = SagaTestService.class, cancel = "rollbackLocal2")
    public Object commitLocal2(int id) {
        if (ThreadLocalRandom.current().nextInt(10)<2) {
            throw new RuntimeException(" commitLocal2 模拟失败");
        }
        logger.info("commitLocal2: {}", id);
        return true;
    }

    public Object rollbackLocal(int id) {
        if (!ThreadLocalRandom.current().nextBoolean()) {
            throw new RuntimeException();
        }
        logger.info("rollbackLocal: {}", id);
        return true;
    }

    public Object rollbackLocal2(int id) {
        if (!ThreadLocalRandom.current().nextBoolean()) {
            throw new RuntimeException();
        }
        logger.info("rollbackLocal2: {}", id);
        return true;
    }

}
