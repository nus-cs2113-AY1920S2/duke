import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

public class DataManager {
    protected static ArrayList<Task> tasks = new ArrayList<> (100);
    private static final String FILEPATH = "\\data\\tasks.txt";

    /** Load the tasks from txt file */
    public static ArrayList<Task> loadTasks() throws IOException {
        String localDir = System.getProperty("user.dir");
        File f = new File(localDir + FILEPATH);

        if (!f.exists()) {
            f.createNewFile();
            return tasks; // Returns empty task list
        }

        Scanner scanner = new Scanner(f);
        String currLine;
        String taskType;
        String isDone;
        String description;
        String timeDescriptor;
        while (scanner.hasNext()) { // Read the file line by line
            currLine = scanner.nextLine();
            String[] parsedLine = currLine.split(",");
            taskType = parsedLine[0];
            isDone = parsedLine[1];
            description = parsedLine[2];
            Task t = new Task(description);
            switch (taskType) {
            case "T":
                t = new Todo(description);
                break;
            case "D":
                timeDescriptor = parsedLine[3];
                t = new Deadline(description, timeDescriptor);
                break;
            case "E":
                timeDescriptor = parsedLine[3];
                t = new Event(description, timeDescriptor);
                break;
            default:
                System.out.println("Invalid task type recorded.");
                break;
            }
            tasks.add(t);
            if (isDone.equals("1")) {
                t.setDone();
            }
        }
        return tasks;
    }

    /** Save the tasks to txt file */
    public static void saveTasks(ArrayList<Task> tasks) throws IOException {
        String localDir = System.getProperty("user.dir");
        FileWriter fw = new FileWriter(localDir + FILEPATH);
        for (Task task : tasks) { // Write tasks line by line
            fw.write(task.getFileRecord());
        }

        fw.close();
    }
}
