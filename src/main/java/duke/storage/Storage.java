package duke.storage;

import duke.task.TaskList;
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

import static duke.constant.Constant.*;

/**
 * Storage class - Class to deal with loading and saving manipulation
 * in the lists and save it back to a data file, also created a new file
 * if the path does not exist
 */
public class Storage {

    /**
     * Method to load data from the data file by processing the data and add
     * them to the task list
     * @return tasks the newly created task list
     * @throws FileNotFoundException if there is no file to load
     */
    public ArrayList<Task> load() throws FileNotFoundException{
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(PATH); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String[] dataSplitter = s.nextLine().split(VERTICAL_BAR_REGREX);
            switch (dataSplitter[0]) {
            case TODO_ABBREVIATION:
                tasks.add(new ToDo(dataSplitter[2]));
                break;
            case DEADLINE_ABBREVIATION:
                tasks.add(new Deadline(dataSplitter[2], dataSplitter[3]));
                break;
            case EVENT_ABBREVIATION:
                tasks.add(new Event(dataSplitter[2], dataSplitter[3]));
                break;
            default:
            }
            if (dataSplitter[1].equals(ONE)) {
                tasks.get(tasks.size() - 1).markAsDone();
            }
        }
        return tasks;
    }

    /**
     * Method to save the changes in the task lists after adjustment from
     * users and create new file if it does not exist yet
     *
     * @param tasks the list of tasks that is passed after adjustment
     * @throws IOException in case exception in file
     */
    public void save(TaskList tasks) throws IOException{
        Path directoryPath = Paths.get(PATH);
        boolean isFileExists = Files.exists(directoryPath);
        if(!isFileExists) {
            File directory = new File(DIRECTORY_PATH);
            directory.mkdir();
        }
        File file = new File(PATH);
        FileWriter fw = new FileWriter(file);
        for (Task task: tasks.getTaskList()) {
            fw.write(task.getType() + VERTICAL_BAR + task.getIsDone() + VERTICAL_BAR + task.getDescription()
                    + VERTICAL_BAR + task.getTime() + System.lineSeparator());
        }
        fw.close();
    }
}
