package com.musicfileutil.core;

import java.io.File;

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
     * @param song    音乐文件
     * @param newName 文件新名称
     */
    void rename(File song, String newName);
}
