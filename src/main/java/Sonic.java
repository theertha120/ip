import java.util.Scanner;

public class Sonic {
    private static int addTask(Task[] tasksList, int tasksCount, Task task) {
        tasksList[tasksCount] = task;
        tasksCount++;
        System.out.println("Got it, I have added this task:");
        System.out.println("   " + task.toString());
        System.out.println("Now you have " + tasksCount + " tasks in the list.");
        return tasksCount;
    }

    public static void main(String[] args) {
        String logo = """
        SSSSS  OOOOO  N   N  III  CCCCC 
        S      O   O  NN  N   I   C     
         SSS   O   O  N N N   I   C     
            S  O   O  N  NN   I   C     
        SSSSS  OOOOO  N   N  III  CCCCC""";


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
                        System.out.println((i + 1) + "." + tasksList[i].toString());
                    }
                }

            } else if (userInput.toLowerCase().startsWith("add")) {
                String taskText = userInput.substring(4);
                Task newTask = new Task(taskText);
                tasksCount = addTask(tasksList, tasksCount, newTask);

            } else if (userInput.toLowerCase().startsWith("mark")) {
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

            } else if (userInput.toLowerCase().startsWith("unmark")) {
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

            } else if (userInput.toLowerCase().startsWith("todo")){
                String taskText = userInput.substring(5);

                Todo newTodo = new Todo(taskText);
                tasksCount = addTask(tasksList, tasksCount, newTodo);

            } else if (userInput.toLowerCase().startsWith("event")) {
                String[] fromSplit = userInput.split(" /from ");
                String[] toSplit = fromSplit[1].split(" /to ");

                String eventName = fromSplit[0].substring(6).trim();
                String startTime = toSplit[0].trim();
                String endTime = toSplit[1].trim();

                Event newEvent = new Event(eventName, startTime, endTime);
                tasksCount = addTask(tasksList, tasksCount, newEvent);

            } else if (userInput.toLowerCase().startsWith("deadline")) {
                String[] userInputSplit = userInput.split(" /by ");

                String deadlineName = userInputSplit[0].substring(9).trim();
                String endTime = userInputSplit[1].trim();

                Deadline newDeadline = new Deadline(deadlineName, endTime);
                tasksCount = addTask(tasksList, tasksCount, newDeadline);

            } else {
                System.out.println("Unknown command: " + userInput);
            }

            System.out.println(line);
        }

    }
}
