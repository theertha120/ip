package sonic.ui;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> TasksList;

    public TaskList() {
        this.TasksList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.TasksList = tasks;
    }

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

    private void addTask(Task task, Ui ui) {
        TasksList.add(task);
        ui.showMessage("Got it, I have added this task:");
        ui.showMessage("   " + task.toString());
        ui.showMessage("Now you have " + TasksList.size() + " tasks in the list.");
    }

    public ArrayList<Task> getTasks() {
        return TasksList;
    }
}