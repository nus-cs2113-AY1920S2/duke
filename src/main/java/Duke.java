import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import Parser.ParseCommand;
import Storage.FileOperation;
import Ui.TextUi;
import Exception.*;
import TaskList.*;

public class Duke {

    private FileOperation storage;
    private TextUi ui;
    private ParseCommand command;
    private ArrayList<Task> taskList;

    public Duke(String filepath) {
        ui = new TextUi();
        storage = new FileOperation(filepath);

        try {
            taskList = storage.loadTaskList();
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            taskList = new ArrayList<>();
        }
    }

    public void run() {
        ui.printWelcomeMsg();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            try {
                command = new ParseCommand(fullCommand);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (IOException e) {
                ui.printIOError();
            } catch (DukeException | NullPointerException e){
                ui.printFormatError();
            }
        }
        ui.printExitMsg();
    }

    public static void main(String[] args){
        new Duke("tasksData.txt").run();
    }
}
