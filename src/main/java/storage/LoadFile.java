package storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.task.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;

public class LoadFile {

    /** Default file path used if the user doesn't provide the file name. */
    public static final String DEFAULT_STORAGE_FILEPATH = "taskManager.txt";
    public static final String DEFAULT_STORAGE_JSON_FILEPATH = "taskManager.json";
    private static String jsonFilePath = DEFAULT_STORAGE_JSON_FILEPATH;
    private Path filePath;
    public  ArrayList<Task> taskArrayList;

    public LoadFile() {
    }

    public LoadFile(Path filePath) {
        this.filePath = filePath;
    }

    public class TaskData {
        /** common attributes*/
        protected int taskIndex;
        protected String taskDescription;
        protected boolean isDone;
        protected char taskType;

        /** deadline and event task attributes*/
        protected String taskTime;
        /** deadline task attributes*/
        protected LocalDate taskDeadlineDate;
    }


    public  void readDom(TaskList taskList)  {
        BufferedReader reader = null;
        try {
            File file = new File(jsonFilePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            reader = new BufferedReader(new FileReader(jsonFilePath));
            Gson gson = new GsonBuilder().create();
            Task[] tasks = gson.fromJson(reader,  Task [].class);
            for (Task task:tasks
                 ) {
                switch (task.getTaskType()){
                case 'T':
                    taskList.getInternalList().add(
                            new TodoTask(task.getTaskIndex(),task.getTaskDescription()));
                    break;
                case 'D':
                    taskList.getInternalList().add(
                            new DeadlineTask(task.getTaskIndex(),task.getTaskDescription(), task.getTaskDeadline()));
                    break;
                case 'E':
                    taskList.getInternalList().add(
                            new EventTask(task.getTaskIndex(),task.getTaskDescription(), task.getTaskStartTime()));
                    break;
                default:
                }
            }
        } catch (NullPointerException npex) {
            System.out.println("ww");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}