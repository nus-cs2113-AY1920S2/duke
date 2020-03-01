package command;

import storage.Storage;
import ui.Ui;
import tasktype.Task;
import tasktype.Todo;
import tasktype.Event;
import tasktype.Deadline;
import common.Messages;
import exceptions.IllegalKeywordException;
import exceptions.NumberFieldException;
import tasklist.TaskList;

import java.io.IOException;
import java.util.ArrayList;

import static common.Messages.BYE_COMMAND;
import static common.Messages.LIST_COMMAND;
import static common.Messages.HELP_COMMAND;
import static common.Messages.CLEAR_COMMAND;
import static common.Messages.DONE_COMMAND;
import static common.Messages.DELETE_COMMAND;
import static common.Messages.FIND_COMMAND;
import static common.Messages.TODO_COMMAND;
import static common.Messages.DEADLINE_COMMAND;
import static common.Messages.EVENT_COMMAND;
import static common.Messages.EMPTY_LIST_ERROR_MESSAGE;
import static common.Messages.LS;
import static common.Messages.INVALID_TASK_NUMBER_ERROR_MESSAGE;
import static common.Messages.TASK_ALREADY_COMPLETED_ERROR_MESSAGE;
import static common.Messages.DISPLAYED_INDEX_OFFSET;
import static common.Messages.SAVE_TASKLIST_TO_FILE_FAILURE_MESSAGE;
import static common.Messages.INVALID_COMMAND_ERROR_MESSAGE;
import static common.Messages.NO_MATCHING_SEARCH_RESULTS_MESSAGE;
import static common.Messages.MATCHING_SEARCH_RESULTS_INTRO_MESSAGE;
import static common.Messages.HELP_COMMAND_LIST;
import static common.Messages.TASKLIST_CLEARED_MESSAGE;

/**
 * This class represents an individual command. The call for command execution is done through here. It also executes the relevant
 * operations on the {@link TaskList} containing the Tasks.
 * @see TaskList
 */
public class Command {

    private Messages messageContainer = new Messages();
    private String keyword;
    private String[] tokenizedInput;
    private String[] taskDescriptionRemarksFieldsInput;
    private String query;
    private Storage storage;

    /**
     * Constructor for LIST, HELP and BYE command.
     * @param keyword the respective command keyword, indicates the operation to be executed
     */
    public Command(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Constructor for DONE, DELETE and FIND command.
     * @param keyword the respective command keyword, indicates the operation to be executed
     * @param queryInput this input is a number index (for DONE and DELETE) or a search keyword (for FIND)
     */
    public Command(String keyword, String queryInput) {
        this.keyword = keyword;
        this.query = queryInput;
    }

    /**
     * Constructor for TODO, EVENT or DEADLINE command.
     * @param keyword the respective command keyword, indicates the operation to be executed
     * @param tokenizedInput a string array of the original user input string, delimited by whitespaces
     * @param processedUserInput a string array of the description and remarks fields in the original user input.
     */
    public Command(String keyword, String[] tokenizedInput, String[] processedUserInput) {
        this.keyword = keyword;
        this.tokenizedInput = tokenizedInput;
        this.taskDescriptionRemarksFieldsInput = processedUserInput;
    }

    /**
     * This method parses the keyword attribute of the {@link Command} object, and carries out the operation corresponding to the keyword on a {@link TaskList} list.
     * <p></p>
     * <p>
     * If any exception is encountered during the operation, they will be thrown and caught by the exception handler
     * in the main class ({@link data.Duke})
     * </p>
     *
     * @param taskListInput the list of tasks
     * @param uiInput for displaying ui.Ui elements
     * @param storageInput for saving updated tasklist to local save file
     * @throws NumberFieldException an exception thrown in DONE and DELETE command operations; when the task number given is not a number, or outside the range of existing tasks
     * @throws IllegalKeywordException an exception thrown when the command keyword is not recognized as a valid command
     * @see TaskList
     * @see NumberFieldException
     * @see Ui
     */
    public void execute(TaskList taskListInput, Ui uiInput, Storage storageInput) throws NumberFieldException,
            IllegalKeywordException {
        this.storage = storageInput;
        switch (keyword.toLowerCase()) {
        case (BYE_COMMAND):
            break;
        case (LIST_COMMAND):
            printTaskList(taskListInput, uiInput);
            break;
        case (HELP_COMMAND):
            printHelpList(uiInput);
            break;
        case (CLEAR_COMMAND):
            clearTaskList(taskListInput, uiInput, storage);
            break;
        case (DONE_COMMAND):
            updateTaskDone(taskListInput, uiInput, query, storage);
            break;
        case (DELETE_COMMAND):
            deleteTask(taskListInput, uiInput, query, storage);
            break;
        case (FIND_COMMAND):
            findTasksByKeyword(taskListInput, uiInput, query);
            break;
        default:
            insertNewTask(taskListInput, uiInput, tokenizedInput, storage);
            break;
        }
    }

    /**
     * This method prints out the {@link Task} objects that are currently existing in the given {@link TaskList} list.
     * <p></p>
     * <p>
     * The list includes the type ({@link Todo}, {@link Event}, {@link Deadline}) of each Task and the completion status
     * of each task. If there are no tasks in the TaskList, an empty list message is printed instead.
     * </p>
     *
     * @param listInput         the list of Tasks
     * @param uiInput           for displaying ui.Ui elements
     * @see TaskList
     * @see Ui
     * @see Todo
     * @see Event
     * @see Deadline
     */
    public void printTaskList(TaskList listInput, Ui uiInput) {
        String taskListPrintOutput = "";

        //if list empty, inform user and await next command
        if (listInput.getTaskCount() == 0) {
            uiInput.displayMessage(EMPTY_LIST_ERROR_MESSAGE);
            return;
        }
        //if list non-empty, print out all existing tasks
        for (int i = 0; i < listInput.getTaskCount(); i++) {
            String currentTaskText = "\t" + Integer.toString(i + 1) + "."
                    + listInput.getTaskList().get(i).toString() + LS;
            taskListPrintOutput += currentTaskText;
        }
        uiInput.displayMessage(taskListPrintOutput);
        return;
    }

    /**
     * This method marks a {@link Task} object (denoted by task number) in the {@link TaskList} list as "done".
     * <p></p>
     * <p>
     * If the task number given is not a valid number or falls outside the range of existing tasks, an error message will be shown stating that the number chosen is out of range
     * </p>
     *
     * @param listInput         the list of tasks
     * @param taskNumberInput   the task number of the task to be marked as done
     * @param uiInput           for displaying ui.Ui elements
     * @param storageInput for saving updated tasklist to local save file
     * @throws NumberFieldException the exception thrown when the task number given is not a number, or outside the range of existing tasks
     * @see TaskList
     * @see NumberFieldException
     * @see Ui
     */
    public void updateTaskDone(TaskList listInput, Ui uiInput, String taskNumberInput, Storage storageInput)
            throws NumberFieldException {
        int queryNumber;
        try {
            queryNumber = Integer.parseInt(taskNumberInput);
        } catch (NumberFormatException e) {
            throw new NumberFieldException(INVALID_TASK_NUMBER_ERROR_MESSAGE);
        }
        boolean isOutOfRange = queryNumber < 1 || queryNumber > listInput.getTaskCount();

        //handle case where user inputs non-existing task number to mark as done
        if (isOutOfRange){
            uiInput.displayMessage(INVALID_TASK_NUMBER_ERROR_MESSAGE);
            return;
        }
        //handle case where user tries to mark as done an already completed task
        boolean isTaskAlreadyDone = listInput.getTaskDoneStatus(queryNumber-1);

        if (isTaskAlreadyDone) {
            uiInput.displayMessage(TASK_ALREADY_COMPLETED_ERROR_MESSAGE);
            return;
        }
        listInput.markTaskAsDone(queryNumber-DISPLAYED_INDEX_OFFSET);

        String taskDoneMessage = messageContainer.getTaskDoneMessage(queryNumber, listInput);
        uiInput.displayMessage(taskDoneMessage);
        try {
            storageInput.saveTaskListToFile(listInput);
        } catch (IOException e) {
            uiInput.displayMessage(SAVE_TASKLIST_TO_FILE_FAILURE_MESSAGE);
        }
    }

    /**
     * This method constructs a new {@link Task} from the attributes of the {@link Command} object, and inserts it into a given {@link TaskList} list.
     * <p></p>
     * <p>
     * If the task number given is not a valid number or falls outside the range of existing tasks, an error message will be shown stating that the number chosen is out of range
     * </p>
     *
     * @param listInput         the list of Tasks
     * @param uiInput           for displaying Ui elements
     * @param storageInput for saving updated tasklist to local save file
     * @throws IllegalKeywordException occurs when the command keyword is an unknown type (not TODO, DEADLINE or EVENT)
     * @see TaskList
     * @see Ui
     */
    private void insertNewTask(TaskList listInput, Ui uiInput, String[] tokenizedInput, Storage storageInput) throws
            IllegalKeywordException {
        Task newTask;
        switch (tokenizedInput[0]) {
        case (TODO_COMMAND):
            newTask = new Todo(taskDescriptionRemarksFieldsInput[0]);
            break;
        case (DEADLINE_COMMAND):
            newTask = new Deadline(taskDescriptionRemarksFieldsInput[0], taskDescriptionRemarksFieldsInput[1]);
            break;
        case (EVENT_COMMAND):
            newTask = new Event(taskDescriptionRemarksFieldsInput[0], taskDescriptionRemarksFieldsInput[1]);
            break;
        default:
            throw new IllegalKeywordException(INVALID_COMMAND_ERROR_MESSAGE);
        }

        listInput.addTask(newTask);
        String taskAddedMessage = messageContainer.getTaskAddedMessage(newTask, listInput);
        uiInput.displayMessage(taskAddedMessage);
        try {
            storageInput.saveTaskListToFile(listInput);
        } catch (IOException e) {
            uiInput.displayMessage(SAVE_TASKLIST_TO_FILE_FAILURE_MESSAGE);
        }
    }

    /**
     * This method deletes a {@link Task} object (denoted by task number) in the {@link TaskList} list.
     * <p></p>
     * <p>
     * If the task number input given is not an integer or falls outside the range of existing tasks, an error message will be shown stating that the number input is invalid.
     * </p>
     *
     * @param listInput         the list of Tasks
     * @param uiInput           for displaying ui.Ui elements
     * @param taskNumberInput   the task number of the task to be deleted
     * @param storageInput for saving updated tasklist to local save file
     * @throws NumberFieldException the exception thrown when the task number given is not an integer, or outside the range of existing tasks
     * @see TaskList
     * @see NumberFieldException
     * @see Ui
     */
    private void deleteTask(TaskList listInput, Ui uiInput, String taskNumberInput, Storage storageInput)
            throws NumberFieldException {
        int taskNumberForRemoval;
        try {
            taskNumberForRemoval = Integer.parseInt(taskNumberInput);
        } catch (NumberFormatException e) {
            throw new NumberFieldException(INVALID_TASK_NUMBER_ERROR_MESSAGE);
        }

        boolean isOutOfBounds = (taskNumberForRemoval <= 0 || taskNumberForRemoval > listInput.getTaskCount());
        if (isOutOfBounds) {
            throw new NumberFieldException(INVALID_TASK_NUMBER_ERROR_MESSAGE);
        }
        Task removedTask = listInput.deleteTask(Integer.valueOf(taskNumberForRemoval)
                - DISPLAYED_INDEX_OFFSET);

        String taskRemovedMessage = messageContainer.getTaskRemovedMessage(removedTask, listInput);
        uiInput.displayMessage(taskRemovedMessage);
        try {
            storageInput.saveTaskListToFile(listInput);
        } catch (IOException e) {
            uiInput.displayMessage(SAVE_TASKLIST_TO_FILE_FAILURE_MESSAGE);
        }
    }

    /**
     * This method searches the Tasks in the {@link TaskList} object input for a keyword. It filters out
     * Tasks containing the search keyword and prints them.
     * @param listInput the TaskList object to be searched
     * @param uiInput helps to display filtered Tasks
     * @param searchQuery the keyword to be searched for in Tasks
     */
    private void findTasksByKeyword(TaskList listInput, Ui uiInput, String searchQuery) {
        int resultNumber = 1;
        ArrayList<Task> searchResults = listInput.findSearchResults(listInput.getTaskList(), searchQuery);

        if (Integer.valueOf(searchResults.size()).equals(Integer.valueOf(0))) {
            uiInput.displayMessage(NO_MATCHING_SEARCH_RESULTS_MESSAGE);
        } else {
            String searchOutput = MATCHING_SEARCH_RESULTS_INTRO_MESSAGE + LS;
            for (Task result : searchResults) {
                searchOutput += "\t"+ Integer.toString(resultNumber) + "." + result.toString() + LS;
                resultNumber++;
            }
            uiInput.displayMessage(searchOutput);
        }
    }

    /**
     * This method prints out a help message listing all the commands and their proper usages.
     * @param uiInput helps to display the help message
     */
    private void printHelpList(Ui uiInput) {
        uiInput.displayMessage(HELP_COMMAND_LIST);
    }

    /**
     * This method clears all existing tasks in the current task list.
     * @param listInput the {@link TaskList} object containing all tasks
     */
    private void clearTaskList(TaskList listInput, Ui uiInput, Storage storageInput) {
        listInput.getTaskList().clear();
        uiInput.displayMessage(TASKLIST_CLEARED_MESSAGE);
        try {
            storageInput.saveTaskListToFile(listInput);
        } catch (IOException e) {
            uiInput.displayMessage(SAVE_TASKLIST_TO_FILE_FAILURE_MESSAGE);
        }
    }
}
