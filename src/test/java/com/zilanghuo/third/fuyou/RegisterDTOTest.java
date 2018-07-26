package com.zilanghuo.third.fuyou;

import cn.hutool.core.bean.BeanUtil;
import com.zilanghuo.third.fuyou.dto.req.RegisterReqDTO;

import java.util.Map;

/**
 * @author lwf
 * @date 2018/7/25
 * use:
 */
public class RegisterDTOTest {

    @org.junit.Test
    public void testBean(){
        RegisterReqDTO registerDTO = new RegisterReqDTO();
        registerDTO.setBack_notify_url("www.baidu.com");
        registerDTO.setCode("ccccc");
        Map<String, Object> objectMap = BeanUtil.beanToMap(registerDTO);
        System.out.println(objectMap.toString());
        RegisterReqDTO mapToBean = BeanUtil.mapToBean(objectMap, RegisterReqDTO.class, true);
        System.out.println(mapToBean.getCode());

    }

}