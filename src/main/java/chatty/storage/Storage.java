package chatty.storage;

import chatty.task.Deadline;
import chatty.task.Event;
import chatty.task.Task;
import chatty.task.TaskList;
import chatty.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.Scanner;

import static chatty.util.Constants.DEFAULT_FILE_PATH;
import static chatty.util.Constants.FALSE_STRING;
import static chatty.util.Constants.FILE_FIELD_SEPARATOR;
import static chatty.util.Constants.MINIMUM_FIELD_NUM_FOR_EVENT_AND_DEADLINE;
import static chatty.util.Constants.MINIMUM_FIELD_NUM_FOR_TASK;
import static chatty.util.Constants.NEW_LINE;
import static chatty.util.Constants.TO_STRING;
import static chatty.util.Constants.TRUE_STRING;

/**
 * Handles reading and writing of tasks from file storage.
 */
public class Storage {

    /**
     * Reads and parses tasks stored in file and writes the result to the list of tasks.
     *
     * @param taskList The list where the tasks read from file should be added to.
     * @return Boolean value indicating whether or not the operation of reading tasks from file is successful.
     */
    public boolean readDataFromFile(TaskList taskList) {
        File file = new File(System.getProperty("user.dir"), DEFAULT_FILE_PATH);
        try {
            // Solution below adapted from: https://stackoverflow
            // .com/questions/6142901/how-to-create-a-file-in-a-directory-in-java
            file.getParentFile().mkdirs();
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                String taskStr = fileScanner.nextLine();
                Optional<Task> taskOptional = stringToTask(taskStr);
                taskOptional.ifPresent(taskList::addTask);
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Saves a list of tasks to file.
     *
     * @param taskList The list of tasks to be stored in the file.
     * @return Boolean value indicating whether or not the operation of saving tasks to the file is successful.
     */
    public boolean saveDataToFile(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(DEFAULT_FILE_PATH);
            for (int i = 0; i < taskList.getTotalTaskNum(); i++) {
                fileWriter.write(taskList.getTaskAtIdx(i).getFileString() + NEW_LINE);
            }
            fileWriter.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Parses a string to create a task.
     *
     * @param taskStr The string which represents a task.
     * @return The task created from the input string.
     */
    public Optional<Task> stringToTask(String taskStr) {
        String[] fields = taskStr.split(FILE_FIELD_SEPARATOR);
        if (fields.length < MINIMUM_FIELD_NUM_FOR_TASK) {
            return Optional.empty();
        }

        String taskType = fields[0];
        boolean isDone;
        if (fields[1].equals(TRUE_STRING)) {
            isDone = true;
        } else if (fields[1].equals(FALSE_STRING)) {
            isDone = false;
        } else {
            return Optional.empty();
        }
        String description = fields[2];
        Task task;

        switch (taskType) {
        case "T":
            task = new ToDo(description);
            break;
        case "E":
            if (fields.length < MINIMUM_FIELD_NUM_FOR_EVENT_AND_DEADLINE) {
                return Optional.empty();
            }
            try {
                String[] times = fields[3].split(TO_STRING);
                LocalDate startTime = LocalDate.parse(times[0], DateTimeFormatter.ISO_DATE);
                LocalDate endTime = LocalDate.parse(times[1], DateTimeFormatter.ISO_DATE);
                task = new Event(description, startTime, endTime);
            } catch (DateTimeParseException e) {
                return Optional.empty();
            }
            break;
        case "D":
            if (fields.length < MINIMUM_FIELD_NUM_FOR_EVENT_AND_DEADLINE) {
                return Optional.empty();
            }
            try {
                String dateTime = fields[3];
                task = new Deadline(description, LocalDate.parse(dateTime, DateTimeFormatter.ISO_DATE));
            } catch (DateTimeParseException e) {
                return Optional.empty();
            }
            break;
        default:
            return Optional.empty();
        }

        if (isDone) {
            task.markAsDone();
        }
        return Optional.of(task);
    }
}
