package com.company;

import java.awt.*;
import java.io.File;
import java.awt.TrayIcon.MessageType;
import java.net.MalformedURLException;

public class Deleting {
    public static void del(String path) throws MalformedURLException, AWTException {
        //в переменную file добавляем файл по пути path, который мы передаем
        //при нажатии кнопки ввод (см. класс Window)
        File file = new File(path);
        if(file.delete()){
            System.out.println("deleting file");
            // SystemTray нужен для прикольного уведомления справа на экране (win10)
            // для понимания условия ниже нужны базовые знания инглиша)
            if (SystemTray.isSupported()) {
                //td - само уведомление, а через строчку мы его высвечиваем
                Deleting td = new Deleting();
                td.displayTray("Готово!");
            } else {
                System.err.println("System tray not supported!");
            }
        }else{
            //вроде бы все просто, если файла по пути нет, пишем что нет такого
            System.out.println("Файл non exist");
            if (SystemTray.isSupported()) {
                Deleting td = new Deleting();
                td.displayTray("Файл не найден");
            } else {
                System.err.println("System tray not supported!");
            }
        }

    }
    // функция с уведомлениями
    public void displayTray(String msg) throws AWTException, MalformedURLException {
        SystemTray tray = SystemTray.getSystemTray();

        //Типа загруаем картинку если есть
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Ну система там размеры че-то изменяет (чтобы маштаб картинки адекватный был ставьте true)
        trayIcon.setImageAutoSize(true);
        //Запустите и найдите этот текст. Объяснить сложно
        trayIcon.setToolTip("Scam squad");
        tray.add(trayIcon);

        //ну и само сообщение (будет маленькими буквами ниже текста)
        trayIcon.displayMessage(msg, "Капец я программист", MessageType.INFO);
    }
}
