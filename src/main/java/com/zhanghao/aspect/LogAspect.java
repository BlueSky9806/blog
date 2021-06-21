package com.zhanghao.aspect;

import com.zhanghao.service.AsyncTask;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/*
 * https://www.cnblogs.com/wangshen31/p/9379197.html
 * https://blog.csdn.net/Fine_Cui/article/details/103067087?utm_medium=distribute.pc_relevant.none-task-blog-baidujs_title-0&spm=1001.2101.3001.4242
 *  @Before
 *  @After
 *      最终通知, 当某连接点退出时执行的通知（不论是正常退出还是异常退出）
 *  @Around
 *      环绕通知使用一个代理 ProceedingJoinPoint 类型的对象来管理目标对象, 所以此通知的第一个参数必须是ProceedingJoinPoint
 *      类型。在通知体内调用ProceedingJoinPoint的proceed()方法 会导致后台的连接点方法执行。proceed() 方法也可能会被调用并且
 *      传入一个Object[]对象, 该数组中的值将被作为方法执行时的入参。
 *
 *  @AfterReturning
 *      后置返回通知, 在某连接点之后执行的通知, 通常在一个匹配的方法返回的时候执行（可以在后置通知中绑定返回值）
 *      - 如果参数中的第一个参数为JointPoint, 则第二个参数为 返回值 的信息
 *      - AfterReturning限定了只有目标方法返回值和通知方法 相同类型 的参数时才能执行后置返回通知, 否则不执行
 *      - 对于returning对应的通知方法参数为 Object 类型将匹配任何目标返回值
 *  @AfterThrowing
 *      后置异常通知, 在方法抛出异常退出时执行的通知
 */

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Autowired
    private AsyncTask asyncTask;

    @Pointcut("execution(* *..controller..*(..))")
    public void pointcut(){}

    /**
     * @param joinPoint: 织入增强处理的连接点。包含了几个很有用的参数
     *                 Object[] getArgs: 返回目标方法的参数
     *                 Signature getSignature: 返回目标方法的签名
     *                 Object getTarget: 返回被织入增强处理的目标对象
     *                 Object getThis: 返回AOP框架为目标对象生成的代理对象
     */
    @Before("pointcut()")
    public void logBeforeController(JoinPoint joinPoint) {
        // 这个RequestContextHolder是SpringMVC提供来获得请求的一个抽象类
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = ((ServletRequestAttributes) attributes).getRequest();

        log.info("请求路径:{}; 请求方式:{}; 请求参数:{}",
                request.getRequestURL().toString(),
                request.getMethod(),
                Arrays.toString(joinPoint.getArgs()));
    }


    /*@AfterReturning(value = "pointcut()", returning = "val")
    public void doAfterReturning(JoinPoint joinPoint, String val) {
        log.info("返回值:{}", val);
    }*/

    /**
     * 异常通知
     * @param e: 异常信息
     */
    @AfterThrowing(value = "pointcut()", throwing = "e")
    public void doAfterThrowing(Exception e) throws MessagingException {
        log.error("错误信息:{}", e.getMessage());
        // 发送邮件
        asyncTask.toSendMail(e);
    }
}
