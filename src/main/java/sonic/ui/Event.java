package sonic.ui;

/**
 * Represents an Event task, a type of Task with a description, start time, and end time.
 */
public class Event extends Task {

    protected String start;
    protected String end;

    /**
     * Initializes an Event task with the description, start time, and end time.
     * The task is initially marked as not done.
     *
     * @param description The description of the Event task.
     * @param start The start time of the event (in string format).
     * @param end The end time of the event (in string format).
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = DateTime.formatInputDateTime(start);
        this.end = DateTime.formatInputDateTime(end);
    }

    /**
     * Returns a string representation of the Event task.
     * The format is "[E][StatusIcon] description (from: start to: end)".
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    /**
     * Returns a string representation of the Event task in a format suitable for saving to a file.
     * The format is: "E | isDone (1 or 0) | description | start | end".
     *
     * @return A string suitable for saving the Event task to a file.
     */
    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + start + " | " + end;
    }
}
