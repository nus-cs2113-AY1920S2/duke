import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a storage object with various methods that
 * helps Duke to write and read from the datafile.
 */
public class Storage {

    private String filePath;

    /**
     * Constructor for the storage object.
     * Initialise the file path used for writing and reading.
     * @param filepath a string that represents the file path.
     */
    public Storage(String filepath) {
        this.filePath = filepath;
    }

    /**
     * Method used to load the file at the specified filepath.
     * Calls the parser object to parse the file and get the task objects.
     * Creates a new file is the file could not be found at the filepath.
     * @return an array list of task objects that will be used to populate the tasklist.
     * @throws IOException if the file could not be loaded/read.
     */
    public ArrayList<Task> loadFile() throws IOException {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        File dataFile = new File(filePath);
        if (!dataFile.exists()) {
            dataFile.createNewFile();
        } else {
            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            String line = reader.readLine();
            while (line != null) {
                Parser parser = new Parser();
                Task task = parser.readTaskFromFile(line);
                listOfTasks.add(task);
                line = reader.readLine();
            }
        }
        return listOfTasks;
    }

    /**
     * Method used to write to the file at the specified filepath.
     * @param tasks a tasklist object that keeps track of the tasks in memory during runtime.
     * @throws IOException if the file could not be written.
     */
    public void saveToFile(TaskList tasks) throws IOException {
        File dataFile = new File(filePath);
        if (!dataFile.exists()) {
            dataFile.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile));

        // format task before writing it to file
        for (Task currTask : tasks.listOfTasks) {
            String formattedTask = String.format("%s, %s, %s", currTask.getTypeIcon(), currTask.getIsDone(),
                    currTask.getDescription());
            if (currTask instanceof Event) {
                formattedTask += ", ";
                formattedTask += ((Event) currTask).getTimePeriod();
            }
            if (currTask instanceof Deadline) {
                formattedTask += ", ";
                formattedTask += ((Deadline) currTask).getDueDate();
            }
            writer.write(formattedTask + "\n");
        }
        writer.close();
    }
}
