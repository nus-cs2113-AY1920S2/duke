package Duke;

import Duke.Asset.IllegalDukeException;
import Duke.Asset.Storage;
import Duke.Asset.Ui;
import Duke.Commands.Command;
import Duke.Parser.Parser;
import Duke.Tasks.Task;
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

    public Duke(String filePath) throws FileNotFoundException, IOException {
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
            new Duke("data/duke.txt").run();
    }
}

