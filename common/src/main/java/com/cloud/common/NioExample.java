package com.cloud.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author ilnaz-92@yandex.ru
 * Created on 2019-08-19
 */
public class NioExample
{
  public static void main(String[] args)
  {
    // Проверка существует ли файл?
    //    Path path = Paths.get(".//file//example.txt");
    //    boolean isExist = Files.exists(path);
    //    System.out.println(isExist);

    // Создание директории
    //    Path path = Paths.get(".//file//newDir");
    //    try
    //    {
    //      Path newDir = Files.createDirectory(path);
    //    }
    //    catch (FileAlreadyExistsException faee)
    //    {
    //      faee.printStackTrace();
    //    }
    //    catch (IOException e)
    //    {
    //      e.printStackTrace();
    //    }

    // Копирование файла в другой
    //    Path sourcePath = Paths.get(".//file//example.txt");
    //    Path targetPath = Paths.get(".//file//exampleCopy.txt");
    //    try
    //    {
    //      Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
    //    }
    //    catch (Exception e)
    //    {
    //      e.printStackTrace();
    //    }

    // Перемещение файла
    //    Path sourcePath = Paths.get(".//file//example.txt");
    //    Path targetPath = Paths.get(".//file//newDir//exampleCopy.txt");
    //    try
    //    {
    //      Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
    //    }
    //    catch (Exception e)
    //    {
    //      e.printStackTrace();
    //    }

    // Удаление файла
    //    Path path = Paths.get(".//file//newDir//exampleCopy.txt");
    //    try
    //    {
    //      Files.delete(path);
    //    }
    //    catch (Exception e)
    //    {
    //      e.printStackTrace();
    //    }

//  Обход дерева
/*
    Path rootPath = Paths.get(".//file");
    try
    {
      Files.walkFileTree(rootPath, new FileVisitor<Path>()
      {
        // Перед посещением директории
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException
        {
          System.out.println("pre visit dir = " + dir);
          return FileVisitResult.CONTINUE;
        }

        // Перед посещением фай1ла
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
        {
          System.out.println("visit file " + file);
          return FileVisitResult.CONTINUE;
        }

        // При сбое посещения файла
        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException
        {
          System.out.println("visit failed " + file);
          return FileVisitResult.CONTINUE;
        }

        // после посещения директории
        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException
        {
          System.out.println("visit post directory " + dir);
          return FileVisitResult.CONTINUE;
        }
      });
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
*/

    // поиск файла от корневой директории
/*
    Path root = Paths.get(".//file");
    String searchQuery = File.separator + "myFile2.txt";

    try
    {
      Files.walkFileTree(root, new SimpleFileVisitor<Path>()
      {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
        {

          String filePath = file.toAbsolutePath().toString();
          if (filePath.endsWith(searchQuery))
          {
            System.out.println("File is found. " + filePath);
            return FileVisitResult.TERMINATE;
          }

          return FileVisitResult.CONTINUE;
        }
      });
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
*/

    // Удаление всего от указанной директории
    // удаляем сначала файлы, потом пустую директорию
    Path root = Paths.get(".//file");

    try
    {
      Files.walkFileTree(root, new SimpleFileVisitor<Path>()
      {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
        {
          Files.delete(file);
          System.out.println("Delete file " + file);
          return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException
        {
          Files.delete(dir);
          System.out.println("Delete dir " + dir);
          return FileVisitResult.CONTINUE;

        }
      });
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }


  }
}
