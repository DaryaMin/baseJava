package com.uirise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainFile {
    public static void main(String[] args) {
        String filePath = ".\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("C:\\D\\QA\\java\\basejava");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);

            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        printFileNames(".\\src");
    }

    public static void printFileNames(String sDir){
        File[] files = new File(sDir).listFiles();
        for(File file: files){
            if(file != null){
                System.out.println(file.getName());
            }
            if(file.isDirectory()){
                printFileNames(file.getAbsolutePath());
            }
        }
    }
}
