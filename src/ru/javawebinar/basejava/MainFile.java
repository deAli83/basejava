package ru.javawebinar.basejava;

import java.io.File;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) throws IOException {
        String filePath = ".\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("./src/ru/javawebinar/basejava");
        //System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        /*try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        listDir(dir);
    }

    public static void listDir(File dir) {
        File[] subList = dir.listFiles();
        if (subList != null) {
            for (File name : subList) {
                if (name.isFile()) {
                    System.out.println(" " + name.getName());
                } else if (name.isDirectory()) {
                    System.out.println(name.getName());
                    listDir(name);
                }
            }
        }
    }
}
