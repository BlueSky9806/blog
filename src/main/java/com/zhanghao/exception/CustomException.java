package com.zhanghao.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 自定义全局异常类，用于处理发生的业务异常
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CustomException extends RuntimeException{

    private CommonEnum commonEnum;

    @Override
    public String toString() {
        return "CustomException{" +
                "commonEnum=" + commonEnum +
                '}';
    }
}
