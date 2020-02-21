package chatty;

import chatty.exception.ChattyChatBotException;
import chatty.storage.Storage;
import chatty.task.Deadline;
import chatty.task.Event;
import chatty.task.Task;
import chatty.task.TaskList;
import chatty.task.ToDo;
import chatty.ui.Ui;

import java.util.Optional;
import java.util.Scanner;

import static chatty.util.Constants.AT_STRING;
import static chatty.util.Constants.BYE_STRING;
import static chatty.util.Constants.BY_STRING;
import static chatty.util.Constants.DEADLINE_STRING;
import static chatty.util.Constants.DELETE_STRING;
import static chatty.util.Constants.DONE_STRING;
import static chatty.util.Constants.EVENT_STRING;
import static chatty.util.Constants.LIST_STRING;
import static chatty.util.Constants.SPACE_SEPARATOR;
import static chatty.util.Constants.TODO_STRING;

public class ChattyChatBot {

    private static final Scanner SCANNER = new Scanner(System.in);

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public ChattyChatBot() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = new TaskList();
    }

    public static void main(String[] args) {
        new ChattyChatBot().run();
    }

    public void run() {
        ui.sendWelcomeMessage();
        ui.sendReadingTaskMessage();
        if (storage.readDataFromFile(taskList)) {
            ui.sendReadTaskSuccessMessage();
        } else {
            ui.sendReadTaskFailMessage();
        }
        ui.listAllTasks(taskList);
        ui.sendLineBreak();

        String userInput;
        do {
            userInput = SCANNER.nextLine();
            ui.sendLineBreak();
            // Solution below adapted from: https://stackoverflow
            // .com/questions/5067942/what-is-the-best-way-to-extract-the-first-word-from-a-string-in-java
            String[] array = userInput.split(SPACE_SEPARATOR, 2);
            String action = array[0];

            switch (action) {
            case LIST_STRING:
                ui.listAllTasks(taskList);
                break;
            case DONE_STRING:
                try {
                    taskList.markTaskAsDone(array[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.sendDonePrompt();
                }
                break;
            case TODO_STRING:
                try {
                    addToDoTask(array[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.sendTodoPrompt();
                }
                break;
            case DEADLINE_STRING:
                try {
                    addDeadlineTask(array[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.sendDeadlineTimePrompt();
                } catch (ChattyChatBotException e) {
                    ui.sendSpecifyDeadlinePrompt();
                }
                break;
            case EVENT_STRING:
                try {
                    addEventTask(array[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.sendEventPrompt();
                } catch (ChattyChatBotException e) {
                    ui.sendSpecifyEventTimePrompt();
                }
                break;
            case DELETE_STRING:
                try {
                    deleteTask(array[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.sendDeletePrompt();
                }
                break;
            case BYE_STRING:
                if (storage.saveDataToFile(taskList)) {
                    ui.sendSaveTaskSuccessMessage();
                } else {
                    ui.sendSaveTaskFailMessage();
                }
                ui.sendByeMessage();
                break;
            default:
                ui.sendDefaultResponse();
            }

            ui.sendLineBreak();
        } while (!userInput.equals(BYE_STRING));
    }

    private void addToDoTask(String description) {
        ToDo newToDoTask = new ToDo(description.trim());
        taskList.addTask(newToDoTask);
        ui.sendTaskAddedMessage(newToDoTask, taskList.getTotalTaskNum());
    }

    private void addDeadlineTask(String inputStr) throws ChattyChatBotException {
        try {
            String[] array = inputStr.split(BY_STRING);
            Deadline newDeadlineTask = new Deadline(array[0].trim(), array[1].trim());
            taskList.addTask(newDeadlineTask);
            ui.sendTaskAddedMessage(newDeadlineTask, taskList.getTotalTaskNum());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ChattyChatBotException();
        }
    }

    private void addEventTask(String inputStr) throws ChattyChatBotException {
        try {
            String[] array = inputStr.split(AT_STRING);
            Event newEventTask = new Event(array[0].trim(), array[1].trim());
            taskList.addTask(newEventTask);
            ui.sendTaskAddedMessage(newEventTask, taskList.getTotalTaskNum());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ChattyChatBotException();
        }
    }

    private void deleteTask(String indexStr) {
        try {
            int taskIdx = Integer.parseInt(indexStr);
            Optional<Task> taskOptional = taskList.deleteTask(taskIdx - 1);
            if (taskOptional.isPresent()) {
                ui.sendTaskDeletedMessage(taskOptional.get());
            } else {
                ui.sendTaskNumberOutOfBoundMessage();
            }
        } catch (NumberFormatException e) {
            ui.sendWrongTaskNumberFormatMessage();
        }
    }
}
