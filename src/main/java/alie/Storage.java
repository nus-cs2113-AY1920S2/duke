package alie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import alie.exceptions.InvalidFileFormatException;
import alie.task.ToDo;
import alie.task.Deadline;
import alie.task.Event;
import alie.task.Task;

/**
 * Handles local storage of TaskManager in ALIE.
 */
public class Storage {
    protected static final String FILENAME = "storage.txt";
    private String filePath;

    /**
     * Construct Storage with default filePath provided.
     * @param filePath Default filePath provided
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Finds non-system dependent file path to desired file.
     * @return String containing the desired file path.
     */
    public static String findStoragePath() {
        String home = System.getProperty("user.home");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "Desktop", FILENAME);
        return path.toString();
    }

    /**
     * Loads information from local file containing information from past ALIE usage.
     * @return TaskManager constructed from the stored information.
     * @throws FileNotFoundException If storage file cannot be found.
     * @throws InvalidFileFormatException If storage file is corrupted or cannot be deciphered.
     */
    public ArrayList<Task> readFromFile() throws FileNotFoundException, InvalidFileFormatException {
        File f = new File(filePath);
        ArrayList<Task> taskList = new ArrayList<>();
        Scanner s = new Scanner(f);

        while (s.hasNextLine()) {
             String encodedTasks = s.nextLine();
             switch (encodedTasks.charAt(0)) {
             case 'T':
                 taskList.add(ToDo.decodeTask(encodedTasks));
                 break;
             case 'D':
                 taskList.add(Deadline.decodeTask(encodedTasks));
                 break;
             case 'E':
                 taskList.add(Event.decodeTask(encodedTasks));
                 break;
             default:
                 throw new InvalidFileFormatException("File format is not correct.");
             }
         }
        System.out.println("File found. Imported data from file.");
        return taskList;
    }

    /**
     * Saves current state of taskManager into local file.
     * @param taskManager The TaskManager to be saved into the file.
     * @throws IOException If there is error writing to the file.
     */
    public void save(TaskManager taskManager) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        StringBuilder allTasks = new StringBuilder();
        for (Task task : taskManager.taskList) {
            allTasks.append(task.encodeTask());
            allTasks.append(System.lineSeparator());
        }
        fw.write(allTasks.toString());
        fw.close();
    }
}
