package com.musicfileutil.ui;

import java.util.Objects;

import javax.swing.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.musicfileutil.core.IllegalNameException;
import com.musicfileutil.core.MusicFile;
import com.musicfileutil.core.MusicName;
import com.musicfileutil.core.NamingFormat;
import com.musicfileutil.core.UnknownNamingFormatException;

/**
 * 音乐重命名界面
 *
 * @author Yi Chen
 * @date 2023-12-10 21:08
 * @since 1.0
 */
@Component
public class RenameInterface {
    private MusicName musicName;
    private MusicFile musicFile;
    private UIWindow window;
    private JButton musicRename;
    private JPanel panel;
    private JTextField oldName;
    private JButton inputClean;
    private JComboBox<NamingFormat> oldNameFormat;
    private JComboBox<NamingFormat> newNameFormat;

    @Autowired
    public void setMusicName(MusicName musicName) {
        this.musicName = musicName;
    }

    @Autowired
    public void setMusicFile(MusicFile musicFile) {
        this.musicFile = musicFile;
    }

    @Autowired
    public void setWindow(UIWindow window) {
        this.window = window;
    }

    private void init() {
        musicRename.addActionListener(event -> {
            String oldName;
            try {
                oldName = this.oldName.getText();
            } catch (NullPointerException e) {
                oldName = "";
            }
            // musicName.copy2clipboard(musicName.rename(oldName, NamingFormat.ART_SONG, NamingFormat.SONG_ART));

            NamingFormat oldFormat = (NamingFormat) oldNameFormat.getSelectedItem();
            NamingFormat newFormat = (NamingFormat) newNameFormat.getSelectedItem();
            if (Objects.equals(oldFormat, newFormat)) {
                new AlertDialog(window.getjFrame(), "警告", "新旧命名格式不能相同").setVisible(true);
                window.getMessageConsole()
                      .append("新旧命名格式不能相同\n");
                return;
            }
            try {
                this.oldName.setText(musicName.rename(oldName, oldFormat, newFormat));
            } catch (IllegalNameException | UnknownNamingFormatException e) {
                window.getMessageConsole()
                      .append(e.getMessage() + "\n");
                return;
            }
            this.oldName.selectAll();
            this.oldName.copy();
        });
        inputClean.addActionListener(event -> {
            this.oldName.setText(null);
        });
        for (NamingFormat namingFormat : NamingFormat.values()) {
            oldNameFormat.addItem(namingFormat);
        }
        for (NamingFormat namingFormat : NamingFormat.values()) {
            newNameFormat.addItem(namingFormat);
        }
    }

    public JPanel getPanel() {
        init();
        return panel;
    }

}
