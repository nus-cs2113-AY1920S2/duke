package Storage;

import Duke.Deadline;
import Duke.Event;
import Duke.Task;
import Duke.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class StorageFile {

    public static final String WORKING_DIRECTORY = System.getProperty("user.dir");
    public static final java.nio.file.Path FOLDER_PATH = java.nio.file.Paths.get(WORKING_DIRECTORY, "Duke");
    public static final java.nio.file.Path FILE_PATH = java.nio.file.Paths.get(WORKING_DIRECTORY, "Duke", "data.txt");

    public static void checkFolderPath() {
        // Locate folder location, if missing create folder
        boolean directoryExists = java.nio.file.Files.exists(FOLDER_PATH);
        try {
            if (!directoryExists) {
                Files.createDirectory(FOLDER_PATH);
                System.out.println("Directory created");
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
        if (newTask.getDate().isEmpty()) {
            myWriter.write(newTask.getTaskType() + " | " + newTask.getStatus() + " | "
                    + newTask.getDescription() + "\n");
        } else {
            myWriter.write(newTask.getTaskType() + " | " + newTask.getStatus() + " | "
                    + newTask.getDescription() + " | " + newTask.getDate() + "\n");
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

    public static void populateList(ArrayList<Task> tasks) {
        try {
            List<String> allLines = Files.readAllLines(Paths.get(String.valueOf(FILE_PATH)));
            for (String oneLine : allLines) {
                processDataFile(oneLine, tasks);
            }
        } catch (IOException e) {
            System.out.println("Error reading data file! \n");
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

    public static void uploadTodo(ArrayList<Task> tasks, String[] words) {
        boolean taskStatus = Boolean.parseBoolean(words[1]);
        String taskDescription = words[2];
        Task newTask = new ToDo(taskDescription);
        if (taskStatus) {
            newTask.markAsDone();
        }
        tasks.add(newTask);
    }

    public static void uploadDeadline(ArrayList<Task> tasks, String[] words) {
        boolean taskStatus = Boolean.parseBoolean(words[1]);
        String taskDescription = words[2];
        String taskDate = words[3];
        Task newTask = new Deadline(taskDescription, taskDate);
        if (taskStatus) {
            newTask.markAsDone();
        }
        tasks.add(newTask);

    }

    public static void uploadEvent(ArrayList<Task> tasks, String[] words ) {
        boolean taskStatus = Boolean.parseBoolean(words[1]);;
        String taskDescription = words[2];
        String taskDate = words[3];
        Task newTask = new Event(taskDescription, taskDate);
        if (taskStatus) {
            newTask.markAsDone();
        }
        tasks.add(newTask);
    }

    public static void processDataFile(String oneLine, ArrayList<Task> tasks) {
        String[] words = oneLine.split(" \\| ");

        switch (words[0]) {
            case "T":
                uploadTodo(tasks, words);
                break;
            case "D":
                uploadDeadline(tasks, words);
                break;
            case "E":
                uploadEvent(tasks, words);
                break;
            default:
                // in case user touches txt file and fills with random data
                System.out.println("Line not added: " + oneLine + "\n");
                break;
        }

    }
}
