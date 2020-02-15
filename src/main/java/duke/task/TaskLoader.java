package duke.task;

import com.google.gson.*;
import duke.ui.Output;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskLoader {

    private Scanner input;
    private File file;
    private Output printer;
    private String filepath;
    private Gson gson;

    private final String EVENT = "E";
    private final String TODO = "T";
    private final String DEADLINE = "D";



    public TaskLoader (Output printer) {
        this.printer = printer;
        gson = new Gson();

        this.filepath = "./data/tasks.txt";
        file = new File(filepath);
    }


    public ArrayList<Task> loadTasks () {

        ArrayList<Task> savedTasks = new ArrayList<>();

        try {
            input = new Scanner(file);

            if (input.hasNextLine()) {

                String json = input.nextLine();
                JsonElement jsonTree = JsonParser.parseString(json);
                JsonArray jsonStrings = jsonTree.getAsJsonArray();

                for ( int i = 0; i < jsonStrings.size(); i++ ) {
                    JsonObject obj = jsonStrings.get(i).getAsJsonObject();
                    Task task = createClassFromJson(obj);

                    savedTasks.add(task);
                }

            }

        } catch (FileNotFoundException e) {
            printer.displayMessage("Unable to load past tasks from file");
        }

        return savedTasks;
    }

    private Task createClassFromJson (JsonObject obj) {

        String taskType = obj.get("taskType").toString();

        if (taskType.contains(TODO)) {
            return gson.fromJson(obj.toString(), Todo.class);

        } else if (taskType.contains(DEADLINE)) {
            return gson.fromJson(obj.toString(), Deadline.class);

        } else if (taskType.contains(EVENT)) {
            return gson.fromJson(obj.toString(), Event.class);

        }

        // Case should never happen
        return null;
    }

}
