package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Storage {

    //private TaskList tasks = new TaskList();

    public static void saveToFile(TaskList tasks) throws IOException {
        File file = new File("save2.txt");

        FileOutputStream fo = new FileOutputStream(file);
        PrintWriter pw = new PrintWriter(fo);

        for (int i = 0; i < tasks.getSize(); i++) {
            pw.println(tasks.get(i).saveFormat());
        }
        pw.close();
        fo.close();
    }

    public static void readFromFile(TaskList tasks) throws IOException {
        File file = new File("save2.txt");
        Scanner scanner = new Scanner(file);
        try {
            while (scanner.hasNextLine()) {
                String task = scanner.nextLine();
                String fields[] = task.split("//");
                switch (fields[0]) {
                case ("t"):
                    tasks.add(new Todo(fields[2]));
                    if (fields[1].equals("true")) {
                        tasks.get(tasks.getSize() - 1).markAsDone();
                    }
                    break;
                case ("d"):
                    tasks.add(new Deadline(fields[2], fields[3]));
                    if (fields[1].equals("true")) {
                        tasks.get(tasks.getSize() - 1).markAsDone();
                    }
                    break;
                case ("e"):
                    tasks.add(new Event(fields[2], fields[3]));
                    if (fields[1].equals("true")) {
                        tasks.get(tasks.getSize() - 1).markAsDone();
                    }
                    break;
                }
            }
        } catch (DukeException e) {
            System.out.println("error");
        }
        scanner.close();
    }
}
