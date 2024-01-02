package com.musicfileutil.ui;

import javax.swing.*;

import org.springframework.stereotype.Component;

import com.musicfileutil.core.NamingFormat;

/**
 * @author Yi Chen
 * @date 2023-12-27 15:41
 * @since 1.0
 */
@Component
public class NameFormatSelectInterface {
    private JComboBox<NamingFormat> oldNameFormat;
    private JComboBox<NamingFormat> newNameFormat;
    private JPanel panel;

    private void init() {
        for (NamingFormat namingFormat : NamingFormat.values()) {
            oldNameFormat.addItem(namingFormat);
        }
        for (NamingFormat namingFormat : NamingFormat.values()) {
            newNameFormat.addItem(namingFormat);
        }
        oldNameFormat.setSelectedItem(NamingFormat.ART_SONG);
        newNameFormat.setSelectedItem(NamingFormat.SONG_ART);
    }

    public JPanel getPanel() {
        init();
        return panel;
    }

    public JComboBox<NamingFormat> getOldNameFormat() {
        return oldNameFormat;
    }

    public JComboBox<NamingFormat> getNewNameFormat() {
        return newNameFormat;
    }
}
