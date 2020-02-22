package TaskList;

import Exceptions.EmptyListException;
import Exceptions.TaskAlreadyDoneException;
import Task.Task;
import Task.Todo;
import Task.Deadline;
import Task.Events;

import java.util.ArrayList;

/**
 * Class that manages all operations regarding TaskList
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs TaskList object for an existing TaskList
     * @param taskList
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Constructs new TaskList
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Returns the TaskList object
     * @return TaskList object
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Prints all task in TaskList in list formatting
     * @throws EmptyListException throws when task list is empty
     */
    public void printTaskList() throws EmptyListException {
        if (taskList.isEmpty()) {
            throw new EmptyListException("The list seems to be empty!");
        } else {
            System.out.println("Here are the tasks in your list:");
            int index = 1;
            for (Task task : taskList) {
                System.out.println(String.format("%d. %s", index++, task.toString()));
            }
        }
    }

    /**
     * Marks Task as done
     * @param itemIndex index of Task to be marked done
     * @throws IndexOutOfBoundsException throws when index provided by user is out of bounds of list
     * @throws TaskAlreadyDoneException throws when user tries to mark done a Task that is already done
     */
    public void markTaskAsDone(int itemIndex) throws IndexOutOfBoundsException {
        int listIndex = itemIndex + 1;
        try {
            if (taskList.get(itemIndex).isDone().equals("Y")) {
                throw new TaskAlreadyDoneException("Task already done");
            } else {
                taskList.get(itemIndex).markAsDone();
                System.out.println(taskList.get(itemIndex).getDoneResponseMessage(listIndex));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You don't have that many items in the list. Please try a number within range!");
        } catch (TaskAlreadyDoneException e) {
            System.out.println(e);
        }
    }

    /**
     * Deletes a Task
     * @param itemIndex index of Task to be deleted provided by user
     * @throws IndexOutOfBoundsException throws when index provided is out of range of list
     */
    public void deleteTask(int itemIndex) throws IndexOutOfBoundsException {
        try{
            String taskDetails = taskList.get(itemIndex).toString();
            taskList.remove(itemIndex);
            printSuccessfulDeletion(taskDetails);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You don't have that many items in the list. Please try a number within range!");
        }
    }

    /**
     * Prints successful deletion message
     * @param taskDetails details of Task being deleted
     */
    public void printSuccessfulDeletion(String taskDetails) {
        Task.reduceNumberOfTaskInList();
        System.out.println(String.format("Removed the task:\n    %s", taskDetails));
        System.out.println(String.format("Now you have %d tasks in the list!",
                Task.getNumberOfTasksInList()));
    }

    /**
     * add a new ToDo and updates user interface
     * @param description description of Todo provided by user
     */
    public void addNewToDo(String description) {
        Todo todo = new Todo(description);
        taskList.add(todo);
        printSuccessfulAddMessage(todo.toString());
    }

    /**
     * add a new Deadline and updates user interface
     * @param description description of Deadline provided by user
     * @param date date of Deadline provided by user
     */
    public void addNewDeadline(String description, String date) {
        Deadline deadline = new Deadline(description, date);
        taskList.add(deadline);
        printSuccessfulAddMessage(deadline.toString());
    }

    /**
     * Add a new Event and updates user interface
     * @param description description of Event provided by user
     * @param date date of Event provided by user
     */
    public void addNewEvent(String description, String date) {
        Events event = new Events(description, date);
        taskList.add(event);
        printSuccessfulAddMessage(event.toString());
    }

    /**
     * Prints to user interface a successful adding of Task
     * @param taskDetails details of Task added
     */
    public void printSuccessfulAddMessage(String taskDetails) {
        System.out.println(String.format("Added the task:\n    %s", taskDetails));
        System.out.println(String.format("Now you have %d tasks in the list!",
                Task.getNumberOfTasksInList()));
    }

    /**
     * Finds matching tasks from keyword
     * @param keyword substring to be searched
     */
    public void findTask(String keyword) {
        int index = 1;
        for (Task task : taskList) {
            if (task.toString().contains(keyword)) {
                if (index == 1) {
                    System.out.println("Here are the matching tasks in your list:");
                }
                System.out.println(String.format("%d. %s", index++, task.toString()));
            }
        }
        if (index == 1) {
            System.out.println("There are no matching results");
        }
    }
}
