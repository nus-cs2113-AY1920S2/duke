package Storage;
import TaskList.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileOperation {
    public int COUNTER = 0;
    File f;
    String PATHNAME;

    public FileOperation(String filepath) {
        f = new File(filepath);
        PATHNAME = f.getAbsolutePath();
    }

    public ArrayList<Task> loadTaskList() throws FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();
        if (f.exists()) {
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String newline = s.nextLine();
                String[] newlineByPart = newline.replaceAll("[\\s][\\|][\\s]",",").split(",");
                String typeOfTask = newlineByPart[0];
               if (typeOfTask.equalsIgnoreCase("t")){
                   Todo item = new Todo(newlineByPart[2]);
                   taskList.add(item);
               } else if (typeOfTask.equalsIgnoreCase("d")) {
                   Deadline item = new Deadline(newlineByPart[2], newlineByPart[3]);
                   taskList.add(item);
               } else if (typeOfTask.equalsIgnoreCase("e")) {
                   Event item = new Event(newlineByPart[2], newlineByPart[3]);
                   taskList.add(item);
               }
               if (newlineByPart[1].equalsIgnoreCase("1")) {
                   taskList.get(COUNTER).setDone();
               }
               COUNTER++;
            }
        }
        return taskList;
    }

    public void saveTaskList (ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(PATHNAME);
        for (int i = 0; i < taskList.size(); i++) {
            String convertedTask = taskList.get(i).createStrForSaving();
            if (i != taskList.size() - 1) {
                fw.write(convertedTask + System.lineSeparator());
            } else {
                fw.write(convertedTask);
            }
        }
        fw.close();
    }
}
