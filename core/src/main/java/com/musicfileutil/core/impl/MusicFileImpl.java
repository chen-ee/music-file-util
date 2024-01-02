package com.musicfileutil.core.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.musicfileutil.core.MusicFile;
import com.musicfileutil.core.MusicName;
import com.musicfileutil.core.NamingFormat;

/**
 * 处理各种音乐文件的信息和内容。
 *
 * @author Yi Chen
 * @date 2023-06-17 16:08
 * @since 1.0
 */
@Component
public class MusicFileImpl implements MusicFile {
    private static Logger LOGGER = LoggerFactory.getLogger(MusicFileImpl.class);
    private MusicName musicName;

    public MusicFileImpl() {
    }

    @Autowired
    public MusicFileImpl(MusicName musicName) {
        this.musicName = musicName;
    }

    @Override
    public String rename(File song, NamingFormat oldFormat, NamingFormat newFormat) throws IOException {
        String oldName = song.getName();
        String newName = musicName.rename(oldName, oldFormat, newFormat);
        FileUtils.moveFile(song, new File(song.getParent(), newName));
        return newName;
    }
}
