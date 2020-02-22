package duke.util;


import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.task.DummyTask;

import static duke.util.Constants.DATA_LOADED_SUCCESSFULLY_PROMPT;
import static duke.util.Constants.DATA_SAVED_SUCCESSFULLY_PROMPT;
import static duke.util.Constants.DEADLINE_COMMAND;
import static duke.util.Constants.DEADLINE_ICON;
import static duke.util.Constants.EVENT_ICON;
import static duke.util.Constants.LINE_DIVIDER;
import static duke.util.Constants.FIVE_SPACES;
import static duke.util.Constants.LOAD_DATA_FROM_FILE_PROMPT_FORMAT_STRING;
import static duke.util.Constants.SAVE_DATA_TO_FILE_PROMPT_FORMAT_STRING;
import static duke.util.Constants.SEVEN_SPACES;
import static duke.util.Constants.LIST_TASKS_PROMPT;
import static duke.util.Constants.DONE_TASK_PROMPT;
import static duke.util.Constants.ADD_TASK_PROMPT;
import static duke.util.Constants.ADD_OR_DELETE_TASK_POST_PROMPT;
import static duke.util.Constants.DELETE_TASKS_PROMPT;
import static duke.util.Constants.LIST_SINGLE_TASK_MESSAGE_FORMAT_STRING;
import static duke.util.Constants.TASK_ID_NOT_PROVIDED_OR_INVALID_ERROR_MESSAGE;
import static duke.util.Constants.TASK_DESCRIPTION_EMPTY_ERROR_MESSAGE;
import static duke.util.Constants.CRYING_FACE;
import static duke.util.Constants.DATA_FILE_PATH;
import static duke.util.Constants.FILE_NOT_FOUND_ERROR_MESSAGE;
import static duke.util.Constants.FILE_OPERATION_IO_ERROR_MESSAGE;
import static duke.util.Constants.YES_ICON;


public class TaskManager {
    private ArrayList<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
        loadDataFromFile(DATA_FILE_PATH);
    }

    public void listTasks() {
        System.out.println(LINE_DIVIDER);

        System.out.println(FIVE_SPACES + LIST_TASKS_PROMPT);
        int taskCount = tasks.size();
        for (int i = 0; i < taskCount; ++i) {
            System.out.printf(SEVEN_SPACES + LIST_SINGLE_TASK_MESSAGE_FORMAT_STRING, i, tasks.get(i));
        }
        System.out.println(LINE_DIVIDER);
    }

    public void addTask(String... args) {
        String taskDescription = args[0].trim();
        try {
            Task taskToAdd;
            if (args.length == 2) {
                taskToAdd = new Todo(taskDescription);
            } else if (args[2].equals(DEADLINE_COMMAND)) {
                taskToAdd = new Deadline(taskDescription, args[1]);
            } else {
                taskToAdd = new Event(taskDescription, args[1]);
            }
            tasks.add(taskToAdd);
            printAddTaskSuccessfulPrompt(taskToAdd);
        } catch (DukeException e) {
            printFormatErrorMsg(TASK_DESCRIPTION_EMPTY_ERROR_MESSAGE);
        }
    }

    private void printAddTaskSuccessfulPrompt(Task addedTask) {
        System.out.println(LINE_DIVIDER);
        System.out.println(FIVE_SPACES + ADD_TASK_PROMPT);
        System.out.println(SEVEN_SPACES + addedTask);
        System.out.printf(FIVE_SPACES + ADD_OR_DELETE_TASK_POST_PROMPT, tasks.size());
        System.out.println(LINE_DIVIDER);
    }

    public void printFormatErrorMsg(String formatErrorMessage) {
        System.out.println(LINE_DIVIDER);
        System.out.println(FIVE_SPACES + CRYING_FACE + formatErrorMessage);
        System.out.println(LINE_DIVIDER);
    }

    public void delTask(int taskId) {
        try {
            Task deletedTask = tasks.get(taskId);
            tasks.remove(taskId);
            printDeleteTaskSuccessfulPrompt(deletedTask);
        } catch (IndexOutOfBoundsException e) {
            printDeleteTaskErrorMsg();
        }
    }

    private void printDeleteTaskSuccessfulPrompt(Task currentTask) {
        System.out.println(LINE_DIVIDER);
        System.out.println(FIVE_SPACES + DELETE_TASKS_PROMPT);
        System.out.println(SEVEN_SPACES + currentTask);
        System.out.printf(FIVE_SPACES + ADD_OR_DELETE_TASK_POST_PROMPT, tasks.size());
        System.out.println(LINE_DIVIDER);
    }

    private void printDeleteTaskErrorMsg() {
        System.out.println(LINE_DIVIDER);
        System.out.println(FIVE_SPACES + CRYING_FACE + TASK_ID_NOT_PROVIDED_OR_INVALID_ERROR_MESSAGE);
        System.out.println(LINE_DIVIDER);
    }

    public void markAsDone(int taskId) {
        try {
            tasks.get(taskId).markAsDone();
            printMarkAsDoneSuccessfulPrompt(tasks.get(taskId));
        } catch (IndexOutOfBoundsException e) {
            printMarkAsDoneErrorMsg();
        }
    }

    private void printMarkAsDoneSuccessfulPrompt(Task currentTask) {
        System.out.println(LINE_DIVIDER);
        System.out.println(FIVE_SPACES + DONE_TASK_PROMPT);
        System.out.println(SEVEN_SPACES + currentTask);
        System.out.println(LINE_DIVIDER);
    }

    private void printMarkAsDoneErrorMsg() {
        System.out.println(LINE_DIVIDER);
        System.out.println(FIVE_SPACES + CRYING_FACE + TASK_ID_NOT_PROVIDED_OR_INVALID_ERROR_MESSAGE);
        System.out.println(LINE_DIVIDER);
    }

    // I know this function sucks, give me some time to think of a better one plz.
    public void loadDataFromFile(String filePath) {
        System.out.printf(FIVE_SPACES + LOAD_DATA_FROM_FILE_PROMPT_FORMAT_STRING, DATA_FILE_PATH);
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            String jsonStr = s.nextLine();
            s.close();

            List<DummyTask> taskList = extractDummyTasks(jsonStr);

            Task currentTask;
            for (DummyTask i : taskList) {
                try {
                    currentTask = convertDummyTaskToSpecificTask(i, i.getIcon());
                    if (i.getStatusIcon().equals(YES_ICON)) {
                        currentTask.markAsDone();
                    }
                    tasks.add(currentTask);
                } catch (DukeException e) {
                    System.out.println(TASK_DESCRIPTION_EMPTY_ERROR_MESSAGE);
                }
            }
            System.out.println(FIVE_SPACES + DATA_LOADED_SUCCESSFULLY_PROMPT);
        } catch (FileNotFoundException e) {
            System.out.println(FIVE_SPACES + CRYING_FACE + FILE_NOT_FOUND_ERROR_MESSAGE);
        }
    }

    private Task convertDummyTaskToSpecificTask(DummyTask task, String typeIcon) throws DukeException {
        Task convertedTask;
        switch (typeIcon) {
        case DEADLINE_ICON:
            convertedTask = new Deadline(task.getTaskDescription(), task.getByTime());
            break;
        case EVENT_ICON:
            convertedTask = new Event(task.getTaskDescription(), task.getAtTime());
            break;
        default:
            convertedTask = new Todo(task.getTaskDescription());
        }
        return convertedTask;
    }

    private List<DummyTask> extractDummyTasks(String jsonStr) {
        Type listType = new TypeToken<List<DummyTask>>(){}.getType();
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, listType);
    }

    public void saveDataToFile() {
        System.out.printf(FIVE_SPACES + SAVE_DATA_TO_FILE_PROMPT_FORMAT_STRING, DATA_FILE_PATH);
        try {
            Gson gson = new Gson();
            FileWriter fw = new FileWriter(DATA_FILE_PATH);
            String json = gson.toJson(tasks);
            fw.write(json);
            fw.flush();
            fw.close();
            System.out.println(FIVE_SPACES + DATA_SAVED_SUCCESSFULLY_PROMPT);
        } catch (IOException e) {
            System.out.println(FIVE_SPACES + CRYING_FACE + FILE_OPERATION_IO_ERROR_MESSAGE);
        }
    }
}
