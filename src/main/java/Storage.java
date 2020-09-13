import Tasks.Task;
import Tasks.ToDo;
import Tasks.Deadline;
import Tasks.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class contains methods that handles the loading and
 * writing of the task list from/to the text file.
 */
public class Storage {
    private String filePath;

    /**
     * Class constructor of the <code>Storage</code> class.
     * @param filePath The location of the text file which contains task list from the previous session.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns <code>ArrayList</code> of <code>Task</code> previously stored in the text file.
     * @return Previously stored task list.
     * @throws IOException If issues were encountered during loading of data.
     */
    public ArrayList<Task> loadFromFile() throws IOException {
        ArrayList<Task> savedTaskList = new ArrayList<>();

        try {
            File f = new File(filePath);
            File directory = new File("data");

            if (!directory.exists()) {
                directory.mkdir();
            }
            if (!f.exists()) {
                f.createNewFile();
            }
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] words = line.split(",");
                switch (words[0]) {
                    case "T":
                        savedTaskList.add(new ToDo(words[2]));
                        break;
                    case "D":
                        savedTaskList.add(new Deadline(words[2], words[3]));
                        break;
                    case "E":
                        savedTaskList.add(new Event(words[2], words[3]));
                        break;
                }
                if (words[1].equals("x")) {
                    savedTaskList.get(savedTaskList.size() - 1).setDone(true);
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("\t ☹ OOPS!!! File is not found!");
        }
        return savedTaskList;
    }

    /**
     * Saves the current task list into the text file.
     * @param taskList <code>ArrayList</code> of <code>Task</code> to be written.
     * @throws IOException If issues were encountered during writing of data.
     */
    public void writeToFile(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt");
        for (Task t : taskList) {
            fw.write(t.toFileString() + System.lineSeparator());
        }
        fw.close();
    }

}
