import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Represents a file destination to store text file containing list of tasks
 * A Storage object corresponds to the file name and the file path.
 */
public class Storage {

    protected File file;
    protected String filePath;
    protected ArrayList <Task> tasks;

    public Storage() {
        file = new File("database.txt");
        filePath = file.getAbsolutePath();
    }

    /**
     * Saves the updated tasklist into the text file
     * If there is an IO Exception, an error message will be printed
     * @param tasks ArrayList storing all the tasks
     * @throws IOException if IO operation fails
     */
    public void save(ArrayList <Task> tasks) {
        this.tasks = tasks;
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task task : tasks) {
                fileWriter.write(task.storeText());
                fileWriter.write(System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException exception) {
            System.out.println(" [Error " + exception.getMessage() + "] ");
            System.out.println(" Please contact the systems adminstrator for assistance");
        }
    }

    /**
     * Adds a new task to the stored ArrayList and updates the text file
     * @param input String input by user
     * @param tasksList ArrayList storing all the tasks that have been queued
     */
    public void addTask(String input, ArrayList <Task> tasksList) {
        String [] parameters = input.split(",");
        String type = parameters[0];
        boolean isDone = Boolean.parseBoolean(parameters[1]);
        String description = parameters[2];
        if (type.equals("[T]")) {
            ToDo existingToDo = new ToDo(description, isDone);
            tasksList.add(existingToDo);
        } else if (type.equals("[E]")) {
            String eventDetails = parameters[3];
            Event existingEvent = new Event(description, isDone, eventDetails);
            tasksList.add(existingEvent);

        } else {
            String by = parameters[3];
            Deadline existingDeadline = new Deadline(description, isDone, by);
            tasksList.add(existingDeadline);
        }
    }

    /**
     * Restore tasks back to ArrayList from text file
     * @param fileReader Java.io.FileWriter to read text document
     * @param tasksList ArrayList storing all the tasks that have been queued
     */
    public void restoreArray(Scanner fileReader, ArrayList <Task> tasksList) {
        while (fileReader.hasNextLine()) {
            String input = fileReader.nextLine();
            addTask(input, tasksList);
        }
    }

    /**
     * Load Tasks back to ArrayList from text file
     * If text file cannot be found, an error message will be displayed
     * @return ArrayList of all tasks stored
     * @throws FileNotFoundException if text file cannot be found
     */
    public ArrayList <Task> loadTasks() {
        ArrayList <Task> tasksList = new ArrayList<Task>();
        try {
            Scanner fileReader = new Scanner(file);
            restoreArray(fileReader, tasksList);
            fileReader.close();
        } catch (FileNotFoundException exception) {
            System.out.println(" [Warning: File not found. Please add a task/'database.txt' file]");
        } catch (Exception exception) {
            System.out.println(" [Warning: File not found. Please add a task/'database.txt' file]");
        }
        return tasksList;
    }
}