package org.testnumber.jnumber.math;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Fonction {

    private final Map<Integer,Integer> map=new TreeMap<>();

    public Map<Integer, Integer> getMap() {
        return map;
    }

    public List<Integer> getKeys(){
        return new ArrayList<>(map.keySet());
    }

    public Integer get(Integer key){
        return map.get(key);
    }

    public void set(Integer key,Integer value){
        map.put(key,value);
    }

    @Override
    public String toString() {
        return "Fonction{" +
                "map=" + map +
                '}';
    }
}
