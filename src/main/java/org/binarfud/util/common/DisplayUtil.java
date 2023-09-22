package org.binarfud.util.common;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DisplayUtil {
    public String printLine(String karakter) {
        return Stream.generate(() -> karakter)
                .limit(40)
                .collect(Collectors.joining());
    }


    public void printWelcomeMessage(String message, String line) {
        System.out.println(printLine(line));
        System.out.println("\t " + message);
        System.out.println(printLine(line));
    }

    public int inputChoice(String message) {
        InputValidator inputValidator = new InputValidator(new Scanner(System.in));
        System.out.print(message + " ");
        return inputValidator.getValidIntegerInput();
    }

    public char inputConfirm(String message) {
        System.out.println(printLine("="));
        System.out.println("\t Mohon Masukan Input Piliha Anda");
        System.out.println(printLine("="));
        System.out.println("(Y) Untuk Lanjut");
        System.out.println("(N) Untuk Batal");
        InputValidator inputValidator = new InputValidator(new Scanner(System.in));
        System.out.print(message + " ");
        return inputValidator.getValidCharInput();
    }
}
