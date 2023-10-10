package com.musicfileutil.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 处理各种音乐文件的信息和内容。
 *
 * @author Yi Chen
 * @date 2023-06-17 16:08
 * @since 1.0
 */
public class FileHandler {
    private static Logger logger = LoggerFactory.getLogger(FileHandler.class);
    /**
     * 歌曲名和歌手名的分隔符：{@value}
     */
    public static final String SEPARATOR = " - ";

    public String getSongName(File songFile, NamingFormat namingFormat) throws UnknownNamingFormatException {
        String fileName = songFile.getName();
        String[] songMessages = StringUtils.split(fileName, SEPARATOR);
        String result;
        switch (namingFormat) {
            case SONG_ART:
                result = songMessages[0];
                break;
            case ART_SONG:
                result = songMessages[1];
                break;
            default:
                throw new UnknownNamingFormatException();
        }
        return result;
    }

    /**
     * 以歌手和歌曲名交换的方式重命名歌曲文件
     *
     * @param basePath 文件路径
     */
    public void renameFile(String basePath, NamingFormat namingFormat) {
        File path = new File(basePath);
        List<File> songFiles = new ArrayList<>();
        if (path.isDirectory()) {
            songFiles.addAll(FileUtils.listFiles(path, new String[]{"mp3", "flac"}, true));
        } else {
            songFiles.add(path);
        }
        if (CollectionUtils.isNotEmpty(songFiles)) {
            for (File song : songFiles) {
                String oldName = song.getName();
                String[] songMessage = StringUtils.splitByWholeSeparator(StringUtils.substring(oldName, 0, oldName.lastIndexOf(".")), FileHandler.SEPARATOR);
                String newName = songMessage[1] + FileHandler.SEPARATOR + songMessage[0] + StringUtils.substring(oldName, oldName.lastIndexOf("."));
                try {
//                    Files.move(Paths.get(basePath+oldName), Paths.get(basePath + newName));
                    FileUtils.moveFile(song, new File(basePath + newName));
                } catch (IOException e) {
                    logger.error("重命名失败", e);
                }
            }
        }
    }
}
