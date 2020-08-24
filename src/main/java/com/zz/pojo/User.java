package com.zz.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer uid;
    private String bankCode;
    private String password;
    private Double balance;
    private Integer status;
    private String imgPath;
}
