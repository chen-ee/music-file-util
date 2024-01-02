package com.musicfileutil.ui;

import java.awt.*;

import javax.swing.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 程序窗口
 *
 * <p>使用此窗口包含程序所需的所有组件
 *
 * @author Yi Chen
 * @date 2023-12-26 17:01
 * @since 1.0
 */
@Component
public class UIWindow {
    private JFrame jFrame;
    private JTextArea messageConsole;
    private RenameInterface renameInterface;
    private FileInterface fileInterface;
    private NameFormatSelectInterface nameFormatSelectInterface;

    @Autowired
    public void setRenameInterface(RenameInterface renameInterface) {
        this.renameInterface = renameInterface;
    }

    @Autowired
    public void setFileInterface(FileInterface fileInterface) {
        this.fileInterface = fileInterface;
    }

    @Autowired
    public void setNameFormatSelectInterface(NameFormatSelectInterface nameFormatSelectInterface) {
        this.nameFormatSelectInterface = nameFormatSelectInterface;
    }

    public JFrame getjFrame() {
        return jFrame;
    }

    public JTextArea getMessageConsole() {
        return messageConsole;
    }

    public void init() {
        jFrame = new JFrame("音乐文件工具");
        messageConsole = new JTextArea();
        messageConsole.setRows(10);
        messageConsole.setEditable(false);
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.addTab("名称重命名", renameInterface.getPanel());
        tabbedPane.addTab("文件重命名", fileInterface.getPanel());
        Container contentPane = jFrame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(tabbedPane, BorderLayout.NORTH);
        contentPane.add(nameFormatSelectInterface.getPanel(), BorderLayout.CENTER);
        contentPane.add(new JScrollPane(messageConsole), BorderLayout.SOUTH);
        jFrame.setMinimumSize(new Dimension(500, 400));
        jFrame.pack();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

}
