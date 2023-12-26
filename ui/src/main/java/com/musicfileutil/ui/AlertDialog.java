package com.musicfileutil.ui;

import java.awt.*;

import javax.swing.*;

public class AlertDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel message;

    public AlertDialog(JFrame owner, String title, String message) {
        super(owner, title);
        setMinimumSize(new Dimension(200, 150));
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        buttonOK.addActionListener(event -> onOK());
        this.message.setText(message);
    }

    private void onOK() {
        dispose();
    }

}
