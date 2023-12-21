package com.musicfileutil.core;

/**
 * 非法名称异常
 *
 * @author Yi Chen
 * @date 2023-10-10 22:28
 * @since 1.0
 */
public class IllegalNameException extends RuntimeException {
    private static final long serialVersionUID = -6476407980410160480L;

    public IllegalNameException() {
        super("音乐名称非法！");
    }
}
