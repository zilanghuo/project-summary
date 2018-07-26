package com.zilanghuo.third.fuyou.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.zilanghuo.third.fuyou.dto.req.QueryBalanceReqDTO;
import com.zilanghuo.third.fuyou.dto.resp.CommomResp;
import com.zilanghuo.utils.SecurityUtils;
import com.zilanghuo.utils.XmlBeanUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lwf
 * @date 2018/7/26
 * use:
 */
@Slf4j
public class AccountService {

    public static void main(String[] args) throws Exception {
        QueryBalanceReqDTO reqDTO = new QueryBalanceReqDTO();
        reqDTO.setCust_no("18525863602");
        reqDTO.setSignature(SecurityUtils.signByBean(reqDTO));
        String response = HttpUtil.post("http://180.168.100.156:8090/BalanceAction.action", BeanUtil.beanToMap(reqDTO));
        log.info("response:"+response);
        CommomResp respData= XmlBeanUtils.convertXml2Bean(response, CommomResp.class);
        log.info("result:{}", JSONUtil.toJsonStr(respData));


    }

}
