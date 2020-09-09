package chatty;

import chatty.command.Command;
import chatty.exception.ChattyChatBotDateCommandException;
import chatty.exception.ChattyChatBotDeadlineCommandException;
import chatty.exception.ChattyChatBotDeleteCommandException;
import chatty.exception.ChattyChatBotDoneCommandException;
import chatty.exception.ChattyChatBotEventCommandException;
import chatty.exception.ChattyChatBotFindCommandException;
import chatty.exception.ChattyChatBotTodoCommandException;
import chatty.parser.Parser;
import chatty.storage.Storage;
import chatty.task.TaskList;
import chatty.ui.Ui;

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
            } catch (ChattyChatBotDoneCommandException e) {
                ui.sendDoneCommandExceptionMessage();
            } catch (ChattyChatBotTodoCommandException e) {
                ui.sendTodoCommandExceptionMessage();
            } catch (ChattyChatBotDeadlineCommandException e) {
                ui.sendDeadlineCommandExceptionMessage();
            } catch (ChattyChatBotEventCommandException e) {
                ui.sendEventCommandExceptionMessage();
            } catch (ChattyChatBotDeleteCommandException e) {
                ui.sendDeleteCommandExceptionMessage();
            } catch (ChattyChatBotFindCommandException e) {
                ui.sendFindCommandExceptionMessage();
            } catch (ChattyChatBotDateCommandException e) {
                ui.sendDateCommandExceptionMessage();
            } catch (IndexOutOfBoundsException e) {
                ui.sendTaskNumberOutOfBoundMessage();
            } catch (Exception e) {
                ui.sendDefaultResponse();
            } finally {
                ui.sendLineBreak();
            }
        }
    }
}
