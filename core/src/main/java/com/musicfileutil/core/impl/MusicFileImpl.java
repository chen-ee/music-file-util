package com.musicfileutil.core.impl;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.musicfileutil.core.MusicFile;

/**
 * 处理各种音乐文件的信息和内容。
 *
 * @author Yi Chen
 * @date 2023-06-17 16:08
 * @since 1.0
 */
public class MusicFileImpl implements MusicFile {
    private static Logger logger = LoggerFactory.getLogger(MusicFileImpl.class);

    @Override
    public void rename(File song, String newName) {
        // List<File> songFiles = new ArrayList<>();
        // if (song.isDirectory()) {
        //     songFiles.addAll(FileUtils.listFiles(song, new String[]{"mp3", "flac"}, true));
        // } else {
        //     songFiles.add(song);
        // }
        // try {
        //     FileUtils.moveFile(song, new File(song.getPath() + newName));
        // } catch (IOException e) {
        //     logger.error("文件重命名失败！", e);
        // }
    }
}
