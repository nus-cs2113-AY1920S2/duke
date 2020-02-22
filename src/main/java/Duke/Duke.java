package Duke;

import Duke.Exception.IllegalCommandException;
import Duke.Exception.IllegalDeleteException;
import Duke.Exception.IllegalDoneTaskException;
import Duke.Exception.IllegalTypeException;

import java.io.IOException;

import static Duke.Library.Feature.*;

public class Duke {

    public static void main(String[] args) throws IllegalCommandException, IOException, IllegalTypeException, IllegalDeleteException, IllegalDoneTaskException {
        System.out.println("Test879070");
        displayWelcomeMessage();
        run();
        exitProgram();
    }
}