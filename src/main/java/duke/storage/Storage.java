package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadData() {
        try {
            return loadTasks();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private ArrayList<Task> loadTasks() throws IOException {
        try {
            return loadTasksFromFile();
        } catch (FileNotFoundException e) {
            File file = new File(filePath);
            file.createNewFile();
            return new ArrayList<>();
        }
    }

    // Loads the tasks saved previously
    public ArrayList<Task> loadTasksFromFile() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            String taskDescription = s.nextLine();
            String[] splitDescription = taskDescription.split("#",3);
            char taskType = splitDescription[0].charAt(0);
            boolean isDone = Boolean.parseBoolean(splitDescription[1].trim());
            String description = splitDescription[2].trim();

            switch (taskType) {
            case 'T':
                ToDo toDo = new ToDo(description);
                toDo.setDone(isDone);
                tasks.add(toDo);
                break;
            case 'D':
                Deadline deadline = new Deadline(description);
                deadline.setDone(isDone);
                tasks.add(deadline);
                break;
            case 'E':
                Event event = new Event(description);
                event.setDone(isDone);
                tasks.add(event);
                break;
            default:
                // Add exception handling
            }
        }
        return tasks;
    }


    public void saveData(TaskList tasks) {
        storeTasksToFile(tasks);
    }

    // Saves the tasks for future usage
    public void storeTasksToFile(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : taskList.tasks) {
                switch (task.taskType) {
                case 'T':
                    ToDo toDo = (ToDo) task;
                    fw.write("T # " + toDo.isDone + " # " + toDo.description + System.lineSeparator());
                    break;
                case 'D':
                    Deadline deadline = (Deadline) task;
                    fw.write("D # " + deadline.isDone + " # " + deadline.description + "/by "
                            + deadline.getByWithoutBraces() + System.lineSeparator());
                    break;
                case 'E':
                    Event event = (Event) task;
                    fw.write("E # " + event.isDone + " # " + event.description + "/at "
                            + event.getPeriodWithoutBraces() + System.lineSeparator());
                    break;
                default:
                    // Add Exception handling
                }
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
