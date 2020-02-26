package duke.storage;

import duke.data.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static final String FILE_PATH = "C:\\Users\\nyanw\\Documents\\y2s2\\2113T\\duke\\data\\duke.txt";

    /**
     * Saves the {@code duke} data to the storage file.
     *
     * @throws IOException if there were errors converting and/or storing data to file.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        try {
            FileWriter fw = new FileWriter(FILE_PATH, false);

            for (Task i : tasks) {
                fw.write(i.toOutput());
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Loads the {@code TaskList} data from this storage file, and then returns it.
     * Returns an empty {@code TaskList} if the file does not exist, or is not a regular file.
     */
    public TaskList load() throws FileNotFoundException {

        if (!Files.exists(Paths.get(FILE_PATH)) || !Files.isRegularFile(Paths.get(FILE_PATH))) {
            return new TaskList();
        }

        TaskList tasks = new TaskList();
        File f = new File(FILE_PATH); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source

        while (s.hasNext()) {
            String fileInput = s.nextLine();
            String[] fileData = fileInput.split("[|]");
            switch (fileData[0].trim()) {
            case ("E"):
                tasks.add(new Event(fileData[2].trim(), fileData[3].trim()));
                break;
            case ("D"):
                tasks.add(new Deadline(fileData[2].trim(), fileData[3].trim()));
                break;
            case ("T"):
                tasks.add(new ToDo(fileData[2].trim()));
                break;
            }
            if (Boolean.parseBoolean(fileData[1])) {
                tasks.get(tasks.size() - 1).taskDone();
            }
        }
        return tasks;
    }
}
