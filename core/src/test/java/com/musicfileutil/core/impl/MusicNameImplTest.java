package com.musicfileutil.core.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Collection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableList;
import com.musicfileutil.core.IllegalNameException;
import com.musicfileutil.core.NamingFormat;
import com.musicfileutil.core.UnknownNamingFormatException;

/**
 * @author Yi Chen
 * @date 2023-10-10 21:08
 * @since 1.0
 */
class MusicNameImplTest {
    private static Logger logger = LoggerFactory.getLogger(MusicNameImplTest.class);

    private MusicNameImpl musicNameHandler = new MusicNameImpl();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void test_copy2clipboard() {
        String musicName = "demo - my.mp3";
        String expected = musicName;
        String actual = null;
        try {
            musicNameHandler.copy2clipboard(musicName);
            Clipboard clipboard = Toolkit.getDefaultToolkit()
                                         .getSystemClipboard();
            Transferable transferable = clipboard.getContents(null);
            assertTrue(transferable.isDataFlavorSupported(DataFlavor.stringFlavor));
            actual = (String) transferable.getTransferData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException | IOException e) {
            logger.error("", e);
        }
        assertEquals(expected, actual);
    }

    static Collection<Arguments> test_rename_data() {
        return ImmutableList.of(
                Arguments.of("初音ミク,八王子P - Blue Star.mp3", NamingFormat.ART_SONG, NamingFormat.SONG_ART, "Blue Star - 初音ミク,八王子P.mp3"),
                Arguments.of("李健 - 贝加尔湖畔.mp3", NamingFormat.ART_SONG, NamingFormat.SONG_ART, "贝加尔湖畔 - 李健.mp3"),
                Arguments.of("demo - my.mp3", NamingFormat.SONG_ART, NamingFormat.ART_SONG, "my - demo.mp3"),
                Arguments.of("demo - my.png.mp3", NamingFormat.SONG_ART, NamingFormat.ART_SONG, "my.png - demo.mp3")
        );
    }

    @ParameterizedTest
    @MethodSource("test_rename_data")
    void test_rename(String oldName, NamingFormat oldFormat, NamingFormat newFormat, String expected) {
        String actual = null;
        try {
            actual = musicNameHandler.rename(oldName, oldFormat, newFormat);
        } catch (UnknownNamingFormatException | IllegalNameException e) {
            logger.error("重命名失败！", e);
        }
        assertEquals(expected, actual);
    }
}