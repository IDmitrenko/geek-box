package com.cloud.common;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author ilnaz-92@yandex.ru
 * Created on 2019-08-19
 */
public class BufferExample
{

  public static void main(String[] args) throws IOException
  {
    // read();
    //write();
  }

  private static void write() throws IOException
  {
    RandomAccessFile accessFile = new RandomAccessFile(".//data//example.txt", "rw");
    FileChannel channel = accessFile.getChannel();
    String newData = "New3";
    ByteBuffer buffer = ByteBuffer.allocate(4);
    buffer.clear();
    buffer.put(newData.getBytes());

    buffer.flip();

    while (buffer.hasRemaining())
    {
      channel.write(buffer);
    }
    channel.close();
  }

  private static void read() throws IOException
  {
    RandomAccessFile accessFile = new RandomAccessFile(".//data//example.txt", "rw");
    FileChannel channel = accessFile.getChannel();
    ByteBuffer buffer = ByteBuffer.allocate(4);
    int bytesRead = channel.read(buffer);
    int count = 0;
    while (bytesRead != -1)
    {
      buffer.flip();
      while (buffer.hasRemaining())
      {
        System.out.println((char) buffer.get());
      }
      buffer.clear();
      bytesRead = channel.read(buffer);
      count++;
    }

    accessFile.close();
    System.out.println(count);
  }
}
