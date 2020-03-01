package Storage;

import Duke.Task;
import Task.TaskList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the fle to store task data
 */
public class Storage {

    private static final char TASK_TODO = 'T';
    private static final char TASK_EVENT = 'E';
    private static final char TASK_DEADLINE = 'D';
    private static final String WORKING_DIRECTORY = System.getProperty("user.dir");
    private static final java.nio.file.Path FOLDER_PATH = java.nio.file.Paths.get(WORKING_DIRECTORY, "Duke");
    private static final java.nio.file.Path FILE_PATH = java.nio.file.Paths.get(WORKING_DIRECTORY, "Duke", "data.txt");

    /**
     * Locate folder location and check availability
     * If missing create folder
     */
    public static void checkFolderPath() {

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

    /**
     * Check whether data file has is present in directory
     *
     * @return status of file availability
     */
    public static boolean checkFileExists() {
        boolean fileExists = java.nio.file.Files.exists(FILE_PATH);
        return fileExists;
    }

    /**
     * Writes newly created task to data file
     *
     * @param newTask newly created task
     * @param myWriter FileWriter object to operate on task
     * @throws IOException if error writing to file
     */
    private static void writeTaskToFile(Task newTask, FileWriter myWriter) throws IOException {
        if (newTask.getDate().isEmpty()) {
            myWriter.write(newTask.getTaskType() + " | " + newTask.getStatus() + " | "
                    + newTask.getDescription() + "\n");
        } else {
            myWriter.write(newTask.getTaskType() + " | " + newTask.getStatus() + " | "
                    + newTask.getDescription() + " | " + newTask.getDate() + "\n");
        }
    }

    /**
     * Parser method for task objects to call when requires saving
     * Sets up environment for writing to file
     *
     * @param newTask newly created task
     */
    public static void saveTask(Task newTask) {
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

    /**
     * Reads in entries from data file line by line
     *
     * @param tasks instance of the list of tasks
     */
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

    /**
     * Parse entry in datafile according to data type
     *
     * @param oneLine current entry in data file
     * @param tasks instance of the list of tasks
     */
    public static void processDataFile(String oneLine, TaskList tasks) {
        String[] words = oneLine.split(" \\| ");
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
                break;
        }

    }

    /**
     * Updates the entire task file after delete or complete command is given
     *
     * @param tasks instance of the list of tasks
     */
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
