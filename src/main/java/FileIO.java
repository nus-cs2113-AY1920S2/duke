import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileIO {
    private File file;

    public FileIO (String path) {
        this.file = new File(path);
    }

    /**
     * Load information from current file to a TaskList object.
     * @param tasks The object to be loaded up
     */
    public void loadTo(TaskList tasks) {
        try {
            String rawCommand;
            String[] breakdown;
            Task task;

            Scanner s = new Scanner(file);
            // load content to tasks
            while(s.hasNext()) {
                rawCommand = s.nextLine();
                breakdown = rawCommand.split(",");
                task = parseCommand(breakdown);
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Transform input to one of the Task objects.
     * @param breakdown
     * @return
     */
    private Task parseCommand(String[] breakdown) {
        String taskType = breakdown[0];
        boolean taskIsDone = Boolean.parseBoolean(breakdown[1]);
        String taskDescription = breakdown[2];
        String taskDescriptionArg = "";
        if (breakdown.length > 4) {
            taskDescriptionArg = breakdown[4];
        }
        Task task = null;

        switch (taskType) {
        case "T":   // todo
            task = new ToDo(taskDescription);
            task.setDone(taskIsDone);
            break;
        case "D":   // deadline
            task = new Deadline(taskDescription, taskDescriptionArg);
            task.setDone(taskIsDone);
            break;
        case "E":   // event
            task = new Event(taskDescription, taskDescriptionArg);
            task.setDone(taskIsDone);
            break;
        }

        return task;
    }
}
