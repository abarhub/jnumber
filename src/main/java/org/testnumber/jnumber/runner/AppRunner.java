package org.testnumber.jnumber.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import org.testnumber.jnumber.service.MathService;

import java.util.HashMap;
import java.util.Map;

@Service
public class AppRunner implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppRunner.class);

    @Autowired
    private MathService mathService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOGGER.info("Running AppRunner");

//        test1();
//        test2();
//        test3();
//        test4();
        test5();

        LOGGER.info("fin");

        System.exit(0);
    }

    private void test5() {
        LOGGER.info("test5");

        mathService.test5();
    }

    private void test4() {
        LOGGER.info("test4");

        mathService.test4();
    }

    private void test3() {
        LOGGER.info("test3");

        mathService.test2();
    }

    private void test2() {
        LOGGER.info("test2");

        mathService.test();
    }

    private void test1() {

        LOGGER.info("test1");

        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 4);

        mathService.construit(map);
    }
}
