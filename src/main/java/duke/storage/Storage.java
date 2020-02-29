package duke.storage;

import duke.tasklist.TaskList;
import duke.tasktypes.Deadline;
import duke.tasktypes.Event;
import duke.tasktypes.Task;
import duke.tasktypes.Todo;
import duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class used to load and store the data from the task list (the {@link TaskList} object's tasks)
 */
public class Storage {

    private static final int SAVED_DESCRIPTION = 2;
    private static final int FIRST_LETTER_OF_TASK_TYPE = 0;
    private static final int IS_TASK_DONE = 1;
    private static final int SAVED_DATE = 3;
    private static final String ERROR_IN_WRITING_FILE = ": Error in writing file!";
    private static final String FILE_NOT_FOUND = ": File not found!";
    private static final String SAVED_TXT_PATH = "data/duke.txt";
    private static final String DIRECTORY_NAME = "data";
    private static final String ONE = "1";
    private static final String EMPTY = "";
    private static final int DESCRIPTION = 2;
    private static final int DATE = 3;
    private static final String T = "T";
    private static final String E = "E";
    private static final String D = "D";
    private ArrayList<Task> taskList = new ArrayList<>();

    public Storage() {
    }

    /**
     * The actual loading of the data from the saved data before returning a task list. If there is no saved data
     * present, this will return an empty task list. Else, it will load the tasks found in the task list and return
     * the filled task list
     * @return an <code>ArrayList<Task></code> containing the tasks. Will be empty if there is no saved tasks
     */
    public ArrayList<Task> load() {
        Path path = getSaveDataPath();
        boolean isFileExists = checkIfDataFileExist(path);

        /* See if file actually exist. If it exist, load the data from the saved data. Else, just return the empty
        ArrayList<Task> of tasks */
        if (isFileExists) {
            File f = new File(String.valueOf(path));
            try {
                Scanner s = initializeFileScanner(f);
                while (s.hasNext()) {
                    String[] obtainedLine = extractData(s);
                    String[] splitTaskDescriptionArray = formatDescriptionAndDate(obtainedLine);

                    /* Import the tasks into the task list and check if the task is saved as done. If it is so, mark
                    the task as done */
                    importToList(taskList, splitTaskDescriptionArray);
                }
            } catch (FileNotFoundException e) {

                Ui.displayGenericException(e, FILE_NOT_FOUND);
            }
        }
        return taskList;

    }

    /**
     * The actual saving of the tasks in the task list into the saved data file itself. It gets list of tasks from the
     * <code>ArrayList<Task></code> of tasks
     * @param taskList      the <code>ArrayList<Task></code> of tasks
     * @param numberOfTasks the number of tasks in the arraylist. Needed for looping the task list itself. Also used to
     *                      check if the task list is empty or not
     */
    public void save(ArrayList<Task> taskList, int numberOfTasks) {

        Path directoryPath = Paths.get(DIRECTORY_NAME);
        boolean isFileExists = Files.exists(directoryPath);

        /* If there is no previous saved data, create the directory to store the saved data text file first before
        doing the actual saving of data */
        if (!isFileExists) {
            File directory = new File(DIRECTORY_NAME);
            directory.mkdir();
        }

        /* The actual writing of the task into the saved file */
        File f = new File(SAVED_TXT_PATH);
        try {
            FileWriter fw = new FileWriter(f);

            /* If the task list is empty, the saved data will not contain anything in it. Hence, filewriter will just
             write a "" to the file */
            if (numberOfTasks == 0) {
                fw.write(EMPTY);
            }

            /* Loop through the array list and store the tasks into the task list itself */
            for (int i = 0; i < numberOfTasks; i++) {
                String[] getTaskInfo = taskList.get(i).getTaskInfo();
                fw.write(Ui.formatSavedDataInput(getTaskInfo));
            }
            fw.close();

        } catch (IOException e) {
            Ui.displayGenericException(e, ERROR_IN_WRITING_FILE);
        }


    }


    private Path getSaveDataPath() {
        return Paths.get("data", "duke.txt");
    }

    private boolean checkIfDataFileExist(Path path) {
        return Files.exists(path);
    }

    private Scanner initializeFileScanner(File f) throws FileNotFoundException {
        return new Scanner(f);
    }

    /**
     * Split a line of data by the <code>|</code> symbol to extract the data in it
     * @param s the line of data to be parsed
     * @return a <code>String[]</code> array that contains data upon the split
     */
    private String[] extractData(Scanner s) {
        return s.nextLine().split("\\|");
    }

    /**
     * Get the type of task, is task done, its description and date (if present) from the line of data
     * @param obtainedLine the line of data to parsed in <code>String[]</code> format
     * @return a <code>String[]</code> array that contains the description and date as different array element
     */
    private String[] formatDescriptionAndDate(String[] obtainedLine) {
        String[] splitTaskDescriptionArray = new String[4];
        splitTaskDescriptionArray[FIRST_LETTER_OF_TASK_TYPE] = obtainedLine[0].trim();
        splitTaskDescriptionArray[IS_TASK_DONE] = obtainedLine[1].trim();
        splitTaskDescriptionArray[DESCRIPTION] = obtainedLine[2].trim();
        if (obtainedLine.length == 4 && !obtainedLine[3].equals(" ")) {
            splitTaskDescriptionArray[DATE] = obtainedLine[3].trim();
        } else {
            splitTaskDescriptionArray[DATE] = EMPTY;
        }
        return splitTaskDescriptionArray;
    }

    /**
     * The actual method used to insert a task (from the imported data) into the task list.
     * @param taskList                  the list of task to import the task into
     * @param splitTaskDescriptionArray the <code>String[]</code> array that contains the first letter of task type, is
     *                                  the task done, the description and (if present) date of the task itself
     */
    private void importToList(ArrayList<Task> taskList, String[] splitTaskDescriptionArray) {
        Task newTask;
        switch (splitTaskDescriptionArray[FIRST_LETTER_OF_TASK_TYPE]) {
        case T:
            newTask = new Todo(splitTaskDescriptionArray[DESCRIPTION]);
            checkIfTaskDone(newTask, splitTaskDescriptionArray[IS_TASK_DONE]);
            taskList.add(newTask);
            break;
        case E:
            newTask = new Event(splitTaskDescriptionArray[DESCRIPTION], splitTaskDescriptionArray[DATE]);
            checkIfTaskDone(newTask, splitTaskDescriptionArray[IS_TASK_DONE]);
            taskList.add(newTask);
            break;
        case D:
            newTask = new Deadline(splitTaskDescriptionArray[DESCRIPTION], splitTaskDescriptionArray[DATE]);
            checkIfTaskDone(newTask, splitTaskDescriptionArray[IS_TASK_DONE]);
            taskList.add(newTask);
            break;
        default:
            String tempMessage = formatTaskToBeDisplayed(splitTaskDescriptionArray);
            Ui.displayErrorImportingTask(tempMessage);
            break;
        }
    }

    /**
     * Help to format the task which produced the error
     * @param splitTaskDescriptionArray the array that contains the split erroneous task
     * @return the string that can be used by the {@link Ui} class to display error importing task
     * @see Ui#displayErrorImportingTask
     */
    private String formatTaskToBeDisplayed(String[] splitTaskDescriptionArray) {
        return splitTaskDescriptionArray[FIRST_LETTER_OF_TASK_TYPE] + " | " +
                splitTaskDescriptionArray[DESCRIPTION];
    }

    /**
     * Check to see if the new imported task is marked as done. If it is so, mark it as done.
     * @param newTask the new task to be checked
     * @param s       the status of task as obtained from the saved data
     * @see Task#markAsDone
     */
    private void checkIfTaskDone(Task newTask, String s) {
        if (s.equals(ONE)) {
            newTask.markAsDone();
        }
    }


}
