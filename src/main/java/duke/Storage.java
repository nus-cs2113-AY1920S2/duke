package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static duke.Duke.PATH;
import static duke.Duke.VERTICAL_BAR;

public class Storage {
    public ArrayList<Task> load() throws FileNotFoundException{
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(PATH); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String[] dataSplitter = s.nextLine().split("\\|");
            switch (dataSplitter[0]) {
            case Duke.TODO_ABBREVIATION:
                tasks.add(new ToDo(dataSplitter[2]));
                break;
            case Duke.DEADLINE_ABBREVIATION:
                tasks.add(new Deadline(dataSplitter[2], dataSplitter[3]));
                break;
            case Duke.EVENT_ABBREVIATION:
                tasks.add(new Event(dataSplitter[2], dataSplitter[3]));
                break;
            default:
            }
            if (dataSplitter[1].equals(Duke.ONE)) {
                tasks.get(tasks.size() - 1).markAsDone();
            }
        }
        return tasks;
    }

    public void save(TaskList tasks) throws IOException{
        Path directoryPath = Paths.get("data");
        boolean isFileExists = Files.exists(directoryPath);
        if(!isFileExists) {
            File directory = new File("data");
            directory.mkdir();
        }
        File f = new File(PATH);
        FileWriter fw = new FileWriter(f);
        for (Task t: tasks.getTaskList()) {
            fw.write(t.getType() + VERTICAL_BAR + t.getIsDone() + VERTICAL_BAR + t.getDescription()
                    + VERTICAL_BAR + t.getTime() + System.lineSeparator());
        }
        fw.close();
    }
}
