package duke;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            taskList = new TaskList(storage.load(ui));
        } catch (DukeException e) {
            ui.printLine(e.getMessage());
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        takeCommands(ui, taskList, storage);
        ui.exit();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    private static void takeCommands(Ui ui, TaskList taskList, Storage storage) {
        String command;
        String input;
        boolean isExiting = false;
        while(!isExiting) {
            try {
                input = ui.readCommand();
                command = Parser.parse(input, taskList.getTaskList().size());
                isExiting = executeCommand(command, ui, taskList, storage);
            } catch (DukeException e) {
                ui.printFormattedString(e.getMessage());
            } catch (NumberFormatException e) {
                ui.printFormattedString(Ui.NUM_FORMAT_ERROR);
            }
        }
    }

    private static boolean executeCommand(String command, Ui ui, TaskList taskList, Storage storage) {
        String[] commandSubstrings = command.split("\\s+");
        switch (commandSubstrings[0]) {
        case "bye":
            return true;
        case "help":
            ui.helpUser();
            break;
        case "list":
            taskList.listTasks(ui);
            break;
        case "thanks":
            ui.printFormattedString(Ui.THANKS_RESPONSE);
            break;
        case "find":
            taskList.findTasks(ui, commandSubstrings[1]);
            break;
        case "done":
            taskList.checkOffTask(ui, Integer.parseInt(commandSubstrings[1]));
            storage.saveTaskstoDisk(ui, taskList);
            break;
        case "delete":
            taskList.deleteTask(ui, Integer.parseInt(commandSubstrings[1]));
            storage.saveTaskstoDisk(ui, taskList);
            break;
        case "todo":
            taskList.addTodo(command.substring(5), ui);
            storage.saveTaskstoDisk(ui, taskList);
            break;
        case "deadline":
            taskList.addDeadline(command.substring(9), ui);
            storage.saveTaskstoDisk(ui, taskList);
            break;
        case "event":
            taskList.addEvent(command.substring(6), ui);
            storage.saveTaskstoDisk(ui, taskList);
            break;
        default:
            break;
        }
        return false;
    }
}
