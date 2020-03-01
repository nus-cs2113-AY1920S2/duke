import Tasks.Deadline;
import Tasks.Events;
import Tasks.Task;
import Tasks.TaskList;
import Tasks.ToDo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private String userDirectory;
    final RuntimeTypeAdapterFactory<Task> typeFactory = RuntimeTypeAdapterFactory
            .of(Task.class, "Type")
            .registerSubtype(ToDo.class, "todo")
            .registerSubtype(Deadline.class, "deadline")
            .registerSubtype(Events.class, "event");
    private Gson gson;

    public Storage() {
        gson = new GsonBuilder().registerTypeAdapterFactory(typeFactory).create();
        userDirectory = System.getProperty("user.dir");
    }

    /**
     * Method that is responsible for saving data by using GSON library to convert to a human editable JSON
     * file.
     * @param taskList : TaskList that is to be converted to a human editable JSON file
     */
    public void save(TaskList taskList) {

        try {
            FileWriter fileWriter = new FileWriter(userDirectory + "/taskList.json");
            gson.toJson(taskList, fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method responsible for loading TaskList from the relative user directory.
     * @throws FileNotFoundException : throws an exception when a file is not found and creates a new file.
     */
    public TaskList load() throws FileNotFoundException {
        try {
            FileReader fileReader = new FileReader(userDirectory + "/taskList.json");
            TaskList newTask = gson.fromJson(fileReader, new TypeToken<TaskList>() {
            }.getType());

            return newTask;
        } catch (FileNotFoundException e) {
            System.out.println("Opening a new file");
            return new TaskList();
        }
    }

}
