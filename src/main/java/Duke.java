/**
 * CS2113T Semester 2 AY19/20
 * Individual Project
 * <p>
 * Project Duke is a educational software project designed to take you through
 * the steps of building a small software incrementally,
 * while applying as many Java and SE techniques as possible along the way.
 * <p>
 * The project aims to build a product named Duke, a Personal Assistant Chatbot that
 * helps a person to keep track of various things. The name Duke was chosen as a placeholder name,
 * in honor of Duke, the Java Mascot. You may give it any other name and personality you wish.
 *
 * @author: Tan Zheng Fu Justin
 */

import java.io.File;
import java.util.List;

public class Duke {

    public static final int DUKE_COMMAND = 0;
    public static final File SAVE_FILE = new File ("data/duke.txt");

    private Storage myTasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        myTasks = new Storage();
        File saveFile = FileSaver.makeNewSaveFile();
        FileLoader.loadFile(myTasks, saveFile);
        Printer.printStart();
    }

    public void run(File saveFile) {
        while (true) {
            String fullCommand = Ui.readFromUser();
            List<String> commands = Parser.parseCommand(fullCommand);
            String command = commands.get(DUKE_COMMAND);

            try {
                Command c = Parser.whatCommand(command);
                c.execute(myTasks, saveFile, commands, command);
            } catch (UnknownCommandException e) {
                Printer.printUnknownCommandError(command);
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run(SAVE_FILE);
    }
}
