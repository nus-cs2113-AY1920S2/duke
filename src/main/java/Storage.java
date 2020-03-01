import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Loads and writes the bot's tasks from and to a storage file.
 * Contains a File object as the storage file.
 */

public class Storage {
    private static File storageFile;

    public Storage(File file){
        this.storageFile = file;
    }

    /**
     * Loads tasks from the storage file into a task list
     * and returns the task list.
     *
     * @throws FileNotFoundException .
     * @return stored task list.
     */
    public ArrayList<Task> loadFile() throws FileNotFoundException {
        Scanner s = new Scanner(this.storageFile);
        ArrayList<Task> storedTaskList = new ArrayList<>();
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] words = line.split(",");
            Task t = new Task(line);
            switch (words[0]) {
                case "T":
                    t = new Todo(words[2]);
                    break;
                case "D":
                    t = new Deadline(words[2], words[3]);
                    break;
                case "E":
                    t = new Event(words[2], words[3]);
                    break;
            }
            storedTaskList.add(t);
            if (words[1].equals("\u2713")) {
                t.markAsDone();
            }
        }
        s.close();
        return storedTaskList;
    }

    /**
     * Writes tasks from the given task list to the storage file.
     *
     * @param taskList task list that contains tasks to be written.
     * @throws IOException .
     */
    public void writeToFile(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(storageFile);
        fw.close();
        for (Task t : taskList) {
            fw = new FileWriter(storageFile, true);
            fw.write(t.toFileString() + System.lineSeparator());
            fw.close();
        }
    }
}