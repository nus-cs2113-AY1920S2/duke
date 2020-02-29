package duke.task.storage;

import com.google.gson.Gson;
import duke.task.tasktypes.Task;
import duke.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskRecorder {

    private Gson gson;
    private FileWriter fileWriter;
    private Ui printer;

    private String filepath;
    private String fileDirectory;

    private File file;


    public TaskRecorder (Ui printer) {
        gson = new Gson();
        this.printer = printer;

        this.filepath = "./data/tasks.txt";
        this.fileDirectory = "./data";

        file = new File(fileDirectory);
    }


    public void recordAllTasks (ArrayList<Task> tasks) {

        if (!file.exists()) {
            file.mkdir();
        }

        try {

            fileWriter = new FileWriter(filepath);
            String toJson = gson.toJson(tasks);
            fileWriter.write(toJson);

            fileWriter.close();

        } catch (IOException e) {
            printer.displayMessage("Note: Unable to save your tasks in save file");
        }

    }


}
