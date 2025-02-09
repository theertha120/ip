import java.util.Scanner;

public class Sonic {
    public static final int MAX_TASKS = 100;
    public static final String DIVIDER = "__________________________________________________________";
    public static final String LOGO = """
         ____              _     \s
        / ___|  ___  _ __ (_) ___\s
        \\___ \\ / _ \\| '_ \\| |/ __|
         ___) | (_) | | | | | (__\s
        |____/ \\___/|_| |_|_|\\___|""";


    private static void welcomeMessage() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("I am Sonic! What can I do for you?");
        System.out.println(DIVIDER);
    }

    private static int addTask(Task[] tasksList, int tasksCount, Task task) {
        tasksList[tasksCount] = task;
        tasksCount++;
        System.out.println("Got it, I have added this task:");
        System.out.println("   " + task.toString());
        System.out.println("Now you have " + tasksCount + " tasks in the list.");
        return tasksCount;
    }

    private static void printTasks(int tasksCount, Task[] tasksList) {
        if (tasksCount == 0) {
            System.out.println("No tasks to show.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasksCount; i++) {
                System.out.println((i + 1) + "." + tasksList[i].toString());
            }
        }
    }

    private static void markTask(String userInput, int tasksCount, Task[] tasksList) {
        String[] splitUserInput = userInput.split(" ");
        int taskNumber = Integer.parseInt(splitUserInput[1]);

        if (taskNumber >= 1 && taskNumber <= tasksCount) {
            Task taskToMark = tasksList[taskNumber - 1];
            taskToMark.setDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(taskToMark);
        } else {
            System.out.println("Invalid task number!");
        }
    }

    private static void unmarkTask(String userInput, int tasksCount, Task[] tasksList) {
        String[] splitUserInput = userInput.split(" ");
        int taskNumber = Integer.parseInt(splitUserInput[1]);

        if (taskNumber >= 1 && taskNumber <= tasksCount) {
            Task taskToUnmark = tasksList[taskNumber - 1];
            taskToUnmark.setDone(false);
            System.out.println("Ok, I've marked this task as not done yet:");
            System.out.println(taskToUnmark);
        } else {
            System.out.println("Invalid task number!");
        }
    }

    private static int addTodo(String userInput, int tasksCount, Task[] tasksList) {
        String taskText = userInput.substring(5).trim();

        Todo newTodo = new Todo(taskText);
        return addTask(tasksList, tasksCount, newTodo);
    }

    private static int addEvent(String userInput, int tasksCount, Task[] tasksList) {
        String[] fromSplit = userInput.split(" /from ");
        String[] toSplit = fromSplit[1].split(" /to ");

        String eventName = fromSplit[0].substring(6).trim();
        String startTime = toSplit[0].trim();
        String endTime = toSplit[1].trim();

        Event newEvent = new Event(eventName, startTime, endTime);
        return addTask(tasksList, tasksCount, newEvent);
    }

    private static int addDeadline(String userInput, int tasksCount, Task[] tasksList) {
        String[] userInputSplit = userInput.split(" /by ");
        String deadlineName = userInputSplit[0].substring(9).trim();
        String endTime = userInputSplit[1].trim();

        Deadline newDeadline = new Deadline(deadlineName, endTime);
        return addTask(tasksList, tasksCount, newDeadline);
    }

    private static void goodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    public static void main(String[] args) {
        Scanner userInputScanner = new Scanner(System.in);
        Task[] tasksList = new Task[MAX_TASKS];
        int tasksCount = 0;

        welcomeMessage();

        while (true) {
            String userInput = userInputScanner.nextLine().trim();
            String command = userInput.split(" ")[0].toLowerCase();

            switch (command) {
                case "bye":
                    goodbyeMessage();
                    return;

                case "list":
                    printTasks(tasksCount, tasksList);
                    break;

                case "add":
                    String taskText = userInput.substring(4).trim();
                    Task newTask = new Task(taskText);
                    tasksCount = addTask(tasksList, tasksCount, newTask);
                    break;

                case "mark":
                    markTask(userInput, tasksCount, tasksList);
                    break;

                case "unmark":
                    unmarkTask(userInput, tasksCount, tasksList);
                    break;

                case "todo":
                    tasksCount = addTodo(userInput, tasksCount, tasksList);
                    break;

                case "event":
                    tasksCount = addEvent(userInput, tasksCount, tasksList);
                    break;

                case "deadline":
                    tasksCount = addDeadline(userInput, tasksCount, tasksList);
                    break;

                default:
                    System.out.println("This is an invalid command: " + userInput);
                    break;
            }

            System.out.println(DIVIDER);
        }
    }
}