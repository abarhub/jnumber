package org.testnumber.jnumber.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.testnumber.jnumber.math.Addition;
import org.testnumber.jnumber.math.Multiplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class MathService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MathService.class);

    public void construit(Map<Integer, Integer> map) {

        LOGGER.info("construit");

        List<Addition> liste1 = new ArrayList<Addition>();

        List<Integer> liste2 = new ArrayList<>(map.keySet());
        Collections.sort(liste2);
        for (int i = 0; i < liste2.size(); i++) {
            int x = liste2.get(i);
            int y = map.get(x);
            LOGGER.info("x=" + x + ",y=" + y);

            List<Multiplication> liste3 = new ArrayList<Multiplication>();

            for( int j=0;i<liste2.size();j++){
                int k = liste2.get(j);
                if(x!=k){

                }
            }
        }

    }
}
