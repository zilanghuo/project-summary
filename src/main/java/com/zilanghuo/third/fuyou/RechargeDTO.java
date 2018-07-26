package com.zilanghuo.third.fuyou;

import lombok.Data;

/**
 * @author lwf
 * @date 2018/7/26
 * use:
 */
@Data
public class RechargeDTO extends BaseDTO {

    public RechargeDTO(){
        super();
        this.code = "quickRecharge";
        this.page_notify_url = "http://180.169.135.188:2688/deposit/registerPage";
        this.back_notify_url = "http://180.169.135.188:2688/core/registerCallback";
    }

    private String login_id;

    private Integer amt;

    private String iss_ins_cd;

    private String order_pay_type;

    private String page_notify_url;

    private String back_notify_url;

}
