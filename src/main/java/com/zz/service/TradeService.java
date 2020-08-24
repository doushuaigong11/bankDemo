package com.zz.service;

import com.zz.pojo.VTradeInfo;

import java.util.Date;
import java.util.List;

public interface TradeService {
    List<VTradeInfo> findAll(Integer uid,Date beginTime,Date endTime);

    void transfer(String bankCode, String otherCode, Double money);
}
