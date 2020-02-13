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
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import duke.Duke;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import static duke.util.Constants.*;

public class TaskManager {
    private ArrayList<Task> tasks;
    private String dataFilePath;

    public TaskManager(String dataFilePath) {
        this.dataFilePath = dataFilePath;
        tasks = new ArrayList<>();
        loadDataFromFile(dataFilePath);
    }

    public void listTasks() {
        System.out.println(LINE_DIVIDER);
        System.out.println(FIVE_SPACES + LIST_TASKS_PROMPT);
        int taskCount = tasks.size();
        for (int i = 0; i < taskCount; ++i) {
            System.out.printf(SEVEN_SPACES + LIST_SINGLE_TASK_MESSAGE, i, tasks.get(i));
        }
        System.out.println(LINE_DIVIDER);
    }

    /**
     * @param args todo: addTasks(description, "todo")
     *             deadline/event: addTasks(description, time, "deadline"/"event")
     */
    public void addTask(String... args) {
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
        }
    }

    private void printAddTaskSuccessfulPrompt(Task addedTask) {
        System.out.println(LINE_DIVIDER);
        System.out.println(FIVE_SPACES + ADD_TASKS_PROMPT);
        System.out.printf(SEVEN_SPACES + ADD_SINGLE_TASK_MESSAGE, addedTask);
        System.out.printf(FIVE_SPACES + ADD_TASKS_POST_PROMPT, tasks.size());
        System.out.println(LINE_DIVIDER);
    }


    public void markAsDone(int taskID) {
        System.out.println(LINE_DIVIDER);
        try {
            tasks.get(taskID).markAsDone();
            System.out.println(FIVE_SPACES + DONE_TASKS_PROMPT);
            System.out.printf(SEVEN_SPACES + DONE_SINGLE_TASK_MESSAGE, tasks.get(taskID));
        } catch (NullPointerException e) {
            System.out.println(FIVE_SPACES + CRYING_FACE + TASK_ID_NOT_EXIST_ERROR_MESSAGE);
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

    public void loadDataFromFile(String filePath) {
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            String json = s.nextLine();
            s.close();

            System.out.println(json);

            Type listType = new TypeToken<List<Event>>(){}.getType();
            Gson gson = new Gson();
            List<Event> taskList = gson.fromJson(json, listType);
            for (Event i: taskList) {
                if (i.getIcon().equals("[D]")) {
                    System.out.println("this is a deadline");
                    try {
                        Deadline tmp = new Deadline(i.getTaskDescription(), i.getTime());
                        tasks.add(tmp);
                    } catch (DukeException e) {
                        System.out.println("asdf");
                    }
                } else if (i.getIcon().equals("[T]")) {
                    System.out.println("this is a todo");
                    try {
                        Todo tmp = new Todo(i.getTaskDescription());
                        tasks.add(tmp);
                    } catch (DukeException e ) {
                        System.out.println("asdf");
                    }
                } else {
                    tasks.add(i);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("opp, the file was not found!");
        }
    }

    public void saveDataToFile() {
        try {
            Gson gson = new Gson();
            FileWriter fw = new FileWriter(dataFilePath);
            String json = gson.toJson(tasks);
            fw.write(json);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.out.println("opp, something went wrong!");
        }
    }
}
