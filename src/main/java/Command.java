import data.*;
import common.Messages;
import tasklist.TaskList;

import static common.Messages.*;


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
            /*
            try {
                deleteTask(taskListInput, uiInput, tokenizedInput[1]);
            } catch (NumberFieldException e) {
                uiInput.displayMessage(INVALID_TASK_NUMBER_ERROR_MESSAGE);
            } catch (MissingParameterException e) {
                uiInput.displayMessage(INSUFFICIENT_COMMAND_PARAMETERS_ERROR_MESSAGE);
            }

             */
            break;
        default:
            insertNewTask(taskListInput, uiInput, tokenizedInput);
            /*
            try {
                insertNewTask(taskListInput, uiInput, tokenizedInput);
            } catch (IllegalKeywordException e) {
                uiInput.displayMessage(INVALID_COMMAND_ERROR_MESSAGE);
            } catch (NoDescriptionException e) {
                uiInput.displayMessage(messageContainer.addTaskEmptyDescriptionErrorMessage(tokenizedInput[0]));
            } catch (NoRemarkException e) {
                uiInput.displayMessage(messageContainer.addTaskEmptyRemarksErrorMessage(tokenizedInput[0]));
            }

             */
            break;
        }
    }

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

    private void deleteTask(TaskList listInput, Ui uiInput, String taskNumberInput) throws NumberFieldException {
        int taskNumberForRemoval;
        //TODO: exceptions - second input out of bounds, not integer, no second input, only whitespaces after firstinput
        try {
            taskNumberForRemoval = Integer.parseInt(taskNumberInput);
        } catch (NumberFormatException e) {
            //throw NumberFieldException if taskNumber is a string eg. "remove foo" OR whitespaces only
            throw new NumberFieldException(INVALID_TASK_NUMBER_ERROR_MESSAGE);
        } /*catch (ArrayIndexOutOfBoundsException e) {
            //throw MissingParameterException if remove cmd given without 2nd input (ie "remove") HANDLED IN PARSER.JAVA
            throw new MissingParameterException(INSUFFICIENT_COMMAND_PARAMETERS_ERROR_MESSAGE);
        }*/

        //throw NumberFieldException if task number out of range
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
