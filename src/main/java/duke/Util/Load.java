package duke.Util;

import duke.taskmanager.Deadline;
import duke.taskmanager.Event;
import duke.taskmanager.Tasks;
import duke.taskmanager.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Load {
    private static Path path;
    public static List<Tasks> list = new ArrayList<>();
    public static List<String> lines = new ArrayList<>();
    public static UI ui;

    public Load(Path path) {
        Load.path = path;
    }

    public void loadData() throws FileNotFoundException {
        String line;
        File file = new File(String.valueOf(path)); // create a File for the given file path
        Scanner readFile = new Scanner(file);
        while (readFile.hasNext()) {
            line = readFile.nextLine();
            lines.add(line);
        }
        System.out.println(lines);
        System.out.println("aoisdioasj");
    }
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
