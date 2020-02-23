import TaskObjects.Deadline;
import TaskObjects.Event;
import TaskObjects.Task;
import TaskObjects.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StorageManager {

    protected String filePath;
    private TaskManager taskManager = new TaskManager();

    public StorageManager(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> Tasks = new ArrayList<>();
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String stringInput = s.nextLine();
            parseTaskFromFile(stringInput, Tasks);
        }
        return Tasks;
    }

    //add an exception later to catch here if cannot be added properly
    private void parseTaskFromFile(String savedString, ArrayList<Task> Tasks){
        String[] taskDetails = savedString.split("[|]");
        switch (taskDetails[0]) {
        case "T":
            Todo todo = new Todo(taskDetails[2]);
            if(taskDetails[1].equals("1")) {
                todo.setDone(true);
            }
            Tasks.add(todo);
            break;
        case "E":
            Event event = new Event(taskDetails[2], taskDetails[3]);
            if(taskDetails[1].equals("1")) {
                event.setDone(true);
            }
            Tasks.add(event);
            break;
        case "D":
            Deadline deadline = new Deadline(taskDetails[2], taskDetails[3]);
            if(taskDetails[1].equals("1")) {
                deadline.setDone(true);
            }
            Tasks.add(deadline);
            break;

        }
    }

    public void addTaskToFile(String textToAdd) throws IOException {
        File file = new File(filePath);
        FileWriter fw = new FileWriter(filePath,true);
        fw.write(textToAdd + "\n");
        fw.close();
    }

    public void saveToFile(ArrayList<Task> Tasks) throws IOException {
        File file = new File(filePath);
        file.createNewFile();
        FileWriter fw = new FileWriter(filePath);
        for (Task task : Tasks) {
            fw.write(task.stringToSave() + "\n");
        }
        fw.close();
    }
}
