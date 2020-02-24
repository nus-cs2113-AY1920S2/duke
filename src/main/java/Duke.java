import java.io.IOException;

public class Duke {

    static String currDir = System.getProperty("user.dir");
    private final static String filePath = currDir + "/data.txt";

    private Storage storage;
    private TaskList tasks;
    private static Ui ui;
    private Parser parser;

    public Duke(String filepath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        while (true) {
            String line = parser.getInput();
            String command;
            try {
                command = parser.getCommand(line);
            } catch (InvalidCommandException c) {
                parser.handleInvalidCommand();
                continue;
            }
            switch (command) {
            case "bye":
                ui.exitFromApp();
                break;
            case "list":
                tasks.listAllTasks();
                break;
            case "done":
                tasks.markTaskAsDone(line);
                try {
                    storage.saveToFile(tasks);
                } catch (IOException e) {
                    ui.showLoadingError();
                }
                break;
            case "delete":
                tasks.deleteTask(line);
                try {
                    storage.saveToFile(tasks);
                } catch (IOException e) {
                    ui.showLoadingError();
                }
                break;
            default:
                String taskInformation;
                try {
                    taskInformation = parser.getTaskInformation(line);
                } catch (IndexOutOfBoundsException b) {
                    parser.handleIndexOutOfBounds(command);
                    continue;
                }
                tasks.storeTaskIntoList(taskInformation, command);
                try {
                    storage.saveToFile(tasks);
                } catch (IOException e) {
                    ui.showLoadingError();
                }
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Duke(filePath).run();
    }
}
