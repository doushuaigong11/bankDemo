package com.zz.service.impl;

import com.zz.dao.TradeDao;
import com.zz.dao.UserDao;
import com.zz.pojo.Trade;
import com.zz.pojo.User;
import com.zz.pojo.VTradeInfo;
import com.zz.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class TradeServiceImpl implements TradeService {
    @Autowired
    private TradeDao tradeDao;
    @Autowired
    private UserDao userDao;

    @Override
    public List<VTradeInfo> findAll(Integer uid,Date beginTime,Date endTime) {
        return tradeDao.findAll(uid,beginTime,endTime);
    }

    @Override
    public void transfer(String code, String otherCode, Double money) {
        //要转账的用户
        User loginUser = userDao.findByCode(code);
        //收款账户
        User otherUser = userDao.findByCode(otherCode);

        if(loginUser ==null){
            throw new RuntimeException("未登录，不能操作");
        } else if(loginUser.getBalance()<money){
            throw new RuntimeException("余额不足，不能转账");
        } else if(loginUser.equals(otherUser)){
            throw new RuntimeException("自己不能给自己转账");
        }
        Trade outTrade = new Trade();
        outTrade.setUid(loginUser.getUid());
        outTrade.setBalance(loginUser.getBalance()-money);
        outTrade.setMoney(0-money);
        tradeDao.add(outTrade);

        Trade inTrade = new Trade();
        inTrade.setUid(otherUser.getUid());
        inTrade.setBalance(otherUser.getBalance()+money);
        inTrade.setMoney(money);
        tradeDao.add(inTrade);

        userDao.update(otherUser);
        userDao.update(loginUser);


    }
}
