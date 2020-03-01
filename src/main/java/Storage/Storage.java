package Storage;

import Duke.Deadline;
import Duke.Event;
import Duke.Task;
import Duke.ToDo;

import Task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;


public class Storage {

    private static final char TASK_TODO = 'T';
    private static final char TASK_EVENT = 'E';
    private static final char TASK_DEADLINE = 'D';
    private static final String WORKING_DIRECTORY = System.getProperty("user.dir");
    private static final java.nio.file.Path FOLDER_PATH = java.nio.file.Paths.get(WORKING_DIRECTORY, "Duke");
    private static final java.nio.file.Path FILE_PATH = java.nio.file.Paths.get(WORKING_DIRECTORY, "Duke", "data.txt");

    public static void checkFolderPath() {
        // Locate folder location, if missing create folder
        boolean directoryExists = java.nio.file.Files.exists(FOLDER_PATH);
        try {
            if (!directoryExists) {
                Files.createDirectory(FOLDER_PATH);
                System.out.println("Directory created\n");
            } else {
                System.out.println("Directory found\n");
            }
        } catch (IOException e) {
            System.out.println("Error creating folder!\n");
        }
    }

    public static boolean checkFileExists() {
        boolean fileExists = java.nio.file.Files.exists(FILE_PATH);
        return fileExists;
    }

    private static void writeTaskToFile(Task newTask, FileWriter myWriter) throws IOException {
        if (newTask instanceof ToDo) {
            myWriter.write(newTask.getTaskType() + " | " + newTask.getStatus() + " | "
                    + newTask.getDescription() + "\n");
        } else {
            myWriter.write(newTask.getTaskType() + " | " + newTask.getStatus() + " | "
                    + newTask.getDescription() + " | " + newTask.getDate().toString() + " | " + newTask.getTime() + "\n");
        }
    }

    public static void saveTask(Task newTask) {
        // Append new task to file
        try {
            File file = new File(String.valueOf(FILE_PATH));
            FileWriter myWriter = new FileWriter(file, true);

            writeTaskToFile(newTask, myWriter);
            myWriter.close();
            System.out.println("Successfully updated data file!\n");
        } catch (IOException e) {
            System.out.println("Error updating file!\n");
        }
    }

    public static void populateList(TaskList tasks) {
        try {
            List<String> allLines = Files.readAllLines(Paths.get(String.valueOf(FILE_PATH)));
            for (String oneLine : allLines) {
                processDataFile(oneLine, tasks);
            }
        } catch (IOException e) {
            System.out.println("Error reading data file! \n");
        }

    }

    public static void processDataFile(String oneLine, TaskList tasks) {
        String[] words = oneLine.split(" \\| ");
        try {
            switch (words[0].charAt(0)) {
            case TASK_TODO:
                tasks.loadFromFileTodo(words);
                break;
            case TASK_DEADLINE:
                tasks.loadFromFileDeadline(words);
                break;
            case TASK_EVENT:
                tasks.loadFromFileEvent(words);
                break;
            default:
                // in case user touches txt file and fills with random data
                System.out.println("Line not added: " + oneLine + "\n");
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Corrupted entry detected, skipping entry");
        }

    }

    public static void rebuildTaskFile(ArrayList<Task> tasks) {
        // replace current list with new updated list

        try {
            File file = new File(String.valueOf(FILE_PATH));
            FileWriter myWriter = new FileWriter(file, false);
            for (Task newTask : tasks) {
                writeTaskToFile(newTask, myWriter);
            }
            myWriter.close();
            System.out.println("Successfully updated data file!\n");
        } catch (IOException e) {
            System.out.println("Error updating file!\n");
        }
    }




}
