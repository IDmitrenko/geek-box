package com.flamexander.netty.example.common;

public class FileRequest extends AbstractMessage {
    // запрос на получение файла по имени
    private String filename;

    public String getFilename() {
        return filename;
    }

    public FileRequest(String filename) {
        this.filename = filename;
    }
}
