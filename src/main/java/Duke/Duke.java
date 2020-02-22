package Duke;

import Duke.Exception.IllegalCommandException;
import Duke.Exception.IllegalDeleteException;
import Duke.Exception.IllegalDoneTaskException;
import Duke.Exception.IllegalTypeException;

import java.io.IOException;

import static Duke.Library.Feature.*;

public class Duke{

    public Duke(String filePath) {
    }

    public void run() throws IllegalCommandException, IOException, IllegalTypeException, IllegalDeleteException, IllegalDoneTaskException {
        displayWelcomeMessage();
        run();
        exitProgram();
    }

    public static void main(String[] args) throws IllegalTypeException, IllegalCommandException, IllegalDoneTaskException, IllegalDeleteException, IOException {
        new Duke("data/output.txt").run();
    }
}