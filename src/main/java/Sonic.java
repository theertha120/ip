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
        Task[] tasksList = new Task[100];
        int tasksCount = 0;

        while (true) {
            String userInput = userInputScanner.nextLine().trim();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;

            } else if (userInput.equalsIgnoreCase("list")) {
                if (tasksCount == 0) {
                    System.out.println("No tasks to show.");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasksCount; i++) {
                        System.out.println((i + 1) + ".[" + tasksList[i].getStatusIcon() + "] " + tasksList[i].getDescription());
                    }
                }

            } else if (userInput.toLowerCase().startsWith("add")) {
                String taskText = userInput.substring(4);
                tasksList[tasksCount] = new Task(taskText);
                tasksCount++;
                System.out.println("added: " + taskText);

            } else if (userInput.toLowerCase().startsWith("mark")) {
                String[] splitUserInput = userInput.split(" ");
                int taskNumber = Integer.parseInt(splitUserInput[1]);
                if (taskNumber >= 1 && taskNumber <= tasksCount) {
                    Task taskToMark = tasksList[taskNumber - 1];
                    taskToMark.setDone(true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  [" + taskToMark.getStatusIcon() + "] " + taskToMark.getDescription());
                } else {
                    System.out.println("Invalid task number!");
                }

            } else if (userInput.toLowerCase().startsWith("unmark")) {
                String[] splitUserInput = userInput.split(" ");
                int taskNumber = Integer.parseInt(splitUserInput[1]);
                if (taskNumber >= 1 && taskNumber <= tasksCount) {
                    Task taskToUnmark = tasksList[taskNumber - 1];
                    taskToUnmark.setDone(false);
                    System.out.println("Ok, I've marked this task as not done yet:");
                    System.out.println("  [" + taskToUnmark.getStatusIcon() + "] " + taskToUnmark.getDescription());
                } else {
                    System.out.println("Invalid task number!");
                }

            } else {
                System.out.println("Unknown command: " + userInput);
            }

            System.out.println(line);
        }

    }
}
