package duke;

import duke.commands.Command;
import duke.exceptions.*;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

/**
 * Project Duke.
 * <p> Duke is a personal text-based chat-bot application used for managing tasks.
 * The application takes in relevant user input to manage and maintain a tasklist.
 * </p>
 * <p>Duke is the public class responsible for creating the application and taking in the
 * relevant user input required by the user.
 * </p>
 *
 * @author dejunnn
 * @version CS2113 AY19/20 Sem 2 Individual Project - Duke
 */

public class Duke {

    /**
     * Creates the relevant objects required for the application to run.
     * It takes in user input and executes the respective commands.
     * <p>Exceptions caused by improper input and other errors are caught
     * and managed by the application.
     * </p>
     */

    public static void main(String[] args) {

        TaskList tasklist = new TaskList();
        UI ui = new UI();
        Storage storage = new Storage(tasklist, ui);

        ui.displayWelcomeMessage();

        while (!ui.isExitStatus()) {
            try {
                String input = ui.readInput();
                Command command = Parser.parseInput(input);
                command.execute(tasklist, ui, storage);
            } catch (InvalidCommandException e) {
                ui.displayInvalidCommandMessage();
            } catch (InvalidDeadlineException e) {
                ui.displayInvalidDeadlineMessage();
            } catch (InvalidEventException e) {
                ui.displayInvalidEventMessage();
            } catch (InvalidToDoException e) {
                ui.displayInvalidToDoMessage();
            } catch (InvalidFormatException e) {
                ui.displayInvalidFormatMessage();
            } catch (IndexOutOfBoundsException e) {
                ui.displayInvalidIndexMessage();
            }
        }
    }

}
