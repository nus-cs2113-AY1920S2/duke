package Duke;

import Duke.TaskTypes.Deadline;
import Duke.TaskTypes.Event;
import Duke.TaskTypes.Task;
import Duke.TaskTypes.Todo;

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

    public static final int SAVED_DESCRIPTION = 2;
    private static final int DESCRIPTION = 0;
    private static final int TIME = 1;
    private static final String T = "T";
    private static final String E = "E";
    private static final String D = "D";
    public static final int FIRST_LETTER_OF_TASK_TYPE = 0;
    public static final int IS_TASK_DONE = 1;
    public static final int SAVED_TIME = 3;
    private ArrayList<Task> taskList = new ArrayList<>();

    public Storage() {
    }


    private static Path getSaveDataPath() {
        return Paths.get("data", "duke.txt");
    }

    private static boolean checkIfDataFileExist(Path path) {
        return Files.exists(path);
    }

    private static Scanner initializeFileScanner(File f) throws FileNotFoundException {
        return new Scanner(f);
    }

    /**
     * Split a line of data by the <code>|</code> symbol to extract the data in it
     * @param s the line of data to be parsed
     * @return a <code>String[]</code> array that contains data upon the split
     */
    private static String[] extractData(Scanner s) {
        return s.nextLine().split("\\|");
    }

    /**
     * Get the description and time (if present) from the line of data
     * @param obtainedLine the line of data to parsed
     * @return a <code>String[]</code> array that contains the description and time as different array element
     */
    private static String[] getDescriptionAndTime(String[] obtainedLine) {
        String[] splitTaskDescriptionArray = new String[2];
        splitTaskDescriptionArray[DESCRIPTION] = obtainedLine[2].trim();
        if (obtainedLine.length == 4) {
            splitTaskDescriptionArray[TIME] = obtainedLine[3].trim();
        } else {
            splitTaskDescriptionArray[TIME] = "";
        }
        return splitTaskDescriptionArray;
    }

    /**
     * The actual method used to insert a task (from the imported data) into the task list.
     * @param taskList the list of task to import the task into
     * @param taskNumber the current task number before the next task is added
     * @param splitTaskDescriptionArray the <code>String[]</code> array that contains the description and (if present) time of the task itself
     * @param s the <code>String</code> containing the type of task. Values are either <code>TODO, EVENT</code> or <code>DEADLINE</code>
     * @return the number of tasks currently in the list. Will be useful for the private {@link Storage#markImportTaskDone} method to mark that task as done
     */
    private static int importToList(ArrayList<Task> taskList, int taskNumber, String[] splitTaskDescriptionArray,
                                    String s) {
        Task newTask;
        int currentTaskNumber = taskNumber;
        switch (s.trim()) {
        case T:
            newTask = new Todo(splitTaskDescriptionArray[DESCRIPTION], splitTaskDescriptionArray[TIME]);
            taskList.add(newTask);
            currentTaskNumber = currentTaskNumber + 1;
            break;
        case E:
            newTask = new Event(splitTaskDescriptionArray[DESCRIPTION], splitTaskDescriptionArray[TIME]);
            taskList.add(newTask);
            currentTaskNumber = currentTaskNumber + 1;
            break;
        case D:
            newTask = new Deadline(splitTaskDescriptionArray[DESCRIPTION], splitTaskDescriptionArray[TIME]);
            taskList.add(newTask);
            currentTaskNumber = currentTaskNumber + 1;
            break;
        default:
            System.out.println("Error in importing this task! Task starts with: " + s.trim());
            break;
        }
        return currentTaskNumber;
    }

    /**
     * Check to see if the task imported into the task list is done. If it is done, mark it as done.
     * @param taskList the task list containing the task
     * @param taskNumber the task number of the task to be checked
     * @param obtainedLine the <code>String[]</code> array that was obtained from the {@link Storage#extractData} method. The second element tells us if the task is done
     * @see Storage#extractData
     */
    private static void markImportTaskDone(ArrayList<Task> taskList, int taskNumber, String[] obtainedLine) {
        if (obtainedLine[1].trim().equals("1")) {
            taskList.get(taskNumber - 1).markAsDone();
        }
    }

    /**
     * The actual loading of the data from the saved data before return a task list. If there is no saved data present, this will return an empty task list. Else, it will load the tasks found in the task list and return the filled task list
     * @return an <code>ArrayList<Task></code> containing the tasks. Will be empty if there is no saved tasks
     */
    public ArrayList<Task> load() {
        Path path = getSaveDataPath();
        boolean isFileExists = checkIfDataFileExist(path);

        // See if file actually exist. If it exist, load the data from the saved data. Else, just return the empty ArrayList<Task> of tasks
        if (isFileExists) {
            File f = new File(String.valueOf(path));
            try {
                Scanner s = initializeFileScanner(f);
                int taskNumber = 0;
                while (s.hasNext()) {
                    String[] obtainedLine = extractData(s);
                    String[] splitTaskDescriptionArray = getDescriptionAndTime(obtainedLine);

                    // Import the task into the task list and check if the task is saved as done. If it is so, mark the task as done
                    taskNumber = importToList(taskList, taskNumber, splitTaskDescriptionArray, obtainedLine[0]);
                    markImportTaskDone(taskList, taskNumber, obtainedLine);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Exception occurred: " + e + "File not found!");
            }
        }
        return taskList;

    }

    /**
     * The actual saving of the tasks in the task list into the saved data file itself. It gets list of tasks from the <code>ArrayList<Task></code> of tasks
     * @param taskList the <code>ArrayList<Task></code> of tasks
     * @param numberOfTasks the number of tasks in the arraylist. Needed for looping the task list itself. Also used to check if the task list is empty or not
     */
    public void save(ArrayList<Task> taskList, int numberOfTasks) {

        Path directoryPath = Paths.get("data");
        boolean isFileExists = Files.exists(directoryPath);

        // If there is no previous saved data, create the directory to store the saved data text file first before doing the actual saving of data
        if (!isFileExists) {
            File directory = new File("data");
            directory.mkdir();
        }

        // The actual writing of the task into the saved file
        File f = new File("data/duke.txt");
        try {
            FileWriter fw = new FileWriter(f);

            // If the task list is empty, the saved data will not contain anything in it. Hence, filewriter will just write a "" to the file
            if (numberOfTasks == 0) {
                fw.write("");
            }

            // Loop through the array list and store the tasks into the task list itself
            for (int i = 0; i < numberOfTasks; i++) {
                String[] getTaskInfo = taskList.get(i).getTaskInfo();
                fw.write(getTaskInfo[FIRST_LETTER_OF_TASK_TYPE]
                        + " | "
                        + getTaskInfo[IS_TASK_DONE]
                        + " | "
                        + getTaskInfo[SAVED_DESCRIPTION]
                        + " | "
                        + getTaskInfo[SAVED_TIME]
                        + System.lineSeparator());
            }
            fw.close();

        } catch (IOException e) {
            System.out.println("Exception occurred: " + e + ": Error in writing file!");
        }


    }
}
