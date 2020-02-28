package duke.parser;

import duke.exceptions.HiroshiException;
import duke.tasks.Task;

import java.util.ArrayList;

/**
 * Parses user input.
 */
public class Parser {

    /** Retutns the type of task, eg. event or deadline, given the user command
     * @param ogString Original command that was fed to the user.
     */
    public static String returnTaskType(String ogString){
        String[] words = ogString.split(" ");
        String taskType = words[0].trim();
        return taskType;
    }

    /** Checks if Clear Statement is correct
     * @param ogString Original command that was fed to the user.
     */
    public static Boolean isClearStatementCorrect (String ogString){
        String[] wordss = ogString.split(" ");
        int LENG_DONE_STATEMENT = wordss.length;
        if (LENG_DONE_STATEMENT != 1) {
            return false;
        } else {
            return true;
        }
    }

    /** Checks if List Statement is correct
     * @param ogString Original command that was fed to the user.
     */
    public static Boolean isListStatementCorrect (String ogString){
        String[] wordss = ogString.split(" ");
        int LENG_DONE_STATEMENT = wordss.length;
        if (LENG_DONE_STATEMENT != 1) {
            return false;
        } else {
            return true;
        }
    }

    /** Checks if delete Statement is correct
     * @param ogString Original command that was fed to the user.
     * @param taskList List that stores the tasks mentioned until now.
     */
    public static Boolean isDeleteStatementCorrect(String ogString, ArrayList<Task> taskList) {
        String[] words = ogString.split(" ");
        int LENG_DELETE_STATEMENT = words.length;
        Boolean isCorrect = false;
        if (LENG_DELETE_STATEMENT != 2) {
            HiroshiException.markIncorrectDeleteStatement();
            isCorrect = false;
            return isCorrect;
        } else {
            try {
                int IDX_WORDS = Integer.parseInt(words[1]) - 1;
                if (IDX_WORDS < 0 || IDX_WORDS > taskList.size() - 1) {
                    HiroshiException.markDeleteStatementOutOfBounds();
                    isCorrect = false;
                    return isCorrect;
                } else {
                    isCorrect = true;
                    return isCorrect;
                }
            } catch (NumberFormatException e) {
                HiroshiException.markDeleteStatementAsNonInt();
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
            HiroshiException.markIncorrectDoneStatement();
            isCorrect = false;
            return isCorrect;
        } else {
            try {
                int IDX_WORDS = Integer.parseInt(words[1]) - 1;
                if (IDX_WORDS < 0 || IDX_WORDS > taskList.size() - 1) {
                    HiroshiException.markDoneStatementOutOfBounds();
                    isCorrect = false;
                    return isCorrect;
                } else {
                    isCorrect = true;
                    return isCorrect;
                }
            } catch (NumberFormatException e) {
                HiroshiException.markDoneStatementAsNonInt();
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
            String[] arrOfStr = line.split(type);
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
            toReturn[0] = (todo[1].substring(1, todo[1].length()));       // task
            toReturn[1] = arrOfStr[1].substring(1, arrOfStr[1].length()); // date
            String description = toReturn[0] + "(" + preposition + toReturn[1] + ")";
            toReturn[0] = description;
            return toReturn;
        }
    }

}
