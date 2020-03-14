package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static TaskList taskListOp = new TaskList();
    /**
     * Save the task items in taskList one by one.
     * It calls writeToFile every time it finds a valid task item.
     *
     * @throws IOException if new file can not be created.
     */
    public static void saveTask() throws IOException {
        System.out.println("Tasks are being saved now");
        try {
            for (Task ignored : taskListOp.taskList) {
                writeToFile();
            }
        } catch (IOException e) {
            System.out.println("Task can not be saved");
        }

    }

    /**
     * Write taskList into a txt file named Duke.txt
     * A new blank file will be created if the programme
     * can not fina a existing one.
     *
     * @throws IOException if new file can not be created.
     */
    private static void writeToFile() throws IOException {
        File duke = new File("duke.txt");
        if (!duke.exists()) {
            try {
                duke.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("New duke file has been created");
        }
        FileWriter fw = new FileWriter("duke.txt");
        for (Task task : taskListOp.taskList) {
            fw.write(task.toString() + System.lineSeparator());
        }
        fw.close();
    }


    /**
     * Searches for a duke.txt file.
     * Display a message if such file does not exist
     * but does not terminate the programme
     *
     */
    public static void loadFile() {
        try {
            readFromFile();
        } catch (FileNotFoundException e) {
            System.out.println("____________________________________________________________");
            System.out.println("File not found");
            System.out.println("____________________________________________________________");
        } catch (IOException e) {
            System.out.println("____________________________________________________________");
            System.out.println("IO error");
            System.out.println("____________________________________________________________");
            e.printStackTrace();
        }
    }

    /**
     * Read from a duke.txt file using a scanner to capture
     * the strings and calls loadTaskList.
     *
     * @throws IOException if new file can not be read.
     */
    public static void readFromFile() throws IOException {
        File file = new File("duke.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String raw = scanner.nextLine();
            loadTaskList(raw, taskListOp.taskList);
        }
    }

    /**
     * Handle raw string inputs from the duke.txt
     * and creates respective task items with details
     * and stores in a list
     *
     * @param raw A string from the duke file containing task details
     * @param  tasks A list used to store task items
     * @throws IOException if new file can not be read.
     */
    private static void loadTaskList(String raw, ArrayList<Task> tasks) throws IOException {
        if (raw.contains("[T]")) {
            String taskDescription = raw.substring(6);
            ToDos newTask = new ToDos(taskDescription);
            if (raw.contains("DONE")) {
                newTask.isDone = true;
            }
            taskListOp.taskList.add(newTask);
        } else if (raw.contains("[D]")) {
            int timeSectionStart = raw.indexOf("(");
            int timeSectionEnd = raw.indexOf(")");
            String taskDescription = raw.substring(6, timeSectionStart - 1);
            String timePeriod = raw.substring(timeSectionStart + 5, timeSectionEnd);
            Deadlines newTask = new Deadlines(taskDescription,timePeriod);
            if (raw.contains("DONE")) {
                newTask.isDone = true;
            }
            taskListOp.taskList.add(newTask);
        } else if (raw.contains("[E]")) {
            int timeSectionStart = raw.indexOf("(");
            int timeSectionEnd = raw.indexOf(")");
            String taskDescription = raw.substring(6, timeSectionStart - 1);
            String timePeriod = raw.substring(timeSectionStart + 5, timeSectionEnd);
            Events newTask = new Events(taskDescription, timePeriod);
            if (raw.contains("DONE")) {
                newTask.isDone = true;
            }
            taskListOp.taskList.add(newTask);
        }
    }
}