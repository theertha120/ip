package sonic.ui;

/**
 * Parses and handles user input commands.
 * It delegates actions to the TaskList and Ui classes, and manages task storage.
 */
public class Parser {
    private static final String DIVIDER = "__________________________________________________________";

    /**
     * Handles the user's command input and delegates appropriate actions to the task list,
     * UI, and storage. It supports commands for listing tasks, marking/unmarking tasks,
     * adding tasks, deleting tasks, finding tasks, and exiting the program.
     *
     * @param userInput The command input provided by the user.
     * @param taskList The TaskList instance that holds and manages the tasks.
     * @param ui The Ui instance used for displaying messages to the user.
     * @param storage The Storage instance for saving task data.
     * @return Returns false if the user input is "bye", terminating the program, otherwise true.
     */
    public static boolean handleCommand(String userInput, TaskList taskList, Ui ui, Storage storage) {
        String[] inputParts = userInput.split(" ", 2);
        switch (inputParts[0]) {
            case "list":
                taskList.printTasks(ui);
                break;
            case "mark":
                taskList.markTask(inputParts, ui);
                storage.save(taskList.getTasks());
                break;
            case "unmark":
                taskList.unmarkTask(inputParts, ui);
                storage.save(taskList.getTasks());
                break;
            case "todo":
                taskList.addTodo(inputParts, ui);
                storage.save(taskList.getTasks());
                break;
            case "event":
                taskList.addEvent(inputParts, ui);
                storage.save(taskList.getTasks());
                break;
            case "deadline":
                taskList.addDeadline(inputParts, ui);
                storage.save(taskList.getTasks());
                break;
            case "delete":
                taskList.deleteTask(inputParts, ui);
                storage.save(taskList.getTasks());
                break;
            case "find":
                taskList.findTasks(inputParts, ui);
                break;
            case "bye":
                ui.showMessage("Goodbye! Have a great day!");
                return false;
            default:
                ui.showMessage("Unknown command!");
        }
        System.out.println(DIVIDER);
        return true;
    }
}
