package duke;

import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tasks.Deadline;
import duke.tasks.Event;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Storage class to get/load and store data.
 */
public class Storage {

    private String filePath;
    private String homePath;

    /**
     * Constructor for Storage.
     * @param filePath String of filepath for stored data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Gets the saved data from text file.
     * @return ArrayList Contains data from saved text file
     * @throws FileNotFoundException If file from file path does not exists.
     */
     public ArrayList<Task> getData() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            System.out.println("testinggg");
            String userData = scanner.nextLine();
            char taskType = userData.charAt(1);
            char isDone = userData.charAt(4);

            switch (taskType) {
            case 'T':
                Task todo = new Todo(userData.substring(7));
                if (isDone == '1') {
                    todo.updateIsDone();
                }
                tasks.add(todo);
                break;
            case 'E': {
                String[] description = userData.substring(7).split(" \\(at: ");
                Task event = new Event(description[0], description[1].substring(0, description[1].length() - 1));
                if (isDone == '1') {
                    event.updateIsDone();
                }
                tasks.add(event);
                break;
            }
            case 'D': {
                String[] description = userData.substring(7).split(" \\(by: ");
                Task deadline = new Deadline(description[0], description[1].substring(0, description[1].length() - 1));
                if (isDone == '1') {
                    deadline.updateIsDone();
                }
                tasks.add(deadline);
                break;
            }
            }
        }
         System.out.println(tasks);
        return tasks;
    }

    /**
     * Saves and stores the tasks in ArrayList into a text file.
     * @param tasks TaskList class object that handles all ArrayList of Tasks command.
     */
    public void saveData(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter("output.txt");
            for (Task task : tasks.getTaskArray()) {
                fw.write(task.toString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
