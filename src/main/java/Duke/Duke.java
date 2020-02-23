package Duke;

import Duke.Exception.IllegalCommandException;
import Duke.Exception.IllegalDeleteException;
import Duke.Exception.IllegalDoneTaskException;
import Duke.Exception.IllegalTypeException;

import java.io.IOException;

import static Duke.Library.Feature.*;

public class Duke{

    public static void main(String[] args) throws IllegalTypeException, IllegalCommandException, IllegalDoneTaskException, IllegalDeleteException, IOException {
        displayWelcomeMessage();
        run();
        exitProgram();
    }
}