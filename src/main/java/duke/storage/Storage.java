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
                System.out.println("New Directory created: " + directoryPath.getFileName());
            }
            File dukeData = new File(FILE_PATH);
            if (dukeData.createNewFile()) {
                System.out.println("No existing file is found, new file created: " + dukeData.getName());
            } else {
                System.out.println("File already exists. Existing data loaded from: " + dukeData.getName());
                this.readFile();
            }
        } catch (IOException e) {
            ui.displayErrorMessage();
            e.printStackTrace();
        }
        this.ui.displayLineSeparator();
    }

    public void readFile() {
        try {
            File dukeData = new File(FILE_PATH);
            Scanner myReader = new Scanner(dukeData);
            while (myReader.hasNextLine()) {
                String dataLine = myReader.nextLine();
                String[] dataLineArray = dataLine.trim().split(" ");
                String taskType = dataLineArray[0];
                Task task;
                switch (taskType) {
                    case "[T]":
                        task = new ToDo(dataLineArray[2]);
                        break;
                    case "[E]":
                        task = new Event(dataLineArray[2], dataLineArray[3]);
                        break;
                    case "[D]":
                        task = new Deadline(dataLineArray[2], dataLineArray[3]);
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
            e.printStackTrace();
        }
    }

    public void clearFile() {
        try {
            PrintWriter pw = new PrintWriter(FILE_PATH);
            pw.close();
        } catch (FileNotFoundException e) {
            ui.displayErrorMessage();
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

    public void writeFileLine(Task task) {
        try {
            BufferedWriter myWriter = new BufferedWriter(new FileWriter(FILE_PATH, true));
            myWriter.write(task.toString() + MessageBank.NEW_LINE);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            ui.displayErrorMessage();
            e.printStackTrace();
        }
    }

}
