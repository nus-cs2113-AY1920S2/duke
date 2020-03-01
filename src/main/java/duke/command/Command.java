package src.main.java.duke.command;

import src.main.java.Storage;
import src.main.java.TaskList;
import src.main.java.Ui;
import src.main.java.duke.exceptions.AlreadyDoneException;
import src.main.java.duke.exceptions.InvalidDeleteException;
import src.main.java.duke.exceptions.InvalidDoneException;

import java.io.IOException;

public abstract class Command{

    public boolean isExit() { return false; }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, InvalidDeleteException, InvalidDoneException, AlreadyDoneException;

//        do {
//            try {
//                String userInput = Ui.scanInput();
//                parse(userCommand);
//                Storage.writeToFile();
//            } catch (IndexOutOfBoundsException e) {
//                Ui.showError("OOPS!!! The description of " + userCommand[0] + " cannot be empty.");
//            } catch (InvalidDateException e) {
//                Ui.showError(InvalidDateException.getMessage());
//            } catch (InvalidCommandException e) {
//                Ui.showError(InvalidCommandException.getMessage());
//            } catch (InvalidDoneException e) {
//                Ui.showError(InvalidDoneException.getMessage());
//            } catch (AlreadyDoneException e) {
//                Ui.showError(AlreadyDoneException.getMessage("  " + taskList.get(Integer.parseInt(userCommand[1]) - 1).toString()));
//            }
//        } while (!userInput.equalsIgnoreCase("bye"));
//    }
}
