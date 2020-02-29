package Duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static void loadFileContents(String filePath, TaskList taskArray) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        int taskListSize = 0;
        while (s.hasNext()) {
            String newLine = s.nextLine();
            String[] tokenizedLine = newLine.split("\\|");
            String type = tokenizedLine[2];
            switch (type) {
            case ("T"):
                taskArray.addTask(new ToDo(tokenizedLine[1], tokenizedLine[0]));
                break;
            case ("E"):
                taskArray.addTask(new Event(tokenizedLine[1], tokenizedLine[3], tokenizedLine[0]));
                break;
            case ("D"):
                taskArray.addTask(new Deadline(tokenizedLine[1], tokenizedLine[3], tokenizedLine[0]));
                break;
            }
        }
        System.out.println("Previous tasks has been loaded successfully:");
    }

    /**
     * Creates a new save file overwriting the existing one.
     *
     * @param filePath destination for the new save file
     * @param textToAdd the pre-formatted text string to be written to the file
     * @throws IOException
     */
    static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Appends task to a pre-existing save file without overwriting.
     *
     * @param filePath file of the existing save file to be appended to
     * @param textToAppend the pre-formatted text string to append to the file
     * @throws IOException
     */
    static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Saves the current state of the task list to a save file.
     *
     * @param filePath the destination file path where the file will be stored
     * @param taskArray the list of tasks to be saved in the file
     * @see Storage#writeToFile(String, String) writeToFile
     * @see Storage#appendToFile(String, String) appendToFile
     * @see ToDo#toSaveFormat() ToDo
     * @see Deadline#toSaveFormat() Deadline
     * @see Event#toSaveFormat() Event
     */
    public static void saveTasks(String filePath, TaskList taskArray) {
        boolean overWrite = true;
        for (Task currTask : taskArray.tasks) {
            if(overWrite == true) {
                try {
                    Storage.writeToFile(filePath, currTask.toSaveFormat() + "\n");
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
                overWrite = false;
            } else {
                try {
                    Storage.appendToFile(filePath, currTask.toSaveFormat() + "\n");
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage() + "\n");
                }
            }
        }
    }
}