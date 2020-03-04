package Duke;

import Duke.Tasks.*;
import Duke.UI.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class handling loading of task list saved from previous execution of the Duke program
 * before starting current run of program and saving the most updated task list back to
 * the text file before termination of Duke program.
 */
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

    /**
     * Checks if the task if done.
     *
     * @param t the task.
     * @param num number in the file, "1" indicates task done and "0" indicates not.
     */
    public void checkTaskDone(Task t, String num){
        if (num.equals("1")) {
            t.markAsDone();
        }
    }

    /**
     * Creates the task by parsing each line of String in the text file.
     *
     * @param lineInTextFile line of String from text file.
     * @return the task created.
     */
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

    /**
     * Creates a new list of tasks that matches the one saved
     * in the previous execution by reading from text file.
     *
     * @return list of tasks saved in previous execution.
     * @throws DukeException if an error occurs in file operations.
     */
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

    /**
     * Formats information of a task in the task list to
     * a line of String to be saved in the text file.
     *
     * @param t current task in task list.
     * @return the line of String containing information about the task.
     */
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

    /**
     * Saves an empty file if the list of tasks is empty.
     *
     * @throws IOException if error in file write operation.
     */
    public void saveEmptyFile() throws IOException {
        FileWriter fw = new FileWriter(filepath);
        fw.write("");
        fw.close();
    }

    /**
     * Saves all the tasks in the task list into the text file line by line.
     *
     * @throws IOException if error in file operations.
     */
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
