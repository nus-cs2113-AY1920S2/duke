import Features.Task;
import Exception.DukeException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Storage {
    private File f;

    public Storage(File userFile) {
        this.f = userFile;
    }

    public void saveToFile(ArrayList<Task> userList) throws IOException {
        FileWriter fw = new FileWriter(f);
        for (Task elem : userList) {
            String toSave = elem.getType() + " , " + elem.getIsDone() + " , " + elem.getDescription();
            if (!elem.getType().equals("Todo")) {
                toSave = toSave + " , " + elem.getTimeToComplete();
            }
            fw.write(toSave + "\n");
        }
        fw.close();
    }
    public ArrayList<Task> loadFromFile() throws IOException {
        if (f.exists()) {
            try {
                return taskFromFile();
            } catch (DukeException | FileNotFoundException e) {
                System.out.println("OOPS! " + e);
            }
        } else {
            f.createNewFile();
        }
        return new ArrayList<>();
    }
    public ArrayList<Task> taskFromFile() throws FileNotFoundException, DukeException {
        Scanner s = new Scanner(f);
        ArrayList<Task> savedUserList = new ArrayList<>();
        String currTaskString = ""; //Follows format: TASK | 1 | DESCRIPTION | TIME
        while (s.hasNext()) {
            currTaskString = s.nextLine();
            ArrayList<String> savedString = Parser.convertStringToArr(currTaskString, " , ");
            ArrayList<String> savedDescription = new ArrayList<String>();
            savedDescription.add(savedString.get(2));
            Task taskToLoad;
            switch(savedString.get(0)) {
                case "Todo":
                    taskToLoad = TaskList.newTodo(savedDescription);
                    break;
                case "Deadline":
                    savedDescription.add("/by");
                    savedDescription.add(savedString.get(3));
                    taskToLoad = TaskList.newDeadline(savedDescription);
                    break;
                case "Event":
                    savedDescription.add("/at");
                    savedDescription.add(savedString.get(3));
                    taskToLoad = TaskList.newEvent(savedDescription);
                    break;
                default:
                    throw new DukeException("File is corrupted. Unable to find task name.");
            }
            taskToLoad.setIsDone(Boolean.parseBoolean(savedString.get(1)));
            savedUserList.add(taskToLoad);
        }
        return savedUserList;
    }
}
