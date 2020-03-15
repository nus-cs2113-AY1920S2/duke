package duke.util;

import duke.taskmanager.Deadline;
import duke.taskmanager.Event;
import duke.taskmanager.Task;
import duke.taskmanager.ToDo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Load {
    public static String path;
    public static List<Task> list = new ArrayList<>();
    public static List<String> lines = new ArrayList<>();
    public static UI ui;
    public static File file;

    public Load() {
        path = "./data/myTasks.txt";
    }

    /**
     * Load the existing task list in designated file
     * "data/myTasks.txt", and save them into an array list
     * lines for reference. If the file does not exist, create
     * it and list = empty.
     */
    public void loadData() {
        try {
            file = new File(path);
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
                System.out.println("Tasks loaded from local successfully!");
                String line;
                Scanner readFile = new Scanner(file);
                while (readFile.hasNext()) {
                    line = readFile.nextLine();
                    lines.add(line);
                }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Read the array list lines and process the content
     * line by line, each line contains information for one
     * task stored in the file, and save the processed task
     * to a new task list, that is the task list to be used
     * in the program.
     */
    public List<Task> readData() {
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
