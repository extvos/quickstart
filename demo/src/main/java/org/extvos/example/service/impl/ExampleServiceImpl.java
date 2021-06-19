package org.extvos.example.service.impl;

import org.extvos.example.service.ExampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author shenmc
 */
@Service
public class ExampleServiceImpl implements ExampleService {

    private static final Logger log = LoggerFactory.getLogger(ExampleServiceImpl.class);

    @Override
    @Async
    public String asyncTest(int duration) {

        try {
            for (int i = 1; i <= duration; i++) {
                log.info(Thread.currentThread().getName() + "----------异步：>" + i);
                Thread.sleep(200);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return Thread.currentThread().getName() + "执行异常:" + ex.getMessage();
        }

        return Thread.currentThread().getName() + "执行完毕";
    }
}
