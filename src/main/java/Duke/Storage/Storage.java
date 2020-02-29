package Duke.Storage;

import Duke.Exception.DukeException;
import Duke.Library.ErrorMessage;
import Duke.Parser.ParserStorage;
import Duke.Task.Task;
import Duke.Ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private ArrayList<Task> tasks;
    private Ui ui;

    public Storage(String filePath, Ui ui) {
        this.filePath = filePath;
        this.ui = ui;
        read();
    }

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
            Ui.displayError(e.getMessage());
        }
        tasks = newTasks;
    }

    public void write() {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(ParserStorage.toStorageString(task) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            Ui.displayError(ErrorMessage.FILE_NOT_SAVE);
        } catch (DukeException e) {
            Ui.displayError(e.getMessage());
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

}