import commands.Task;
import commands.Todo;
import commands.Deadline;
import commands.Event;

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

     public ArrayList<Task> getData() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            String userData = scanner.nextLine();
            char taskType = userData.charAt(0);
            char isDone = userData.charAt(4);

            switch (taskType) {
            case 'T':
                Task todo = new Todo(userData.substring(8));
                if (isDone == '1') {
                    todo.updateIsDone();
                }
                tasks.add(todo);
                break;
            case 'E': {
                String[] description = userData.substring(8).split(" \\(at: ");
                Task event = new Event(description[0], description[1].substring(0, description[1].length() - 1));
                if (isDone == '1') {
                    event.updateIsDone();
                }
                tasks.add(event);
                break;
            }
            case 'D': {
                String[] description = userData.substring(8).split(" \\(by: ");
                Task deadline = new Deadline(description[0], description[1].substring(0, description[1].length() - 1));
                if (isDone == '1') {
                    deadline.updateIsDone();
                }
                tasks.add(deadline);
                break;
            }
            }
        }
        return tasks;
    }

    public void saveData(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter("output.txt");

            for (Task task : tasks){
                String addData;
                switch (task.getTaskDescription()) {
                case "todo":
                    if (task.isDone()) {
                        addData = "T |" + " 1 | " + task.getDescription();
                    } else {
                        addData = "T |" + " 0 | " + task.getDescription();
                    }
                    fw.write(addData + System.lineSeparator());
                    break;
                case "event":
                    if (task.isDone()) {
                        addData = "E |" + " 1 | " + task.getDescription() + task.getAtDescription();
                    } else {
                        addData = "E |" + " 0 | " + task.getDescription() + task.getAtDescription();
                    }
                    fw.write(addData + System.lineSeparator());
                    break;
                case "deadline":
                    if (task.isDone()) {
                        addData = "D |" + " 1 | " + task.getDescription() + task.getByDescription();
                    } else {
                        addData = "D |" + " 0 | " + task.getDescription() + task.getByDescription();
                    }
                    fw.write(addData + System.lineSeparator());
                    break;
                }
            }
            fw.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
