package com.zilanghuo.third.fuyou.dto.resp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author lwf
 * @date 2018/7/26
 * use:
 */
@lombok.Setter
@XmlRootElement(name = "ap")
public class CommomResp {

    private QueryBalanceRespDTO plain;

    private String signature;

    @XmlElement(name = "plain")
    public QueryBalanceRespDTO getPlain() {
        return plain;
    }

    @XmlElement(name = "signature")
    public String getSignature() {
        return signature;
    }
}
