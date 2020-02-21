package chatty;

import chatty.exception.ChattyChatBotException;
import chatty.storage.Storage;
import chatty.task.Deadline;
import chatty.task.Event;
import chatty.task.Task;
import chatty.task.ToDo;
import chatty.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static chatty.util.Constants.ADDED_TASK_CONFIRMATION;
import static chatty.util.Constants.AT_STRING;
import static chatty.util.Constants.BYE_STRING;
import static chatty.util.Constants.BY_STRING;
import static chatty.util.Constants.DEADLINE_STRING;
import static chatty.util.Constants.DEFAULT_FILE_PATH;
import static chatty.util.Constants.DELETE_STRING;
import static chatty.util.Constants.DONE_STRING;
import static chatty.util.Constants.EVENT_STRING;
import static chatty.util.Constants.FALSE_STRING;
import static chatty.util.Constants.FILE_FIELD_SEPARATOR;
import static chatty.util.Constants.LIST_STRING;
import static chatty.util.Constants.MINIMUM_FIELD_NUM_FOR_EVENT_AND_DEADLINE;
import static chatty.util.Constants.MINIMUM_FIELD_NUM_FOR_TASK;
import static chatty.util.Constants.NEW_LINE;
import static chatty.util.Constants.SPACE_SEPARATOR;
import static chatty.util.Constants.TASK_SUMMARY_FIRST_HALF;
import static chatty.util.Constants.TASK_SUMMARY_SECOND_HALF;
import static chatty.util.Constants.TODO_STRING;
import static chatty.util.Constants.TRUE_STRING;

public class ChattyChatBot {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static String filePath;

    private Ui ui;
    private Storage storage;

    public ChattyChatBot() {
        this.ui = new Ui();
        this.storage = new Storage();
    }

    public static void main(String[] args) {
        new ChattyChatBot().run();
    }

    public void run() {
        ui.sendWelcomeMessage();
        List<Task> tasks = new ArrayList<>();
        ui.sendReadingTaskMessage();
        if (storage.readDataFromFile(tasks)) {
            ui.sendReadTaskSuccessMessage();
        } else {
            ui.sendReadTaskFailMessage();
        }
        ui.listAllTasks(tasks);
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
                ui.listAllTasks(tasks);
                break;
            case DONE_STRING:
                try {
                    markTaskAsDone(tasks, array[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.sendDonePrompt();
                }
                break;
            case TODO_STRING:
                try {
                    addToDoTask(tasks, array[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.sendTodoPrompt();
                }
                break;
            case DEADLINE_STRING:
                try {
                    addDeadlineTask(tasks, array[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.sendDeadlineTimePrompt();
                } catch (ChattyChatBotException e) {
                    ui.sendSpecifyDeadlinePrompt();
                }
                break;
            case EVENT_STRING:
                try {
                    addEventTask(tasks, array[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.sendEventPrompt();
                } catch (ChattyChatBotException e) {
                    ui.sendSpecifyEventTimePrompt();
                }
                break;
            case DELETE_STRING:
                try {
                    deleteTask(tasks, array[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.sendDeletePrompt();
                }
                break;
            case BYE_STRING:
                if (storage.saveDataToFile(tasks)) {
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

    private static void markTaskAsDone(List<Task> tasks, String indexStr) {
        try {
            int taskIdx = Integer.parseInt(indexStr);
            Task task = tasks.get(taskIdx - 1);
            task.markAsDone();
            System.out.println("Congratulations! You've successfully marked the following task as done:");
            System.out.println(task.toString());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid task number");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The number you entered does not match any task in your list");
        }
    }

    private static void addToDoTask(List<Task> tasks, String description) {
        ToDo newToDoTask = new ToDo(description.trim());
        tasks.add(newToDoTask);
        confirmAddTask(tasks, newToDoTask);
    }

    private static void addDeadlineTask(List<Task> tasks, String inputStr) throws ChattyChatBotException {
        try {
            String[] array = inputStr.split(BY_STRING);
            Deadline newDeadlineTask = new Deadline(array[0].trim(), array[1].trim());
            tasks.add(newDeadlineTask);
            confirmAddTask(tasks, newDeadlineTask);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ChattyChatBotException();
        }
    }

    private static void addEventTask(List<Task> tasks, String inputStr) throws ChattyChatBotException {
        try {
            String[] array = inputStr.split(AT_STRING);
            Event newEventTask = new Event(array[0].trim(), array[1].trim());
            tasks.add(newEventTask);
            confirmAddTask(tasks, newEventTask);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ChattyChatBotException();
        }
    }

    private static void confirmAddTask(List<Task> tasks, Task newTask) {
        System.out.println(ADDED_TASK_CONFIRMATION);
        System.out.println(newTask);
        System.out.println(TASK_SUMMARY_FIRST_HALF + tasks.size() + TASK_SUMMARY_SECOND_HALF);
    }

    private static void deleteTask(List<Task> tasks, String indexStr) {
        try {
            int taskIdx = Integer.parseInt(indexStr);
            Task task = tasks.get(taskIdx - 1);
            tasks.remove(taskIdx - 1);
            System.out.println("Successfully deleted the following task:");
            System.out.println(task.toString());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid task number");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The number you entered does not match any task in your list");
        }
    }


}
