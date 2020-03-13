package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Open file from file directory if any and convert the string into the respective Task at the
     * start of the programs launch.
     * @return ArrayList of Task stored in the file if it exist.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasksStored = new ArrayList<>();
        int taskCount = 0;
        try {
            File file = new File(filePath); // create a File for the given file path
            Scanner fileScanner = new Scanner(file); // create a Scanner using the File as the source
            while (fileScanner.hasNext()) {
                String[] existingTask = fileScanner.nextLine().split(" \\| ");
                if (existingTask[0].equals("T")) {
                    tasksStored.add(new Todo(existingTask[2]));
                }
                if (existingTask[0].equals("D")) {
                    tasksStored.add(new Deadline(existingTask[2], existingTask[3]));
                }
                if (existingTask[0].equals("E")) {
                    tasksStored.add(new Event(existingTask[2], existingTask[3]));
                }
                if (existingTask[1].equals("1")) {
                    tasksStored.get(taskCount).markAsDone();
                }
                taskCount++;
            }
            return tasksStored;
        } catch (FileNotFoundException e) {
            return tasksStored;
        }
    }
}
