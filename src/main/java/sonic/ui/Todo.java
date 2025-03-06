package sonic.ui;

/**
 * Represents a Todo task, a type of Task with a description.
 */
public class Todo extends Task {

    /**
     * Initializes a Todo task with the given description.
     * The task is initially marked as not done.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task.
     * The format is "[T][StatusIcon] description".
     *
     * @return A string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the Todo task in a format suitable for saving to a file.
     * The format is: "T | isDone (1 or 0) | description".
     *
     * @return A string suitable for saving the Todo task to a file.
     */
    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}