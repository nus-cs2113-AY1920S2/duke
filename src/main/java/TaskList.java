import java.util.ArrayList;

/**
 * Represents all the Task objects
 * A TaskList object corresponds to storage, dukeExceptions and ArrayList object
 */
public class TaskList {

    protected Storage storage;
    protected Event newEvent;
    protected ToDo newToDo;
    protected Deadline newDeadline;
    protected ArrayList<Task> tasks;
    protected DukeExceptions dukeExceptions;

    public TaskList() {
        storage = new Storage();
        dukeExceptions = new DukeExceptions();
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Returns index for delete and done operations
     * @param input String input by user
     * @param position number of characters of delete / done operation
     * @return index
     */
    public int getIndex (String input, int position) {
        String removeTrailingSpaces = input.trim();
        String numericalIndex = removeTrailingSpaces.substring(position,input.length()).trim();
        int index = Integer.valueOf(numericalIndex) - 1;
        return index;
    }

    public void markAsDone(int index) {
        Task completedTask = tasks.get(index);
        boolean isCompleted = completedTask.getStatus();
        if (isCompleted) {
            dukeExceptions.printDoneExceptions();
        } else {
            completedTask.markAsDone();
            tasks.set(index, completedTask);
            System.out.println(" Duke has marked the following tasks as done:");
            System.out.println("  " + completedTask);
            storage.save(tasks);
        }
    }

    public void addToDo(String input) {
        String removeTrailingSpaces = input.trim();
        String description = removeTrailingSpaces.substring(5, input.length());
        newToDo = new ToDo(description);
        tasks.add(newToDo);
        System.out.println(" Got it! I have added the following tasks: ");
        System.out.println( newToDo);
        System.out.println("There are currently " + tasks.size() + " task(s) queued");
        storage.save(tasks);
    }

    public void addEvent(String input) {
        String description = input.substring(5, input.length()).trim();
        int index = description.indexOf("/");
        String details = description.substring(0, index).trim();
        String specifier = description.substring(index + 1, index + 3);
        String time = description.substring(index + 3, description.length()).trim();
        boolean isAt = specifier.toLowerCase().equals("at");
        if (details.isEmpty() || time.isEmpty() || !isAt) {
            String errorMessage = "Missing placeholder";
            dukeExceptions.printEventExceptions(errorMessage, input);
        } else {
            newEvent = new Event(details, time);
            tasks.add(newEvent);
            System.out.println(" New task has been added:");
            System.out.println(" " + newEvent);
            System.out.println(" There are currently " + tasks.size() + " task(s) being queued");
            storage.save(tasks);
        }
    }

    public void addDeadline(String input) {
        String description = input.substring(9, input.length()).trim();
        int index = description.indexOf("/");
        String details = description.substring(0, index);
        String day = description.substring(index + 3, description.length()).trim();
        String specifier = description.substring(index + 1, index + 3);
        boolean isBy = specifier.toLowerCase().equals("by");
        if (details.isEmpty() || day.isEmpty() || !isBy) {
            String errorMessage = "Missing placeholder";
            dukeExceptions.printDeadlineExceptions(errorMessage, input);
        } else {
            newDeadline = new Deadline(details, day);
            tasks.add(newDeadline);
            System.out.println(" Got it!I've added this task:");
            System.out.println(" " + newDeadline);
            System.out.println(" There are currently " + tasks.size() + " task(s) being queued");
            storage.save(tasks);
        }
    }

    public void deleteItem(int index) {
        Task deletedTask = tasks.get(index);
        int length = tasks.size() - 1;
        tasks.remove(index);
        System.out.println(" Got it!I've removed this task: ");
        System.out.println(" " + deletedTask);
        System.out.println(" There are currently " + length + " task(s) being queued");
        storage.save(tasks);
    }

    /**
     * Prints task in tasks list
     */
    public void printTasks() {
        int position = 1;
        for (Task text: tasks) {
            System.out.println("    " + position + ". " + text);
            position ++;
        }
    }

    /**
     * List operation to print task in tasks list
     */
    public void list() {
        tasks = storage.loadTasks();
        if (tasks.size() == 0) {
            dukeExceptions.printListExceptions();
        } else {
            System.out.println(" Here are the list of tasks:");
            printTasks();
        }
    }

    /**
     * Marks Task object as completed
     * Prints error messages if IndexOutOfBoundsException
     * Prints error message if NumberFormatException
     * @param input String input by user
     * @exception IndexOutOfBoundsException if index < length of task list or index > length of task list
     * @exception NumberFormatException if index is not a number
     */
    public void getDoneExceptions(String input) {
        try {
            int index = getIndex(input,5);
            markAsDone(index);
        } catch(IndexOutOfBoundsException exception) {
            dukeExceptions.printIOBDoneExceptions(input);
        } catch (NumberFormatException exception) {
            dukeExceptions.printNFEDoneExceptions(input);
        }
    }

    /**
     * Adds ToDo task into tasks list
     * @param input String input by user
     * @throws StringIndexOutOfBoundsException if description of todo is empty
     */
    public void getToDoExceptions(String input) {
        try {
            addToDo(input);
        } catch (StringIndexOutOfBoundsException exception) {
            dukeExceptions.printToDoExceptions(input);
        }
    }

    /**
     * Adds Event task into tasks list
     * Prints error message if StringIndexOutOfBoundsException
     * @param input String input by user
     * @exception StringIndexOutOfBoundsException if description of event / event location or time is empty
     */
    public void getEventExceptions(String input) {
        try {
            addEvent(input);
        } catch (IndexOutOfBoundsException exception) {
            String errorMessage = "Missing event description(s) / location or time / placeholder";
            dukeExceptions.printEventExceptions(errorMessage, input);
        }
    }

    /**
     * Adds Deadline task into tasks list
     * Prints error message if IndexOutOfBoundsException
     * @param input String input by user
     * @exception IndexOutOfBoundsException if description of deadline / deadline date or time is empty
     */
    public void getDeadlineExceptions(String input) {
        try {
            addDeadline(input);
        } catch (IndexOutOfBoundsException exception) {
            String errorMessage = "Missing event description(s) / date or time / placeholder";
            dukeExceptions.printDeadlineExceptions(errorMessage, input);
        }
    }

    /**
     * Deletes object based on index
     * Prints error message if IndexOutOfBoundsException
     * Prints error message if NumberFormatException
     * @param input String input by user
     * @throws IndexOutOfBoundsException if index < length of task list or index > length of task list
     * @throws NumberFormatException if index is not a number
     */
    public void getDeleteExceptions(String input) {
        try {
            int index = getIndex(input, 6);
            deleteItem(index);
        } catch (IndexOutOfBoundsException exception) {
            dukeExceptions.printIOBDeleteExceptions(input);
        } catch (NumberFormatException exception) {
            dukeExceptions.printNFEDeleteExceptions(input);
        }
    }

    public ArrayList<Task> getSearchQuery(String input) {
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

    public void printSearchQuery(String description) {
        ArrayList<Task> results = getSearchQuery(description);
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
     * Finds task based on keywords
     * Prints error message if search query is empty
     * @param input String input by user
     */
    public void getFindExceptions(String input) {
        String description = input.substring(4, input.length()).trim();
        if (description.isBlank()) {
            dukeExceptions.printFindExceptions(input);
        } else {
            printSearchQuery(description);
        }
    }
}