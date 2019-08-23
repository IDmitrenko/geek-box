package com.netty.common;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.Consumer;

public class TestMain {
    public static void main(String[] args) throws Exception {
        Files.walkFileTree(Paths.get("1"), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                return super.visitFile(file, attrs);
            }
        });
        Files.newDirectoryStream(Paths.get("1")).forEach(new Consumer<Path>() {
            @Override
            public void accept(Path path) {
                System.out.println(path);
            }
        });
    }
}
