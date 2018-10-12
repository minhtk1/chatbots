package com.robot.generic;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public interface IGeneric  {
//    public T getRandomList(List<T> list);
    static <T> T getRandomList(List<T> list) {
        int index = ThreadLocalRandom.current().nextInt(list.size());
        return (T) list.get(index);
    }

    static <E> boolean indexOfArrays(E[] arrays,E value) {
//        int index = ThreadLocalRandom.current().nextInt(list.size());
        for(E element: arrays){
            if(value.equals(element)){
                return true;
            }
        }
        return false;
    }

    static <E> boolean listEqualArrays(List<E[]> list,E[] arrays) {
//        int index = ThreadLocalRandom.current().nextInt(list.size());
        for(E[] element: list){
            if(Arrays.equals(element,arrays)){
                return true;
            }
        }
        return false;
    }

    static <T> String findStringOfList(List<T> list,String valueEqual) throws IllegalArgumentException, IllegalAccessException {
//        int index = ThreadLocalRandom.current().nextInt(list.size());
        for(T element: list){
            Class<? extends Object> c1 = element.getClass();
            Field[] fields = c1.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                Object value = fields[i].get(element);
                if(valueEqual.equals(value)){
                    return value.toString();
                }
            }
        }
        return null;
    }
}
