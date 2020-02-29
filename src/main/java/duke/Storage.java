package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.Duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static File storageFile;

    public Storage(File file){
        this.storageFile = file;
    }

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