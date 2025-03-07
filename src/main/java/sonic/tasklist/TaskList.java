package sonic.tasklist;

import sonic.task.Deadline;
import sonic.task.Event;
import sonic.task.Task;
import sonic.task.Todo;
import sonic.ui.Ui;

import java.util.ArrayList;

/**
 * Represents a list of tasks and provides methods to interact with the list.
 * Tasks can be marked as done, added, deleted, or searched.
 */
public class TaskList {
    private final ArrayList<Task> tasksList;

    public TaskList() {
        this.tasksList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasksList = tasks;
    }

    /**
     * Prints all tasks in the list to the user.
     *
     * @param ui The Ui instance for displaying messages
     */
    public void printTasks(Ui ui) {
        if (tasksList.isEmpty()) {
            ui.showMessage("No tasks in your list right now, you are free!");
        } else {
            ui.showMessage("Here are the tasks in your list:");
            for (int i = 0; i < tasksList.size(); i++) {
                ui.showMessage((i + 1) + ". " + tasksList.get(i));
            }
        }
    }

    /**
     * Marks a task as completed based on user input.
     *
     * @param taskNumber The task number to mark.
     * @param ui The Ui instance for displaying messages.
     */
    public void markTask(int taskNumber, Ui ui) {
        try {
            Task taskToMark = tasksList.get(taskNumber - 1);
            taskToMark.setDone(true);
            ui.showMessage("Nice! I've marked this task as done:\n" + taskToMark);

        } catch (IndexOutOfBoundsException e) {
            ui.showMessage("The task does not exist");
        }
    }

    /**
     * Marks a task as not completed based on user input.
     *
     * @param taskNumber The task number to unmark.
     * @param ui The Ui instance for displaying messages.
     */
    public void unmarkTask(int taskNumber, Ui ui) {
        try {
            Task taskToUnmark = tasksList.get(taskNumber - 1);
            taskToUnmark.setDone(false);
            ui.showMessage("Ok, I've marked this task as not done yet:\n" + taskToUnmark);

        } catch (IndexOutOfBoundsException e) {
            ui.showMessage("The task does not exist");
        }
    }

    /**
     * Adds a new Todo task to the list.
     *
     * @param description The description of the Todo task.
     * @param ui The Ui instance for displaying messages.
     */
    public void addTodo(String description, Ui ui) {
        Todo newTodo = new Todo(description);
        addTask(newTodo, ui);
    }

    /**
     * Adds a new Event task to the list.
     *
     * @param description The description of the Event task.
     * @param ui The Ui instance for displaying messages.
     */
    public void addEvent(String description, Ui ui) {
        String[] fromSplit = description.split(" /from ");
        String[] toSplit = fromSplit[1].split(" /to ");

        Event newEvent = new Event(fromSplit[0].trim(), toSplit[0].trim(), toSplit[1].trim());
        addTask(newEvent, ui);
    }

    /**
     * Adds a new Deadline task to the list.
     *
     * @param description The description of the Deadline task.
     * @param ui The Ui instance for displaying messages.
     */
    public void addDeadline(String description, Ui ui) {
        String[] userInputSplit = description.split(" /by ");
        Deadline newDeadline = new Deadline(userInputSplit[0].trim(), userInputSplit[1].trim());
        addTask(newDeadline, ui);
    }

    /**
     * Deletes a task from the list based on task number.
     *
     * @param taskNumber The task number to delete.
     * @param ui The Ui instance for displaying messages.
     */
    public void deleteTask(int taskNumber, Ui ui) {
        try {
            Task taskToDelete = tasksList.remove(taskNumber - 1);
            ui.showMessage("Noted, I've removed this task:\n" + taskToDelete);
        } catch (IndexOutOfBoundsException e) {
            ui.showMessage("The task does not exist");
        }
    }

    /**
     * Searches for tasks that match a keyword.
     *
     * @param keyword The search keyword.
     * @param ui The Ui instance for displaying messages.
     */
    public void findTasks(String keyword, Ui ui) {
        boolean found = false;

        ui.showMessage("Here are the tasks matching your search:");
        for (Task task : tasksList) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                ui.showMessage(task.toString());
                found = true;
            }
        }

        if (!found) {
            ui.showMessage("No tasks found matching your search.");
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     * @param ui The Ui instance for displaying messages.
     */
    private void addTask(Task task, Ui ui) {
        tasksList.add(task);
        ui.showMessage("Got it! I've added this task:\n" + task);
        ui.showMessage("Now you have " + tasksList.size() + " tasks in the list.");
    }

    public ArrayList<Task> getTasks() {
        return tasksList;
    }
}
