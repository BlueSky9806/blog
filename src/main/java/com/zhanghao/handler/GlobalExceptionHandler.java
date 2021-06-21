package com.zhanghao.handler;

import com.zhanghao.exception.CommonEnum;
import com.zhanghao.exception.CustomException;
import com.zhanghao.vo.ExceptionResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理类
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler implements HandlerInterceptor {

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ExceptionResult> handlerException(CustomException e) {
        CommonEnum en = e.getCommonEnum();
        return ResponseEntity.status(en.getResultCode()).body(new ExceptionResult(en));
    }

    @ExceptionHandler(value = ArithmeticException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView arithmeticException(HttpServletRequest request, ArithmeticException e) {
        log.error("Request URI : {}, Exception : {}", request.getRequestURI(), e.getMessage());
        ModelAndView view = new ModelAndView();
        view.addObject("url", request.getRequestURI());
        view.addObject("exception", e);
        view.setViewName("error/error");
        return view;
    }

}
