package duke.storage;
import duke.tasklist.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.ui.Ui;
import duke.parser.Parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {

    /**
     * Represents the file used to store TaskList data.
     */
    public Storage(String filePath){
        createTaskList(filePath, TaskList.taskList);
    }

    /** Given the checked or crossed Icon, returns a Bool value */
    public static Boolean convertMarkToBool(String mark){
        if (mark.equals("\u2713")){
            return true;
        }
        else {
            return false;
        }
    }

    /** Creates a taskList at the begenning og the program */
    public static ArrayList<Task> createTaskList(String filepathe, ArrayList<Task> taskList) {
        try {
            File f = new File(filepathe);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String ogString = s.nextLine();
                String[] words = ogString.split(" \\| ");
                String taskType = words[0];
                String mark = words[1];
                String description = words[2];
                Boolean isTaskDone = convertMarkToBool(mark);
                if (taskType.equals("[T]")) {
                    Todo t = new Todo(description);
                    t.isDone = isTaskDone;
                    taskList.add(t);
                } else {
                    if (taskType.equals("[E]")) {
                        String date = words[3].trim();
                        LocalDate AT = LocalDate.parse(date);
                        Event t = new Event(description, AT);
                        t.isDone = isTaskDone;
                        taskList.add(t);
                    } else {
                        String date = words[3].trim();
                        LocalDate BY = LocalDate.parse(date);
                        Deadline t = new Deadline(description, BY);
                        t.isDone = isTaskDone;
                        taskList.add(t);
                    }
                }

            }
        } catch(Exception e){
            taskList.clear();
            Ui.markLoadingError();
        }
        return taskList;
    }

    /** Saves the taskList at the termination of the the program */
    public static void saveTaskList(String filePath, ArrayList<Task> taskList) {
        try {
            for (int i = 0; i < taskList.size(); i++){
                Task t = taskList.get(i);
                String description = t.getDescription();
                String typeIcon = t.getTypeIcon();
                String statusIcon = t.getStatusIcon();
                String stringToAdd = Parser.returnDescription(t, description, typeIcon, statusIcon);
                if (i == 0){
                    FileWriter fw = new FileWriter(filePath);
                    fw.write(stringToAdd);
                    fw.write("\n");
                    fw.close();
                } else {
                    FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
                    fw.write(stringToAdd);
                    fw.write("\n");
                    fw.close();

                }
            }
        }
        catch(IOException e){
            //We do not need tp fill this part because we hardcode the known relative path  of the file where the data is saved
        }
    }

}
