package duke;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class Storage {
    protected String fname;
    public Storage(String fname) {
        this.fname = fname;
    }
    public static void writeToFile(String filePath, TaskList tasksToWrite) throws IOException {
        /**
         * This method writes the tasklist to a file
         *
         * @param filePath the path that the tasks will be written into
         * @param tasksToWrite list of tasks that are to be written to the file
         * @throws IOException IOException if the path doesn't exist
         */

        FileWriter fw = new FileWriter(filePath);
        try {
            for (int i = 0; i < tasksToWrite.getSize(); i++) {
                String writtenString = String.format("%d. %s\n", i + 1, tasksToWrite.getIndex(i).toString());
                fw.write(writtenString);
            }
        } catch (IOException e){
            System.out.println("Invalid path");
        }
        fw.close();
    }
    public static TaskList readFromFile(String filePath) throws FileNotFoundException {
        /**
         *  This method takes a file and adds all the tasks in the file to the tasklist
         *
         * @param filePath the file that the list of tasks will be retrieved from
         * @throws FileNotFoundException if the file doesn't exist
         */

        File file = new File(filePath);
        Scanner sc = null;
        TaskList tasks1 = new TaskList();
        if (file.exists()){
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                Task task1 = createTask(task);
                tasks1.addTask(task1);
            }
        }
        sc.close();

        return tasks1;
        }

    public static Task createTask(String task) {
        /**
         * This method takes a string and converts it to a task object
         *
         * @param task the task to be converted into a 'task' object
         */

        String[] splitString = task.split("]",2);
        Task newTask;
        if (splitString[0].contains("T")){
            //Todo
            String[] splitString1 = splitString[1].split("] ", 2);
            newTask= new Todo(splitString1[1]);
            if (splitString1[0].contains("\u2713")){
                newTask.markAsDone();
            }
        } else if (splitString[0].contains("E")){
            //Event
            String[] splitString1 = splitString[1].split("] ",2);
            String[] splitString2 = splitString1[1].split(" \\(at:");

            newTask = new Event(splitString2[0],splitString2[1]);
            if (splitString1[0].contains("\u2713")){
                newTask.markAsDone();
            }
        } else {
            //Deadline
            String[] splitString1 = splitString[1].split("] ",2);
            String[] splitString2 = splitString1[1].split(" \\(by:", 2);
            newTask = new Deadline(splitString2[0],splitString2[1]);
            if (splitString1[0].contains("\u2713")){
                newTask.markAsDone();
            }
        }
        return  newTask;
    }
    }


