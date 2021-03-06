package com.zilanghuo.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.zilanghuo.third.fuyou.dto.req.RechargeReqDTO;
import com.zilanghuo.third.fuyou.dto.req.RegisterReqDTO;
import com.zilanghuo.third.fuyou.dto.req.WithdrawReqDTO;
import com.zilanghuo.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * @author lwf
 * @date 2018/7/24
 * use:
 */
@Controller
@RequestMapping(value = "/deposit")
@Slf4j
public class DepositController {

    @RequestMapping(value = "/home")
    public String home(ModelMap model) {
        log.info("-----------home");
        return "/deposit/home";
    }


    @RequestMapping(value = "/register")
    public String register(ModelMap model) {
        RegisterReqDTO registerDTO = new RegisterReqDTO();
        registerDTO.setMchnt_txn_ssn(DateUtil.format(new Date(), DatePattern.PURE_DATETIME_MS_PATTERN));
        registerDTO.setUsr_attr("1");
        registerDTO.setMobile_no("18525863602");
        registerDTO.setCust_nm("秦海贤");
        registerDTO.setCertif_id("360822198609284091");
        registerDTO.setEmail("lll@qq.com");
        registerDTO.setSignature(SecurityUtils.signByBean(registerDTO));
        getModelMap(registerDTO, model);
        return "/deposit/register";
    }

    @RequestMapping(value = "/recharge")
    public String recharge(HttpServletRequest request, ModelMap model) {
        log.info("recharge");
        RechargeReqDTO rechargeDTO = new RechargeReqDTO();
        rechargeDTO.setAmt(Integer.parseInt(request.getParameter("amt")));
        rechargeDTO.setLogin_id(request.getParameter("login_id"));
        rechargeDTO.setSignature(SecurityUtils.signByBean(rechargeDTO));
        getModelMap(rechargeDTO, model);
        return "/deposit/recharge";
    }

    @RequestMapping(value = "/rechargeInternet")
    public String rechargeInternet(HttpServletRequest request, ModelMap model) {
        log.info("rechargeInternet");
        RechargeReqDTO rechargeDTO = new RechargeReqDTO();
        rechargeDTO.setCode(null);
        rechargeDTO.setClient_tp(null);
        rechargeDTO.setVer(null);
        rechargeDTO.setAmt(Integer.parseInt(request.getParameter("amt")));
        rechargeDTO.setLogin_id(request.getParameter("login_id"));
        rechargeDTO.setSignature(SecurityUtils.signByBean(rechargeDTO));
        getModelMap(rechargeDTO, model);
        return "/deposit/rechargeInternet";
    }

    @RequestMapping(value = "/rechargeInternetBank")
    public String rechargeInternetBank(HttpServletRequest request, ModelMap model) {
        log.info("rechargeInternetBank");
        RechargeReqDTO rechargeDTO = new RechargeReqDTO();
        rechargeDTO.setCode(null);
        rechargeDTO.setClient_tp(null);
        rechargeDTO.setVer(null);
        rechargeDTO.setIss_ins_cd("0803080000");
        rechargeDTO.setAmt(Integer.parseInt(request.getParameter("amt")));
        rechargeDTO.setLogin_id(request.getParameter("login_id"));
        rechargeDTO.setOrder_pay_type("B2C");
        rechargeDTO.setSignature(SecurityUtils.signByBean(rechargeDTO));
        getModelMap(rechargeDTO, model);
        return "/deposit/rechargeInternetBank";
    }

    @RequestMapping(value = "/withdraw")
    public String withdraw(HttpServletRequest request, ModelMap model) {
        log.info("withdraw");
        WithdrawReqDTO withdrawDTO = new WithdrawReqDTO();
        withdrawDTO.setAmt(Integer.parseInt(request.getParameter("amt")));
        withdrawDTO.setLogin_id(request.getParameter("login_id"));
        withdrawDTO.setSignature(SecurityUtils.signByBean(withdrawDTO));
        getModelMap(withdrawDTO, model);
        return "/deposit/withdraw";
    }

    @RequestMapping(value = "registerPage")
    public String registerPage() {
        log.info("enter registerPage");
        return "deposit/registerBack";
    }

    /**
     * 对象转model
     *
     * @param bean
     * @param modelMap
     * @return
     */
    public ModelMap getModelMap(Object bean, ModelMap modelMap) {
        Map<String, Object> beanToMap = BeanUtil.beanToMap(bean);
        Iterator<Map.Entry<String, Object>> iterator = beanToMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> next = iterator.next();
            modelMap.put(next.getKey(), next.getValue());
        }
        return modelMap;
    }
}
