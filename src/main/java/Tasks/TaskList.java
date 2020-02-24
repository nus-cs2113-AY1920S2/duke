package Tasks;

import Exceptions.CompletedTaskException;
import Exceptions.EmptyStringException;
import Exceptions.IndexFormatException;
import Exceptions.MissingDescriptionsException;
import Exceptions.MissingParameterException;
import Exceptions.MissingDetailsException;
import Exceptions.MissingSearchQueryException;
import Exceptions.OutOfRangeException;
import Exceptions.MissingIndexException;
import Tasks.ErrorMessages;
import Storage.Storage;
import java.util.ArrayList;

public class TaskList {

    protected Storage storage;
    protected ArrayList<Task> tasks;
    protected ErrorMessages errorMessages;

    public TaskList() {
        storage = new Storage();
        tasks = new ArrayList<Task>();
        errorMessages = new ErrorMessages();
    }

    /**
     * Returns the String format of index for done and delete operations
     * @param input String input by user
     * @param position number of characters to remove
     * @return String format of index
     */
    public String getStringIndex (String input, int position) {
        String removeTrailingSpaces = input.trim();
        String numericalIndex = removeTrailingSpaces.substring(position,input.length()).trim();
        return numericalIndex;
    }

    /**
     * Returns true if String is numeric. Otherwise, return false
     * @param number String to be checked against
     * @return boolean value
     */
    public boolean isNumeric(String number) {
        boolean isDigit = true;
        for (int i = 0; i < number.length(); i ++) {
            char letter = number.charAt(i);
            if (!Character.isDigit(letter)) {
                isDigit = false;
                break;
            }
        }
        return isDigit;
    }

    /**
     * Return true if String is out of range, otherwise false
     * @param number String index to be checked against
     * @return boolean value
     */
    public boolean isOutOfRange(String number) {
        int index = Integer.parseInt(number);
        return (index <= 0 || index > tasks.size());
    }

    /**
     * Return true if task is completed, otherwise false
     * @param number String index of task
     * @return boolean value
     */
    public boolean isCompleted(String number) {
        int index = Integer.parseInt(number) - 1;
        Task completedTask = tasks.get(index);
        return completedTask.getStatus();
    }

    /**
     * Prints list of tasks in task list
     */
    public void printTasks() {
        int position = 1;
        for (Task text: tasks) {
            System.out.println("    " + position + ". " + text);
            position ++;
        }
    }

    /**
     * Returns description of task
     * @param input String input by user
     * @param lengthOfCommand length of the command that the user input
     * @return description of task
     */
    public String getDescription(String input, int lengthOfCommand) {
        String removeCommand = input.substring(lengthOfCommand).trim();
        int index = removeCommand.indexOf("/");
        String description = removeCommand.substring(0,index);
        return description.trim();
    }

    /**
     * Returns date/time/location of task
     * @param input String input by user
     * @param lengthOfCommand length of command that user input
     * @return data/time/location of task
     */
    public String getDetails(String input, int lengthOfCommand) {
        String removeCommand = input.substring(lengthOfCommand).trim();
        int index = removeCommand.indexOf("/");
        String details = removeCommand.substring(index + 3).trim();
        return details.trim();
    }

    /**
     * Returns true if user input an empty description, otherwise false
     * @param input String input by user
     * @param lengthOfCommand length of command that user inputs
     * @return boolean value
     */
    public boolean isEmptyDescription(String input, int lengthOfCommand) {
        String description = getDescription(input, lengthOfCommand);
        if (description.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns true if user input an empty details, otherwise false
     * @param input String input by user
     * @param lengthOfCommand length of command that user inputs
     * @return boolean value
     */
    public boolean isEmptyDetails(String input, int lengthOfCommand) {
        String details = getDetails(input, lengthOfCommand);
        if (details.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Prints out the list of  tasks in the task list
     */
    public void list() {
        tasks = storage.loadTasks();
        if (tasks.size() == 0) {
            System.out.println("    [Warning: There are no items queued in the list]");
        } else {
            System.out.println(" Here are the list of tasks:");
            printTasks();
        }
    }

    /**
     * Marks task as completed
     * @param input String input by user
     * @throws IndexFormatException if index is not a number
     * @throws OutOfRangeException if index <= 0 or index > size of tasks list
     * @throws CompletedTaskException if task has already been completed
     */
    public void markAsDone(String input) throws IndexFormatException, OutOfRangeException, CompletedTaskException,
            MissingIndexException{
        String numericalIndex = getStringIndex(input,4);
        boolean isNumber = isNumeric(numericalIndex);
        if (numericalIndex.length() == 0) {
            errorMessages.doneErrorMessage();
            throw new MissingIndexException();
        } else if (!isNumber) {
            errorMessages.doneErrorMessage();
            throw new IndexFormatException();
        } else if(isOutOfRange(numericalIndex)) {
            errorMessages.doneErrorMessage();
            throw new OutOfRangeException();
        } else if (isCompleted(numericalIndex)){
            throw new CompletedTaskException();
        } else {
            int index = Integer.parseInt(numericalIndex) - 1;
            Task completedTask = tasks.get(index);
            completedTask.markAsDone();
            tasks.set(index, completedTask);
            System.out.println(" Duke.Duke has marked the following tasks as done:");
            System.out.println("  " + completedTask);
            storage.save(tasks);
        }
    }

    /**
     * Deletes tasks from task list
     * @param input String input by user
     * @throws IndexFormatException if index is not a number
     * @throws OutOfRangeException if index <= 0 or index >size of task list
     */
    public void deleteItem(String input) throws IndexFormatException, OutOfRangeException, MissingIndexException {
        String numericalIndex = getStringIndex(input,6);
        boolean isNumber = isNumeric(numericalIndex);
        if (numericalIndex.length() == 0) {
            errorMessages.deleteErrorMessage();
            throw new MissingIndexException();
        } else if (!isNumber) {
            errorMessages.deleteErrorMessage();
            throw new IndexFormatException();
        } else if(isOutOfRange(numericalIndex)) {
            errorMessages.deleteErrorMessage();
            throw new OutOfRangeException();
        } else {
            int index = Integer.parseInt(numericalIndex) - 1;
            Task deletedTask = tasks.get(index);
            int length = tasks.size() - 1;
            tasks.remove(index);
            System.out.println(" Got it!I've removed this task: ");
            System.out.println(" " + deletedTask);
            System.out.println(" There are currently " + length + " task(s) being queued");
            storage.save(tasks);
        }
    }

    /**
     * Adds a toDo task to the task list
     * @param input String input by user
     * @throws MissingDescriptionsException ToDo task is missing a description
     */
    public void addToDo(String input) throws MissingDescriptionsException {
        String removeTrailingSpaces = input.trim();
        int lengthOfCommand = 4;
        if (removeTrailingSpaces.length() == lengthOfCommand) {
            errorMessages.toDoErrorMessage();
            throw new MissingDescriptionsException();
        } else {
            String description = removeTrailingSpaces.substring(5, input.length());
            ToDo newToDo = new ToDo(description);
            tasks.add(newToDo);
            System.out.println(" Got it! I have added the following tasks: ");
            System.out.println( newToDo);
            System.out.println("There are currently " + tasks.size() + " task(s) queued");
            storage.save(tasks);
        }
    }

    /**
     * Adds deadline to task list
     * @param input String input by user
     * @param lengthOfCommand length of command used by user
     */
    public void addDeadlineToList(String input, int lengthOfCommand) {
        String description = getDescription(input,lengthOfCommand);
        String details = getDetails(input,lengthOfCommand);
        Deadline newDeadline = new Deadline(description, details);
        tasks.add(newDeadline);
        System.out.println(" Got it!I've added this task:");
        System.out.println(" " + newDeadline);
        System.out.println(" There are currently " + tasks.size() + " task(s) being queued");
        storage.save(tasks);
    }

    /**
     * Adds deadline task to the task list
     * @param input String input by user
     * @throws EmptyStringException if all fields of deadline tasks are empty
     * @throws MissingParameterException if /by is missing
     * @throws MissingDescriptionsException if description of deadline task is missing
     * @throws MissingDetailsException if details of deadline is missing
     */
    public void addDeadline(String input) throws EmptyStringException, MissingParameterException,
            MissingDescriptionsException, MissingDetailsException {
        String removeTrailingSpaces = input.trim();
        int lengthOfCommand = 8;
        if (removeTrailingSpaces.length() == lengthOfCommand) {
            errorMessages.deadlineErrorMessage();
            throw new EmptyStringException();
        } else if (!removeTrailingSpaces.contains("/by")) {
            errorMessages.deadlineErrorMessage();
            throw new MissingParameterException();
        } else if (isEmptyDescription(input, lengthOfCommand)) {
            errorMessages.deadlineErrorMessage();
            throw new MissingDescriptionsException();
        } else if (isEmptyDetails(input, lengthOfCommand)) {
            errorMessages.deadlineErrorMessage();
            throw new MissingDetailsException();
        } else {
            addDeadlineToList(input, lengthOfCommand);
        }
    }

    /**
     * Adds Event to task list
     * @param input String user input
     * @param lengthOfCommand length of command that user input
     */
    public void addEventToList(String input, int lengthOfCommand) {
        String description = getDescription(input,lengthOfCommand);
        String details = getDetails(input,lengthOfCommand);
        Event newEvent = new Event(description, details);
        tasks.add(newEvent);
        System.out.println(" Got it!I've added this task:");
        System.out.println(" " + newEvent);
        System.out.println(" There are currently " + tasks.size() + " task(s) being queued");
        storage.save(tasks);
    }

    /**
     * Adds an event task to the task list
     * @param input String input by user
     * @throws EmptyStringException if all fields are missing
     * @throws MissingParameterException if /at is missing
     * @throws MissingDescriptionsException if descriptions of event task is missing
     * @throws MissingDetailsException if event details are missing
     */
    public void addEvent(String input) throws EmptyStringException, MissingParameterException,
            MissingDescriptionsException, MissingDetailsException {
        String removeTrailingSpaces = input.trim();
        int lengthOfCommand = 5;
        if (removeTrailingSpaces.length() == lengthOfCommand) {
            errorMessages.eventErrorMessage();
            throw new EmptyStringException();
        } else if (!removeTrailingSpaces.contains("/at")) {
            errorMessages.eventErrorMessage();
            throw new MissingParameterException();
        } else if (isEmptyDescription(input, lengthOfCommand)) {
            errorMessages.eventErrorMessage();
            throw new MissingDescriptionsException();
        } else if (isEmptyDetails(input, lengthOfCommand)) {
            errorMessages.eventErrorMessage();
            throw new MissingDetailsException();
        } else {
            addEventToList(input, lengthOfCommand);
        }
    }

    /**
     * Returns an ArrayList containing the search results
     * @param input String input by user
     * @return ArrayList of search results
     */
    public ArrayList<Task> getSearchResults(String input) {
        String descriptionLowerCase = input.toLowerCase();
        ArrayList<Task> results = new ArrayList<Task>();
        for (Task task : tasks) {
            String taskDescription = task.getDescription().toLowerCase();
            if (taskDescription.contains(descriptionLowerCase)) {
                results.add(task);
            }
        }
        return results;
    }

    /**
     * Prints search results
     * @param description search query
     */
    public void printSearchResults(String description) {
        ArrayList<Task> results = getSearchResults(description);
        if (results.size() == 0) {
            System.out.println(" [WARNING: There are no tasks that matched the search queries]");
        } else {
            System.out.println(" Here are the matching tasks in your list:");
            int count = 1;
            for (Task task: results) {
                System.out.println("  " + count + ". " + task);
                count += 1;
            }
        }
    }

    /**
     * Prints search results based on query given
     * @param input String input by user
     * @throws MissingSearchQueryException if search query is missing
     */
    public void findSearchQuery(String input) throws MissingSearchQueryException {
        int lengthOfCommand = 4;
        String searchQuery = input.substring(lengthOfCommand).trim();
        if (searchQuery.length() == 0) {
            errorMessages.findErrorMessage();
            throw new MissingSearchQueryException();
        } else {
            printSearchResults(searchQuery);
        }
    }
}
