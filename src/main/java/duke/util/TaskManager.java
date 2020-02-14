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
import duke.Duke;
import duke.exception.DukeException;
import duke.task.*;


import static duke.util.Constants.LINE_DIVIDER;
import static duke.util.Constants.FIVE_SPACES;
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

    /**
     * @param args todo: addTasks(description, "todo")
     *             deadline/event: addTasks(description, time, "deadline"/"event")
     */
    public void addTask(String... args) {
        System.out.println(LINE_DIVIDER);
        String taskDescription = args[0].trim();
        try {
            Task taskToAdd;
            if (args.length == 2) {
                taskToAdd = new Todo(taskDescription);
            } else if (args[2].equals("deadline")) {
                taskToAdd = new Deadline(taskDescription, args[1]);
            } else {
                taskToAdd = new Event(taskDescription, args[1]);
            }
            tasks.add(taskToAdd);
            printAddTaskSuccessfulPrompt(taskToAdd);
        } catch (DukeException e) {
            printErrorMsg(TASK_DESCRIPTION_EMPTY_ERROR_MESSAGE);
        } finally {
            System.out.println(LINE_DIVIDER);
        }
    }

    public void delTask(int taskID) {
        System.out.println(LINE_DIVIDER);
        try {
            Task deletedTask = tasks.get(taskID);
            tasks.remove(taskID);
            printDeleteTaskSuccessfulPrompt(deletedTask);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(FIVE_SPACES + CRYING_FACE + TASK_ID_NOT_PROVIDED_OR_INVALID_ERROR_MESSAGE);
        }
    }

    private void printAddTaskSuccessfulPrompt(Task addedTask) {
        System.out.println(FIVE_SPACES+ ADD_TASK_PROMPT);
        System.out.println(SEVEN_SPACES+addedTask);
        System.out.printf(FIVE_SPACES+ ADD_OR_DELETE_TASK_POST_PROMPT, tasks.size());
    }

    private void printDeleteTaskSuccessfulPrompt(Task currentTask) {
        System.out.println(FIVE_SPACES+DELETE_TASKS_PROMPT);
        System.out.println(SEVEN_SPACES+currentTask);
        System.out.printf(FIVE_SPACES+ ADD_OR_DELETE_TASK_POST_PROMPT, tasks.size());
    }


    public void markAsDone(int taskID) {
        System.out.println(LINE_DIVIDER);
        try {
            tasks.get(taskID).markAsDone();
            System.out.println(FIVE_SPACES + DONE_TASK_PROMPT);
            System.out.println(SEVEN_SPACES + tasks.get(taskID));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(FIVE_SPACES + CRYING_FACE + TASK_ID_NOT_PROVIDED_OR_INVALID_ERROR_MESSAGE);
        } finally {
            System.out.println(LINE_DIVIDER);
        }
    }

    public void printErrorMsg(String formatErrorMessage) {
        System.out.println(LINE_DIVIDER);
        System.out.println(FIVE_SPACES+CRYING_FACE+ formatErrorMessage);
        System.out.println(LINE_DIVIDER);
    }

    // I know this function sucks, give me some time to think of a better one plz.
    public void loadDataFromFile(String filePath) {
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            String json = s.nextLine();
            s.close();

            System.out.println(json);

            Type listType = new TypeToken<List<DummyTask>>(){}.getType();
            Gson gson = new Gson();
            List<DummyTask> taskList = gson.fromJson(json, listType);
            for (DummyTask i: taskList) {
                if (i.getIcon().equals("[D]")) {
                    System.out.println("this is a deadline");
                    try {
                        System.out.println(i.getByTime());
                        Deadline tmp = new Deadline(i.getTaskDescription(), i.getByTime());
                        tasks.add(tmp);
                    } catch (DukeException e) {
                        System.out.println(TASK_DESCRIPTION_EMPTY_ERROR_MESSAGE);
                    }
                } else if (i.getIcon().equals("[T]")) {
                    System.out.println("this is a todo");
                    try {
                        Todo tmp = new Todo(i.getTaskDescription());
                        tasks.add(tmp);
                    } catch (DukeException e ) {
                        System.out.println(TASK_DESCRIPTION_EMPTY_ERROR_MESSAGE);
                    }
                } else {
                    System.out.println("this is an event");
                    try {
                        System.out.println(i.getAtTime());
                        Event tmp = new Event(i.getTaskDescription(), i.getAtTime());
                        tasks.add(tmp);
                    } catch (DukeException e ) {
                        System.out.println(TASK_DESCRIPTION_EMPTY_ERROR_MESSAGE);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("opp, the file was not found!");
        }
    }

    public void saveDataToFile() {
        try {
            Gson gson = new Gson();
            FileWriter fw = new FileWriter(DATA_FILE_PATH);
            String json = gson.toJson(tasks);
            fw.write(json);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.out.println("opp, something went wrong!");
        }
    }
}
