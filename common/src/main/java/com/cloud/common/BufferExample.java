package com.cloud.common;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.nio.channels.FileChannel;

/**
 * @author ilnaz-92@yandex.ru
 * Created on 2019-08-19
 */
public class BufferExample
{

  public static void main(String[] args) throws IOException
  {
     //read();
    write();
  }

  private static void write() throws IOException
  {
    // открываем файл
    RandomAccessFile accessFile = new RandomAccessFile(".//data//example.txt", "rw");
    // получаем для него канал
    FileChannel channel = accessFile.getChannel();
    String newData = "New3";
    // выделяем буфер
    ByteBuffer buffer = ByteBuffer.allocate(4);
//    LongBuffer longBuffer = LongBuffer.allocate(2134215433);
    // очистили буфер
    buffer.clear();
    // записали данные в буфер
    buffer.put(newData.getBytes());

    // переключаем режим
    buffer.flip();

    while (buffer.hasRemaining())
    {
      // записываем данные из буфера в канал посимвольно
      channel.write(buffer);
    }
    channel.close();
  }

  private static void read() throws IOException
  {
    StringBuilder sb = new StringBuilder();
    // открываем файл
    RandomAccessFile accessFile = new RandomAccessFile(".//data//example.txt", "rw");
    // получаем для него канал
    FileChannel channel = accessFile.getChannel();
    // выделяем буфер
    ByteBuffer buffer = ByteBuffer.allocate(4);
    // считываем данные в буфер
    int bytesRead = channel.read(buffer);
    int count = 0;
    //
    while (bytesRead != -1)
    {
      // запись в буфер закончена
      buffer.flip();
      while (buffer.hasRemaining())
      {
        sb.append((char) buffer.get());
      }
      System.out.println(sb);

      // очищаем буфер
      buffer.clear();
      // читаем новую порцию в буфер
      bytesRead = channel.read(buffer);
      count++;
    }

    // закрываем канал
    accessFile.close();
    System.out.println(count);
  }
}
