package duke.storage;

import duke.exceptions.InvalidDataException;
import duke.tasks.*;
import duke.ui.UI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private TaskList tasklist;
    private UI ui;
    private static String FILE_PATH = "data" + File.separator + "duke.txt";

    public Storage(TaskList tasklist, UI ui) {
        this.tasklist = tasklist;
        this.ui = ui;
        this.loadFile();
    }

    public void loadFile() {
        try {
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
/*
        try {
            FileWriter myWriter = new FileWriter("filename.txt");
            myWriter.write("Files in Java might be tricky, but it is fun enough!");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
*/
}
