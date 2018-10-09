package com.robot.generic;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.*;

public class GeneralGeneric implements Serializable {

    private Map<String, Object> getFieldNamesAndValues(final Object obj, boolean publicOnly)
            throws IllegalArgumentException,IllegalAccessException{
        Class<? extends Object> c1 = obj.getClass();
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = c1.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            String name = fields[i].getName();
            if (publicOnly) {
                if(Modifier.isPublic(fields[i].getModifiers())) {
                    Object value = fields[i].get(obj);
                    map.put(name, value);
                }
            }
            else {
                fields[i].setAccessible(true);
                Object value = fields[i].get(obj);
                map.put(name, value);
            }
        }
        if(map.size() > 0){
            map = sortByValue(map);
        }
        return map;
    }

    private static Map<String, Object> sortByValue(Map<String, Object> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<String, Object>> list =
                new LinkedList<Map.Entry<String, Object>>((Collection<? extends Map.Entry<String, Object>>) unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<String, Object>>() {
            public int compare(Map.Entry<String, Object> o1,
                               Map.Entry<String, Object> o2) {
                return (o1.getKey()).compareTo(o2.getKey());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<String, Object> sortedMap = new LinkedHashMap<String, Object>();
        for (Map.Entry<String, Object> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        /*
        //classic iterator example
        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext(); ) {
            Map.Entry<String, Integer> entry = it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }*/


        return sortedMap;
    }
}
