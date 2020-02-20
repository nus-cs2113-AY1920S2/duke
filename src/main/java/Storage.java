import Tasks.Task;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Storage {
    private String userDirectory = System.getProperty("user.dir");

    public void save(ArrayList<Task> taskList) {
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

    public ArrayList<Task> load() throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(userDirectory + "/taskList.json"));
        ArrayList<Task> newTask = gson.fromJson(bufferedReader, new TypeToken<ArrayList<Task>>(){}.getType());
        return newTask;
    }

}
