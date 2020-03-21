package duke.storage;

import duke.exception.DukeException;
import duke.library.ErrorMessage;
import duke.parser.ParserStorage;
import duke.task.Task;
import duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class that manages duke.storage of duke.Duke data in local duke.storage.
 */
public class Storage {
    private String filePath;
    private ArrayList<Task> tasks;
    private Ui ui;

    /**
     * Constructs a Storage object that contains duke.tasks and duke.storage related operations.
     * Mainly save duke.tasks and get duke.tasks.
     *
     * @param filePath The filepath to the txt file.
     * @param ui The user interface displaying events on the task list.
     */
    public Storage(String filePath, Ui ui) {
        this.filePath = filePath;
        this.ui = ui;
        read();
    }

    /**
     * Reads duke.tasks from filepath. Creates empty duke.tasks if file cannot be read.
     */
    private void read() {
        ArrayList<Task> newTasks = new ArrayList<>();
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            try {
                s = new Scanner(f);
            } catch (java.io.FileNotFoundException e) {
                e.printStackTrace();
            }
            while (s.hasNext()) {
                newTasks.add(ParserStorage.createTaskFromStorage(s.nextLine()));
            }
            s.close();
        } catch (DukeException | FileNotFoundException e) {
            Ui.displayError(ErrorMessage.FILE_NOT_FOUND);
            Ui.displayError(ErrorMessage.NEW_FILE_CREATED);
        }
        tasks = newTasks;
    }

    /**
     * Writes the duke.tasks into a file of the given filepath.
     */
    public void write() {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(ParserStorage.toStorageString(task) + "\n");
            }
            writer.close();
        } catch (IOException | DukeException e) {
            Ui.displayError(ErrorMessage.FILE_NOT_SAVE);
        }
    }

    /**
     * Retrieves the existing tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

}
