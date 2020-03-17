package duke.storage;

import duke.commands.Command;
import duke.exceptions.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class to handle loading and storing of data.
 */
public class Storage {
    File file;

    /**
     * Constructor specifying file path.
     * @param filePath the file path to store on hard drive
     * @throws FileNotFoundException If file is not found
     */
    public Storage (String filePath) throws FileNotFoundException {
        file = new File("data/duke_list.txt");
    }

    /**
     * Update list onto hard drive.
     *
     * Creates the file and directory if they do not exist.
     *
     * @param list
     */
    public void updateListDataOnDisk(ArrayList<Command> list) {
        try {
            Files.createDirectory(Paths.get("data"));
        } catch (IOException ignored) {
            //ignored as error would mean that directory exists, thus no action needed
        }
        try {
            Files.createFile(Paths.get("data/duke_list.txt"));
        } catch (IOException ignored) {
            //ignored as error would mean that file exists, thus no action needed
        }
        try {
            FileWriter fw = new FileWriter("data/duke_list.txt");
            list.forEach((n) -> {
                try {
                    fw.write(n.command + System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Loads file from hard drive on start of program.
     *
     * @return list from hard drive
     * @throws FileNotFoundException If file is not found
     */
    public ArrayList <Command> loadListDataFromDisk() throws FileNotFoundException, DukeException {
        ArrayList<Command> list = new ArrayList<Command>();
        checkIntegrity();
        Scanner reader = new Scanner(file);
        while (reader.hasNext()) {
            list.add(new Loader(reader.nextLine()));
        }
        return list;
    }

    /**
     * Checks previously written data file if it follows syntax of Duke.
     *
     * @throws FileNotFoundException
     * @throws DukeException
     */
    private void checkIntegrity() throws FileNotFoundException, DukeException {
        Scanner reader = new Scanner(file);
        while (reader.hasNext()) {
            if (!reader.nextLine().matches("\\[(T|E|D)]\\[(\\s|1)].*")) {
                throw new DukeException("corrupted", 5);
            }
        }
    }
}
