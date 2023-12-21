package com.musicfileutil.core;

/**
 * 未知命名格式异常
 *
 * @author Yi Chen
 * @date 2023-06-17 16:37
 * @since 1.0
 */
public class UnknownNamingFormatException extends RuntimeException {
    private static final long serialVersionUID = -8871320019382834647L;

    public UnknownNamingFormatException() {
        super("命名格式类型未知！");
    }
}
