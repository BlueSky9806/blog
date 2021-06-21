package com.zhanghao.vo;

import com.zhanghao.exception.CommonEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExceptionResult {

    private Integer code;

    private String message;

    public ExceptionResult(CommonEnum commonEnum) {
        this.code = commonEnum.getResultCode();
        this.message = commonEnum.getResultMsg();
    }
}
