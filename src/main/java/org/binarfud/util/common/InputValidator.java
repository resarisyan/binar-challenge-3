package org.binarfud.util.common;

import lombok.RequiredArgsConstructor;

import java.util.Scanner;

@RequiredArgsConstructor
public class InputValidator {
    private final Scanner scanner;

    public int getValidIntegerInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Masukkan angka yang valid: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public char getValidCharInput() {
        char userInput = 'n';
        boolean validInput = false;

        while (!validInput) {
            String input = scanner.nextLine();
            if (input.length() == 1) {
                userInput = input.charAt(0);
                if (Character.toLowerCase(userInput) == 'y' || userInput == 'n') {
                    validInput = true;
                } else {
                    System.out.println("Masukan tidak valid. Harap masukkan 'y' atau 'n'.");
                }
            } else {
                System.out.println("Masukan tidak valid. Harap masukkan hanya satu karakter.");
            }
        }

        return userInput;
    }
}
