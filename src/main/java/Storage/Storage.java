package Storage;

import Exceptions.HardDiskCorruptedException;
import Task.Task;
import Task.Todo;
import Task.Deadline;
import Task.Events;
import TaskList.TaskList;

import java.io.*;

import java.util.ArrayList;

public class Storage {
    protected String filePath;
    protected String dirPath;
    protected File file;

    public Storage() {
        this.filePath = "duke.txt";
        this.dirPath = "data";

        if (new File(dirPath).mkdir()) {
            System.out.println("No existing data dir found, creating new data dir!");
        }
        this.file = new File(dirPath, filePath);
    }

    public ArrayList<Task> load() throws IndexOutOfBoundsException, FileNotFoundException {
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
                    case "[T]":
                        Todo todo = new Todo(taskDescription);
                        if (isDone.equals("Y")) {
                            todo.markAsDone();
                        }
                        taskArrayList.add(todo);
                        break;
                    case "[D]":
                        Deadline deadline = new Deadline(taskDescription, parseLine[3]);
                        if (isDone.equals("Y")) {
                            deadline.markAsDone();
                        }
                        taskArrayList.add(deadline);
                        break;
                    case "[E]":
                        Events event = new Events(taskDescription, parseLine[3]);
                        if (isDone.equals("Y")) {
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

    private void writeToDataFile(TaskList taskArrayList, FileWriter fileWriter) throws IOException, HardDiskCorruptedException {
        for (Task task : taskArrayList.getTaskList()) {
            if (isToDo(task)) {
                fileWriter.write(String.format("%s/%s/%s\n", task.getEventType(),
                        task.isDone(), task.getDescription()));
            } else if (isDeadline(task) || isEvent(task)){
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
