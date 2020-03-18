package duke.storage;

import duke.exceptions.WhitespaceExceptions;
import duke.taskManager.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static duke.Duke.FILE_PATH;

public class Storage {
    ArrayList<Task> tasksArray = new ArrayList<>();
    private String pathName;

    public Storage(String pathName) {
        this.pathName = pathName;
    }

    /**
     * Import task from file when program starts loading
     *
     * @return taskArray
     */
    public ArrayList<Task> importTaskFromFile() {
        Path filePath = Paths.get("data");
        try {
            if (!Files.exists(filePath)) {
                createNewDirectories(filePath);
            }
            scanFromFile();
        } catch (FileNotFoundException e) {
            System.out.println("File not found exception");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("\n\n    Error occurred while loading file. Please try again!\n\n");
        }
        return tasksArray;
    }

    private void scanFromFile() throws FileNotFoundException {
        File file = new File(pathName);
        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] TaskDescriptions = line.split("\\|");
            switch (TaskDescriptions[0]) {
                case "T":
                    tasksArray.add(new Todo(TaskDescriptions[2]));
                    if (Integer.parseInt(TaskDescriptions[1]) == 1) {
                        tasksArray.get(tasksArray.size() - 1).importDone();
                    }
                    break;

                case "D":
                    tasksArray.add(new Deadline(TaskDescriptions[2], TaskDescriptions[3]));
                    if (Integer.parseInt(TaskDescriptions[1]) == 1) {
                        tasksArray.get(tasksArray.size() - 1).importDone();
                    }
                    break;

                case "E":
                    tasksArray.add(new Events(TaskDescriptions[2], TaskDescriptions[3]));
                    if (Integer.parseInt(TaskDescriptions[1]) == 1) {
                        tasksArray.get(tasksArray.size() - 1).importDone();
                    }
                    break;
            }
        }
    }

    private void createNewDirectories(Path filePath) throws IOException {
        Files.createDirectory(filePath);
        System.out.println("path created!");
        FileWriter fileWriter = new FileWriter(pathName);
        fileWriter.write("");
        fileWriter.close();
        System.out.println("tasklist.txt created!");
    }


    /**
     * Write new task created to Tasklist.txt
     *
     * @param str1 function name of the current action
     * @param str2 A String description of the task
     */
    public ArrayList<Task> writeToFile(String str1, String str2) {
        try {
            FileWriter fw = new FileWriter(pathName, true);
            if (str1.contains("todo")) {
                String newLineFormatted = str2.trim();
                fw.write("T|0|" + newLineFormatted + System.lineSeparator());
                fw.close();
            }
            if (str1.contains("deadline")) {
                String[] newLineFormatted = str2.split("/by");
                fw.write("D|0|" + newLineFormatted[0].trim() + "|" + newLineFormatted[1].trim() + System.lineSeparator());
                fw.close();
            }
            if (str1.contains("event")) {
                String[] newLineFormatted = str2.split("/at");
                fw.write("E|0|" + newLineFormatted[0].trim() + "|" + newLineFormatted[1].trim() + System.lineSeparator());
                fw.close();
            }
        } catch (IOException e) {
            System.out.println("Error while writing into file. Please try again! Your task is not saved in tasklist.txt!");
        }
        return tasksArray;
    }

    /**
     * Append to a task in Tasklist.txt
     *
     * @param line String description of task from the txt file
     * @throws WhitespaceExceptions If there is undefined or additional whitespaces in the txt file
     */
    public ArrayList<Task> appendToFile(String line) {
        FormatDescriptions newLine = new FormatDescriptions();
        try {
            newLine.format(line);
        } catch (WhitespaceExceptions e) {
            e.printStackTrace();
        }

        File fileToBeModified = new File(pathName);
        String originalFileContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            String currentReadingLine = reader.readLine();
            String oldString = null;
            String newString = null;

            while (currentReadingLine != null) {
                if (currentReadingLine.contains(newLine.getDescription())) {
                    oldString = currentReadingLine;
                    newString = newLine.getTaskSymbol() + 1 + "|" + newLine.getDescription() + "|" + newLine.getTimeDate();
                }
                originalFileContent += currentReadingLine + System.lineSeparator();
                currentReadingLine = reader.readLine();
            }
            String newFileContent = originalFileContent.replace(oldString, newString);
            writer = new FileWriter(fileToBeModified);
            writer.write(newFileContent);

        } catch (IOException e) {
            System.out.println("Error appending to file! Please try again!");
        } finally {
            try {
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tasksArray;
    }

    /**
     * Delete a task from Tasklist.txt
     *
     * @param line String description of task from the txt file
     * @throws WhitespaceExceptions If there is undefined or additional whitespaces in the txt file
     */
    public ArrayList<Task> deleteToFile(String line) {
        FormatDescriptions newLine = new FormatDescriptions();
        try {
            newLine.format(line);
        } catch (WhitespaceExceptions e) {
            e.printStackTrace();
        }
        File fileToBeModified = new File(pathName);
        String originalFileContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            String currentReadingLine = reader.readLine();
            String oldString = null;
            String newString = null;

            while (currentReadingLine != null) {
                if (currentReadingLine.contains(newLine.getDescription())) {
                    oldString = currentReadingLine;
                    newString = "";
                }
                originalFileContent += currentReadingLine + System.lineSeparator();
                currentReadingLine = reader.readLine();
            }
            String newFileContent = originalFileContent.replace(oldString, newString);
            writer = new FileWriter(fileToBeModified);
            writer.write(newFileContent);

        } catch (IOException e) {
            System.out.println("Error appending to file! Please try again!");
        } finally {
            try {
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tasksArray;
    }
}
