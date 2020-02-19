package duke.storage;

import duke.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Storage {
    public String dataFilePath;
    public File dataFile;

    public Storage(String dataFilePath) {
        this.dataFilePath = dataFilePath;
        dataFile = new File(dataFilePath);
    }

    public void writeToFile(String s) throws IOException {
        FileWriter fw = new FileWriter(dataFilePath, true);
        fw.write(s + System.lineSeparator());
        fw.close();
    }

    // Return true if file previously existed, false if it didn't exist (and it created a new one)
    public boolean loadFile() {

        // Create data file if it does not exist already
        if (!dataFile.exists()) {
            try  {
                dataFile.getParentFile().mkdirs(); // Create data directory (does nothing if directory already exists)
                dataFile.createNewFile();
                return false;
            } catch (IOException e) {
                Ui.formatPrint("Error loading data file.");
            }
        }
        return true;
    }

    public void removeLine(int lineNumber) throws IOException {
        // Read file into list of strings, where each string is a line in the file
        List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(dataFilePath), StandardCharsets.UTF_8));
        int removedIndex = 0;

        // Iterate through the lines
        for (int i = 0; i < fileContent.size(); i++) {
            // If the current line matches the taskId
            if (fileContent.get(i).startsWith(String.valueOf(lineNumber))) {
                // Remove current line
                removedIndex = i; // Store index of removed line to use as initial index of next loop
                fileContent.remove(i);
                break;
            }
        }

        // Rewrite list into data file
        Files.write(Paths.get(dataFilePath), fileContent, StandardCharsets.UTF_8);

        // Update indexes of subsequent tasks (e.g. if you remove task 2, task 3 becomes task 2)
        for (int i = removedIndex; i < fileContent.size(); i++)
        {
            // Get current comma separated string
            String currString = fileContent.get(i);
            // Split into different cells
            List<String> cells = Arrays.asList(currString.split(","));
            // Change first cell (taskID) to new ID (taskID-1)
            // Task 3 becomes task 2, task 4 becomes task 3, until end of list
            cells.set(0, String.valueOf(i));
            // Recombine the comma separated string
            String updatedString = String.join(",", cells);
            // Replace the line with the updated string
            replaceLine(i+1, updatedString);
        }

    }

    public void replaceLine(int lineNumber, String newString) throws IOException {
        // Read file into list of strings, where each string is a line in the file
        List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(dataFilePath), StandardCharsets.UTF_8));

        // Iterate through the lines
        for (int i = 0; i < fileContent.size(); i++) {
            // If the current line matches the taskId
            if (fileContent.get(i).startsWith(String.valueOf(lineNumber))) {
                // Replace it with the new task string
                fileContent.set(i, newString);
                break;
            }
        }

        Files.write(Paths.get(dataFilePath), fileContent, StandardCharsets.UTF_8);
    }
}
