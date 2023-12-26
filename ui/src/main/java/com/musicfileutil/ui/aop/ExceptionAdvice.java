package com.musicfileutil.ui.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.musicfileutil.core.IllegalNameException;
import com.musicfileutil.ui.UIWindow;

/**
 * 全局异常处理切面
 *
 * @author Yi Chen
 * @date 2023-10-19 9:17
 * @since 1.0
 */
@Aspect
@Component
public class ExceptionAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAdvice.class);

    private UIWindow window;

    @Autowired
    public void setWindow(UIWindow window) {
        this.window = window;
    }

    @Pointcut("execution(* com.musicfileutil.ui..*.*(..)) && !@annotation(org.aspectj.lang.annotation.Aspect)")
    public void uiMethod() {
    }

    @AfterThrowing(pointcut = "uiMethod()", throwing = "e")
    public void show(JoinPoint joinPoint, Exception e) {
        joinPoint.getSignature()
                 .getDeclaringTypeName();
        joinPoint.getStaticPart()
                 .getKind();
        if (e instanceof IllegalNameException) {
            LOGGER.warn(e.getMessage());
            window.getMessageConsole()
                  .append(e.getMessage() + "\n");
        } else if (e instanceof RuntimeException) {
            LOGGER.error(e.getMessage(), e);
            window.getMessageConsole()
                  .append(e.getMessage() + "\n");
        } else {
            LOGGER.error("可检查异常\n" + e.getMessage(), e);
            window.getMessageConsole()
                  .append(e.getMessage() + "\n");
        }
    }
}
