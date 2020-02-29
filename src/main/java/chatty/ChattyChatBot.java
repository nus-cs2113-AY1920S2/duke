package chatty;

import chatty.command.Command;
import chatty.exception.ChattyChatBotException;
import chatty.parser.Parser;
import chatty.storage.Storage;
import chatty.task.TaskList;
import chatty.ui.Ui;

import java.time.format.DateTimeParseException;

/**
 * Entry point of ChattyChatBot where the application is initialized.
 */
public class ChattyChatBot {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    /**
     * Constructor for ChattyChatBot.
     */
    public ChattyChatBot() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = new TaskList();
        this.parser = new Parser();
    }

    /**
     * Main method of the ChattyChatBot class.
     *
     * @param args Arguments passed in to the ChattyChatBot class.
     */
    public static void main(String[] args) {
        new ChattyChatBot().run();
    }

    /**
     * Runs the main program which involves multiple steps:
     * 1. Read tasks from file
     * 2. Interact with the user
     * 3. Save tasks to file
     */
    public void run() {
        ui.sendWelcomeMessage();
        ui.sendReadingTaskMessage();
        if (storage.readDataFromFile(taskList)) {
            ui.sendReadTaskSuccessMessage();
        } else {
            ui.sendReadTaskFailMessage();
        }
        ui.listAllTasks(taskList);

        boolean isBye = false;
        while (!isBye) {
            try {
                String fullCommand = ui.readCommand();
                ui.sendLineBreak();
                Command command = parser.parseCommand(fullCommand);
                command.execute(taskList, ui, storage);
                isBye = command.isBye();
            } catch (ChattyChatBotException e) {
                ui.sendDefaultResponse();
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.askForMoreDetails();
            } catch (IndexOutOfBoundsException e) {
                ui.sendTaskNumberOutOfBoundMessage();
            } catch (NumberFormatException e) {
                ui.sendWrongTaskNumberFormatMessage();
            } catch (DateTimeParseException e) {
                ui.sendWrongDateTimeFormatMessage();
            } catch (Exception e) {
                ui.sendUnexpectedExceptionMessage();
            } finally {
                ui.sendLineBreak();
            }
        }
    }
}
