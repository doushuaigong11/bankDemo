package com.zz.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult {
    private int code; //1正常  0   异常
    private Object info;//返回给前端的数据

}
