package com.musicfileutil.core;

/**
 * 文件名操作接口
 *
 * @author Yi Chen
 * @date 2023-09-22 23:05
 * @since 1.0
 */
public interface MusicName {
    /**
     * 复制文件名到剪贴板
     *
     * @param name 文件名称
     * @throws IllegalStateException 若剪贴板不可用
     */
    void copy2clipboard(String name);

    /**
     * 根据命名格式重命名文件
     *
     * @param oldName   旧文件名
     * @param oldFormat 旧命名格式
     * @param newFormat 新命名格式
     * @return 新文件名
     * @throws UnknownNamingFormatException 若名称解析异常
     * @throws IllegalNameException         若名称格式异常
     */
    String rename(String oldName, NamingFormat oldFormat, NamingFormat newFormat);
}
