package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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
    public ArrayList<Task> load() {
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
            return tasks;
        } catch (FileNotFoundException e) {
            return tasks;
        }
    }
}
