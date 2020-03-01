package duke.storage;

import duke.tasklist.TaskList;
import duke.exceptions.ChatboxException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private String dirPath;
    private String filePath;
    
    private List<Task> fileTasks = new ArrayList<>();

    /**
     * Defines the constructor.
     * Specifies the location of the txt file.
     * 
     * @param dirPath
     * @param fileName
     */
    public Storage(String dirPath, String fileName) {
        this.dirPath = dirPath;
        this.filePath = dirPath + File.separator + fileName;
    }

    /**
     * Loads the content of the txt files into the task list.
     * Prints the task list to the screen.
     * Creates the directory and the file if the directory and file do not exist.
     * 
     * @return ArrayList of tasks read from the txt file.
     * @throws IOException If error occurs during creating and loading the txt file.
     * @throws ChatboxException If the txt file is empty at the first place.
     */
    public List<Task> loadFileContents() throws IOException, ChatboxException {
        if (dirPath != "") {
            File directory = new File(dirPath);
            if (!directory.exists()) { // create the folder
                directory.mkdir();
            }
        }

        File f = new File(filePath); // create a File for the given file path
        if (!f.exists()) {
            f.createNewFile();
        }

        System.out.println("Tasks in the existing list: ");
        
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        
        if (!s.hasNext()) { // empty list
            throw new ChatboxException();
        }

        while (s.hasNext()) {
            String taskLine = s.nextLine();
            System.out.println(taskLine);
            saveStringtoTask(taskLine);
        }
        
        return fileTasks;
    }

    /**
     * Converts the task string from the txt file to Task object.
     * Adds the converted tasks into the ArrayList of Task.
     * 
     * @param fileLine Task string read from the txt file.
     */
    public void saveStringtoTask(String fileLine) {
        String[] arrays = fileLine.split("\\|");
        String type = arrays[0].trim();
        String isDone = arrays[1].trim();
        String description = arrays[2].trim();
        switch (type) {
        case "T":
            Task todo = new Todo(description);
            if (isDone == "1") {
                todo.markAsDone();
            }
            fileTasks.add(todo);
            break;
        case "D":
            String by = arrays[3].trim();
            LocalDate byDate = LocalDate.parse(by);
            Task deadline = new Deadline(description, byDate);
            if (isDone == "1") {
                deadline.markAsDone();
            }
            fileTasks.add(deadline);
            break;
        case "E":
            String at = arrays[3].trim();
            LocalDate atDate = LocalDate.parse(at);
            Task event = new Event(description, atDate);
            if (isDone == "1") {
                event.markAsDone();
            }
            fileTasks.add(event);
            break;
        }
    }

    /**
     * Writes the all tasks inside the task list to the txt file whenever the task list changes.
     * 
     * @param taskList Existing list of tasks.
     * @throws IOException If error occurs during opening the txt file and writing to it.
     */
    public void updateTasksToFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        int listSize = taskList.getListSize();
        for (int i = 1; i <= listSize; i++) {
            fw.write(taskList.getTask(i).getFileString() + System.lineSeparator());
        }
        fw.close();
    }
}
