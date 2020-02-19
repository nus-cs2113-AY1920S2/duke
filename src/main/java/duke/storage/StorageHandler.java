package duke.storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StorageHandler {
    public static void removeLine(int lineNumber, String dataFilePath) throws IOException {
        // Read file into list of strings, where each string is a line in the file
        List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(dataFilePath), StandardCharsets.UTF_8));
        int removedIndex = 0;

        // Remove line from fileContent list
        removedIndex = removeLineFromList(lineNumber, fileContent, removedIndex);

        // Rewrite list into data file
        Files.write(Paths.get(dataFilePath), fileContent, StandardCharsets.UTF_8);

        // Update indexes of subsequent lines
        updateIndexes(fileContent, removedIndex, dataFilePath);
    }

    /**
     * Remove line from a list based on its index. Returns index at which the line was removed.
     * @param lineNumber
     * @param list
     * @param removedIndex
     * @return
     */
    private static int removeLineFromList(int lineNumber, List<String> list, int removedIndex) {
        // Iterate through the lines
        for (int i = 0; i < list.size(); i++) {
            // If the current line matches the taskId
            if (list.get(i).startsWith(String.valueOf(lineNumber))) {
                // Remove current line
                removedIndex = i; // Store index of removed line to use as initial index of next loop
                list.remove(i);
                break;
            }
        }
        return removedIndex;
    }

    private static void updateIndexes(List<String> fileContent, int removedIndex, String dataFilePath) throws IOException {
        // Update indexes of subsequent tasks (e.g. if you remove task 2, task 3 becomes task 2)
        for (int i = removedIndex; i < fileContent.size(); i++)
        {
            String updatedString = decrementIndex(fileContent, i);

            // Replace the line with the updated string
            replaceLine(i+1, updatedString, dataFilePath);
        }
    }

    private static String decrementIndex(List<String> fileContent, int i) {
        // Get current comma separated string
        String currString = fileContent.get(i);

        // Split into different cells
        List<String> cells = Arrays.asList(currString.split(","));

        // Change first cell (taskID) to new ID (taskID-1)
        // Task 3 becomes task 2, task 4 becomes task 3, until end of list
        cells.set(0, String.valueOf(i));

        // Recombine the comma separated string
        return String.join(",", cells);
    }

    public static void replaceLine(int lineNumber, String newString, String dataFilePath) throws IOException {
        // Read file into list of strings, where each string is a line in the file
        List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(dataFilePath), StandardCharsets.UTF_8));

        // Replace line in the list of strings
        replaceLineInList(lineNumber, newString, fileContent);

        // Write new list of strings to file
        Files.write(Paths.get(dataFilePath), fileContent, StandardCharsets.UTF_8);
    }

    private static void replaceLineInList(int lineNumber, String newString, List<String> list) {
        // Iterate through the lines
        for (int i = 0; i < list.size(); i++) {
            // If the current line matches the taskId
            if (list.get(i).startsWith(String.valueOf(lineNumber))) {
                // Replace it with the new task string
                list.set(i, newString);
                break;
            }
        }
    }
}
