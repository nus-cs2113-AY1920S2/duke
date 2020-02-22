package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public Storage(String filePath){
        createTaskList(filePath, TaskList.taskList);
    }


    public static Boolean convertMarkToBool(String mark){
        if (mark.equals("\u2713")){
            return true;
        }
        else {
            return false;
        }
    }

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
                        String[] descriptionAndDate = description.split("at:");
                        String AT = descriptionAndDate[1].trim();
                        Event t = new Event(description, AT);
                        t.isDone = isTaskDone;
                        taskList.add(t);
                    } else {
                        String[] descriptionAndDate = description.split("by:");
                        String BY = descriptionAndDate[1].trim();
                        Deadline t = new Deadline(description, BY);
                        t.isDone = isTaskDone;
                        taskList.add(t);
                    }
                }

            }

        } catch(FileNotFoundException e){
            //Dont need to return anything as we hardcode the name of the file
        }
        return taskList;
    }


    public static void saveTaskList(String filePath, ArrayList<Task> taskList) {
        try {
            for (int i = 0; i < taskList.size(); i++){
                Task t = taskList.get(i);
                String description = t.getDescription();
                String typeIcon = t.getTypeIcon();
                String statusIcon = t.getStatusIcon();
                String stringToAdd = typeIcon + " | " + statusIcon + " | " + description;
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
        }
    }


}
