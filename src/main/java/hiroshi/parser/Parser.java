package hiroshi.parser;

import hiroshi.tasks.Deadline;
import hiroshi.tasks.Event;
import hiroshi.tasks.Task;
import hiroshi.ui.Ui;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.File;

/**
 * Parses user input.
 */
public class Parser {

    /** Retutns the type of task, eg. event or deadline, given the user command
     * @param line Original command that was fed to the user.
     */
    public static String returnTaskType(String line){
        String[] words = line.split(" ");
        String taskType = words[0].trim();
        return taskType;
    }

    public static Boolean isFindStatementCorrect (String ogString){
        String[] wordss = ogString.split(" ");
        int LENG_DONE_STATEMENT = wordss.length;
        if (LENG_DONE_STATEMENT != 2) {
            Ui.markIncorrectFindStatement();
            return false;
        } else {
            return true;
        }
    }

    /** Checks if Clear Statement is correct
     * @param ogString Original command that was fed to the user.
     * @return isClear Returns if clear command is in correct format.
     */
    public static Boolean isClearStatementCorrect (String ogString){
        Boolean isClear;
        String[] wordss = ogString.split(" ");
        int LENG_DONE_STATEMENT = wordss.length;
        if (LENG_DONE_STATEMENT != 1) {
            Ui.markIncorrectClearStatement();
            isClear = false;
        } else {
            isClear = true;
        }
        return isClear;
    }

    /** Checks if List Statement is correct
     * @param ogString Original command that was fed to the user.
     * @return Returns a true value if the List statement is correct.
     */
    public static Boolean isListStatementCorrect (String ogString){
        String[] wordss = ogString.split(" ");
        int LENG_DONE_STATEMENT = wordss.length;
        if (LENG_DONE_STATEMENT != 1) {
            Ui.markIncorrectListStatement();
            return false;
        } else {
            return true;
        }
    }

    public static String findKeyWord(String ogString){
        String[] wordss = ogString.split(" ");
        String KEY_WORD = wordss[1].trim();
        return KEY_WORD;
    }

    /** Checks if delete Statement is correct
     * @param ogString Original command that was fed to the user.
     * @param taskList List that stores the tasks mentioned until now.
     * @return Returns a true value if the Delete statement is correct.
     */
    public static Boolean isDeleteStatementCorrect(String ogString, ArrayList<Task> taskList) {
        String[] words = ogString.split(" ");
        int LENG_DELETE_STATEMENT = words.length;
        Boolean isCorrect = false;
        if (LENG_DELETE_STATEMENT != 2) {
            Ui.markIncorrectDeleteStatement();
            isCorrect = false;
            return isCorrect;
        } else {
            try {
                int IDX_WORDS = Integer.parseInt(words[1]) - 1;
                if (IDX_WORDS < 0 || IDX_WORDS > taskList.size() - 1) {
                    Ui.markDeleteStatementOutOfBounds();
                    isCorrect = false;
                    return isCorrect;
                } else {
                    isCorrect = true;
                    return isCorrect;
                }
            } catch (NumberFormatException e) {
                Ui.markDeleteStatementAsNonInt();
            }
        }
        return isCorrect;
    }


    /**
     * Returns a boolean if a given task is marked as done
     *
     * @param ogString  Line that represents the task that is supposed to marked as done.
     * @param taskList TaskList of all available tasks.
     * @return isDone Is the task marked as Done.
     */
    public static Boolean isDoneStatementCorrect(String ogString, ArrayList<Task> taskList) {
        String[] words = ogString.split(" ");
        int LENG_DONE_STATEMENT = words.length;
        Boolean isCorrect = false;
        if (LENG_DONE_STATEMENT != 2) {
            Ui.markIncorrectDoneStatement();
            isCorrect = false;
            return isCorrect;
        } else {
            try {
                int IDX_WORDS = Integer.parseInt(words[1]) - 1;
                if (IDX_WORDS < 0 || IDX_WORDS > taskList.size() - 1) {
                    Ui.markDoneStatementOutOfBounds();
                    isCorrect = false;
                    return isCorrect;
                } else {
                    isCorrect = true;
                    return isCorrect;
                }
            } catch (NumberFormatException e) {
                Ui.markDoneStatementAsNonInt();
            }
        }
        return isCorrect;
    }

    /**
     * Returns the description of the task in the required format
     * @param line Line that represents the task that is supposed to marked as done.
     * @param type  Type of subclass of task.
     * @return description Description of the task in the required format.
     */
    public static String[] returnStringToAdd(String line, String type) {
        if (type.equals("todo")) {
            String[] arrOfStr = line.trim().split(type);
            return arrOfStr;
        } else {
            String preposition;
            String splitter;
            if (type.equals("deadline")) {
                preposition = "by: ";
                splitter = "/by";
            } else {
                preposition = "at: ";
                splitter = "/at";
            }
            String[] arrOfStr = line.split(splitter);
            String[] toReturn = new String[2];
            String[] todo = arrOfStr[0].split(type);
            String taskDescritpion = (todo[1].substring(1, todo[1].length()));       // task
            String date = arrOfStr[1].substring(1, arrOfStr[1].length()); // date
            LocalDate d1 = LocalDate.parse(date);
            String dateFormatted = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            taskDescritpion = taskDescritpion + "(" + preposition + dateFormatted + ")";
            toReturn[0] = taskDescritpion;
            toReturn[1] = date;
            return toReturn;
        }
    }

    /**
     * Returns the desctipton of a given task that is to be added to the Tasklist.
     *
     * @param t Task that is taken into consideration.
     * @param description Description og task.
     * @param typeIcon Icon of the Task t.
     * @param statusIcon Status Icon representing if the task is done.
     * @return stringToAdd String that is added to the description of the Task in the TaskList
     */
    public static String returnDescription(Task t, String description, String typeIcon, String statusIcon) {
        String stringToAdd;
        if (typeIcon.equals("[T]")){
            stringToAdd = typeIcon + " | " + statusIcon + " | " + description;
        } else if(typeIcon.equals("[D]")) {
            Deadline d = (Deadline) t;
            String date = d.returnDate().toString();
            stringToAdd = typeIcon + " | " + statusIcon + " | " + description + " | " + date;
        } else {
            Event d = (Event) t;
            String date = d.returnDate().toString();
            stringToAdd = typeIcon + " | " + statusIcon + " | " + description + " | " + date;
        }
        return stringToAdd;
    }

    public static String returnFilePath(String defaultFilePath){
        String curFilePath = defaultFilePath;
        File curDir = new File(curFilePath);
        boolean exists = curDir.exists();
        if (!exists){
            final String NEW_FILE_PATH = System.getProperty("user.dir") + "/files";
            curFilePath = NEW_FILE_PATH;
        }
        return curFilePath;
    }
}