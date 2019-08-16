package com.cloud.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoxMainWindow extends JFrame {

    private final JList<FileList> fileList;
    private final DefaultListModel<FileList> fileListModel;
    private final FileListCellRender fileListCellRender;
    private final JScrollPane scroll;
    private final JButton sendButton;
    private final JButton removeButton;
    private final JButton updateButton;
    private final JPanel sendCommandPanel;

    public BoxMainWindow() {

        setTitle("CLOUD");
        setBounds(200, 200, 800, 800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        fileList = new JList<>();
        fileListModel = new DefaultListModel<>();
        fileListCellRender = new FileListCellRender();
        fileList.setModel(fileListModel);
        fileList.setCellRenderer(fileListCellRender);

        scroll = new JScrollPane(fileList,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scroll, BorderLayout.CENTER);

        sendCommandPanel = new JPanel();
        sendCommandPanel.setLayout(new BorderLayout());

        sendButton = new JButton("Отправить файл");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        removeButton = new JButton("Удалить файл");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        updateButton = new JButton("Обновить");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        sendCommandPanel.add(sendButton, BorderLayout.EAST);
        sendCommandPanel.add(removeButton, BorderLayout.CENTER);
        sendCommandPanel.add(updateButton, BorderLayout.WEST);

        add(sendCommandPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
