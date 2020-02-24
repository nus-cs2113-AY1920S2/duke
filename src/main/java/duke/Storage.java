package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.*;
import java.util.Scanner;

public class Storage {
    public static final String PATH = "duke.txt";

    public static void saveFile(TaskList tasks) throws IOException {
        File f = new File(PATH);
        FileOutputStream fos = new FileOutputStream(f);
        PrintWriter pw = new PrintWriter(fos);
        String status;

        for (int i = 0; i < tasks.getSize(); i++) {
            String line = tasks.get(i).toString();
            String[] sentence = line.split("]");
            String taskType = sentence[0].substring(1).toLowerCase();
            if (tasks.get(i).isDone) {
                status = "done";
            } else {
                status = "pending";
            }

            if (taskType.equals("t")) {
                pw.println(taskType + " | " + status + " | " + tasks.get(i).description + System.lineSeparator());
            } else {
                String [] newSentence = line.split(":");
                String dueDate = newSentence[1].substring(1);
                int length = dueDate.length();
                String newDate = newSentence[1].substring(1,length);
                pw.println(taskType + " | " + status + " | " + tasks.get(i).description + "| " + newDate + System.lineSeparator());
            }
        }

        pw.close();
        fos.close();
    }

    public static void insertFileContents(TaskList tasks) throws IOException {
        File f = new File(PATH); // create a File for the given file path

        Scanner s = new Scanner(f); // create a Scanner using the File as the source

        while (s.hasNext()) {
            String line;
            line = s.nextLine();
            String[] sentence = line.split("\\|");
            String taskType = sentence[0].toLowerCase();
            Task task;
            switch(taskType) {
                case "t ":
                    task = new Todo(sentence[2]);
                    tasks.add(task);
                    if (sentence[1].equals(" done ")) {
                        tasks.get(tasks.getSize() - 1).markAsDone();
                    }
                    break;
                case "d ":
                    task = new Deadline(sentence[2], sentence[3]);
                    tasks.add(task);
                    if (sentence[1].equals(" done ")) {
                        tasks.get(tasks.getSize() - 1).markAsDone();
                    }
                    break;
                case "e ":
                    task = new Event(sentence[2], sentence[3]);
                    tasks.add(task);
                    if (sentence[1].equals(" done ")) {
                        tasks.get(tasks.getSize() - 1).markAsDone();
                    }
                    break;
                }
            }

        s.close();
    }

}
