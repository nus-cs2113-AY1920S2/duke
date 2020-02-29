package chatty;

import chatty.command.ByeCommand;
import chatty.command.Command;
import chatty.command.DateCommand;
import chatty.command.DeadlineCommand;
import chatty.command.DeleteCommand;
import chatty.command.DoneCommand;
import chatty.command.EventCommand;
import chatty.command.ListCommand;
import chatty.command.TodoCommand;
import chatty.exception.ChattyChatBotException;
import chatty.parser.Parser;
import chatty.storage.Storage;
import chatty.task.Deadline;
import chatty.task.Event;
import chatty.task.Task;
import chatty.task.TaskList;
import chatty.task.ToDo;
import chatty.ui.Ui;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import static chatty.util.Constants.BYE_STRING;

public class ChattyChatBot {

    private static final Scanner SCANNER = new Scanner(System.in);

    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    public ChattyChatBot() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = new TaskList();
        this.parser = new Parser();
    }

    public static void main(String[] args) {
        new ChattyChatBot().run();
    }

    public void run() {
        ui.sendLineBreak();
        ui.sendWelcomeMessage();
        ui.sendLineBreak();

        ui.sendReadingTaskMessage();
        if (storage.readDataFromFile(taskList)) {
            ui.sendReadTaskSuccessMessage();
        } else {
            ui.sendReadTaskFailMessage();
        }

        ui.listAllTasks(taskList);

        String userInput;
        do {
            userInput = SCANNER.nextLine();
            ui.sendLineBreak();
            try {
                Command command = parser.parseCommand(userInput);
                if (command instanceof ListCommand) {
                    ui.listAllTasks(taskList);
                } else if (command instanceof DoneCommand) {
                    Task doneTask = taskList.markTaskAsDone(((DoneCommand) command).getIdx());
                    ui.sendTaskDoneMessage(doneTask);
                } else if (command instanceof TodoCommand) {
                    ToDo toDo = new ToDo(((TodoCommand) command).getDescription());
                    taskList.addTask(toDo);
                    ui.sendTaskAddedMessage(toDo, taskList.getTotalTaskNum());
                } else if (command instanceof DeadlineCommand) {
                    Deadline deadline = new Deadline(((DeadlineCommand) command).getDescription(),
                            ((DeadlineCommand) command).getDateTime());
                    taskList.addTask(deadline);
                    ui.sendTaskAddedMessage(deadline, taskList.getTotalTaskNum());
                } else if (command instanceof EventCommand) {
                    Event event = new Event(((EventCommand) command).getDescription(),
                            ((EventCommand) command).getStartTime(), ((EventCommand) command).getEndTime());
                    taskList.addTask(event);
                    ui.sendTaskAddedMessage(event, taskList.getTotalTaskNum());
                } else if (command instanceof DeleteCommand) {
                    Task deletedTask = taskList.deleteTask(((DeleteCommand) command).getIdx());
                    ui.sendTaskDeletedMessage(deletedTask);
                } else if (command instanceof DateCommand) {
                    TaskList tasksOnDate = taskList.getTasksOnDate(((DateCommand) command).getDateTime());
                    ui.listTasksOnDate(tasksOnDate, ((DateCommand) command).getDateTime());
                } else if (command instanceof ByeCommand) {
                    if (storage.saveDataToFile(taskList)) {
                        ui.sendSaveTaskSuccessMessage();
                    } else {
                        ui.sendSaveTaskFailMessage();
                    }
                    ui.sendByeMessage();
                }
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
            }
            ui.sendLineBreak();
        } while (!userInput.equals(BYE_STRING));
    }
}
