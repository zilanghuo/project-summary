package com.zilanghuo.third.fuyou;

import lombok.Data;

/**
 * @author lwf
 * @date 2018/7/25
 * use: 富友注册
 */
@Data
public class RegisterDTO {

    public RegisterDTO() {
        this.ver = "1.00";
        this.code = "regUserByFive";
        this.mchnt_cd = "0002900F0352200";
        this.client_tp = "0";
        this.page_notify_url = "http://180.169.135.188:2688/deposit/registerPage";
        this.back_notify_url = "http://180.169.135.188:2688/core/registerCallback";

    }

    private String ver;

    private String code;

    private String mchnt_cd;

    private String mchnt_txn_ssn;

    private String client_tp;

    private String mobile_no;

    private String cust_nm;

    private String certif_tp;

    private String certif_id;

    private String usr_attr;

    private String email;

    private String page_notify_url;

    private String back_notify_url;

    private String signature;

}
