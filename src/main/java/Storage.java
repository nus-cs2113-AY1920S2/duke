import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
        if (num == "1") {
            t.markAsDone();
        }
    }

    public Task createTask(String lineInTextFile) {
        String[] taskParts = lineInTextFile.split(FILE_DIVIDER);
        Task t;
        if (taskParts[TASK_TYPE_PART] == "T") {
            t = new Todo(taskParts[ACTION_PART]);
        } else if (taskParts[TASK_TYPE_PART] == "D") {
            t = new Deadline(taskParts[ACTION_PART], taskParts[DATE_PART]);
        } else {
            t = new Event(taskParts[ACTION_PART], taskParts[DATE_PART]);
        }
        checkTaskDone(t, taskParts[TASK_DONE_PART]);
        return t;
    }

    public void load() throws FileNotFoundException {
        File f = new File(filepath);
        try {
            f.createNewFile();
        } catch (Exception e) {
            System.err.println(e);
        }
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            Task t = createTask(line);
            TaskList.addTask(t);
        }
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
        if (t instanceof Deadline || t instanceof Event) {
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
