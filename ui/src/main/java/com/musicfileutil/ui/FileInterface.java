package com.musicfileutil.ui;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.musicfileutil.core.ApplicationContextUtils;
import com.musicfileutil.core.MusicFile;
import com.musicfileutil.core.MusicName;
import com.musicfileutil.core.NamingFormat;

/**
 * @author Yi Chen
 * @date 2023-12-26 22:24
 * @since 1.0
 */
@Component
public class FileInterface {
    private AtomicReference<JFileChooser> files = new AtomicReference<>(new JFileChooser());
    private MusicName musicName;
    private MusicFile musicFile;
    private NameFormatSelectInterface nameFormatSelectInterface;

    private JPanel panel;
    private JButton fileChoose;
    private JTextArea selectedFiles;

    private void init() {
        fileChoose.addActionListener(event -> {
            UIWindow window = ApplicationContextUtils.getBean(UIWindow.class);
            NamingFormat oldFormat = (NamingFormat) nameFormatSelectInterface.getOldNameFormat()
                                                                             .getSelectedItem();
            NamingFormat newFormat = (NamingFormat) nameFormatSelectInterface.getNewNameFormat()
                                                                             .getSelectedItem();
            if (Objects.equals(oldFormat, newFormat)) {
                // XXX 各组件中间该如何通信？
                new AlertDialog(window.getjFrame(), "警告", "新旧命名格式不能相同").setVisible(true);
                window.getMessageConsole()
                      .append("新旧命名格式不能相同\n");
                return;
            }
            JFileChooser fileChooser = files.get();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setMultiSelectionEnabled(true);
            // 设置默认使用的文件过滤器
            fileChooser.setFileFilter(new FileNameExtensionFilter("audio", "mp3", "flac"));
            // 打开文件选择框（线程将被阻塞, 直到选择框被关闭）
            int result = fileChooser.showOpenDialog(window.getjFrame());

            if (result == JFileChooser.APPROVE_OPTION) {
                files.set(fileChooser);
                selectedFiles.setText(null);
                for (File musicFile : files.get()
                                           .getSelectedFiles()) {
                    try {
                        selectedFiles.append(musicFile.getName() + "--->" + this.musicFile.rename(musicFile, oldFormat, newFormat) + "\n");
                    } catch (IOException e) {
                        window.getMessageConsole()
                              .append(e.getMessage() + "\n");
                    }
                    fileChooser.setCurrentDirectory(musicFile);
                }
            }
        });
    }

    @Autowired
    public void setMusicName(MusicName musicName) {
        this.musicName = musicName;
    }

    @Autowired
    public void setMusicFile(MusicFile musicFile) {
        this.musicFile = musicFile;
    }

    @Autowired
    public void setNameFormatSelectInterface(NameFormatSelectInterface nameFormatSelectInterface) {
        this.nameFormatSelectInterface = nameFormatSelectInterface;
    }

    public JPanel getPanel() {
        init();
        return panel;
    }
}
