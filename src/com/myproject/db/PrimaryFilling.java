package com.myproject.db;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class PrimaryFilling {
    /**
     * Java program to read File in Java. It demonstrate two ways by simple example,
     * one uses java.util.Scanner class and other by using java.io.BufferedReader
     * class.
     *
     * @author http://java67.blogspot.com
     */
    //D:\newrepo\src\com\myproject\db
    private final String FILE_NAME = "D://newrepo//src//com//myproject//db//products.txt";

    public String getFILE_NAME() {
        return FILE_NAME;
    }

    void bufferReaderWay() throws IOException {
        // 2nd way to read File in Java - Using BufferedReader
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_NAME)));
        String line = buffReader.readLine();
        while (line != null) {
            System.out.println(line);
            line = buffReader.readLine();
        }
    }

    public ArrayList<String> scanerWay(String fileName) throws IOException {
        // 1st way to read File in Java - Using Scanner
        Scanner scnr = new Scanner(new FileInputStream(fileName));
        ArrayList<String> s = new ArrayList<String>();
        while (scnr.hasNextLine()) {
            s.add(scnr.nextLine());
        }
        scnr.close();
        return s;
    }
}