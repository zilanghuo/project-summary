package com.zilanghuo.third.fuyou;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lwf
 * @date 2018/7/26
 * use:
 */
@Getter
@AllArgsConstructor
public enum DealType {

    RECHARGE("充值", "2"),
    WITHDRAW("充值", "3");

    private String value;

    private String msg;

}
