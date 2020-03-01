package duke.storage;

import duke.data.TaskList;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;
import duke.exception.CorruptedFileException;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Storage {
    private static final String TASK_LIST_PATH = "./data/taskList.txt";

    public void loadTaskList() throws FileNotFoundException, CorruptedFileException {
        File taskListFile = new File(TASK_LIST_PATH);
        ArrayList<Task> decodedList = decode(taskListFile);

        for (Task task : decodedList) {
            TaskList.add(task);
        }
    }

    public void saveTaskList() throws IOException {
        File taskListFile = new File(TASK_LIST_PATH);

        // Ensure task list file exists
        if (!taskListFile.exists()) {
            createTaskListFile();
        }

        FileWriter writer = new FileWriter(TASK_LIST_PATH);

        for (int i = 0; i < TaskList.size(); ++i) {
            Task task = TaskList.get(i);
            String taskData = encode(task);
            writer.write(taskData + System.lineSeparator());
        }

        writer.close();
    }

    public void createTaskListFile() throws IOException {
        File taskListFile = new File(TASK_LIST_PATH);
        taskListFile.getParentFile().mkdirs();
        if (taskListFile.exists()) {
            new FileWriter(TASK_LIST_PATH).close(); // Overwrite file with a new file
        } else {
            taskListFile.createNewFile();
        }
    }

    private ArrayList<Task> decode(File filePath) throws CorruptedFileException, FileNotFoundException {
        ArrayList<Task> list = new ArrayList<>();

        Scanner fileScanner = new Scanner(filePath);

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine().trim();

            if (line.isEmpty()) {
                break;
            }

            String[] taskInformation = line.split("__", 4);
            String taskType = taskInformation[0];
            String doneStatus = taskInformation[1];
            String taskDescription = taskInformation[2];
            String taskDateTime = taskInformation[3];

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
                Deadline newDeadlineTask = new Deadline(taskDescription, taskDateTime);
                newDeadlineTask.setIsDone(doneStatus.equals("1"));
                list.add(newDeadlineTask);
                break;
            case "E":
                Event newEventTask = new Event(taskDescription, taskDateTime);
                newEventTask.setIsDone(doneStatus.equals("1"));
                list.add(newEventTask);
                break;
            default:
                throw new CorruptedFileException();
            }
        }

        fileScanner.close();
        return list;
    }


    private String encode(Task task) throws IOException {
        String taskType = getTaskType(task);
        String doneStatus = task.getIsDone() ? "1" : "0";
        String taskDescription = task.getTask();
        String taskDetail = task.getDateTime();

        return String.join("__", new String[]{taskType, doneStatus, taskDescription, taskDetail});
    }

    private String getTaskType(Task task) throws IOException{
        if (task instanceof ToDo) {
            return "T";
        } else if (task instanceof Deadline) {
            return "D";
        } else if (task instanceof Event) {
            return "E";
        } else throw new IOException();
    }
}
