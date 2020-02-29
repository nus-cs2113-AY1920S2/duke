package duke.util;

import duke.taskmanager.Deadline;
import duke.taskmanager.Event;
import duke.taskmanager.Tasks;
import duke.taskmanager.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Load {
    public static Path path;
    public static List<Tasks> list = new ArrayList<>();
    public static List<String> lines = new ArrayList<>();
    public static UI ui;
    public static File file;
    public Load(Path path) {
        Load.path = path;
        file = new File(String.valueOf(path));
    }

    /**
     * Load the existing task list in designated file
     * "data/myTasks.txt", and save them into an array list
     * lines for reference. If the file does not exist,
     * throws FileNotFoundException.
     * @throws FileNotFoundException when the file at
     *                               designated path is
     *                               not found
     */
    public void loadData() throws FileNotFoundException {
        String line;
        Scanner readFile = new Scanner(file);
        while (readFile.hasNext()) {
            line = readFile.nextLine();
            lines.add(line);
        }
    }

    /**
     * Read the array list lines and process the content
     * line by line, each line contains information for one
     * task stored in the file, and save the processed task
     * to a new task list, that is the task list to be used
     * in the program.
     * @throws FileNotFoundException when the file at
     *                               designated path is
     *                               not found
     */
    public List<Tasks> readData() throws FileNotFoundException {
        loadData();
        for (String line: lines) {
            if(line != null) {
                String[] data = line.split(Pattern.quote("|"));
                switch (data[0]) {
                case "E":
                    Event e = new Event(data[1], data[3]);
                    if (data[2].equals("Y")) {
                        e.markAsDone();
                    }
                    list.add(e);
                    break;
                case "D":
                    Deadline d = new Deadline(data[1], data[3]);
                    if (data[2].equals("Y")) {
                        d.markAsDone();
                    }
                    list.add(d);
                    break;
                case "T":
                    ToDo t = new ToDo(data[1]);
                    if (data[2].equals("Y")) {
                        t.markAsDone();
                    }
                    list.add(t);
                    break;
                }
            }
        }
        return list;
    }
}
