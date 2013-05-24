package com.drleinbach.model;

import com.drleinbach.model.impl.PlayerImpl;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA.
 * User: Daniel
 * Date: 4/26/13
 * Time: 8:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerTest {

    @Test
    public void myTest(){
        Field[] fields = PlayerImpl.class.getDeclaredFields();

        for(Field field : fields){

            if((field.getModifiers() & 8) == 0)
            System.out.println(field.getName() + " " + Long.toBinaryString(field.getModifiers()));
        }
    }
}
