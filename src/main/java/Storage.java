import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Represents the location where text files are stored
 * A Storage object correspnds to filepath and file name
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
     * saves the tasks in ArrayList into text file
     * If there is an IOException, an error message will be displayed
     * @param tasks ArrayList of Tasks
     * @throws IOException if IO is invalid
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
     * Adds Task from text file to ArrayList
     *
     * @param input String input by user
     * @param tasksList ArrayList to store tasks
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
     * Restores Tasks from text file into ArrayList
     * @param fileReader java.io.FileWriter to read the text file
     * @param tasksList ArrayList to store tasks
     */
    public void restoreArray(Scanner fileReader, ArrayList <Task> tasksList) {
        while (fileReader.hasNextLine()) {
            String input = fileReader.nextLine();
            addTask(input, tasksList);
        }
    }

    /**
     * Returns an ArrayList containing all the updated tasks
     * @return ArrayList containing tasks
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