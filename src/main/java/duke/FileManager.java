package duke;

import duke.exception.CorruptedFileException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    private static final String TASK_LIST_PATH = "./data/taskList.txt";

    public static void loadTaskList(ArrayList<Task> list) throws FileNotFoundException, CorruptedFileException {
        File taskListFile = new File(TASK_LIST_PATH);
        Scanner fileScanner = new Scanner(taskListFile);

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine().trim();

            if (line.isEmpty()) {
                break;
            }

            String[] taskInformation = line.split("__");
            String taskType = taskInformation[0];
            String doneStatus = taskInformation[1];
            String taskDescription = taskInformation[2];
            String taskDetail = taskInformation[3];

            if (!doneStatus.equals("1") && !doneStatus.equals("0")) {
                throw new CorruptedFileException();
            }

            switch (taskType) {
            case "T":
                ToDo newToDoTask = new ToDo(taskDescription);
                newToDoTask.setIsDone(doneStatus.equals("1"));
                list.add(newToDoTask);
                break;
            case "D":
                Deadline newDeadlineTask = new Deadline(taskDescription, taskDetail);
                newDeadlineTask.setIsDone(doneStatus.equals("1"));
                list.add(newDeadlineTask);
                break;
            case "E":
                Event newEventTask = new Event(taskDescription, taskDetail);
                newEventTask.setIsDone(doneStatus.equals("1"));
                list.add(newEventTask);
                break;
            default:
                throw new CorruptedFileException();
            }
        }

        fileScanner.close();
    }

    public static void saveTaskList(ArrayList<Task> list) throws IOException {
        File taskListFile = new File(TASK_LIST_PATH);

        // Ensure task list file exists
        if (!taskListFile.exists()) {
            createTaskListFile();
        }

        FileWriter writer = new FileWriter(TASK_LIST_PATH);

        for (Task task : list) {
            String taskType = task.getClass().getSimpleName().substring(0, 1);
            String doneStatus = task.getIsDone() ? "1" : "0";
            String taskDescription = task.getTask();
            String taskDetail = task.getDetails();

            String taskData = String.join("__", new String[]{taskType, doneStatus, taskDescription, taskDetail});
            writer.write(taskData + System.lineSeparator());
        }

        writer.close();
    }

    public static void createTaskListFile() throws IOException {
        File taskListFile = new File(TASK_LIST_PATH);
        taskListFile.getParentFile().mkdirs();
        taskListFile.createNewFile();
    }
}
