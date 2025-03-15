package org.controller;

import java.util.Scanner;

public class InputController {
    public static InputController inputController;

    public static InputController getInstance() {
        if (inputController == null) {
            inputController = new InputController();
        }
        return inputController;
    }

    public InputController() {

    }

    @SuppressWarnings("unchecked")
    public <T> T GetInput(String message, boolean isInt) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);

        while (true) {
            String input = scanner.nextLine();

            if (isInt) {
                try {
                    return (T) Integer.valueOf(input);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input, please enter an integer:");
                }
            } else {
                // check input not integer
                try {
                    Integer.parseInt(input);
                    System.out.println("Invalid input, please enter a string:");
                    continue;
                } catch (NumberFormatException ignored) {
                }
                return (T) input;
            }
        }
    }
}
