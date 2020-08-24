package com.zz.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zz.common.JsonResult;
import com.zz.pojo.User;
import com.zz.pojo.VTradeInfo;
import com.zz.service.TradeService;
import com.zz.utils.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/trade")
public class TradeController {
    @Autowired
    private TradeService tradeService;
    @ResponseBody
    @RequestMapping("/findAll")
    public Map<String,Object> findAll(User user, Integer page, Integer limit, HttpServletRequest request, Date beginTime, Date endTime){
        user=(User) request.getSession().getAttribute(StrUtils.LOGIN_USER);
//        user = (User) request.getServletContext().getAttribute(StrUtils.LOGIN_USER);
        System.out.println(user+"这是trade下面的findAll方法的user值");
        PageHelper.startPage(page,limit);
        System.out.println(user.getUid()+"这是trade下面uid的值");
        List<VTradeInfo> vTradeInfoList = tradeService.findAll(user.getUid(),beginTime,endTime);
        PageInfo<VTradeInfo> pageInfo = new PageInfo<>(vTradeInfoList);
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }
    @ResponseBody
    @RequestMapping("/transfer")
    public JsonResult transfer(User user,String otherCode,Double money,HttpServletRequest request){
         user = (User)request.getSession().getAttribute(StrUtils.LOGIN_USER);
        tradeService.transfer(user.getBankCode(),otherCode,money);
        return new JsonResult(1,"转账成功");
    }

}
