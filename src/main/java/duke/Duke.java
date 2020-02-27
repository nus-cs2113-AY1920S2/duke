package duke;

import duke.asset.IllegalDukeException;
import duke.asset.Storage;
import duke.asset.Ui;
import duke.commands.Command;
import duke.parser.Parser;
import duke.tasks.Task;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
/**
 *Hello, World!
 *The program implements an application .
 *that keeps a list of Tasks
 *
 *
 *
 *
 *
 * @author  Nizar Mohamed
 * @version 1.0
 * @since   2020-02-21
 */

public class Duke{
    private Storage storage;
    private Ui ui;
    private ArrayList<Task> l1;

    public Duke(String filePath) throws IOException {
        this.ui = new Ui();
        try {
            this.storage = new Storage(filePath);
            this.l1=storage.loadFile();
        } catch (IOException e) {
            this.ui.showLoadingError();
            this.l1 = new ArrayList<>();
            this.storage = new Storage(ui);
        }
    }
/**
 * This method runs continuously, asking user for inputs
 * until user input 'bye'
 */
    public void run(){
        int status = 1;
        this.ui.printWelcomeMessage();
        while (status == 1) {
            try {
                String input = this.ui.getUserIn();
                this.ui.printLine();
                Command command = Parser.parse(input);
                command.execute(this.l1, this.ui, this.storage);
                status=command.getStatus();
            }catch(IllegalDukeException | FileNotFoundException e ){
                this.ui.printError(e.getMessage());
            }finally{
                this.ui.printLine();
            }
        }
        this.ui.close();
    }

    public static void main(String[] args) throws IOException {
        try {
            new Duke("data/duke.txt").run();
        } catch(FileNotFoundException e){
            System.err.println(e.getMessage());
        }
    }
}

