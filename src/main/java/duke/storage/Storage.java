package duke.storage;

import duke.data.TaskList;
import duke.format.DateTime;
import duke.format.DateTimeFormat;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;
import duke.exception.CorruptedFileException;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static duke.format.DateTimeFormat.stringToDate;
import static duke.format.DateTimeFormat.stringToTime;

/**
 * <h3>Storage</h3>
 * The <b>Storage</b> manages the operations of the <i>task list file</i>.
 * <br> Specifically, it decodes and loads data from the file into the <b>Task List</b>, and encodes and saves data
 * from the <b>Task List</b> back into the file.
 * @see TaskList
 */
public class Storage {
    private static final String TASK_LIST_PATH = "./data/taskList.txt"; // Relative path of the task list file

    /**
     * Loads the data from the <i>task list file</i> into the <b>Task List</b>.
     *
     * @throws FileNotFoundException If the <i>task list file</i> cannot be found in the path
     * @throws CorruptedFileException If the <i>task list file</i> is corrupted
     * @throws DateTimeFormat.InvalidDateException If the <i>date</i> data in the <i>task list file</i> is corrupted
     * @throws DateTimeFormat.InvalidTimeException If the <i>time</i> data in the <i>task list file</i> is corrupted
     * @see TaskList
     */
    public void loadTaskList()
            throws FileNotFoundException, CorruptedFileException,
            DateTimeFormat.InvalidDateException, DateTimeFormat.InvalidTimeException {
        File taskListFile = new File(TASK_LIST_PATH);
        ArrayList<Task> decodedList = decode(taskListFile);

        for (Task task : decodedList) {
            TaskList.add(task);
        }
    }

    /**
     * Saves the data from the <b>Task List</b> into the <i>task list file</i>.
     *
     * @throws IOException If there is an error saving into the <i>task list file</i>
     * @see TaskList
     */
    public void saveTaskList() throws IOException {
        File taskListFile = new File(TASK_LIST_PATH);

        // Ensure task list file exists
        if (!taskListFile.exists()) {
            createTaskListFile();
        }

        FileWriter writer = new FileWriter(TASK_LIST_PATH);

        for (int i = 0; i < TaskList.size(); ++i) {
            Task task = TaskList.get(i);
            String taskData = encode(task);
            writer.write(taskData + System.lineSeparator());
        }

        writer.close();
    }

    /**
     * Creates a new <b>empty</b> <i>task list file</i> in the path ({@value TASK_LIST_PATH}).
     *
     * @throws IOException If there is an error creating the new file
     */
    public void createTaskListFile() throws IOException {
        File taskListFile = new File(TASK_LIST_PATH);
        taskListFile.getParentFile().mkdirs();
        if (taskListFile.exists()) {
            new FileWriter(TASK_LIST_PATH).close(); // Overwrite file with a new file
        } else {
            taskListFile.createNewFile();
        }
    }

    /**
     * Decodes and interprets the data in the <i>task list file</i> and stores the decoded data into an
     * <code>Array List</code>.
     * <p></p>
     * <b>Note</b>: Data in the <i>task list file</i> <u>cannot</u> be corrupted for successful decoding of data.
     *
     * @param filePath The path of the <i>task list file</i>
     * @return An <code>Array List</code> containing the decoded data
     * @throws CorruptedFileException If the <i>task list file</i> is corrupted
     * @throws FileNotFoundException If the <i>task list file</i> cannot be found in the path
     * @throws DateTimeFormat.InvalidDateException If the <i>date</i> data in the <i>task list file</i> is corrupted
     * @throws DateTimeFormat.InvalidTimeException If the <i>time</i> data in the <i>task list file</i> is corrupted
     */
    private ArrayList<Task> decode(File filePath)
            throws CorruptedFileException, FileNotFoundException,
            DateTimeFormat.InvalidDateException, DateTimeFormat.InvalidTimeException {
        ArrayList<Task> list = new ArrayList<>();

        Scanner fileScanner = new Scanner(filePath);

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine().trim();

            if (line.isEmpty()) {
                break;
            }

            String[] taskData = line.split("__", 5);
            String taskType = taskData[0];
            String doneStatus = taskData[1];
            String taskDescription = taskData[2];
            String dateString = taskData[3];
            String timeString = taskData[4];

            if (!doneStatus.equals("1") && !doneStatus.equals("0")) {
                throw new CorruptedFileException();
            }

            switch (taskType) {
            case "T":
                ToDo newToDoTask = new ToDo(taskDescription);
                newToDoTask.setIsDone(doneStatus.equals("1"));
                list.add(newToDoTask);
                break;
            case "D":
                Deadline newDeadlineTask =
                        new Deadline(taskDescription, new DateTime(stringToDate(dateString), stringToTime(timeString)));
                newDeadlineTask.setIsDone(doneStatus.equals("1"));
                list.add(newDeadlineTask);
                break;
            case "E":
                Event newEventTask =
                        new Event(taskDescription, new DateTime(stringToDate(dateString), stringToTime(timeString)));
                newEventTask.setIsDone(doneStatus.equals("1"));
                list.add(newEventTask);
                break;
            default:
                throw new CorruptedFileException();
            }
        }

        fileScanner.close();
        return list;
    }


    /**
     * Encodes the task data of the specified task into an encoded format to be stored in the <i>task list file</i>.
     *
     * @param task The task to be encoded
     * @return The encoded task data to be stored in the <i>task list file</i>
     * @throws IOException If there is an error encoding the task data
     */
    private String encode(Task task) throws IOException {
        String taskType = getTaskType(task);
        String doneStatus = task.getIsDone() ? "1" : "0";
        String taskDescription = task.getTask();
        String taskDate = (task.getDateTime() != null) ? task.getDateTime().getDate() : null;
        String taskTime = (task.getDateTime() != null) ? task.getDateTime().getTime() : null;

        return String.join("__", new String[]{taskType, doneStatus, taskDescription, taskDate, taskTime});
    }

    /**
     * Returns the short-hand <i>task type</i> of the specified task.
     * <p></p>
     * Short-hand <i>task types</i> are:
     * <br> <b>T</b> for <b>To Do</b> task
     * <br> <b>D</b> for <b>Deadline</b> task, and
     * <br> <b>E</b> for <b>Event</b> task.
     *
     * @param task The task with the <i>task type</i> in question
     * @return The short-hand <i>task type</i> of the specified task
     * @throws IOException If the specified task has an unknown <i>task type</i>
     */
    private String getTaskType(Task task) throws IOException {
        if (task instanceof ToDo) {
            return "T";
        } else if (task instanceof Deadline) {
            return "D";
        } else if (task instanceof Event) {
            return "E";
        } else throw new IOException();
    }
}
