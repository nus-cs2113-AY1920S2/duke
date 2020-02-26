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

/**
 * storage class: deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {

    private ArrayList<Task> taskList;
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * load data from file to initialize task list at the beginning of the launching Duke
     * @return a ArrayList that contains all task stored in the local file
     * @throws DukeException if file cannot be found
     * */
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

    /**
     * save data to file if there are some changes made to the task list before exiting from Duke
     * @param taskList the task list stored in the tasks object
     * @throws DukeException if the file cannot be written
     * */
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
