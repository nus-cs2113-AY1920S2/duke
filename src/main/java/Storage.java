import Tasks.Task;
import Tasks.TaskList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private String userDirectory = System.getProperty("user.dir");

    public void save(TaskList taskList) {
        Gson gson = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .setPrettyPrinting()
                .create();

        try {
            FileWriter fileWriter = new FileWriter(userDirectory + "/taskList.json");
            gson.toJson(taskList, fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TaskList load() throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileReader fileReader = new FileReader(userDirectory + "/taskList.json");
        TaskList newTask = gson.fromJson(fileReader, new TypeToken<TaskList>(){}.getType());

        return newTask;
    }

}
