package com.zz.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trade {
    private Integer id;
    private Integer uid;
    private Integer otherUid;
    private Double money;
    private String consumType;
    private Date createTime;
    private String comment;
    private Double balance;
}
