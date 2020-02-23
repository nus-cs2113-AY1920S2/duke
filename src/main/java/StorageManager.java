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

    /**
     * Read in line by line from the text file and return an arraylist with all
     * tasks added
     * @return an arraylist containing all the tasks loaded from the text file
     * @throws FileNotFoundException
     */
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

    /**
     * Add task information to the text file
     * @param textToAdd
     * @throws IOException
     */
    public void addTaskToFile(String textToAdd) throws IOException {
        File file = new File(filePath);
        FileWriter fw = new FileWriter(filePath,true);
        fw.write(textToAdd + "\n");
        fw.close();
    }

    /**
     * Overwrite all existing tasks in the text file with the updated
     * tasks in the arrayList
     * @param Tasks
     * @throws IOException
     */
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
