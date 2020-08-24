package com.zz.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VTradeInfo {
//    private Integer uid;
    private Date createTime;
    private Double income;
    private Double pay;
    private Double balance;
    private String consumType;
    private String comment;

}
