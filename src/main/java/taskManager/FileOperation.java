package taskManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class FileOperation {
    int maxTask = 100;
    int counter = 0;
    public FileOperation() {

    }

    public Task[] loadTaskList() throws FileNotFoundException {
        Task[] tasks = new Task[maxTask];
        File f = new File("data/duke.txt");
        if (f.exists()) {
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String newline = s.nextLine();
                String[] newlineByPart = newline.replaceAll("[\\s][\\|][\\s]",",").split(",");
                String typeOfTask = newlineByPart[0];
               if (typeOfTask.equalsIgnoreCase("t")){
                   Todo item = new Todo(newlineByPart[2]);
                   tasks[counter] = item;
               } else if (typeOfTask.equalsIgnoreCase("d")) {
                   Deadline item = new Deadline(newlineByPart[2], newlineByPart[3]);
                   tasks[counter] = item;
               } else if (typeOfTask.equalsIgnoreCase("e")) {
                   Event item = new Event(newlineByPart[2], newlineByPart[3]);
                   tasks[counter] = item;
               }
               if (newlineByPart[1].equalsIgnoreCase("1")) {
                   tasks[counter].setDone();
               }
               counter++;
            }
        }
        return tasks;
    }
}
