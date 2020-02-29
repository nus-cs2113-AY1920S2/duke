import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.*;
import java.util.ArrayList;

public class Storage {

    public static void saveData(ArrayList<Task> Tasks) {
        try {
            File file = new File("duke.txt");
            file.createNewFile();
            FileWriter fileWriter = new FileWriter("duke.txt", false);
            for (Task task : Tasks) {
                fileWriter.write(task.saveTask());
                fileWriter.write(System.lineSeparator());
            }
            fileWriter.close();
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Data cannot be saved.");
        }

    }

    public static ArrayList<Task> openData() {
        ArrayList<Task> Tasks = new ArrayList<Task>();
        try {
            FileReader fileReader = new FileReader("duke.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                String arr[] = line.split("[|]", 4);
                switch (arr[0]) {
                case ("T"):
                    Todo todo = new Todo(arr[2]);
                    if (arr[1].equals("1")) {
                        todo.setDone(true);
                    }
                    Tasks.add(todo);
                    break;
                case ("D"):
                    Deadline deadline = new Deadline(arr[2], arr[3]);
                    if (arr[1].equals("1")) {
                        deadline.setDone(true);
                    }
                    Tasks.add(deadline);
                    break;
                case ("E"):
                    Event event = new Event(arr[2], arr[3]);
                    if (arr[1].equals("1")) {
                        event.setDone(true);
                    }
                    Tasks.add(event);
                    break;
                }
                line = bufferedReader.readLine();
            }
            return Tasks;
        } catch (IOException e) {
            System.out.println("No previous data found.");
            return Tasks;
        }
    }

}
