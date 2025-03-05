package sonic.ui;

public class Parser {
    private static final String DIVIDER = "__________________________________________________________";
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
