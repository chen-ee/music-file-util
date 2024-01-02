package com.musicfileutil.ui;

import java.util.Objects;

import javax.swing.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.musicfileutil.core.ApplicationContextUtils;
import com.musicfileutil.core.IllegalNameException;
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
    private JButton musicRename;
    private JPanel panel;
    private JTextField oldName;
    private JButton inputClean;

    @Autowired
    public void setMusicName(MusicName musicName) {
        this.musicName = musicName;
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
            UIWindow window = ApplicationContextUtils.getBean(UIWindow.class);
            JComboBox<NamingFormat> oldNameFormat = ApplicationContextUtils.getBean(NameFormatSelectInterface.class)
                                                                           .getOldNameFormat();
            JComboBox<NamingFormat> newNameFormat = ApplicationContextUtils.getBean(NameFormatSelectInterface.class)
                                                                           .getNewNameFormat();
            NamingFormat oldFormat = (NamingFormat) oldNameFormat.getSelectedItem();
            NamingFormat newFormat = (NamingFormat) newNameFormat.getSelectedItem();
            if (Objects.equals(oldFormat, newFormat)) {
                // XXX 各组件中间该如何通信？
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
    }

    public JPanel getPanel() {
        init();
        return panel;
    }

}
