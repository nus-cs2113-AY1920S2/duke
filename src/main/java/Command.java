import data.*;
import common.Messages;
import tasklist.TaskList;

import java.util.ArrayList;

import static common.Messages.*;


public class Command {

    private Messages messageContainer = new Messages();
    private String keyword;
    private String[] tokenizedInput;
    private String[] taskDescriptionRemarksFieldsInput;
    private String query;

    //for list and bye
    public Command(String keyword) {
        this.keyword = keyword;
    }

    //for done, delete and find
    public Command(String keyword, String queryInput) {
        this.keyword = keyword;
        this.query = queryInput;
    }

    //for todo, event and deadline
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
            updateTaskDone(taskListInput, uiInput, query);
            break;
        case (DELETE_COMMAND):
            deleteTask(taskListInput, uiInput, query);
            break;
        case (FIND_COMMAND):
            findTasksByKeyword(taskListInput, uiInput, query);
            break;
        default:
            insertNewTask(taskListInput, uiInput, tokenizedInput);
            break;
        }
    }

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
        }

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

    private void findTasksByKeyword(TaskList listInput, Ui uiInput, String searchQuery) {
        int resultNumber = 1;
        ArrayList<Task> searchResults = listInput.findSearchResults(listInput.getTaskList(), searchQuery);

        if (Integer.valueOf(searchResults.size()).equals(Integer.valueOf(0))) {
            uiInput.displayMessage(NO_MATCHING_SEARCH_RESULTS_MESSAGE);
        } else {
            String searchOutput = "\tHere are the search results: " + LS;
            for (Task result : searchResults) {
                searchOutput += "\t"+ Integer.toString(resultNumber) + "." + result.toString() + LS;
                resultNumber++;
            }
            uiInput.displayMessage(searchOutput);
        }
    }

}
