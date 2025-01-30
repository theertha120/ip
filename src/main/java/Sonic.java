import java.util.Scanner;
import java.util.Arrays;

public class Sonic {
    public static void main(String[] args) {
        String logo = " SSSSS  OOOOO  N   N  III  CCCCC \n"
                + " S      O   O  NN  N   I   C     \n"
                + "  SSS   O   O  N N N   I   C     \n"
                + "     S  O   O  N  NN   I   C     \n"
                + " SSSSS  OOOOO  N   N  III  CCCCC \n";

        System.out.println("Hello from\n" + logo);
        String line = "__________________________________________________________";
        System.out.println("I am Sonic! What can I do for you?");
        System.out.println(line);

        Scanner userInputScanner = new Scanner(System.in);
        String[] tasksList = new String[100];
        int tasksCount = 0;

        while (true) {
            String userInput = userInputScanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                if (tasksCount == 0) {
                    System.out.println("No tasks to show.");
                } else {
                    for (int i = 0; i < tasksCount; i++) {
                        System.out.println((i + 1) + ". " + tasksList[i]);
                    }
                }
            } else {
                tasksList[tasksCount] = userInput;
                tasksCount++;
                System.out.println("added: " + userInput);
            }

            System.out.println(line);
        }

    }
}
