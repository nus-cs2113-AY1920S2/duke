import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileIO {
    private FileReader fileToReadFrom;
    private FileWriter fileToWriteTo;

    public FileIO (File f, TaskList tasks) {
        try {
            this.fileToReadFrom = new FileReader(f);
            this.loadAllTo(tasks);
            this.fileToWriteTo = new FileWriter(f);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Load information from current file to a TaskList object.
     * @param tasks The object to be loaded up
     */
    public void loadAllTo(TaskList tasks) {
        String rawCommand;
        String[] breakdown;
        Task task;

        Scanner s = new Scanner(fileToReadFrom);
        // load content to tasks
        while(s.hasNext()) {
            rawCommand = s.nextLine();
            breakdown = rawCommand.split(",");
            task = parseCommand(breakdown);
            tasks.add(task);
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

    /**
     * Store information from a TaskList object to current file.
     * Note: Will replace existing content in current file.
     * @param tasks The object to store information from
     */
    public void storeAllFrom(TaskList tasks) throws IOException {
        Task currTask;
        String taskInString = "";

        // Create string to be stored into current file
        for (int i = 0; i < tasks.size(); ++i) {
            currTask = tasks.getByIndex(i);
            taskInString += String.format("%c,%b,%s",
                    currTask.getType(),
                    currTask.getDoneInBoolean(),
                    currTask.getDescription());

            // now write additional info (for Deadline and Event objects)
            if (currTask.getType() == 'D') {
                Deadline currDeadline = (Deadline)currTask;
                taskInString += (",by," + currDeadline.getBy());
            } else if (currTask.getType() == 'E') {
                Event currEvent = (Event) currTask;
                taskInString += (",at," + currEvent.getAt());
            }

            // mark end of Task
            taskInString += System.lineSeparator();
        }

        // now add everything to file (from the start)
        fileToWriteTo.write(taskInString);
    }

    public void close() throws IOException {
        this.fileToReadFrom.close();
        this.fileToWriteTo.close();
    }
}
