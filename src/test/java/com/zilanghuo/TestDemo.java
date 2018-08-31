package com.zilanghuo;

import com.zilanghuo.third.fuyou.dto.AsynNotifyResp;
import com.zilanghuo.utils.XmlBeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lwf
 * @date 2018/7/13
 * use:
 */
public class TestDemo {

    @org.junit.Test
    public void testOne() throws Exception {

        List<String> listOne = new ArrayList();
        listOne.add("1");
        listOne.add("2");

        List<String> listTwo = new ArrayList();
        listTwo.add("2");

        listOne.retainAll(listTwo);
        System.out.println(listOne);


    }

    public static void main(String[] args) {
        System.out.println("-----");
    }

}
