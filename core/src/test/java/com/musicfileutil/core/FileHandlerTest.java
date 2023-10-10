package com.musicfileutil.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author Yi Chen
 * @date 2023-06-17 17:07
 * @since 1.0
 */
class FileHandlerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("验证一些内容")
    // @Disabled
    public void test_something() {
        Clipboard clipboard = Toolkit.getDefaultToolkit()
                                     .getSystemClipboard();
        clipboard.setContents(new StringSelection("atob哈哈哈"), null);
        Transferable transferable = clipboard.getContents(null);
        assertTrue(transferable.isDataFlavorSupported(DataFlavor.stringFlavor));
        try {
            String data = (String) transferable.getTransferData(DataFlavor.stringFlavor);
            assertEquals(data, "atob哈哈哈");
        } catch (UnsupportedFlavorException | IOException e) {
            e.printStackTrace();
        }
    }
}