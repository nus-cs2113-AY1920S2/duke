package duke.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import duke.exception.DukeLoadingException;
import duke.exception.DukeWritingException;
import duke.exception.DukeNullDateException;
import duke.exception.DukeNullDescriptionException;
import duke.task.Deadline;
import duke.task.DummyTask;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static duke.util.Constants.DEADLINE_ICON;
import static duke.util.Constants.EVENT_ICON;
import static duke.util.Constants.YES_ICON;

/**
 * This class handles the storage related operation in the programme.
 *
 * @author A11riseforme
 */
public class Storage {
    private String dataFileName;

    /**
     * Constructor with name of the data file as argument.
     *
     * @param dataFileName the name of the file in which the data is stored.
     */
    public Storage(String dataFileName) {
        this.dataFileName = dataFileName;
    }

    /**
     * Load the json string from the file and convert into an ArrayList of Tasks.
     *
     * @return a list of Task objects, which represents the tasks.
     * @throws DukeLoadingException exception is thrown if error occurs when loading from the file.
     * @throws DukeNullDescriptionException exception is thrown if error occurs when parsing the json string and any
     *      task with empty description.
     * @throws DukeNullDateException exception is thrown if error occurs when parsing the json strings and any
     *      deadline/event task with empty date.
     */
    public ArrayList<Task> load() throws DukeLoadingException, DukeNullDescriptionException {
        Ui.showLoadDataPrompt();

        String jsonStr;
        try {
            jsonStr = loadJsonStringFromFile();
        } catch (DukeLoadingException e) {
            throw new DukeLoadingException();
        }

        List<DummyTask> taskList = extractDummyTasks(jsonStr);
        ArrayList<Task> tasksList = new ArrayList<>();
        Task currentTask;
        for (DummyTask i : taskList) {
            try {
                currentTask = convertDummyTaskToSpecificTask(i);
                tasksList.add(currentTask);
            } catch (DukeNullDescriptionException e) {
                throw new DukeNullDescriptionException();
            }
        }
        Ui.showLoadDataSuccessfulPrompt();

        return tasksList;
    }

    /**
     * Read the file and return its content, which is a json string.
     *
     * @return the content of the date file, which is a json string.
     * @throws DukeLoadingException exception is thrown if error occurs when loading from the file.
     */
    private String loadJsonStringFromFile() throws DukeLoadingException {
        String jsonStr;
        try {
            File f = new File(dataFileName);
            Scanner s = new Scanner(f);
            jsonStr = s.nextLine();
            s.close();
        } catch (FileNotFoundException e) {
            throw new DukeLoadingException();
        }
        return jsonStr;
    }

    /**
     * Extract the DummyTask objects from the jsonstr, and return as a List.
     *
     * @param jsonStr the content of the date file, which is a json string.
     * @return a List of DummyTask objects, which will be further converted into specific tasks.
     */
    private List<DummyTask> extractDummyTasks(String jsonStr) {
        Type listType = new TypeToken<List<DummyTask>>(){}.getType();
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, listType);
    }

    /**
     * Convert a DummyTask to its actual task type.
     * @param task the DummyTask object to be converted.
     * @return the actual task object
     * @throws DukeNullDescriptionException exception is thrown when the task has empty description.
     * @throws DukeNullDateException exception is thrown when the deadline/event task has empty date.
     */
    private Task convertDummyTaskToSpecificTask(DummyTask task) throws DukeNullDescriptionException {
        Task convertedTask;

        switch (task.getIcon()) {
        case DEADLINE_ICON:
            convertedTask = new Deadline(task.getTaskDescription(), task.getByDate());
            break;
        case EVENT_ICON:
            convertedTask = new Event(task.getTaskDescription(), task.getAtDate());
            break;
        default:
            convertedTask = new Todo(task.getTaskDescription());
        }

        if (convertedTask.getStatusIcon().equals(YES_ICON)) {
            convertedTask.markAsDone();
        }

        return convertedTask;
    }

    /**
     * Save the whole list of tasks into the date file.
     *
     * @param tasksList the TaskList object used to store the task information.
     * @throws DukeWritingException exception is thrown if error occurs when writing to the file.
     */
    public void save(TaskList tasksList) throws DukeWritingException {
        Ui.showSaveDataToFilePrompt();
        try {
            saveObjectsAsJsonStringToFile(dataFileName, tasksList.getList());
            Ui.showSaveDataToFileSuccessfulPrompt();
        } catch (IOException e) {
            throw new DukeWritingException();
        }
    }

    /**
     * Save a list of Task objects as json string into the file.
     *
     * @param dataFileName the name of the file in which the data is stored.
     * @param list a list of the Task objects which represents the tasks.
     * @throws IOException exception is thrown if error occurs when writing to the file.
     */
    static void saveObjectsAsJsonStringToFile(String dataFileName, ArrayList<Task> list) throws IOException {
        Gson gson = new Gson();
        FileWriter fw = new FileWriter(dataFileName);
        String json = gson.toJson(list);
        fw.write(json);
        fw.flush();
        fw.close();
    }
}
