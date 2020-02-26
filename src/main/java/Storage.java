import task.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;

/**
 * Handles loading and saving of tasks to a text file, so that data is preserved in future runs of the program.
 */
public class Storage {

    private String path;
    private static final String FILENAME = "/tasks.txt";

    public Storage (String path) {
        this.path = path;
    }

    /**
     * Returns a TaskList object containing tasks loaded from a .txt file.
     * If the file is empty or does not exist, returns an empty TaskList.
     *
     * @return TaskList.
     */
    public TaskList loadTasks() throws IOException {
        String localDir = System.getProperty("user.dir");
        File f = new File(localDir + path);

        if (!f.exists()) {
            f.mkdir();
        }

        f = new File(localDir + path + FILENAME);
        if (!f.exists()) {
            f.createNewFile();
            return new TaskList(); // Returns empty task list
        }

        Scanner scanner = new Scanner(f);
        String currLine;
        String taskType;
        String isDone;
        String description;
        String timeDescriptor;
        TaskList tasks = new TaskList();
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
            tasks.addTask(t);
            if (isDone.equals("1")) {
                t.setDone();
            }
        }
        return tasks;
    }

    /**
     * Saves the tasks from the current TaskList to a .txt file.
     *
     * @param tasks Most updated TaskList.
     */
    public void saveTasks(TaskList tasks) throws IOException {
        String localDir = System.getProperty("user.dir");
        FileWriter fw = new FileWriter(localDir + path + FILENAME);
        for (Task task : tasks.getTaskList()) { // Write tasks line by line
            fw.write(task.getFileRecord());
        }

        fw.close();
    }
}
