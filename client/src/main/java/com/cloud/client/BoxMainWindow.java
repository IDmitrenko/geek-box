package com.cloud.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoxMainWindow extends JFrame {

    private final JList<FileList> fileListClient;
    private final DefaultListModel<FileList> fileListModelClient;
    private final FileListCellRender fileListCellRenderClient;
    private final JScrollPane scrollClient;
    private final JButton sendButtonClient;
    private final JButton removeButtonClient;
    private final JButton updateButtonClient;

    private final JPanel sendCommandPanel;

    private final JList<FileList> fileListServer;
    private final DefaultListModel<FileList> fileListModelServer;
    private final FileListCellRender fileListCellRenderServer;
    private final JScrollPane scrollServer;
    private final JButton downloadButtonServer;
    private final JButton removeButtonServer;
    private final JButton updateButtonServer;

    private final JPanel titleBox;
    private final JLabel titleClient;
    private final JLabel titleServer;

    public BoxMainWindow() {

        setTitle("CLOUD");
        setBounds(200, 200, 800, 800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        fileListClient = new JList<>();
        fileListModelClient = new DefaultListModel<>();
        fileListCellRenderClient = new FileListCellRender();
        fileListClient.setModel(fileListModelClient);
        fileListClient.setCellRenderer(fileListCellRenderClient);

        scrollClient = new JScrollPane(fileListClient,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollClient, BorderLayout.WEST);
        scrollClient.setPreferredSize(new Dimension(390, 720));

        titleBox = new JPanel();
        titleBox.setLayout(new BoxLayout(titleBox, BoxLayout.X_AXIS));
        titleClient = new JLabel("Локальное хранилище", SwingConstants.CENTER);
        titleClient.setPreferredSize(new Dimension(390, 40));
        Font f = titleClient.getFont();
        titleClient.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        titleBox.add(titleClient);
        titleServer = new JLabel("Облачное хранилище", SwingConstants.CENTER);
        titleServer.setPreferredSize(new Dimension(390, 40));
        titleServer.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        titleBox.add(titleServer);
        add(titleBox, BorderLayout.NORTH);

        sendCommandPanel = new JPanel();
        sendCommandPanel.setLayout(new BoxLayout(sendCommandPanel, BoxLayout.X_AXIS));

        sendButtonClient = new JButton("Отправить файл");
        sendButtonClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        removeButtonClient = new JButton("Удалить файл");
        removeButtonClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        updateButtonClient = new JButton("Обновить");
        updateButtonClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        fileListServer = new JList<>();
        fileListModelServer = new DefaultListModel<>();
        fileListCellRenderServer = new FileListCellRender();
        fileListServer.setModel(fileListModelServer);
        fileListServer.setCellRenderer(fileListCellRenderServer);

        scrollServer = new JScrollPane(fileListServer,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollServer, BorderLayout.EAST);
        scrollServer.setPreferredSize(new Dimension(390, 720));

        downloadButtonServer = new JButton("Скачать файл");
        downloadButtonServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        removeButtonServer = new JButton("Удалить файл");
        removeButtonServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        updateButtonServer = new JButton("Обновить");
        updateButtonServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        sendCommandPanel.setPreferredSize(new Dimension(800, 40));
        sendCommandPanel.add(sendButtonClient);
        sendButtonClient.setPreferredSize(new Dimension(140, 30));
        sendCommandPanel.add(removeButtonClient);
        removeButtonClient.setPreferredSize(new Dimension(130, 30));
        sendCommandPanel.add(updateButtonClient);
        updateButtonClient.setPreferredSize(new Dimension(120, 30));

        sendCommandPanel.add(downloadButtonServer);
        downloadButtonServer.setPreferredSize(new Dimension(134, 30));
        sendCommandPanel.add(removeButtonServer);
        removeButtonServer.setPreferredSize(new Dimension(134, 30));
        sendCommandPanel.add(updateButtonServer);
        updateButtonServer.setPreferredSize(new Dimension(130, 30));

        add(sendCommandPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
