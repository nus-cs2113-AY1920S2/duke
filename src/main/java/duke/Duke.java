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
 *Hello, World!<br>
 *The program implements an application .<br>
 *that keeps a list of Tasks<br>
 *
 * @author  Nizar Mohamed<br>
 * @version 1.0<br>
 * @since   2020-02-21<br>
 */

public class Duke{
    private Storage storage;
    private Ui ui;
    private ArrayList<Task> l1;
    public static final String FILEPATH ="data/duke.txt";

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
 * This method runs continuously, asking user for inputs<br>
 * until user input 'bye'<br>
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
                this.ui.promptUser(e.getMessage());
            }finally{
                this.ui.printLine();
            }
        }
        this.ui.close();
    }

    /**
     * This is the main method for Duke.<br>
     * @param args This is the argument enter (if any) by User when running Duke.<br>
     * @throws IOException if the data directory is missing.<br>
     */
    public static void main(String[] args) throws IOException {
        try {
            new Duke(FILEPATH).run();
        } catch(FileNotFoundException e){
            System.err.println(e.getMessage());
        }
    }
}

