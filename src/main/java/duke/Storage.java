package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.*;
import java.util.Scanner;

/**
 * Handles the loading of tasks from the file and saving of tasks to the file.
 */
public class Storage {

    /** Default file path used if the user doesn't provide the file name. */
    public static final String PATH = "duke.txt";

    /**
     * Saves the existing tasks in the TaskList to the specified file
     * @param tasks list of tasks
     * @throws IOException
     */
    public static void saveFile(TaskList tasks) throws IOException {
        File f = new File(PATH);
        FileOutputStream fos = new FileOutputStream(f);
        PrintWriter pw = new PrintWriter(fos);
        String status;

        for (int i = 0; i < tasks.getSize(); i++) {
            pw.println(tasks.get(i).formatResult());
        }

        pw.close();
        fos.close();
    }

    /**
     * Inserts the tasks in the file into the TaskList
     * @param tasks lists of tasks
     * @throws IOException
     */
    public static void insertFileContents(TaskList tasks) throws IOException {
        File f = new File(PATH); // create a File for the given file path

        Scanner s = new Scanner(f); // create a Scanner using the File as the source

        try {
            while (s.hasNext()) {
                String line;
                line = s.nextLine();
                String[] sentence = line.split("\\|");
                String taskType = sentence[0].toLowerCase();
                Task task;
                switch (taskType) {
                case "t":
                    task = new Todo(sentence[2]);
                    tasks.add(task);
                    if (sentence[1].equals("true")) {
                        tasks.get(tasks.getSize() - 1).markAsDone();
                    }
                    break;
                case "d":
                    task = new Deadline(sentence[2], sentence[3]);
                    tasks.add(task);
                    if (sentence[1].equals("true")) {
                        tasks.get(tasks.getSize() - 1).markAsDone();
                    }
                    break;
                case "e":
                    task = new Event(sentence[2], sentence[3]);
                    tasks.add(task);
                    if (sentence[1].equals("true")) {
                        tasks.get(tasks.getSize() - 1).markAsDone();
                    }
                    break;
                }
            }
        } catch (DukeException e) {
            System.out.println("Error!");
        }

        s.close();
    }

}
