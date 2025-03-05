package sonic.ui;

public class Sonic {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

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

    public void run() {
        ui.showWelcomeMessage();
        boolean isRunning = true;
        while (isRunning) {
            String userInput = ui.readCommand();
            isRunning = Parser.handleCommand(userInput, tasks, ui, storage);
        }
    }

    public static void main(String[] args) {
        new Sonic("./data/sonic.txt").run();
    }
}
