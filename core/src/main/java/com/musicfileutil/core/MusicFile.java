package com.musicfileutil.core;

import java.io.File;
import java.io.IOException;

/**
 * 音乐文件操作接口
 *
 * @author Yi Chen
 * @date 2023-09-22 23:38
 * @since 1.0
 */
public interface MusicFile {
    /**
     * 使用新名称重命名音乐文件
     *
     * @param song      音乐文件
     * @param oldFormat 旧命名格式
     * @param newFormat 新命名格式
     * @return 新文件名
     * @throws IOException 重命名文件时发生 IO 异常
     */
    String rename(File song, NamingFormat oldFormat, NamingFormat newFormat) throws IOException;
}
