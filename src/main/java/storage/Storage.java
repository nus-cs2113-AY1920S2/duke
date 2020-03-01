package storage;

import exception.DukeException;
import task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final String dataPath;

    public Storage(String dataPath){
        this.dataPath = dataPath;
    }

    public ArrayList<Task> load() throws DukeException {
        File f = new File(dataPath);
        try {
            Scanner s = new Scanner(f);
            ArrayList<Task> tasks = new ArrayList<>();

            while (s.hasNext()) {
                String taskString = s.nextLine();
                String[] details = taskString.split("\\|", -1);
                boolean isDone = Integer.parseInt(details[1]) == 1;
                String taskType = details[0];
                switch (taskType){
                case "T":
                    tasks.add(new Todo(details[2], isDone));
                    break;
                case "D":
                    tasks.add(new Deadline(details[2], details[3], isDone));
                    break;
                case "E":
                    tasks.add(new Event(details[2], details[3], isDone));
                    break;
                default:
                    throw new DukeException("☹ OOPS!!! Problem loading data.");
                }
            }

            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found.");
        }
    }

    public void saveTasks(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(dataPath);
            StringBuilder textToAdd = new StringBuilder();
            ArrayList<Task> tasksToSave = tasks.getTasks();
            for (Task t : tasksToSave) {
                textToAdd.append(t.convertToData()).append("\n");
            }
            fw.write(textToAdd.toString());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Problem saving task.");
        }
    }
}
