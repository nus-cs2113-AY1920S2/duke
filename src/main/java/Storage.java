import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {

    private String filePath;

    public Storage(String filepath) {
        this.filePath = filepath;
    }

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
