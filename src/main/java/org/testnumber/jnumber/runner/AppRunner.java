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
        test1();
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
