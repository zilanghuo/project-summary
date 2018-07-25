package com.zilanghuo.third.fuyou;

import cn.hutool.core.bean.BeanUtil;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author lwf
 * @date 2018/7/25
 * use:
 */
public class RegisterDTOTest {

    @org.junit.Test
    public void testBean(){
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setBack_notify_url("www.baidu.com");
        registerDTO.setCode("ccccc");
        Map<String, Object> objectMap = BeanUtil.beanToMap(registerDTO);
        System.out.println(objectMap.toString());
        RegisterDTO mapToBean = BeanUtil.mapToBean(objectMap, RegisterDTO.class, true);
        System.out.println(mapToBean.getCode());

    }

}