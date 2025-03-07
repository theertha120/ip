package sonic.parser;

import sonic.storage.Storage;
import sonic.tasklist.TaskList;
import sonic.ui.Ui;

/**
 * Parses user input, validates commands, and delegates appropriate actions.
 */
public class Parser {
    private static final String DIVIDER = "__________________________________________________________";

    /**
     * Parses the user's command input and delegates appropriate actions to the task list,
     * UI, and storage.
     *
     * @param userInput The command input provided by the user.
     * @param taskList  The TaskList instance that holds and manages the tasks.
     * @param ui        The Ui instance used for displaying messages to the user.
     * @param storage   The Storage instance for saving task data.
     * @return Returns false if the user input is "bye", terminating the program, otherwise true.
     */
    public static boolean parseCommand(String userInput, TaskList taskList, Ui ui, Storage storage) {
        String[] inputParts = userInput.split(" ", 2);
        switch (inputParts[0]) {
        case "list":
            taskList.printTasks(ui);
            break;

        case "mark":
            if (inputParts.length < 2) {
                ui.showMessage("Mark format error! Try the command: 'mark <task number>'");
                break;
            }
            int markTaskNumber = Integer.parseInt(inputParts[1]);
            taskList.markTask(markTaskNumber, ui);
            storage.save(taskList.getTasks());
            break;

        case "unmark":
            if (inputParts.length < 2) {
                ui.showMessage("Unmark format error! Try the command: 'unmark <task number>'");
                break;
            }
            int unmarkTaskNumber = Integer.parseInt(inputParts[1]);
            taskList.unmarkTask(unmarkTaskNumber, ui);
            storage.save(taskList.getTasks());
            break;

        case "todo":
            if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                ui.showMessage("Todo needs a description! Try the command: 'todo <task description>'");
                break;
            }
            taskList.addTodo(inputParts[1], ui);
            storage.save(taskList.getTasks());
            break;

        case "event":
            if (!isValidEvent(inputParts, ui)) {
                break;
            }
            taskList.addEvent(inputParts[1], ui);
            storage.save(taskList.getTasks());
            break;

        case "deadline":
            if (!isValidDeadline(inputParts, ui)) {
                break;
            }
            taskList.addDeadline(inputParts[1], ui);
            storage.save(taskList.getTasks());
            break;

        case "delete":
            if (inputParts.length < 2) {
                ui.showMessage("Delete format error! Try the command: 'delete <task number>'");
                break;
            }
            int deleteTaskNumber = Integer.parseInt(inputParts[1]);
            taskList.deleteTask(deleteTaskNumber, ui);
            storage.save(taskList.getTasks());
            break;

        case "find":
            if (inputParts.length < 2) {
                ui.showMessage("Please provide a keyword for search.");
                break;
            }
            taskList.findTasks(inputParts[1], ui);
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

    /**
     * Validates the format of the 'event' command.
     *
     * @param inputParts The split command input.
     * @param ui The Ui instance for displaying messages.
     * @return True if the event command is valid, false otherwise.
     */
    private static boolean isValidEvent(String[] inputParts, Ui ui) {
        if (inputParts.length < 2 || !inputParts[1].contains(" /from ") || !inputParts[1].contains(" /to ")) {
            ui.showMessage("Event format error! Try the command: 'event <event description> /from <start> /to <end>'");
            return false;
        }
        return true;
    }

    /**
     * Validates the format of the 'deadline' command.
     *
     * @param inputParts The split command input.
     * @param ui The Ui instance for displaying messages.
     * @return True if the deadline command is valid, false otherwise.
     */
    private static boolean isValidDeadline(String[] inputParts, Ui ui) {
        if (inputParts.length < 2 || !inputParts[1].contains(" /by ")) {
            ui.showMessage("Deadline format error! Try the command: 'deadline <description> /by <time>'");
            return false;
        }
        return true;
    }
}
