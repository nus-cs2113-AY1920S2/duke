package duke.storage;

import duke.taskList.TaskList;
import duke.taskList.task.Deadline;
import duke.taskList.task.Event;
import duke.taskList.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Storage {
    private static String filePath;

    public Storage() {
    }

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Helper function that change the format of a task in file to correct format of a task in taskList
     * @param oneTask a line in the hard disk
     */
    public static void loadATask(String oneTask, TaskList tasks) {
        String[] taskSplit = oneTask.split(" \\| ");
        switch (taskSplit[0]) {
        case "T":
            tasks.addTask(new Todo(taskSplit[2]));
            break;
        case "D":
            tasks.addTask(new Deadline(taskSplit[2],taskSplit[3]));
            break;
        case "E":
            tasks.addTask(new Event(taskSplit[2],taskSplit[3]));
            break;
        default:
            break;
        }
        if (taskSplit[1].equals("1")) {
            tasks.getATask(tasks.size() - 1).markAsDone();
        }
    }

    /**
     * Load the content in the file to storage
     * @return a list of tasks
     */
    public void load(TaskList tasks) throws FileNotFoundException {
        File f = new File(filePath);
        if (!f.exists()) {
            throw new FileNotFoundException();
        }
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String oneTask;
            loadATask(s.nextLine(),tasks);
        }
    }

    public static void write(TaskList tasks) throws IOException {
        File f = new File(filePath);
        if(!f.exists()) {
            throw new IOException();
        }
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tasks.size(); i++) {
            fw.write(tasks.getATask(i).toFile() + "\n");
        }
        fw.close();
    }
}
