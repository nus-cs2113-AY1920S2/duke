package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String FILE_PATH;
    private String DIR_PATH;
    private File f;

    public Storage(String filePath){
        this.FILE_PATH = filePath;
        this.DIR_PATH = FILE_PATH.substring(0,FILE_PATH.indexOf("/"));
        this.f = new File(FILE_PATH);
    }

    public void loadFileData(TaskList tasks) throws FileNotFoundException {
        checkDirectory();
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String record = s.nextLine().replace("[", "")
                    .replace("]", "");
            restoreTasks(record,tasks);
        }
    }

    private void checkDirectory() {
        File dir = new File(DIR_PATH);
        if(!dir.exists()){
            dir.mkdir();
        }
    }

    public void restoreTasks(String record,TaskList tasks) {
        String taskType = record.substring(0, 1);
        String taskStatus = "["+record.substring(1, 2)+"]";
        String taskName = "";
        String timeDescription = "";
        if (record.contains("(")) {
            taskName = record.substring(2,record.indexOf('(')).trim();
            timeDescription = record.substring(record.indexOf(":") + 1).replace(")","").trim();
        } else{
            taskName = record.substring(2).trim();
        }
        switch (taskType) {
        case "T":
            tasks.append(new ToDo(taskName,taskStatus));
            break;
        case "E":
            tasks.append(new Event(taskName, timeDescription,taskStatus));
            break;
        case "D":
            tasks.append(new Deadline(taskName, timeDescription,taskStatus));
            break;
        }
    }

    public void saveToFile(TaskList tasks){
        try {
            rewriteFile(tasks);
        } catch (IOException e) {
            Ui.printIoWrongInfo();
        }
    }

    private void rewriteFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (int i = 0; i < TaskList.getLenOfList(); i++) {
            Task cur_task = tasks.getOneTask(i);
            String taskInfo = cur_task.showTaskInfo();
            fw.write(taskInfo+System.lineSeparator());
        }
        fw.close();
    }

}
