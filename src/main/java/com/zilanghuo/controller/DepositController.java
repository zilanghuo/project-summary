package com.zilanghuo.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.asymmetric.AsymmetricAlgorithm;
import cn.hutool.crypto.asymmetric.AsymmetricCrypto;
import com.zilanghuo.third.fuyou.RegisterDTO;
import com.zilanghuo.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * @author lwf
 * @date 2018/7/24
 * use:
 */
@Controller
@RequestMapping(value = "/deposit")
@Slf4j
public class DepositController {

    AsymmetricCrypto crypto = new AsymmetricCrypto(AsymmetricAlgorithm.RSA, "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAO2u+9vkuQGYwe2Yw0XFXRdhCkvwY4328H2STJjeW2LGDJqYYQVbpw1CNwJ0hKKcpk1/APENbdv84RP7x3YEkQVNoB0uSj8qnpUsnuyBdxLdToohikvOrNRWgQx/ZvgFE+rWjka9wVaKqLUbUWxpq9GiUAPFa78kYOABD8dIMtg9AgMBAAECgYEAgpNzQiaxjLMDNyiJfrcioUlqgrWZu9BB5nqNIh5mTilHm1bDVlI3wAz0c6DXjQ5KPqDbP5KFHCoc7QGRXsC7egNBX9kNtL7ZCuYw78pE5sNM4+885fgoqaBCbnc+PxgyAqQ+ZIO5u6QKXQpEoe7PpvxCVBAGyn/1klaQVidUivECQQD87PnV05v8ibOv0N6cSpEZ8s/mdFVDSw0sFBdxMseFGY/WjDl1g9ZQCuwjrcT5S/mnYgb6MzRJn+s0rfCFlImLAkEA8JKURMVg6GqIleQq4e03uqEZ6AgErBlh2e+1/T9vgij6n/ueZysamHydZAupk3Wsfn1bkmdA4zqOCf7UZueOVwJAHDwIF8qrmyF0IahbcW8Ri6gDdWJ/MifqrIUBqO1WQJF98SFuOKQjBIRzn/gCCSJmGD1lMgENUTq88wCH3SGbyQJBAJzEuDAUe3EZM0aSOEufvQg2QV6OExVfOP+/ENYmB3FHaQLmAjRyx1MFKb9vRiMctLp80DaYaJVqq/Lhh+JDFOMCQQCXrBhjTx4KfLzfUhOVzm5D8w5sAn9Sg1jDeMwe8tyiyUBbbkw+k9qK0YLOfnwKuC3MNI5URjaKyLzilPDZZkrs", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDcp9NU29EfqPReJLGBS0WZwCKxORrc4IQpKbup1cF4KzQnpMCwcJXF9KW1vJ/ZzOMwAlGfhq2V96MGPOO6T/Zkesasjdmy19wnOdzDxGXu2pEMbFMDOonYxf1m5/VNs2+TZ18eyW585XefXoNlYCzg6RJmXK0fZ1UPAU9ZxgocEQIDAQAB");

    @RequestMapping(value = "/register")
    public String pay(HttpServletRequest request, ModelMap model) {
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setMchnt_txn_ssn(DateUtil.format(new Date(), DatePattern.PURE_DATETIME_MS_PATTERN));
        registerDTO.setUsr_attr("1");
        registerDTO.setMobile_no("18525863602");
        registerDTO.setCust_nm("秦海贤");
        registerDTO.setCertif_id("360822198609284091");
        registerDTO.setEmail("lll@qq.com");
        registerDTO.setSignature(SecurityUtils.signByBean(registerDTO));
        getModelMap(registerDTO, model);
        return "/deposit/register";
    }

    @RequestMapping(value = "registerPage")
    public String registerPage() {
        log.info("enter registerPage");
        return "deposit/registerBack";
    }

    /**
     * 对象转model
     *
     * @param bean
     * @param modelMap
     * @return
     */
    public ModelMap getModelMap(Object bean, ModelMap modelMap) {
        Map<String, Object> beanToMap = BeanUtil.beanToMap(bean);
        Iterator<Map.Entry<String, Object>> iterator = beanToMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> next = iterator.next();
            modelMap.put(next.getKey(), next.getValue());
        }
        return modelMap;
    }
}
