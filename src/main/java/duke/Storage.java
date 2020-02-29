package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the classes for all saving/writing in Duke
 */
public class Storage {

    /**
     * File path for saving and loading
     */
    private static final String FILE_PATH = "./duke.txt";

    /**
     * Write a single task to file
     * @param t task
     * @param fr fileWriter
     * @throws IOException exception
     */
     static void writeToFile(types.Task t, FileWriter fr) throws IOException {
        String str = t.getType() + " ; " + t.getDone() + " ; " + t.getName();
        if (t.getType().equals("D")) {
            str += " ; " + t.getBy();
        } else if (t.getType().equals("E")) {
            str += " ; " + t.getAt();
        }
        fr.write(str);
        fr.write(System.lineSeparator());
    }

    /**
     * Write entire task list array to file
     * @param arr task list array
     * @throws IOException exception
     */
     static void taskListWrite(types.Task[] arr, File f) throws IOException {
        FileWriter fr = null;
        fr = new FileWriter(f);
        for (int i = 0; i < TaskList.getNumTasks(); i++) {
            writeToFile(arr[i], fr);
        }
        fr.close();
    }

    /**
     * Load tasks from file
     * @return file
     * @throws FileNotFoundException exception
     */
    static File loadFile() throws FileNotFoundException {
        File f = new File(FILE_PATH);
        if (f.length() != 0) {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                boolean bool = false;
                String cl = s.nextLine();
                String[] splitTask = cl.split(" ; ");
                String type = splitTask[0];
                if (type.equals("D")) {
                    TaskList.getTaskList()[TaskList.getNumTasks()] = new types.Deadline(splitTask[2], splitTask[3]);
                    if (Integer.parseInt(splitTask[1].trim()) == 1) {
                        bool = true;
                    }
                    TaskList.getTaskList()[TaskList.getNumTasks()].setDone(bool);
                    TaskList.setNumTasks(TaskList.getNumTasks() + 1);
                } else if (type.equals("E")) {
                    TaskList.getTaskList()[TaskList.getNumTasks()] = new types.Event(splitTask[2], splitTask[3]);
                    if (Integer.parseInt(splitTask[1]) == 1) {
                        bool = true;
                    }
                    TaskList.getTaskList()[TaskList.getNumTasks()].setDone(bool);
                    TaskList.setNumTasks(TaskList.getNumTasks() + 1);
                } else {
                    TaskList.getTaskList()[TaskList.getNumTasks()] = new types.Todo(splitTask[2]);
                    if (Integer.parseInt(splitTask[1]) == 1) {
                        bool = true;
                    }
                    TaskList.getTaskList()[TaskList.getNumTasks()].setDone(bool);
                    TaskList.setNumTasks(TaskList.getNumTasks() + 1);
                }
            }
        }
        return f;
    }

    /**
     * Save changes to file
     * @param f file
     */
    static void saveChanges(File f) {
        try {
            taskListWrite(TaskList.getTaskList(), f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
