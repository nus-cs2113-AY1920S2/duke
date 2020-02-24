package duke.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import duke.exception.DukeException;
import duke.exception.DukeLoadingException;
import duke.exception.DukeNullDescriptionException;
import duke.exception.DukeWritingException;
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

import static duke.util.Constants.CRYING_FACE;
import static duke.util.Constants.DATA_FILE_NAME;
import static duke.util.Constants.DATA_LOADED_SUCCESSFULLY_PROMPT;
import static duke.util.Constants.DATA_SAVED_SUCCESSFULLY_PROMPT;
import static duke.util.Constants.DEADLINE_ICON;
import static duke.util.Constants.EVENT_ICON;
import static duke.util.Constants.FILE_OPERATION_IO_ERROR_MESSAGE;
import static duke.util.Constants.FIVE_SPACES;
import static duke.util.Constants.LOAD_DATA_FROM_FILE_PROMPT_FORMAT_STRING;
import static duke.util.Constants.TASK_DESCRIPTION_EMPTY_ERROR_MESSAGE;
import static duke.util.Constants.YES_ICON;

public class Storage {
    private String dataFileName;

    public Storage(String dataFileName) {
        this.dataFileName = dataFileName;
    }

    public ArrayList<Task> load() throws DukeLoadingException, DukeNullDescriptionException {
        Ui.showLoadDataPrompt();

        String jsonStr;
        try {
            jsonStr = loadJsonStringFromFile();
        } catch (DukeLoadingException e) {
            throw new DukeLoadingException(dataFileName);
        }

        List<DummyTask> taskList = extractDummyTasks(jsonStr);
        ArrayList<Task> tasksList = new ArrayList<>();
        Task currentTask;
        for (DummyTask i : taskList) {
            try {
                currentTask = convertDummyTaskToSpecificTask(i);
                tasksList.add(currentTask);
            } catch (DukeException e) {
                throw new DukeNullDescriptionException();
            }
        }
        Ui.showLoadDataSuccessfulPrompt();

        return tasksList;
    }

    private String loadJsonStringFromFile() throws DukeLoadingException {
        String jsonStr;
        try {
            File f = new File(dataFileName);
            Scanner s = new Scanner(f);
            jsonStr = s.nextLine();
            s.close();
        } catch (FileNotFoundException e) {
            throw new DukeLoadingException(dataFileName);
        }
        return jsonStr;
    }

    private List<DummyTask> extractDummyTasks(String jsonStr) {
        Type listType = new TypeToken<List<DummyTask>>(){}.getType();
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, listType);
    }

    private Task convertDummyTaskToSpecificTask(DummyTask task) throws DukeException {
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

    public void save(TaskList tasksList) throws DukeWritingException {
        Ui.showSaveDataToFilePrompt();
        try {
            saveObjectsAsJsonStringToFile(dataFileName, tasksList.getList(), tasksList);
            Ui.showSaveDataToFileSuccessfulPrompt();
        } catch (IOException e) {
            throw new DukeWritingException(dataFileName);
        }
    }

    static void saveObjectsAsJsonStringToFile(String dataFileName, ArrayList<Task> list, TaskList tasksList) throws IOException {
        Gson gson = new Gson();
        FileWriter fw = new FileWriter(dataFileName);
        String json = gson.toJson(list);
        fw.write(json);
        fw.flush();
        fw.close();
    }
}
