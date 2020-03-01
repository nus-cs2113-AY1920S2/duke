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

public class Storage {

    private TaskList tasklist;
    private UI ui;
    private static String FILE_PATH = "data" + File.separator + "duke.txt";
    private static String DIRECTORY_PATH = "data";
    private static String CREATE_DIRECTORY_MESSAGE = "New Directory created: ";
    private static String CREATE_NEW_FILE_MESSAGE = "No existing file is found, new file created: ";
    private static String LOAD_EXISTING_FILE_MESSAGE = "File already exists. Existing data loaded from: ";

    public Storage(TaskList tasklist, UI ui) {
        this.tasklist = tasklist;
        this.ui = ui;
        this.loadFile();
    }

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
                System.out.println(LOAD_EXISTING_FILE_MESSAGE + dukeData.getName());
                this.readFile();
            }
        } catch (IOException e) {
            ui.displayErrorMessage();
        }
        this.ui.displayLineSeparator();
    }

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

    public void clearFile() {
        try {
            PrintWriter pw = new PrintWriter(FILE_PATH);
            pw.close();
        } catch (FileNotFoundException e) {
            ui.displayErrorMessage();
        }
    }

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
