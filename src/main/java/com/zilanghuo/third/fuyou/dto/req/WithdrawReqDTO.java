package com.zilanghuo.third.fuyou.dto.req;

import lombok.Data;

/**
 * @author lwf
 * @date 2018/7/26
 * use:
 */
@Data
public class WithdrawReqDTO extends BaseReqDTO {

    public WithdrawReqDTO(){
        super();
        this.code = "withdraw";
        this.page_notify_url = "http://180.169.135.188:2688/deposit/registerPage";
        this.back_notify_url = "http://180.169.135.188:2688/core/registerCallback";
    }

    private String login_id;

    private Integer amt;

    private String page_notify_url;

    private String back_notify_url;

}
