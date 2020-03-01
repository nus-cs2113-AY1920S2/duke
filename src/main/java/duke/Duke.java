package duke;

import duke.commands.Command;
import duke.exceptions.*;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

public class Duke {

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
