package com.musicfileutil.core.impl;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import com.musicfileutil.core.IllegalNameException;
import com.musicfileutil.core.MusicName;
import com.musicfileutil.core.NamingFormat;
import com.musicfileutil.core.UnknownNamingFormatException;

/**
 * 歌曲名操作实现
 *
 * @author Yi Chen
 * @date 2023-10-10 19:55
 * @since 1.0
 */
public class MusicNameImpl implements MusicName {
    @Override
    public void copy2clipboard(String name) throws IllegalStateException {
        try {
            Clipboard clipboard = Toolkit.getDefaultToolkit()
                                         .getSystemClipboard();
            clipboard.setContents(new StringSelection(name), null);
        } catch (HeadlessException e) {
            throw new IllegalStateException();
        }
    }

    @Override
    public String rename(String oldName, NamingFormat oldFormat, NamingFormat newFormat) throws UnknownNamingFormatException, IllegalNameException {
        String songName;
        String artName;
        String extension = FilenameUtils.getExtension(oldName);
        String baseName = FilenameUtils.getBaseName(oldName);
        String[] songMessages = StringUtils.splitByWholeSeparator(baseName, NamingFormat.SEPARATOR);
        if (2 != songMessages.length) {
            throw new IllegalNameException();
        }
        switch (oldFormat) {
            case ART_SONG:
                artName = songMessages[0];
                songName = songMessages[1];
                break;
            case SONG_ART:
                artName = songMessages[1];
                songName = songMessages[0];
                break;
            default:
                throw new UnknownNamingFormatException();
        }
        switch (newFormat) {
            case SONG_ART:
                return String.format("%s%s%s.%s", songName, NamingFormat.SEPARATOR, artName, extension);
            case ART_SONG:
                return String.format("%s%s%s.%s", artName, NamingFormat.SEPARATOR, songName, extension);
            default:
                throw new UnknownNamingFormatException();
        }
    }
}
