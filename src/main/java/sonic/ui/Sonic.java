package sonic.ui;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Sonic {
    public static final String DIVIDER = "__________________________________________________________";
    public static final String LOGO = """
         ____              _     \s
        / ___|  ___  _ __ (_) ___\s
        \\___ \\ / _ \\| '_ \\| |/ __|
         ___) | (_) | | | | | (__\s
        |____/ \\___/|_| |_|_|\\___|""";

    private static final String FILE_PATH = "./data/duke.txt";
    private static final String DIRECTORY_PATH = "./data";


    private static void welcomeMessage() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("I am Sonic! What can I do for you?");
        System.out.println(DIVIDER);
    }

    private static void addTask(ArrayList<Task> tasksList, Task task) {
        tasksList.add(task);
        System.out.println("Got it, I have added this task:");
        System.out.println("   " + task.toString());
        System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
        saveTasks(tasksList);
    }

    private static void printTasks(ArrayList<Task> tasksList) {
        if (tasksList.isEmpty()) {
            System.out.println("No tasks in your list right now, you are free!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasksList.size(); i++) {
                System.out.println((i + 1) + "." + tasksList.get(i).toString());
            }
        }
    }

    private static void markTask(String userInput, ArrayList<Task> tasksList) {
        try {
            String[] splitUserInput = userInput.split(" ");

            if (splitUserInput.length == 1) {
                System.out.println("Mark format error! Try the command: 'mark <task number>'");
                return;
            }

            int taskNumber = Integer.parseInt(splitUserInput[1]);

            if (taskNumber < 1 || taskNumber > tasksList.size()) {
                throw new IndexOutOfBoundsException();
            }

            Task taskToMark = tasksList.get(taskNumber - 1);
            taskToMark.setDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(taskToMark);
            saveTasks(tasksList);

        } catch (NumberFormatException e) {
            System.out.println("That's not a number! Try the command: 'mark <task number>'");

        } catch (IndexOutOfBoundsException e) {
            System.out.println("The task does not exist... yet");
        }
    }

    private static void unmarkTask(String userInput, ArrayList<Task> tasksList) {
        try {
            String[] splitUserInput = userInput.split(" ");

            if (splitUserInput.length == 1) {
                System.out.println("Mark format error! Try the command: 'unmark <task number>");
                return;
            }

            int taskNumber = Integer.parseInt(splitUserInput[1]);

            if (taskNumber < 1 || taskNumber > tasksList.size()) {
                throw new IndexOutOfBoundsException();
            }

            Task taskToUnmark = tasksList.get(taskNumber - 1);
            taskToUnmark.setDone(false);
            System.out.println("Ok, I've marked this task as not done yet:");
            System.out.println(taskToUnmark);
            saveTasks(tasksList);

        } catch (NumberFormatException e) {
            System.out.println("That's not a number! Try the command: 'unmark <task number>'");

        } catch (IndexOutOfBoundsException e) {
            System.out.println("The task does not exist... yet");
        }
    }

    private static void addTodo(String userInput, ArrayList<Task> tasksList) throws IllegalArgumentException{
        try {
            if (userInput.length() <= 4) {
                throw new IllegalArgumentException("Todo needs a description! Try the command: 'todo <task text>'");
            }
            String taskText = userInput.substring(5).trim();
            Todo newTodo = new Todo(taskText);
            addTask(tasksList, newTodo);

        } catch (IllegalArgumentException e) {
            System.out.println("Todo needs a description! Try the command: 'todo <task text>'");
        }

    }

    private static void addEvent(String userInput, ArrayList<Task> tasksList) throws IllegalArgumentException {
        try {
            if (!userInput.contains(" /from ") || !userInput.contains(" /to ")) {
                throw new IllegalArgumentException();
            }

            String[] fromSplit = userInput.split(" /from ");
            String[] toSplit = fromSplit[1].split(" /to ");

            String eventName = fromSplit[0].substring(6).trim();
            String startTime = toSplit[0].trim();
            String endTime = toSplit[1].trim();

            Event newEvent = new Event(eventName, startTime, endTime);
            addTask(tasksList, newEvent);

        } catch (IllegalArgumentException e) {
            System.out.println("Event format entered wrong! Try the command: ‘event <name> /from <start> /to <end>’");
        }

    }

    private static void addDeadline(String userInput, ArrayList<Task> tasksList) {
        try {
            if (!userInput.contains(" /by ")) {
                throw new IllegalArgumentException();
            }

            String[] userInputSplit = userInput.split(" /by ");
            String deadlineName = userInputSplit[0].substring(9).trim();
            String endTime = userInputSplit[1].trim();

            Deadline newDeadline = new Deadline(deadlineName, endTime);
            addTask(tasksList, newDeadline);

        } catch (IllegalArgumentException e) {
            System.out.println("Deadline format error! Try the command: ‘deadline <name> /by <time>’");
        }

    }

    private static void deleteTask(String userInput, ArrayList<Task> tasksList) {
        try {
            String[] splitUserInput = userInput.split(" ");
            if (splitUserInput.length == 1) {
                System.out.println("Delete format error! Try the command: 'delete <task number>'");
                return;
            }

            int taskNumber = Integer.parseInt(splitUserInput[1]);

            if (taskNumber < 1 || taskNumber > tasksList.size()) {
                throw new IndexOutOfBoundsException();
            }

            Task taskToDelete = tasksList.remove(taskNumber - 1);
            System.out.println("Okay! I've deleted this task:");
            System.out.println(taskToDelete);
            System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
            saveTasks(tasksList);

        } catch (NumberFormatException e) {
            System.out.println("That's not a number! Try the command: 'delete <task number>'");

        } catch (IndexOutOfBoundsException e) {
            System.out.println("The task does not exist... yet");
        }
    }

    private static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasksList = new ArrayList<>();
        File file = new File(FILE_PATH);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                Task task;
                switch (parts[0]) {
                    case "T":
                        if (parts.length >= 3) {
                            task = new Todo(parts[2]);
                        } else {
                            System.out.println("Todo format is incomplete! Expected: 'todo <description>'");
                            continue;
                        }
                        break;
                    case "D":
                        if (parts.length >= 4) {
                            task = new Deadline(parts[2], parts[3]);
                        } else {
                            System.out.println("Deadline format is incomplete! Expected: 'deadline <name> /by <time>'");
                            continue;
                        }
                        break;
                    case "E":
                        if (parts.length >= 5) {
                            task = new Event(parts[2], parts[3], parts[4]);
                        } else {
                            System.out.println("Event format is incomplete! Expected: 'event <name> /from <start> /to <end>'");
                            continue;
                        }
                        break;
                    default:
                        continue;
                }

                if (parts[1].equals("1")) {
                    task.setDone(true);
                }

                tasksList.add(task);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        return tasksList;
    }

    private static void saveTasks(ArrayList<Task> tasksList) {
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) {
            boolean dirCreated = directory.mkdir();
            if (!dirCreated) {
                System.out.println("Error: Unable to create directory for storing tasks.");
                return;
            }
        }

        try (FileWriter fileWriter = new FileWriter(FILE_PATH)) {
            for (Task task : tasksList) {
                fileWriter.write(task.toFileString());
                fileWriter.write(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    private static void goodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    public static void main(String[] args) {
        Scanner userInputScanner = new Scanner(System.in);
        ArrayList<Task> tasksList = loadTasks();

        welcomeMessage();

        while (true) {
            String userInput = userInputScanner.nextLine().trim();
            String command = userInput.split(" ")[0].toLowerCase();

            switch (command) {
                case "bye":
                    goodbyeMessage();
                    return;

                case "list":
                    printTasks(tasksList);
                    break;

                case "add":
                    if (userInput.trim().equalsIgnoreCase("add")) {
                        System.out.println("Add format error! Try the command: 'add <task text>'");
                        break;
                    }

                    String taskText = userInput.substring(4).trim();
                    Task newTask = new Task(taskText);
                    addTask(tasksList, newTask);
                    break;

                case "mark":
                    markTask(userInput, tasksList);
                    break;

                case "unmark":
                    unmarkTask(userInput, tasksList);
                    break;

                case "todo":
                    addTodo(userInput, tasksList);
                    break;

                case "event":
                    addEvent(userInput, tasksList);
                    break;

                case "deadline":
                    addDeadline(userInput, tasksList);
                    break;

                case "delete":
                    deleteTask(userInput, tasksList);
                    break;

                default:
                    System.out.println("This is an invalid command: " + userInput);
                    break;
            }

            System.out.println(DIVIDER);
        }
    }
}