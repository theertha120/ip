package sonic;

import sonic.parser.Parser;
import sonic.storage.Storage;
import sonic.tasklist.TaskList;
import sonic.ui.Ui;

/**
 * Initializes the user interface (UI), storage, and task list.
 * It is the main class for the Sonic task management program.
 * It runs the main logic for interacting with the user and processing commands.
 */
public class Sonic {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Initializes the Sonic application with the specified file path.
     * It sets up the UI, storage, and attempts to load tasks from the storage file.
     * If an error occurs while loading the tasks, an error message is shown and an empty task list is created.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Sonic(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the Sonic application.
     * It repeatedly displays the welcome message, reads user input,
     * and processes commands until the user exits the program.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isRunning = true;
        while (isRunning) {
            String userInput = ui.readCommand();
            isRunning = Parser.parseCommand(userInput, tasks, ui, storage);
        }
    }

    public static void main(String[] args) {
        Sonic sonic = new Sonic("./data/sonic.txt");
        sonic.run();
    }
}
