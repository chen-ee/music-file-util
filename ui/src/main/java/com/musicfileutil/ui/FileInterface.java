package com.musicfileutil.ui;

import java.io.File;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Yi Chen
 * @date 2023-12-26 22:24
 * @since 1.0
 */
@Component
public class FileInterface {
    AtomicReference<JFileChooser> files = new AtomicReference<>(new JFileChooser());
    private UIWindow window;
    private JPanel panel;
    private JButton fileChoose;

    public void init() {
        fileChoose.addActionListener(event -> {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            jFileChooser.setMultiSelectionEnabled(true);
            // 设置默认使用的文件过滤器
            jFileChooser.setFileFilter(new FileNameExtensionFilter("audio", "mp3", "flac"));
            // 打开文件选择框（线程将被阻塞, 直到选择框被关闭）
            int result = jFileChooser.showOpenDialog(window.getjFrame());

            if (result == JFileChooser.APPROVE_OPTION) {
                // 如果点击了"确定", 则获取选择的文件路径
                File file = jFileChooser.getSelectedFile();

                // 如果允许选择多个文件, 则通过下面方法获取选择的所有文件
                // File[] files = fileChooser.getSelectedFiles();
            }
            files.set(jFileChooser);
            for (File selectedFile : files.get()
                                          .getSelectedFiles()) {
                window.getMessageConsole()
                      .append(selectedFile.getName() + "\n");
            }
        });
    }

    @Autowired
    public void setWindow(UIWindow window) {
        this.window = window;
    }

    public JPanel getPanel() {
        init();
        return panel;
    }
}
