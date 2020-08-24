package com.zz.dao;

import com.zz.pojo.Trade;
import com.zz.pojo.VTradeInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TradeDao {

    List<VTradeInfo> findAll(@Param("uid") Integer uid, @Param("beginTime") Date beginTime,@Param("endTime") Date endTime);
    void add(Trade trade);
}
