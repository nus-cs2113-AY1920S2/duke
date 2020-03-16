package duke.storage;

import duke.data.TaskList;
import duke.exception.DukeDateTimeException;
import duke.exception.DukeFileException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import static duke.constant.Constant.FILE_NAME;

/**
 * The Storage class is the class handles the loading of Task from a specified filePath and
 * storing of Task into the filePath.
 * @author Lam Yue Wei
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public class Storage {
    private String filePath;

    /**
     * Public constructor for Storage.
     * @param filePath File path where the Tasks will be loaded from or stored into.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Open file from file directory if any and convert the string into the respective Task at the
     * start of the programs launch.
     * @return ArrayList of Task stored in the file if it exist.
     */
    public ArrayList<Task> load() throws DukeFileException, DukeDateTimeException {
        ArrayList<Task> tasks = new ArrayList<>();
        int taskCount = 0;
        try {
            File file = new File(filePath); // create a File for the given file path
            Scanner fileScanner = new Scanner(file); // create a Scanner using the File as the source
            while (fileScanner.hasNext()) {
                String[] existingTask = fileScanner.nextLine().split(" \\| ");
                if (existingTask[0].equals("T")) {
                    tasks.add(new Todo(existingTask[2]));
                }
                if (existingTask[0].equals("D")) {
                    tasks.add(new Deadline(existingTask[2], existingTask[3]));
                }
                if (existingTask[0].equals("E")) {
                    tasks.add(new Event(existingTask[2], existingTask[3]));
                }
                if (existingTask[1].equals("1")) {
                    tasks.get(taskCount).markAsDone();
                }
                taskCount++;
            }
            System.out.println("     File successfully loaded into TaskList.");
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeFileException("     File does not exist. Launching new TaskList.");
        } catch (DateTimeParseException e) {
            throw new DukeDateTimeException("     Error loading Date/Time. Launching new TaskList.");
        }
    }

    /**
     * Open the file directory based on the filePath, add Task for storage into hard disk and close file.
     * @param filePath  File directory path.
     * @param taskToAdd Sting containing Task information to store into file.
     * @throws IOException If input or output operation failed.
     */
    public void writeFileTask(String filePath, String taskToAdd) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(taskToAdd);
        fileWriter.close();
    }

    /**
     * Convert the tasks in TaskList into a suitable String for saving and pass this String to writeFileTask method
     * to write it into an external file for storage.
     * @param taskList Task manager that deals with getting and setting Task Object.
     * @throws IOException If input or output operation failed.
     */
    public void saveTask(TaskList taskList) throws IOException {
        Path path = Paths.get(FILE_NAME);
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
        String taskToAdd = "";
        for (int i = 0; i < taskList.getTaskCount(); i++) {
            if (taskList.getTask(i) instanceof Todo) {
                Todo todo = (Todo) taskList.getTask(i);
                taskToAdd = taskToAdd + "T | " + (todo.getIsDone() ? 1 : 0) + " | ";
                taskToAdd = taskToAdd + todo.getDescription();
            }
            if (taskList.getTask(i) instanceof Deadline) {
                Deadline deadline = (Deadline) taskList.getTask(i);
                taskToAdd = taskToAdd + "D | " + (deadline.getIsDone() ? 1 : 0) + " | ";
                taskToAdd = taskToAdd + deadline.getDescription() + " | " + deadline.getDateTime();
            }
            if (taskList.getTask(i) instanceof Event) {
                Event event = (Event) taskList.getTask(i);
                taskToAdd = taskToAdd + "E | " + (event.getIsDone() ? 1 : 0) + " | ";
                taskToAdd = taskToAdd + event.getDescription() + " | " + event.getDateTime();
            }
            taskToAdd += "\n";
        }
        writeFileTask(filePath, taskToAdd);
    }
}
