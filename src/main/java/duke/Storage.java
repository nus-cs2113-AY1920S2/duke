package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private ArrayList<Task> taskList;
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        File f = new File(filePath);
        Scanner fileIn;
        try {
            fileIn = new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new DukeException("Cannot find the file!");
        }
        taskList = new ArrayList<Task>();
        String record;
        while (fileIn.hasNextLine()) {
            record = fileIn.nextLine();
            String[] attributes = record.split("\\|");
            switch (attributes[0].trim()) {
            case "T":
                taskList.add(new Todo(attributes[2].trim(), attributes[1].trim().equals("1")));
                break;
            case "D":
                taskList.add(new Deadline(attributes[2].trim(), attributes[1].trim().equals("1"), attributes[3].trim()));
                break;
            case "E":
                taskList.add(new Event(attributes[2].trim(), attributes[1].trim().equals("1"), attributes[3].trim()));
                break;
            }
        }
        return taskList;
    }

    public void save(ArrayList<Task> taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for(Task task: taskList) {
                fw.write(task.textToFile());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Cannot write to the file!");
        }
    }
}
