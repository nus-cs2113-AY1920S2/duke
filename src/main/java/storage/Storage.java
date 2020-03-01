package storage;

import exceptions.HardDiskCorruptedException;
import task.Task;
import task.Todo;
import task.Deadline;
import task.Events;
import tasklist.TaskList;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Manages the loading and saving of all data
 */
public class Storage {
    private static final String TODO = "[T]";
    private static final String DEADLINE = "[D]";
    private static final String EVENT = "[E]";
    protected String filePath;
    protected String dirPath;
    protected File file;

    /**
     * Construct Storage object
     * Creates directory if not found
     */
    public Storage() {
        this.filePath = "duke.txt";
        this.dirPath = "data";

        if (new File(dirPath).mkdir()) {
            System.out.println("No existing data directory found. Creating new data directory!");
        }
        this.file = new File(dirPath, filePath);
    }

    /**
     * Returns a ArrayList of Task objects saved in the data file
     * @return ArrayList of Task objects saved in data file
     * @throws IndexOutOfBoundsException throws when file operations fails
     * @throws FileNotFoundException throws when file is not found
     * @throws HardDiskCorruptedException throws when data file data is corrupted
     */
    public ArrayList<Task> load() throws IndexOutOfBoundsException,
            FileNotFoundException, HardDiskCorruptedException {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        if (!file.exists()) {
            throw new FileNotFoundException();
        } else {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    String[] parseLine = line.split("/");
                    if (parseLine.length < 3) {
                        throw new HardDiskCorruptedException("HDD data corrupted!");
                    }
                    String taskType = parseLine[0];
                    String isDone = parseLine[1];
                    String taskDescription = parseLine[2];

                    switch (taskType) {
                    case TODO:
                        Todo todo = new Todo(taskDescription);
                        if (isDone(isDone)) {
                            todo.markAsDone();
                        }
                        taskArrayList.add(todo);
                        break;
                    case DEADLINE:
                        Deadline deadline = new Deadline(taskDescription, parseLine[3]);
                        if (isDone(isDone)) {
                            deadline.markAsDone();
                        }
                        taskArrayList.add(deadline);
                        break;
                    case EVENT:
                        Events event = new Events(taskDescription, parseLine[3]);
                        if (isDone(isDone)) {
                            event.markAsDone();
                        }
                        taskArrayList.add(event);
                        break;
                    default:
                        throw new HardDiskCorruptedException("Event type is corrupted!");
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            } catch (HardDiskCorruptedException e) {
                System.out.println(e);
            }
        }
        return taskArrayList;
    }

    private boolean isDone(String isDone) {
        return isDone.equals("Y");
    }

    /**
     * Saves all Task in TaskList to the data file
     * @param taskArrayList TaskList of current Tasks
     */
    public void saveToHardDisk(TaskList taskArrayList) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            writeToDataFile(taskArrayList, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (HardDiskCorruptedException e) {
            System.out.println(e);
        }
    }

    /**
     * Writes Task data in TaskList to the data file in proper formatting
     * @param taskArrayList TaskList of current Tasks
     * @param fileWriter Object that writes to the data file
     * @throws IOException throws when writing to data file fails
     * @throws HardDiskCorruptedException throws when task data is corrupted in current TaskList
     */
    private void writeToDataFile(TaskList taskArrayList, FileWriter fileWriter)
            throws IOException, HardDiskCorruptedException {
        for (Task task : taskArrayList.getTaskList()) {
            if (isToDo(task)) {
                fileWriter.write(String.format("%s/%s/%s\n", task.getEventType(),
                        task.isDone(), task.getDescription()));
            } else if (isDeadline(task) || isEvent(task)) {
                fileWriter.write((String.format("%s/%s/%s/%s\n", task.getEventType(),
                        task.isDone(), task.getDescription(), task.getTaskTime())));
            } else {
                throw new HardDiskCorruptedException("File writing is corrupted!");
            }
        }
    }

    private boolean isToDo(Task task) {
        return task.getEventType().equals("[T]");
    }

    private boolean isDeadline(Task task) {
        return task.getEventType().equals("[D]");
    }

    private boolean isEvent(Task task) {
        return task.getEventType().equals("[E]");
    }
}
