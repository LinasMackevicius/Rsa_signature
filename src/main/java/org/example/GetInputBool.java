package org.example;

import java.util.Scanner;

public class GetInputBool {
    public static boolean getBooleanInput() {

        Scanner scanner = new Scanner(System.in);
        int input;

        do {
            System.out.print("Enter 0 for NO or 1 for YES: ");
            input = scanner.nextInt();
        } while (input != 0 && input != 1);

        return input == 1;
    }
}
