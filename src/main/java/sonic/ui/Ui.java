package sonic.ui;

import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "__________________________________________________________";
    private static final String LOGO = """
         ____              _     \s
        / ___|  ___  _ __ (_) ___\s
        \\___ \\ / _ \\| '_ \\| |/ __|
         ___) | (_) | | | | | (__\s
        |____/ \\___/|_| |_|_|\\___|""";

    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcomeMessage() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("I am Sonic! What can I do for you?");
        System.out.println(DIVIDER);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLoadingError() {
        System.out.println("Error loading task list. Creating new task list...");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
