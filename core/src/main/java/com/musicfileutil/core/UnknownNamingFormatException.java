package com.musicfileutil.core;

/**
 * @author Yi Chen
 * @date 2023-06-17 16:37
 * @since 1.0
 */
public class UnknownNamingFormatException extends Exception{
    public UnknownNamingFormatException() {
        super("命名格式类型未知！");
    }
}
