package duke;

import duke.command.Command;
import duke.excpetions.DukeException;

/**
 * This is the main class of the software. A duke object is a tool to list and manage tasks.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * A constructor which create a Duke from a file which always keeps track with users tasks
     * If this is the first time using Duke and there is not such a file.
     * The constructor will create a new one and set an empty task list.
     *
     * @param filePath the filePath of the file which Duke loads the data from when starts up
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try{
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasksList.txt").run();
    }

    /**
     * The main logic of the software. It will terminate when users type "bye", the exit command.
     * It will give an error message when the command is not acceptable.
     */
    public void run(){
        Ui.showWelcomeMessage();
        boolean isExit = false;
        while(!isExit){
            try{
                String fullCommand = ui.readCommand();
                Ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError();
            }finally{
                ui.showLine();
            }
        }
    }

    public TaskList getTasks() {
        return tasks;
    }

}
