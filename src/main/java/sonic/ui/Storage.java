package sonic.ui;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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

    public void save(ArrayList<Task> tasks) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.toFileString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

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
