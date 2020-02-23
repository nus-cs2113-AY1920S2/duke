import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileIO {
    private FileReader fileToReadFrom;
    private FileWriter fileToWriteTo;

    public FileIO (String directory, TaskList tasks) {
        File f = new File(directory);
        ensurePathExist(f);

        try {
            this.fileToReadFrom = new FileReader(f);
            this.readAll(tasks);
            this.fileToWriteTo = new FileWriter(f);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Checks if the file denoted by this abstract pathname exists.
     * If it does not exist, create directories until that path.
     * @param f the abstract pathname
     */
    private void ensurePathExist(File f) {
        if (!f.exists()) {
            System.out.println("Storage file not found.");
            try {
                new File(f.getParent()).mkdir();    // mkdir
                f.createNewFile();
                System.out.println("A storage file is created.");
            } catch (IOException m) {
                System.out.println("... but storage file already exists??");
            }
        }
    }

    /**
     * Read information from current file to tasks.
     * @param tasks The object to write the information to
     */
    public void readAll(TaskList tasks) {
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

        s.close();
    }

    /**
     * Transform input to one of the Task objects.
     * @param breakdown An array of command arguments from storage file
     * @return The task to be created
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
     * Write from tasks to current file.
     * Note: Will replace existing content in current file.
     * @param tasks The object to write information from
     */
    public void writeAll(TaskList tasks) throws IOException {
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
