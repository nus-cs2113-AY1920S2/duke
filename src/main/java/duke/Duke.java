package duke;

import duke.command.Command;
import duke.excpetions.DukeException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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

    public void run(){
        Ui.showWelcome();
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
