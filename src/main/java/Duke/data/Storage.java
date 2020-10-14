package Duke.data;

import Duke.data.objects.Deadline;
import Duke.data.objects.Event;
import Duke.data.objects.Task;
import Duke.data.objects.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static final String SAVE_FILE_DIRECTORY = System.getProperty("user.dir") + "\\duke.txt";

    public void loadList(TaskList taskList){
        int taskCounter = 0;
        ArrayList<Task> tasks = taskList.getList();
        try {
            File file =
                    new File(SAVE_FILE_DIRECTORY);
            if(file.exists()) {
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    String text = sc.nextLine();
                    String[] scanned = text.split(" \\| ");
                    switch (scanned[0]) {
                    case "T":
                        tasks.add(new ToDo(scanned[2]));
                        if (scanned[1].equals("1")) {
                            tasks.get(taskCounter).markAsDone();
                        }
                        taskCounter++;
                        break;
                    case "D":
                        tasks.add(new Deadline(scanned[2], scanned[3]));
                        if (scanned[1].equals("1")) {
                            tasks.get(taskCounter).markAsDone();
                        }
                        taskCounter++;
                        break;
                    case "E":
                        tasks.add(new Event(scanned[2], scanned[3]));
                        if (scanned[1].equals("1")) {
                            tasks.get(taskCounter).markAsDone();
                        }
                        taskCounter++;
                        break;
                    }
                }
                System.out.println("Duke List loaded.");
            }
        }catch (Exception e){
            System.out.println("Error loading file.");
        }

    }
    public static void saveList(TaskList taskList){
        ArrayList<Task> tasks = taskList.getList();
        try{
            FileWriter fw =new FileWriter(SAVE_FILE_DIRECTORY, false);
            for(int i =0; i<tasks.size();i++){
                String data = "";
                if(tasks.get(i).getType().equals("T")){
                    int dataBoolean = tasks.get(i).getIsDone() ? 1:0;
                    data = tasks.get(i).getType() + " | " + dataBoolean + " | " + tasks.get(i).getDescription() + "\n";
                }else{
                    int dataBoolean = tasks.get(i).getIsDone() ? 1:0;
                    data = tasks.get(i).getType() + " | " + dataBoolean + " | " + tasks.get(i).getDescription()
                            + " | " + tasks.get(i).getExtra() + "\n";
                }
                fw.write(data);
            }
            fw.close();
        }catch (Exception e){
            System.out.println("Writing to file failed.");
        }
    }
}
