import java.io.FileNotFoundException;
import java.util.ArrayList;
import Asset.IllegalDukeException;
import Asset.Storage;
import Asset.Ui;
import Commands.*;
import Parser.Parser;
import Tasks.*;

public class Duke {
    private Storage storage;
    private Ui ui;
    private ArrayList<Task> l1;

    public Duke(String filePath) throws FileNotFoundException {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.l1=storage.loadFile();
        } catch (FileNotFoundException e) {
            this.ui.showLoadingError();
            this.l1 = new ArrayList<>();
        }
    }
    public void run(){
        int status = 1;
        this.ui.printWelcomeMessage();
        while (status == 1) {
            try {
                String input = this.ui.getUserIn();
                Command command = Parser.parse(input);
                command.execute(this.l1, this.ui, this.storage);
                status=command.status;
            }catch(IllegalDukeException | FileNotFoundException e){
                this.ui.printError(e.getMessage());
            }
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        new Duke("data/duke.txt").run();
    }
}

