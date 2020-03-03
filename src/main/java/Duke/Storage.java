package Duke;

import Duke.Tasks.*;
import Duke.UI.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    protected static String filepath;
    protected static final String FILE_DIVIDER = "|";
    protected static final int TASK_TYPE_PART = 0;
    protected static final int TASK_DONE_PART = 1;
    protected static final int ACTION_PART = 2;
    protected static final int DATE_PART = 3;

    public Storage (String filepath) {
        this.filepath = filepath;
    }

    public void checkTaskDone(Task t, String num){
        if (num.equals("1")) {
            t.markAsDone();
        }
    }


    public Task createTask(String lineInTextFile) {
        String[] taskParts = lineInTextFile.split("\\|");
        if (taskParts.length == 4) {
            if (taskParts[TASK_TYPE_PART].equals("D")) {
                Task t = new Deadline(taskParts[ACTION_PART], taskParts[DATE_PART]);
                checkTaskDone(t, taskParts[TASK_DONE_PART]);
                return t;
            } else {
                Task t = new Event(taskParts[ACTION_PART], taskParts[DATE_PART]);
                checkTaskDone(t, taskParts[TASK_DONE_PART]);
                return t;
            }
        }
        Task t = new Todo(taskParts[ACTION_PART]);
        checkTaskDone(t, taskParts[TASK_DONE_PART]);
        return t;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        File f = new File(filepath);
        try {
            f.createNewFile();
            Ui.out.println(filepath);
        } catch (Exception e) {
            throw new DukeException();
        }
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new DukeException();
        }
        while (s.hasNext()) {
            String line = s.nextLine();
            Task t = createTask(line);
            tasks.add(t);
        }
        return tasks;
    }

    public String getTaskType (Task t){
        return t.taskType;
    }

    public String getTaskComplete (Task t){
        if (t.isDone) {
            return "1";
        } else {
            return "0";
        }
    }

    public String getAction (Task t){
        return t.action;
    }

    public String getDate (Task t) {
        return t.date;
    }

    public String getLineInSavedFile(Task t) {
        String lineInSavedFile;
        lineInSavedFile = getTaskType(t) + FILE_DIVIDER + getTaskComplete(t)
                + FILE_DIVIDER + getAction(t);
        if (getTaskType(t).equals("D") || getTaskType(t).equals("E")) {
            lineInSavedFile += (FILE_DIVIDER + getDate(t));
        }
        lineInSavedFile += "\n";
        return lineInSavedFile;
    }

    public void saveEmptyFile() throws IOException {
        FileWriter fw = new FileWriter(filepath);
        fw.write("");
        fw.close();
    }

    //save taskList to text file before end program
    public void save() throws IOException {
        if (TaskList.getSize() == 0) {
            saveEmptyFile();
            return;
        }
        for (int i = 0; i < TaskList.getSize(); i++) {
            FileWriter fw;
            if (i == 0) {
                fw = new FileWriter(filepath);
            } else {
                fw = new FileWriter(filepath, true);
            }
            String lineInTextFile = getLineInSavedFile(TaskList.fetchTask(i));
            fw.write(lineInTextFile);
            fw.close();
        }
    }


}
