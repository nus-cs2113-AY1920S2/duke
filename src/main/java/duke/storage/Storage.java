package duke.storage;

import duke.exceptions.InvalidDataException;
import duke.tasks.*;
import duke.ui.MessageBank;
import duke.ui.UI;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Storage is the public class responsible for creating and managing the data files generated from the application.
 */

public class Storage {

    /**
     * The object containing the list containing all current tasks.
     */

    private TaskList tasklist;

    /**
     * The object containing user interface functions.
     */

    private UI ui;

    /**
     * The file path of the data file that contains tasklist information.
     */

    private static String FILE_PATH = "data" + File.separator + "duke.txt";

    /**
     * The file path of the directory that contains the data file.
     */

    private static String DIRECTORY_PATH = "data";

    /**
     * Message to notify user that a new directory is created.
     */

    private static String CREATE_DIRECTORY_MESSAGE = "New Directory created: ";

    /**
     * Message to notify user that there is no existing data file and that a new data file is created.
     */

    private static String CREATE_NEW_FILE_MESSAGE = "No existing file is found, new file created: ";

    /**
     * Message to notify user that there is an existing data file and that it is loaded into the tasklist.
     */

    private static String LOAD_EXISTING_FILE_MESSAGE = "File already exists. Existing data loaded from: ";

    /**
     * Constructs the Storage object.
     * @param tasklist the object containing the list containing all current tasks.
     * @param ui the object containing user interface functions.
     */

    public Storage(TaskList tasklist, UI ui) {
        this.tasklist = tasklist;
        this.ui = ui;
        this.loadFile();
    }

    /**
     * Firstly, searches for the data directory, if absent, creates a new directory. <br>
     * Secondly, searches for the data file in the directory, if absent, creates a new data file. <br>
     * If data file is present, loads the existing data from the file into the tasklist
     * such that it is accessible by the user.
     */

    public void loadFile() {
        this.ui.displayLineSeparator();
        try {
            Path directoryPath = Paths.get(DIRECTORY_PATH);
            if (!Files.exists(directoryPath)) {
                Files.createDirectory(directoryPath);
                System.out.println(CREATE_DIRECTORY_MESSAGE + directoryPath.getFileName());
            }
            File dukeData = new File(FILE_PATH);
            if (dukeData.createNewFile()) {
                System.out.println(CREATE_NEW_FILE_MESSAGE + dukeData.getName());
            } else {
                this.readFile();
                System.out.println(LOAD_EXISTING_FILE_MESSAGE + dukeData.getName());
            }
        } catch (IOException e) {
            ui.displayErrorMessage();
        }
        this.ui.displayLineSeparator();
    }

    /**
     * Reads the data file and parses the existing data in the file, converting it into tasks which is added into
     * the tasklist such that it is accessible by the user.
     */

    public void readFile() {
        try {
            File dukeData = new File(FILE_PATH);
            Scanner myReader = new Scanner(dukeData);
            while (myReader.hasNextLine()) {
                String dataLine = myReader.nextLine();
                String[] dataLineArray = dataLine.trim().split(" ", 3);
                String taskType = dataLineArray[0];
                Task task;
                switch (taskType) {
                    case "[T]":
                        String todoDesc = dataLineArray[2];
                        task = new ToDo(todoDesc);
                        break;
                    case "[E]":
                        String[] eventInfo = dataLineArray[2].split("at:", 2);
                        String eventDesc = eventInfo[0].replaceAll("[\\\\[\\\\](){}]", "").trim();
                        String eventAt = eventInfo[1].replaceAll("[\\\\[\\\\](){}]", "").trim();
                        task = new Event(eventDesc, eventAt);
                        break;
                    case "[D]":
                        String[] deadlineInfo = dataLineArray[2].split("by:", 2);
                        String deadlineDesc = deadlineInfo[0].replaceAll("[\\\\[\\\\](){}]", "").trim();
                        String deadlineBy = deadlineInfo[1].replaceAll("[\\\\[\\\\](){}]", "").trim();
                        task = new Deadline(deadlineDesc, deadlineBy);
                        break;
                    default:
                        throw new InvalidDataException();
                }
                if (dataLineArray[1].equals("[/]")) {
                    task.setDone(true);
                } else {
                    task.setDone(false);
                }
                tasklist.getTaskList().add(task);
            }
            myReader.close();
        } catch (FileNotFoundException | InvalidDataException e) {
            ui.displayErrorMessage();
        }
    }

    /**
     * Clears all content in the data file.
     */

    public void clearFile() {
        try {
            PrintWriter pw = new PrintWriter(FILE_PATH);
            pw.close();
        } catch (FileNotFoundException e) {
            ui.displayErrorMessage();
        }
    }

    /**
     * Rewrites the data file to reflect the current data in the tasklist.
     */

    public void rewriteFile() {
        try {
            FileWriter myWriter = new FileWriter(FILE_PATH, false);
            for (Task task : tasklist.getTaskList()) {
                myWriter.write(task.toString() + MessageBank.NEW_LINE);
            }
            myWriter.close();
        } catch (IOException e) {
            ui.displayErrorMessage();
        }
    }

    /**
     * Appends a new line of data into the data file.
     * @param task The task to be written to the file.
     */

    public void writeFileLine(Task task) {
        try {
            BufferedWriter myWriter = new BufferedWriter(new FileWriter(FILE_PATH, true));
            myWriter.write(task.toString() + MessageBank.NEW_LINE);
            myWriter.close();
        } catch (IOException e) {
            ui.displayErrorMessage();
        }
    }

}
