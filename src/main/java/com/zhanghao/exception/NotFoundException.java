package com.zhanghao.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NotFoundException extends RuntimeException{

    private CommonEnum commonEnum;

    public NotFoundException(String message) {
        super(message);
    }

}
