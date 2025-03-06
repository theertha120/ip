package sonic.ui;

/**
 * Represents a Deadline task, a type of Task with a description and a due date.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Initializes a Deadline task with the given description and due date.
     * The task is initially marked as not done.
     *
     * @param description The description of the Deadline task.
     * @param by The due date of the Deadline task (in string format).
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = DateTime.formatInputDateTime(by);
    }

    /**
     * Returns a string representation of the Deadline task.
     * The format is "[D][StatusIcon] description (by: dueDate)".
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a string representation of the Deadline task in a format suitable for saving to a file.
     * The format is: "D | isDone (1 or 0) | description | by".
     *
     * @return A string suitable for saving the Deadline task to a file.
     */
    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
