package duke.taskList;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;
import duke.exception.DuplicateInformationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Represents the entire task  list. Contains the data of the task list.
 */
public class TaskList {

    public static ArrayList<Task> taskList = new ArrayList<>();

    public TaskList() {
    }

    /**
     * Constructs a list from the items in the given collection.
     *
     * @param Tasks a collection of tasks
     * @throws DuplicateTaskException if the {@code persons} contains duplicate tasks
     */
    public TaskList(List<Task> Tasks) throws DuplicateTaskException {
        if (!uniqueElements(Tasks)) {
            throw new DuplicateTaskException();
        }
        taskList.addAll(Tasks);
    }


    public static boolean contains(Task toCheck) {
        for (Task a : taskList) {
            if (a instanceof ToDos && toCheck instanceof ToDos) {
                return (((ToDos)a).isSameTask(toCheck));
            } else if (a instanceof Events && toCheck instanceof Events) {
                return (((Events)a).isSameTask(toCheck));
            } else if (a instanceof Deadlines && toCheck instanceof Deadlines) {
                return (((Deadlines)a).isSameTask(toCheck));
            }
        }
        return false;
    }

    /**
     * Adds one task to the list.
     *
     * @throws DuplicateTaskException if the task to add is a duplicate of an existing task in the list.
     */
    public static void add(Task toAdd) throws DuplicateTaskException {
        if (contains(toAdd)) {
            throw new DuplicateTaskException();
        }
        taskList.add(toAdd);
    }

    public static Task retrieve(int targetIndex) {
        return taskList.get(targetIndex);
    }

    /**
     * Removes task from the list.
     *
     * @throws TaskNotFoundException if no such task could be found in the list.
     */
    public static void remove(int toRemove) throws TaskNotFoundException {
        Task t = taskList.get(toRemove);
        final boolean taskFoundAndDeleted = taskList.remove(t);
        if (!taskFoundAndDeleted) {
            throw new TaskNotFoundException();
        }
    }


    public static int size() {
        return taskList.size();
    }

    /**
     * Clears task list.
     */
    public static void clear() {
        taskList.clear();
    }

    /**
     * @return the duplicate task list
     */
    public static ArrayList<Task> copy() {

        return (new ArrayList<>(taskList));
    }

    /**
     * Shows to user all elements in task list.
     */
    public static void printList(ArrayList<Task> taskList) throws NullPointerException {
        int taskCounter = 1;
        if (taskList.size() == 0) {
            throw new NullPointerException();
        } else {
            System.out.println("----------------------------");
            System.out.println("Sheena: Well, you have these items in your list: \n");
            for (Task t : taskList) {
                System.out.println(" " + taskCounter + ". " + t.toString());
                taskCounter++;
            }
            System.out.println("----------------------------");
        }
    }

    public static ArrayList<Task> filterTheList(ArrayList<Task> taskList, String keyword) {

        return (ArrayList<Task>) taskList.stream()
                .filter(task -> task.description.contains(keyword))
                .collect(Collectors.toList());
    }

    /**
     * Marks a task as [done].
     */
    public static Task markAsDone(int targetIndex) throws TaskNotFoundException {
        Task t = TaskList.retrieve(targetIndex);
        t.markAsDone();
        final boolean taskFound_Marked = t.isDone;
        if (!taskFound_Marked) {
            throw new TaskNotFoundException();
        }
        return t;
    }

    /**
     * Returns a new task list of all tasks in list at the time of the call.
     */
    public ArrayList<Task> getAllTasks() {
        return taskList;
    }


    /**
     * Signals that an operation would have violated the 'no duplicates' property of the list.
     */
    public static class DuplicateTaskException extends DuplicateInformationException {
        protected DuplicateTaskException() {
            super("Sheena: Erm. There will be duplicate tasks tho..");
        }
    }


    public static class TaskNotFoundException extends Exception {
        protected TaskNotFoundException() {
            super("Sheena: Erm, I can't delete because you haven't added that task..");
        }
    }


    public static boolean uniqueElements(Collection<?> items) {
        final Set<Object> testSet = new HashSet<>();
        for (Object item : items) {
            final boolean itemExists = !testSet.add(item); // see Set documentation
            if (itemExists) {
                return false;
            }
        }
        return true;
    }

    public static void storedTaskList() throws NullPointerException {
        int counter = 1;
        if (taskList.size() == 0) {
            throw new NullPointerException();
        } else {
            for (Task t : taskList) {
                System.out.println(" " + counter + ". " + t.toString());
                counter++;
            }
        }
    }


}
