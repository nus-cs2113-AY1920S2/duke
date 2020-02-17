package Duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.nio.file.Files;

public abstract class Task {

    public static final String TICK = "✓";
    public static final String CROSS = "✘";

    String workingDir = System.getProperty("user.dir");
    java.nio.file.Path folderPath = java.nio.file.Paths.get(workingDir, "Save");
    java.nio.file.Path filePath = java.nio.file.Paths.get(workingDir, "Save", "data.txt");
    boolean directoryExists = java.nio.file.Files.exists(folderPath);

    protected String description;
    protected String date;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.date = "";
        this.isDone = false;
    }

    public Task(String description, String date) {
        this.description = description;
        this.date = date;
        this.isDone = false;
    }

    public boolean getStatus() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? TICK : CROSS); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getTaskType() {
        return null;
    }

    public void printAddDetails(int taskCounter) {
        System.out.println("The following task has been added:\n[" + getTaskType() +"][" + getStatusIcon() + "] " + getDescription());
        System.out.println("\nYou've got " + taskCounter + " task(s) in the list!\n");
    }

    public abstract void printListDetails(int count);

    public void getSavePath() {
        System.out.println("Addr: " + filePath);
    }

    public void saveTask() {
        // Locate folder location, if missing create folder
        try {
            if (!directoryExists) {
                Files.createDirectory(folderPath);
                System.out.println("Directory created");
            }
        } catch (IOException e) {
            System.out.println("Error creating folder!\n");
        }

        // Append new task to file
        try {
            File file = new File(String.valueOf(filePath));
            FileWriter myWriter = new FileWriter(file, true);

            if (date.isEmpty()) {
                myWriter.write( getTaskType() + " | " + isDone + " | " + description);
            } else {
                myWriter.write( getTaskType() + " | " + isDone + " | " + description + " | " + date);
            }
            myWriter.close();
            System.out.println("Successfully updated data file!\n");
        } catch (IOException e) {
            System.out.println("Error updating file!\n");
        }
    }

}
