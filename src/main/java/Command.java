import data.*;
import common.Messages;
import exceptions.*;
import tasklist.TaskList;

import static common.Messages.*;


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
    private String queryTaskNumberText;

    public Command(String keyword) {
        this.keyword = keyword;
    }

    public Command(String keyword, String queryTaskNumberTextInput) {
        this.keyword = keyword;
        this.queryTaskNumberText = queryTaskNumberTextInput;
    }

    public Command(String keyword, String[] tokenizedInput, String[] processedUserInput) {
        this.keyword = keyword;
        this.tokenizedInput = tokenizedInput;
        this.taskDescriptionRemarksFieldsInput = processedUserInput;
    }

    //TODO
    /**
     * This method parses the keyword attribute of the {@link Command} object, and carries out the operation corresponding to the keyword on a {@link TaskList} list.
     * <p></p>
     * <p>
     * If any exception is encountered during the operation, they will be thrown and caught by the exception handler
     * in the main class ({@link Duke})
     * </p>
     *
     * @param taskListInput         the list of tasks
     * @param uiInput           for displaying Ui elements
     * @throws MissingParameterException
     * @throws NumberFieldException an exception thrown in DONE and DELETE command operations; when the task number given is not a number, or outside the range of existing tasks
     * @throws NoRemarkException an exception thrown in EVENT and DEADLINE command operations; when the new task does not contain a remarks field
     * @throws IllegalKeywordException an exception thrown when the command keyword is not recognized as a valid command
     * @throws NoDescriptionException an exception thrown in TODO, EVENT and DEADLINE command operations; when the new task does not contain a description field
     * @see TaskList
     * @see NumberFieldException
     * @see Ui
     */
    public void execute(TaskList taskListInput, Ui uiInput) throws MissingParameterException,
            NumberFieldException, NoRemarkException, IllegalKeywordException, NoDescriptionException {
        switch (keyword.toLowerCase()) {
        case (BYE_COMMAND):
            break;
        case (LIST_COMMAND):
            printTaskList(taskListInput, uiInput);
            break;
        case (DONE_COMMAND):
            updateTaskDone(taskListInput, uiInput, queryTaskNumberText);
            break;
        case (DELETE_COMMAND):
            deleteTask(taskListInput, uiInput, queryTaskNumberText);
            break;
        default:
            insertNewTask(taskListInput, uiInput, tokenizedInput);
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
     * @param uiInput           for displaying Ui elements
     * @see TaskList
     * @see Ui
     * @see Todo
     * @see Event
     * @see Deadline
     */
    public void printTaskList(TaskList listInput, Ui uiInput) {
        String taskListPrintOutput = new String();

        //if list empty, inform user and await next command
        if (listInput.getTaskCount() == 0) {
            uiInput.displayMessage(EMPTY_LIST_ERROR_MESSAGE);
            return;
        }
        //if list non-empty, print out all existing tasks
        for (int i = 0; i < listInput.getTaskCount(); i++) {
            taskListPrintOutput += "\t" + Integer.toString(i + 1) + "."
                    + listInput.getTaskList().get(i).toString() + LS;
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
     * @param uiInput           for displaying Ui elements
     * @throws NumberFieldException the exception thrown when the task number given is not a number, or outside the range of existing tasks
     * @see TaskList
     * @see NumberFieldException
     * @see Ui
     */
    public void updateTaskDone(TaskList listInput, Ui uiInput, String taskNumberInput) throws NumberFieldException {
        int queryNumber;
        try {
            queryNumber = Integer.parseInt(taskNumberInput);
        } catch (NumberFormatException e) {
            //throw NumberFieldException if taskNumber is a string eg. "remove foo" OR whitespaces only
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
    }

    //TODO
    /**
     * This method constructs a new {@link Task} from the attributes of the {@link Command} object, and inserts it into a given {@link TaskList} list.
     * <p></p>
     * <p>
     * If the task number given is not a valid number or falls outside the range of existing tasks, an error message will be shown stating that the number chosen is out of range
     * </p>
     *
     * @param listInput         the list of Tasks
     * @param uiInput           for displaying Ui elements
     * @throws MissingParameterException
     * @throws NumberFieldException the exception thrown when the task number given is not a number, or outside the range of existing tasks
     * @throws NoRemarkException
     * @throws IllegalKeywordException
     * @throws NoDescriptionException
     * @see TaskList
     * @see NumberFieldException
     * @see Ui
     */
    private void insertNewTask(TaskList listInput, Ui uiInput, String[] tokenizedInput) throws
    IllegalKeywordException, NoDescriptionException, NoRemarkException {
        Task newTask;
        switch (tokenizedInput[0]) {
        case (TODO_COMMAND):
            newTask = new Todo(taskDescriptionRemarksFieldsInput[0], taskDescriptionRemarksFieldsInput[1]);
            break;
        case (DEADLINE_COMMAND):
            newTask = new Deadline(taskDescriptionRemarksFieldsInput[0], taskDescriptionRemarksFieldsInput[1]);
            break;
        case (EVENT_COMMAND):
            newTask = new Event(taskDescriptionRemarksFieldsInput[0], taskDescriptionRemarksFieldsInput[1]);
            break;
        default:
            throw new IllegalKeywordException(INVALID_COMMAND_ERROR_MESSAGE);
            //break;
        }

        listInput.addTask(newTask);
        String taskAddedMessage = messageContainer.getTaskAddedMessage(newTask, listInput);
        uiInput.displayMessage(taskAddedMessage);
    }

    /**
     * This method deletes a {@link Task} object (denoted by task number) in the {@link TaskList} list.
     * <p></p>
     * <p>
     * If the task number input given is not an integer or falls outside the range of existing tasks, an error message will be shown stating that the number input is invalid.
     * </p>
     *
     * @param listInput         the list of Tasks
     * @param uiInput           for displaying Ui elements
     * @param taskNumberInput   the task number of the task to be deleted
     * @throws NumberFieldException the exception thrown when the task number given is not an integer, or outside the range of existing tasks
     * @see TaskList
     * @see NumberFieldException
     * @see Ui
     */
    private void deleteTask(TaskList listInput, Ui uiInput, String taskNumberInput) throws NumberFieldException {
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
    }
}
