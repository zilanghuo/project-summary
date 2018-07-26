package com.zilanghuo.third.fuyou.dto.req;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @author lwf
 * @date 2018/7/26
 * use:
 */

@lombok.Data
public class QueryBalanceReqDTO {

    private String mchnt_cd;

    private String mchnt_txn_ssn;

    private String mchnt_txn_dt;

    private String cust_no;

    private String signature;

    public QueryBalanceReqDTO() {
        this.mchnt_cd = "0002900F0352200";
        this.mchnt_txn_ssn = DateUtil.format(new Date(), DatePattern.PURE_DATETIME_MS_PATTERN);
        this.mchnt_txn_dt = DateUtil.format(new Date(), DatePattern.PURE_DATE_PATTERN);
    }


}
