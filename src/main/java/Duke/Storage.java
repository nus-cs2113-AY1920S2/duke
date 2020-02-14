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

public class Storage {

    private static final int DESCRIPTION = 0;
    private static final int TIME = 1;
    private static final String T = "T";
    private static final String E = "E";
    private static final String D = "D";
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

    private static String[] extractData(Scanner s) {
        return s.nextLine().split("\\|");
    }

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

    private static int importToList(ArrayList<Task> taskList, int taskNumber, String[] splitTaskDescriptionArray,
                                    String s) {
        Task newTask;
        int currentTaskNumber = taskNumber;
        switch (s.trim()) {
        case T:
            newTask = new Todo(splitTaskDescriptionArray[0], splitTaskDescriptionArray[1]);
            taskList.add(newTask);
            currentTaskNumber = currentTaskNumber + 1;
            break;
        case E:
            newTask = new Event(splitTaskDescriptionArray[0], splitTaskDescriptionArray[1]);
            taskList.add(newTask);
            currentTaskNumber = currentTaskNumber + 1;
            break;
        case D:
            newTask = new Deadline(splitTaskDescriptionArray[0], splitTaskDescriptionArray[1]);
            taskList.add(newTask);
            currentTaskNumber = currentTaskNumber + 1;
            break;
        default:
            System.out.println("Error in importing this task! Task starts with: " + s.trim());
            break;
        }
        return currentTaskNumber;
    }

    private static void markImportTaskDone(ArrayList<Task> taskList, int taskNumber, String[] obtainedLine) {
        if (obtainedLine[1].trim().equals("1")) {
            taskList.get(taskNumber - 1).markAsDone();
        }
    }

    public ArrayList<Task> load() {
        Path path = getSaveDataPath();
        boolean isFileExists = checkIfDataFileExist(path);
        if (isFileExists) {
            File f = new File(String.valueOf(path));
            try {
                Scanner s = initializeFileScanner(f);
                int taskNumber = 0;

                while (s.hasNext()) {
                    String[] obtainedLine = extractData(s);
                    String[] splitTaskDescriptionArray = getDescriptionAndTime(obtainedLine);
                    taskNumber = importToList(taskList, taskNumber, splitTaskDescriptionArray, obtainedLine[0]);
                    markImportTaskDone(taskList, taskNumber, obtainedLine);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Exception occurred: " + e + "File not found!");
            }
        }
        return taskList;

    }

    public void save(ArrayList<Task> taskList, int numberOfTasks) {
        if (numberOfTasks > 0) {
            Path directoryPath = Paths.get("data");
            boolean isFileExists = Files.exists(directoryPath);
            if (!isFileExists) {
                File directory = new File("data");
                directory.mkdir();
            }
            File f = new File("data/duke.txt");
            try {
                FileWriter fw = new FileWriter(f);
                for (int i = 0; i < numberOfTasks; i++) {
                    String[] getTaskInfo = taskList.get(i).getTaskInfo();
                    fw.write(getTaskInfo[0]
                            + " | "
                            + getTaskInfo[1]
                            + " | "
                            + getTaskInfo[2]
                            + " | "
                            + getTaskInfo[3]
                            + System.lineSeparator());
                }
                fw.close();

            } catch (IOException e) {
                System.out.println("Exception occurred: " + e + ": Error in writing file!");
            }

        }
    }
}
