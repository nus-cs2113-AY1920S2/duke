package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

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
        try {
            Ui.welcomeMessage();
            ui.getNextCommand();
            while(!ui.getCommand().equals("bye")){
                Ui.printDividingLine();
                Parser.executeCommand(ui.getCommand(),tasks);
                ui.getNextCommand();
            }
            storage.writeFile(tasks);
            Ui.exitMessage();
        }catch (FileNotFoundException e){
            System.out.println("File can not be found!");
        }catch (IOException e){
            System.out.println("Something goes wrong.");
        }
    }

    public TaskList getTasks() {
        return tasks;
    }

}
