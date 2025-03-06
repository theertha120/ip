package sonic.ui;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the loading and saving of tasks to a file.
 * It is responsible for reading task data from a file and saving task data to a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Initializes the Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from the file specified by the file path.
     * If the file does not exist, an empty task list is returned.
     *
     * @throws FileNotFoundException If file does not exist.
     * @return An ArrayList of Task objects loaded from the file.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Task task = parseTask(line);
            if (task != null) {
                tasks.add(task);
            }
        }
        scanner.close();
        return tasks;
    }

    /**
     * Saves the provided list of tasks to the file specified by the file path.
     * If there is an issue with writing to the file, an error message is printed.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void save(ArrayList<Task> tasks) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.toFileString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Parses a line from the file into a Task object.
     * The line format is expected to be "Type | Done Status | Description | Additional Info".
     *
     * @param line The line to be parsed.
     * @return A Task object created from the parsed data, or null if the line is invalid.
     */
    private Task parseTask(String line) {
        String[] taskData = line.split(" \\| ");
        if (taskData.length < 3) {
            return null;
        }

        String type = taskData[0];
        boolean isDone = taskData[1].equals("1");
        String description = taskData[2];

        Task task = null;
        switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                if (taskData.length >= 4) {
                    task = new Deadline(description, taskData[3]);
                }
                break;
            case "E":
                if (taskData.length >= 5) {
                    task = new Event(description, taskData[3], taskData[4]);
                }
                break;
            default:
                return null;
        }

        if (task != null) {
            task.setDone(isDone);
        }

        return task;
    }
}
