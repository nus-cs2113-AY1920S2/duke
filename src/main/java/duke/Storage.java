package duke;

import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.TaskList;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;


public class Storage {

    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String data = scanner.nextLine();
            char task = data.charAt(1);
            char isDone = data.charAt(4);
            if (task == 'T') {
                String todoTask = data.substring(7);
                Task todo = new Todo(todoTask);
                if (isDone == '\u2713') {
                    todo.markAsDone();
                }
                tasks.add(todo);
            } else if (task == 'E') {
                String details = data.substring(7);
                String[] eventDetailsArray = details.split(" \\(at: ");
                String eventDate = eventDetailsArray[1].substring(0, eventDetailsArray[1].length() - 1);
                Task event = new Event(eventDetailsArray[0], eventDate);
                if (isDone == '\u2713') {
                    event.markAsDone();
                }
                tasks.add(event);
            } else if (task == 'D') {
                String details = data.substring(7);
                String[] deadlineDetailsArray = details.split(" \\(by: ");
                String deadlineDate = deadlineDetailsArray[1].substring(0, deadlineDetailsArray[1].length() - 1);
                Task deadline = new Deadline(deadlineDetailsArray[0], deadlineDate);
                if (isDone == '\u2713') {
                    deadline.markAsDone();
                }
                tasks.add(deadline);
            }
        }

        return tasks;
    }

    public void save(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter("output.txt");
            for (Task task : tasks.getTaskArray()) {
                fw.write(task.toString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

}







