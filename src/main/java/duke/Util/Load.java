package duke;

import duke.Storage;
import duke.taskmanager.Deadline;
import duke.taskmanager.Event;
import duke.taskmanager.TaskManager;
import duke.taskmanager.ToDo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class Load {
    private static Path path;
    private static List<String> lines;
    private static List<TaskManager> list;
    Storage storage = new Storage();

    public Load() {
        path = Paths.get("data/myTasks.txt");
    }

    public static List<TaskManager> loadData() {
        try {
            lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String line:lines) {
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
        return list;
    }
}
