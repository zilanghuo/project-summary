package com.zilanghuo.third.fuyou.dto.req;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author lwf
 * @date 2018/7/26
 * use:
 */
@Data
public class BaseReqDTO {

    public BaseReqDTO() {
        this.ver = "1.00";
        this.mchnt_cd = "0002900F0352200";
        this.mchnt_txn_ssn = DateUtil.format(new Date(), DatePattern.PURE_DATETIME_MS_PATTERN);
        this.client_tp = "0";
    }

    protected String ver;

    protected String mchnt_cd;

    protected String mchnt_txn_ssn;

    protected String client_tp;

    protected String code;

    protected String signature;
}
