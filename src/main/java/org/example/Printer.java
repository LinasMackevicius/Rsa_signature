package org.example;

public class Printer {
    public static void printTheSignature(byte[] digitalSignature) {

        for (int i = 0; i < 256; i++) {
            System.out.println(digitalSignature[i]);
        }
    }
}
