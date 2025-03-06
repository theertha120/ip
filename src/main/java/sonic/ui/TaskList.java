package sonic.ui;

import java.util.ArrayList;

/**
 * Represents a list of tasks and provides methods to interact with the list.
 * Tasks can be marked as done, added, deleted, or searched.
 */
public class TaskList {
    private final ArrayList<Task> TasksList;

    public TaskList() {
        this.TasksList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.TasksList = tasks;
    }

    /**
     * Prints all tasks in the list to the user.
     *
     * @param ui The Ui instance for displaying messages
     */
    public void printTasks(Ui ui) {
        if (TasksList.isEmpty()) {
            ui.showMessage("No tasks in your list right now, you are free!");
        } else {
            ui.showMessage("Here are the tasks in your list:");
            for (int i = 0; i < TasksList.size(); i++) {
                ui.showMessage((i + 1) + ". " + TasksList.get(i));
            }
        }
    }

    /**
     * Marks a task as completed based on user input.
     *
     * @param userInput The input from the user, where the second part is the task number
     * @param ui The Ui instance for displaying messages
     */
    public void markTask(String[] userInput, Ui ui) {
        try {
            if (userInput.length < 2) {
                ui.showMessage("Mark format error! Try the command: 'mark <task number>'");
                return;
            }

            int taskNumber = Integer.parseInt(userInput[1]);

            if (taskNumber < 1 || taskNumber > TasksList.size()) {
                throw new IndexOutOfBoundsException();
            }

            Task taskToMark = TasksList.get(taskNumber - 1);
            taskToMark.setDone(true);
            ui.showMessage("Nice! I've marked this task as done:\n" + taskToMark);

        } catch (NumberFormatException e) {
            ui.showMessage("That's not a number! Try the command: 'mark <task number>'");
        } catch (IndexOutOfBoundsException e) {
            ui.showMessage("The task does not exist");
        }
    }

    /**
     * Marks a task as not completed based on user input.
     *
     * @param userInput The input from the user, where the second part is the task number.
     * @param ui The Ui instance for displaying messages.
     */
    public void unmarkTask(String[] userInput, Ui ui) {
        try {
            if (userInput.length < 2) {
                ui.showMessage("Unmark format error! Try the command: 'unmark <task number>'");
                return;
            }

            int taskNumber = Integer.parseInt(userInput[1]);

            if (taskNumber < 1 || taskNumber > TasksList.size()) {
                throw new IndexOutOfBoundsException();
            }

            Task taskToUnmark = TasksList.get(taskNumber - 1);
            taskToUnmark.setDone(false);
            ui.showMessage("Ok, I've marked this task as not done yet:\n" + taskToUnmark);

        } catch (NumberFormatException e) {
            ui.showMessage("That's not a number! Try the command: 'unmark <task number>'");
        } catch (IndexOutOfBoundsException e) {
            ui.showMessage("The task does not exist");
        }
    }

    /**
     * Adds a new Todo task to the list.
     *
     * @param userInput The input from the user, where the second part is the todo task description.
     * @param ui The Ui instance for displaying messages.
     */
    public void addTodo(String[] userInput, Ui ui) {
        try {
            if (userInput.length < 2 || userInput[1].trim().isEmpty()) {
                throw new IllegalArgumentException();
            }

            Todo newTodo = new Todo(userInput[1]);
            addTask(newTodo, ui);
        } catch (IllegalArgumentException e) {
            ui.showMessage("Todo needs a description! Try the command: 'todo <task description>'");
        }
    }

    /**
     * Adds a new Event task to the list.
     *
     * @param userInput The input from the user, where the second part contains event details.
     * @param ui The Ui instance for displaying messages.
     */
    public void addEvent(String[] userInput, Ui ui) {
        try {
            if (userInput.length < 2 || !userInput[1].contains(" /from ") || !userInput[1].contains(" /to ")) {
                throw new IllegalArgumentException();
            }

            String[] fromSplit = userInput[1].split(" /from ");
            String[] toSplit = fromSplit[1].split(" /to ");

            if (fromSplit[0].trim().isEmpty() || toSplit[0].trim().isEmpty() || toSplit[1].trim().isEmpty()) {
                throw new IllegalArgumentException();
            }

            Event newEvent = new Event(fromSplit[0].trim(), toSplit[0].trim(), toSplit[1].trim());
            addTask(newEvent, ui);
        } catch (IllegalArgumentException e) {
            ui.showMessage("Event format entered wrong! Try the command: 'event <event description> /from <start> /to <end>'");
        }
    }

    /**
     * Adds a new Deadline task to the list.
     *
     * @param userInput The input from the user, where the second part contains deadline details.
     * @param ui The Ui instance for displaying messages.
     */
    public void addDeadline(String[] userInput, Ui ui) {
        try {
            if (userInput.length < 2 || !userInput[1].contains(" /by ")) {
                throw new IllegalArgumentException();
            }

            String[] userInputSplit = userInput[1].split(" /by ");

            if (userInputSplit[0].trim().isEmpty() || userInputSplit[1].trim().isEmpty()) {
                throw new IllegalArgumentException();
            }

            Deadline newDeadline = new Deadline(userInputSplit[0].trim(), userInputSplit[1].trim());
            addTask(newDeadline, ui);
        } catch (IllegalArgumentException e) {
            ui.showMessage("Deadline format error! Try the command: 'deadline <deadline description> /by <time>'");
        }
    }

    /**
     * Deletes a task from the list based on user input.
     *
     * @param inputParts The input from the user, where the second part is the task number to delete.
     * @param ui The Ui instance for displaying messages.
     */
    public void deleteTask(String[] inputParts, Ui ui) {
        try {
            if (inputParts.length < 2) {
                throw new IllegalArgumentException("Delete format error! Try the command: 'delete <task number>'");
            }

            int taskNumber = Integer.parseInt(inputParts[1]);

            if (taskNumber < 1 || taskNumber > TasksList.size()) {
                throw new IndexOutOfBoundsException("The task number is out of range. Please enter a valid task number.");
            }

            Task taskToDelete = TasksList.remove(taskNumber - 1);
            ui.showMessage("Okay! I've deleted this task:");
            ui.showMessage(taskToDelete.toString());
            ui.showMessage("Now you have " + TasksList.size() + " tasks in the list.");

        } catch (NumberFormatException e) {
            ui.showMessage("Invalid input! Task number must be a valid integer. Try the command: 'delete <task number>'");
        } catch (IndexOutOfBoundsException e) {
            ui.showMessage("The task number is out of range. Please enter a valid task number.");
        } catch (IllegalArgumentException e) {
            ui.showMessage(e.getMessage());
        }
    }

    /**
     * Finds tasks in the list that match the given keyword.
     *
     * @param userInput The input from the user, where the second part is the keyword to search for.
     * @param ui The Ui instance for displaying messages.
     */
    public void findTasks(String[] userInput, Ui ui) {
        try {
            if (userInput.length < 2 || userInput[1].trim().isEmpty()) {
                throw new IllegalArgumentException();
            }

            String keyword = userInput[1].toLowerCase();
            ArrayList<Task> matchingTasks = new ArrayList<>();

            for (Task task : TasksList) {
                if (task.getDescription().toLowerCase().contains(keyword)) {
                    matchingTasks.add(task);
                }
            }

            if (matchingTasks.isEmpty()) {
                ui.showMessage("No matching tasks found for: " + keyword);
            } else {
                ui.showMessage("Here are the matching tasks in your list:");
                for (int i = 0; i < matchingTasks.size(); i++) {
                    ui.showMessage((i + 1) + ". " + matchingTasks.get(i));
                }
            }

        } catch (IllegalArgumentException e) {
            ui.showMessage("Please provide a keyword for search. Try the command: 'find <keyword>'");
        }
    }

    /**
     * Adds a task to the list and notifies the user.
     *
     * @param task The task to add.
     * @param ui The Ui instance for displaying messages.
     */
    private void addTask(Task task, Ui ui) {
        TasksList.add(task);
        ui.showMessage("Got it, I have added this task:");
        ui.showMessage("   " + task.toString());
        ui.showMessage("Now you have " + TasksList.size() + " tasks in the list.");
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return TasksList;
    }
}