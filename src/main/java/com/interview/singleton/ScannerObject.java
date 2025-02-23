package com.interview.singleton;

import java.util.Scanner;

public class ScannerObject {
    public static Scanner scanner = new Scanner(System.in);

    private ScannerObject(){

    }

    public static Scanner getInstance(){
        return scanner;
    }

    public static void close(){
        scanner.close();
    }
}
