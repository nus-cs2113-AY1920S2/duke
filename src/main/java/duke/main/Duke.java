package duke.main;

/**
 * CS2113T Semester 2 AY19/20
 * Individual Project
 * <p>
 * Project duke.main.Duke is a educational software project designed to take you through
 * the steps of building a small software incrementally,
 * while applying as many Java and SE techniques as possible along the way.
 * <p>
 * The project aims to build a product named duke.main.Duke, a Personal Assistant Chatbot that
 * helps a person to keep track of various things. The name duke.main.Duke was chosen as a placeholder name,
 * in honor of duke.main.Duke, the Java Mascot. You may give it any other name and personality you wish.
 *
 * @author: Tan Zheng Fu Justin
 */
import duke.commands.Command;
import duke.exceptions.UnknownCommandException;
import duke.filemanager.FileLoader;
import duke.filemanager.FileSaver;
import duke.parser.Parser;
import duke.printer.Printer;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.File;
import java.util.List;

public class Duke {
    public static final int DUKE_COMMAND = 0;
    public static final File SAVE_FILE = new File ("data/duke.txt");
    public static boolean isNewUser;

    private Storage myTasks;
    private Ui ui;

    public Duke() {
        isNewUser = true;
        ui = new Ui();
        myTasks = new Storage();
        File saveFile = FileSaver.makeNewSaveFile();
        FileLoader.loadFile(myTasks, saveFile);
        Printer.printStart(isNewUser);
    }

    /**
     * Runs the program.
     * The supplied param is where the save file will be at.
     * @param saveFile the file object of the save file.
     */
    @SuppressWarnings("InfiniteLoopStatement")
    public void run(File saveFile) {
        while (true) {
            String fullCommand = ui.readFromUser();
            List<String> commands = Parser.parseCommand(fullCommand);
            String command = commands.get(DUKE_COMMAND);
            try {
                Command c = Parser.whatCommand(command);
                assert c != null;
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
