package duke;

import duke.command.Task;
import duke.command.Deadline;
import duke.command.Event;
import duke.command.Todo;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;


public class Storage {

    private String filePath;
    private static final String TODO_COMMAND = "todo";
    private static final String EVENT_COMMAND = "event";
    private static final String DEADLINE_COMMAND = "deadline";

    public Storage(String filePath) {
        this.filePath = filePath;
    }
    
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String data = scanner.nextLine();
            char task = data.charAt(0);
            char isDone = data.charAt(4);
            if (task == 'T') {
                String todoTask = data.substring(8);
                Task todo = new Todo(todoTask);
                if (isDone == '1') {
                    todo.markAsDone();
                }
                tasks.add(todo);
            } else if (task == 'E') {
                String details = data.substring(8);
                String[] eventDetailsArray = details.split(" \\(at: ");
                String eventDetails = eventDetailsArray[0];
                String eventDate = eventDetailsArray[1].substring(0, eventDetailsArray[1].length() - 1);
                Task event = new Event(eventDetails, eventDate);
                if (isDone == '1') {
                    event.markAsDone();
                }
                tasks.add(event);
            } else if (task == 'D') {
                String details = data.substring(8);
                String[] deadlineDetailsArray = details.split(" \\(by: ");
                String deadlineDetails = deadlineDetailsArray[0];
                String deadlineDate = deadlineDetailsArray[1].substring(0, deadlineDetailsArray[1].length() - 1);
                Task deadline = new Deadline(deadlineDetails, deadlineDate);
                if (isDone == '1') {
                    deadline.markAsDone();
                }
                tasks.add(deadline);
            }
        }

        return tasks;
    }

    public void save(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter("output.txt");
            for (Task task : tasks) {
                if (task.getTaskDescription().equals(TODO_COMMAND)) {
                    String taskTypeDescription = "T |";
                    String taskDoneDescription = "";
                    if (task.isDone()) {
                        taskDoneDescription = " 1 | ";
                    } else {
                        taskDoneDescription = " 0 | ";
                    }
                    String taskDescription = task.getTask();
                    String taskToAdd = taskTypeDescription + taskDoneDescription + taskDescription;
                    fw.write(taskToAdd + System.lineSeparator());
                } else if (task.getTaskDescription().equals(EVENT_COMMAND)) {
                    String taskTypeDescription = "E |";
                    String taskDoneDescription = "";
                    if (task.isDone()) {
                        taskDoneDescription = " 1 | ";
                    } else {
                        taskDoneDescription = " 0 | ";
                    }
                    String taskDescription = task.getTask();
                    String taskAtDescription = task.getAt();
                    String taskToAdd = taskTypeDescription + taskDoneDescription + taskDescription + taskAtDescription;
                    fw.write(taskToAdd + System.lineSeparator());
                } else if (task.getTaskDescription().equals(DEADLINE_COMMAND)) {
                    String taskTypeDescription = "D |";
                    String taskDoneDescription = "";
                    if (task.isDone()) {
                        taskDoneDescription = " 1 | ";
                    } else {
                        taskDoneDescription = " 0 | ";
                    }
                    String taskDescription = task.getTask();
                    String taskByDescription = task.getBy();
                    String taskToAdd = taskTypeDescription + taskDoneDescription + taskDescription + taskByDescription;
                    fw.write(taskToAdd + System.lineSeparator());
                }
            }
            fw.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }


}







