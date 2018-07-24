package com.zilanghuo.controller;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.AsymmetricAlgorithm;
import cn.hutool.crypto.asymmetric.AsymmetricCrypto;
import cn.hutool.crypto.asymmetric.KeyType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author lwf
 * @date 2018/7/24
 * use:
 */
@Controller
@RequestMapping(value = "/deposit")
@Slf4j
public class DepositController {

    AsymmetricCrypto crypto = new AsymmetricCrypto(AsymmetricAlgorithm.RSA, "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAO2u+9vkuQGYwe2Yw0XFXRdhCkvwY4328H2STJjeW2LGDJqYYQVbpw1CNwJ0hKKcpk1/APENbdv84RP7x3YEkQVNoB0uSj8qnpUsnuyBdxLdToohikvOrNRWgQx/ZvgFE+rWjka9wVaKqLUbUWxpq9GiUAPFa78kYOABD8dIMtg9AgMBAAECgYEAgpNzQiaxjLMDNyiJfrcioUlqgrWZu9BB5nqNIh5mTilHm1bDVlI3wAz0c6DXjQ5KPqDbP5KFHCoc7QGRXsC7egNBX9kNtL7ZCuYw78pE5sNM4+885fgoqaBCbnc+PxgyAqQ+ZIO5u6QKXQpEoe7PpvxCVBAGyn/1klaQVidUivECQQD87PnV05v8ibOv0N6cSpEZ8s/mdFVDSw0sFBdxMseFGY/WjDl1g9ZQCuwjrcT5S/mnYgb6MzRJn+s0rfCFlImLAkEA8JKURMVg6GqIleQq4e03uqEZ6AgErBlh2e+1/T9vgij6n/ueZysamHydZAupk3Wsfn1bkmdA4zqOCf7UZueOVwJAHDwIF8qrmyF0IahbcW8Ri6gDdWJ/MifqrIUBqO1WQJF98SFuOKQjBIRzn/gCCSJmGD1lMgENUTq88wCH3SGbyQJBAJzEuDAUe3EZM0aSOEufvQg2QV6OExVfOP+/ENYmB3FHaQLmAjRyx1MFKb9vRiMctLp80DaYaJVqq/Lhh+JDFOMCQQCXrBhjTx4KfLzfUhOVzm5D8w5sAn9Sg1jDeMwe8tyiyUBbbkw+k9qK0YLOfnwKuC3MNI5URjaKyLzilPDZZkrs","MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDcp9NU29EfqPReJLGBS0WZwCKxORrc4IQpKbup1cF4KzQnpMCwcJXF9KW1vJ/ZzOMwAlGfhq2V96MGPOO6T/Zkesasjdmy19wnOdzDxGXu2pEMbFMDOonYxf1m5/VNs2+TZ18eyW585XefXoNlYCzg6RJmXK0fZ1UPAU9ZxgocEQIDAQAB");

    @RequestMapping(value = "/pay")
    public String pay(HttpServletRequest request, ModelMap model) {
        TreeMap<String, String> treeMap = new TreeMap();
        treeMap.put("ver", "1.00");
        treeMap.put("code", "regUserByFive");
        treeMap.put("mchnt_cd", "0002900F0352200");
        treeMap.put("mchnt_txn_ss", "201807240000002");
        treeMap.put("client_tp", "0");
        treeMap.put("usr_attr", "1");
        treeMap.put("page_notify_url", "https://www.baidu.com");
        treeMap.put("back_notify_url", "https://www.baidu.com");
        log.info("{}",treeMap);

        model.put("ver", "1.00");
        model.put("code", "regUserByFive");
        model.put("mchnt_cd", "0002900F0352200");
        model.put("mchnt_txn_ss", System.currentTimeMillis() + "");
        model.put("client_tp", "1");
        model.put("usr_attr", "1");
        model.put("page_notify_url", "https://www.baidu.com");
        model.put("back_notify_url", "https://www.baidu.com");
        Map<String, String> sign = sign(treeMap);
        System.out.println(sign);

        return "deposit/pay";
    }


    public Map<String,String> sign(TreeMap<String, String> treeMap) {
        Map<String,String> result = new HashMap();
        Iterator<Map.Entry<String, String>> iterator = treeMap.entrySet().iterator();
        StringBuffer sb = new StringBuffer();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            if (!StringUtils.isEmpty(next.getKey()) && !StringUtils.isEmpty(next.getValue())) {
                sb.append(next.getValue()).append("|");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        result.put("key",sb.toString());
        String pre = crypto.encryptBcd(sb.toString(), KeyType.PrivateKey);
        result.put("signature",pre);

        return result;
    }

}
