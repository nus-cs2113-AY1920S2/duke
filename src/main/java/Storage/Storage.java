package Storage;

import Exceptions.HardDiskCorruptedException;
import Task.Task;
import Task.Todo;
import Task.Deadline;
import Task.Events;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IndexOutOfBoundsException {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
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
        return taskArrayList;
    }

    public void saveToHardDisk(ArrayList<Task> taskArrayList) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task task : taskArrayList) {
                if (task.getEventType().equals("[T]")) {
                    fileWriter.write(String.format("%s/%s/%s\n", task.getEventType(),
                            task.isDone(), task.getDescription()));
                } else if (task.getEventType().equals("[D]") || task.getEventType().equals("[E]")){
                    fileWriter.write((String.format("%s/%s/%s/%s\n", task.getEventType(),
                            task.isDone(), task.getDescription(), task.getTaskTime())));
                } else {
                    throw new HardDiskCorruptedException("Event type is corrupted");
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (HardDiskCorruptedException e) {
            System.out.println(e);
        }
    }
}
