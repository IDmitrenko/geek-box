package com.cloud.client;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class SimpleTableTest2 extends JFrame
{
    // Данные для таблиц
    private Object[][] array = new String[][] {{ "Сахар" , "кг", "1.5" },
            { "Мука"  , "кг", "4.0" },
            { "Молоко", "л" , "2.2" }};
    // Заголовки столбцов
    private Object[] columnsHeader = new String[] {"Наименование", "Ед.измерения",
            "Количество"};
    public SimpleTableTest2() {
        super("Простой пример с JTable");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Таблица с настройками
        JTable table2 = new JTable(3, 5);
        // Настройка таблицы
        table2.setRowHeight(30);
        table2.setRowHeight(1, 20);
        table2.setIntercellSpacing(new Dimension(10, 10));
        table2.setGridColor(Color.blue);
        table2.setShowVerticalLines(false);

        // Данные для таблицы на основе Vector
        Vector<Vector<String>> data = new Vector<Vector<String>>();
        // Вектор с заголовками столбцов
        Vector<String> header = new Vector<String>();
        // Формирование в цикле массива данных
        for (int j = 0; j < array.length; j++) {
            header.add((String)columnsHeader[j]);
            Vector<String> row = new Vector<String>();
            for (int i = 0; i < array[j].length; i++) {
                row.add((String)array[j][i]);
            }
            data.add(row);
        }
        // Размещение таблиц в панели с блочным расположением
        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(new JScrollPane(table2));

        // Вывод окна на экран
        setContentPane(contents);
        setSize(500, 400);
        setVisible(true);
    }
    public static void main(String[] args) {
        new SimpleTableTest2();
    }
}