package sonic.ui;

/**
 * Represents a task with a description and a status indicating whether it is done or not.
 * It provides methods to get the status, description, and to serialize the task for saving.
 */
public  class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes a Task with a given description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a status icon representing whether the task is done or not.
     * The icon is "X" if the task is done, and " " if it is not done.
     *
     * @return The status icon of the task ("X" or " ").
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Returns a string representation of the task, including its status and description.
     * The format is: "[StatusIcon] description".
     *
     * @return A string representation of the task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns a string representation of the task in a format suitable for saving to a file.
     * The format is: "TaskType | isDone (1 or 0) | description".
     *
     * @return A string suitable for saving the task to a file.
     */
    public String toFileString() {
        return this.getClass().getSimpleName() + " | " + (isDone ? 1 : 0) + " | " + description;
    }
}



