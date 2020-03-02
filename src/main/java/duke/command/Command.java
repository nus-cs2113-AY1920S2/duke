package src.main.java.duke.command;

import src.main.java.Storage;
import src.main.java.TaskList;
import src.main.java.Ui;
import src.main.java.duke.exceptions.AlreadyDoneException;
import src.main.java.duke.exceptions.InvalidDeleteException;
import src.main.java.duke.exceptions.InvalidDoneException;
import src.main.java.duke.exceptions.NoTaskFoundException;

import java.io.IOException;

public abstract class Command{

    public boolean isExit() { return false; }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, InvalidDeleteException, InvalidDoneException, AlreadyDoneException, NoTaskFoundException;

}
