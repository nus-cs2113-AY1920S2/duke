import task.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;

public class Storage {

    private String filepath;

    public Storage (String filepath) {
        this.filepath = filepath;
    }

    /** Load the tasks from txt file */
    public TaskList loadTasks() throws IOException {
        String localDir = System.getProperty("user.dir");
        File f = new File(localDir + filepath);

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

    /** Save the tasks to txt file */
    public void saveTasks(TaskList tasks) throws IOException {
        String localDir = System.getProperty("user.dir");
        FileWriter fw = new FileWriter(localDir + filepath);
        for (Task task : tasks.getTaskList()) { // Write tasks line by line
            fw.write(task.getFileRecord());
        }

        fw.close();
    }
}
