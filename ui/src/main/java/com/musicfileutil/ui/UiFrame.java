package com.musicfileutil.ui;

import java.awt.*;

import javax.swing.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.musicfileutil.core.MusicFile;
import com.musicfileutil.core.MusicName;
import com.musicfileutil.core.NamingFormat;

/**
 * 面板容器
 *
 * <p>通过实现 {@code ApplicationListener} 接口来使 {@code spring} 组件注册完成后
 * 再初始化生成窗口
 *
 * @author Yi Chen
 * @date 2023-12-10 21:08
 * @since 1.0
 */
@Component
public class UiFrame implements ApplicationListener<ContextRefreshedEvent> {
    private MusicName musicName;
    private MusicFile musicFile;
    private JButton musicRename;
    private JPanel nameMenage;
    private JTextField oldName;
    private JButton inputClean;
    private JTextArea messageConsole;

    public UiFrame() {
    }

    @Autowired
    public UiFrame(MusicName musicName, MusicFile musicFile) {
        this.musicName = musicName;
        this.musicFile = musicFile;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (null == event.getApplicationContext()
                         .getParent()) {
            init();
        }
    }

    public void init() {
        JFrame jFrame = new JFrame("音乐文件工具");
        jFrame.setContentPane(nameMenage);
        musicRename.addActionListener(event -> {
            String oldName;
            try {
                oldName = this.oldName.getText();
            } catch (NullPointerException e) {
                oldName = "";
            }
            // musicName.copy2clipboard(musicName.rename(oldName, NamingFormat.ART_SONG, NamingFormat.SONG_ART));
            this.oldName.setText(musicName.rename(oldName, NamingFormat.ART_SONG, NamingFormat.SONG_ART));
            this.oldName.selectAll();
            this.oldName.copy();
        });
        inputClean.addActionListener(event -> {
            this.oldName.setText(null);
        });
        jFrame.setMinimumSize(new Dimension(500, 400));
        jFrame.pack();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
}
