package com.musicfileutil.ui;

import javax.swing.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Yi Chen
 * @date 2023-09-21 22:48
 * @since 1.0
 */
class SwingAreaTest {
    SwingArea swingArea;

    @BeforeEach
    void setUp() {
        swingArea = SwingArea.getInstance();
    }

    @AfterEach
    void tearDown() {
        swingArea = null;
    }

    @Test
    void initUI() {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            System.out.println(info.getName() + "--->" + info);
        }
    }
}