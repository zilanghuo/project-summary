package com.zilanghuo.third.fuyou.dto.resp;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * @author lwf
 * @date 2018/7/26
 * use:
 * <ap>
 *  <plain>
 *  <resp_code>返回码</resp_code>
 *  <mchnt_cd>商户代码</mchnt_cd>
 *  <mchnt_txn_ssn>商户流水号</mchnt_txn_ssn>
 *  <results>
 *      <result>
 *          <user_id>用户名</user_id>
 *          <ct_balance>账面总余额</ct_balance>
 *          <ca_balance>可用余额</ca_balance>
 *          <cf_balance>冻结余额</cf_balance>
 *          <cu_balance>未转结余额</cu_balance>
 *      </result>
 *  </results>
 *  </plain>
 *  <signature>签名数据</signature>
 * </ap>
 */

@lombok.Setter
public class QueryBalanceRespDTO {

    private String resp_code;

    private String mchnt_cd;

    private String mchnt_txn_ssn;

    private ResultDto resultDto;

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

    @XmlElement(name = "results")
    public ResultDto getResultDto() {
        return resultDto;
    }


}

@lombok.Setter
class ResultDto {

    private List<Result> resultList;

    @XmlElement(name = "result")
    public List<Result> getResultList() {
        return resultList;
    }
}

@lombok.Setter
class Result {

    private String user_id;

    private String ct_balance;

    private String ca_balance;

    private String cf_balance;

    private String cu_balance;

    @XmlElement(name = "user_id")
    public String getUser_id() {
        return user_id;
    }

    @XmlElement(name = "ct_balance")
    public String getCt_balance() {
        return ct_balance;
    }

    @XmlElement(name = "ca_balance")
    public String getCa_balance() {
        return ca_balance;
    }

    @XmlElement(name = "cf_balance")
    public String getCf_balance() {
        return cf_balance;
    }

    @XmlElement(name = "cu_balance")
    public String getCu_balance() {
        return cu_balance;
    }
}
