package sonic.ui;

public  class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

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

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String toFileString() {
        return this.getClass().getSimpleName() + " | " + (isDone ? 1 : 0) + " | " + description;
    }
}





