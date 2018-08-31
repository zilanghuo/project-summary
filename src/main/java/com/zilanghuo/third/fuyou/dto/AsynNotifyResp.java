package com.zilanghuo.third.fuyou.dto;

import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author laiwufa
 * @date 2018/8/23
 * use:
 */
@XmlRootElement(name = "ap")
@Setter
public class AsynNotifyResp {

    /**
     * plain域
     */
    private Plain plain;

    /**
     * 验签
     */
    private String signature;

    @XmlElement(name = "plain")
    public Plain getPlain() {
        return plain;
    }

    @XmlElement(name = "signature")
    public String getSignature() {
        return signature;
    }


    @Setter
    @XmlRootElement(name = "plain")
    public static class Plain{

        private String resp_code;

        private String mchnt_cd;

        private String mchnt_txn_ssn;

        @XmlElement(name = "resp_code")
        public String getResp_code() {
            return resp_code;
        }

        @XmlElement(name = "mchnt_cd")
        public String getMchnt_cd() {
            return mchnt_cd;
        }

        @XmlElement(name = "mchnt_txn_ssn")
        public String getMchnt_txn_ssn() {
            return mchnt_txn_ssn;
        }
    }
}
