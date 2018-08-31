package com.zilanghuo.utils;

import cn.hutool.core.bean.BeanUtil;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author laiwufa
 * @date 2018/8/2
 * use:
 * <dependency>
 * <groupId>cn.hutool</groupId>
 * <artifactId>hutool-core</artifactId>
 * <version>4.1.3</version>
 * </dependency>
 */
public class MD5Util {

    private final static String[] hexDigits = {
            "0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "a", "b", "c", "d", "e", "f"};

    public static void main(String[] args) {
        TestBean testBean = new TestBean();
        testBean.setAge(432);
        testBean.setName("eewr");

        //可自己写
        Map<String, Object> map = BeanUtil.beanToMap(testBean);
        System.out.println(MD5Encode(map, "11111"));

    }


    public static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(Map<String, Object> map, String key) {
        String inputStr = getInputStr(map);
        String origin = inputStr + "|" + key;
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString.getBytes("utf-8")));
        } catch (Exception ex) {
        }
        return resultString;
    }

    /**
     * map 获取字符串，排除signature 字段
     *
     * @param map
     * @return
     */
    public static String getInputStr(Map<String, Object> map) {
        Map<String, Object> treeMap = new TreeMap<>();
        treeMap.putAll(map);
        Iterator<Map.Entry<String, Object>> iterator = treeMap.entrySet().iterator();
        StringBuffer sb = new StringBuffer();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> next = iterator.next();
            if (!StringUtils.isEmpty(next.getKey()) && !StringUtils.isEmpty(next.getValue())
                    && !"signature".equals(next.getKey())) {
                sb.append(next.getValue()).append("|");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    @Data
    static class TestBean {

        private String name;

        private Integer age;
    }
}

