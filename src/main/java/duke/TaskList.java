package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

/**
 * contains the task list
 * Has to do with the addition and deletion of the current list
 */
public class TaskList {
    public static ArrayList<Task> listOfTasks = new ArrayList<>(); // array of tasks that is <=100
    public static int sizeOfList = 0; // number of items in the list
    public static ArrayList<Integer> indexOfFound = new ArrayList<>();


    /**
     * removes the task in the given index from the list
     *
     * @throws DukeException if the index is not given
     */
    public static void removeTaskFromList() throws DukeException {

        String[] commandArray = Duke.inputLine.split(" ");
        if (commandArray.length < 2) {
            throw new IndexOutOfBoundsException();
        }
        if (commandArray.length > 2) {
            throw new DukeException();
        }
        int indexToRemove = Integer.parseInt(commandArray[1]) - 1; // obtain index of task to remove
        if (indexToRemove >= sizeOfList) {
            throw new DukeException();
        }
        Task taskToDelete = listOfTasks.get(indexToRemove);

        listOfTasks.remove(taskToDelete);
        sizeOfList--;

        Ui.printWhenCommand(taskToDelete,
                " Noted. I've removed this task:", "   ", " in the list.");
    }


    /**
     * find tasks in the list (description, 'at' and 'by')
     * with the stated keyword from user
     *
     * @throws DukeException if user does not state keyword to find with
     */
    public static void findTasks() throws DukeException {
        String[] commandArray = Duke.inputLine.split(" ");
        if (commandArray.length < 2) {
            throw new IndexOutOfBoundsException();
        } else {
            String keyword = Duke.inputLine.substring(5);
            keyword = keyword.toLowerCase();
            for (int i = 0; i < TaskList.sizeOfList; i++) {
                String description = TaskList.listOfTasks.get(i).getDescription();
                description = description.toLowerCase();
                if (description.contains(keyword)) {
                    indexOfFound.add(i);
                }
            }
            Ui.printOutFound();
            clearFindingList();

        }
    }


    /**
     * clear the list used to find tasks
     * with the stated keyword
     */
    private static void clearFindingList() {
        while (indexOfFound.size() != 0) {
            indexOfFound.remove(0);
        }
    }


    /**
     * Differentiates task and adds into array
     */
    public static void addInNewTask() throws DukeException {
        String inputCommand = Duke.inputLine.substring(0, 4);
        // creates new task
        if (inputCommand.contains("todo")) {
            if (Duke.inputLine.equals("todo")) {
                throw new IndexOutOfBoundsException();
            }
            addToDo();

        } else if (inputCommand.contains("dead")) {
            if (Duke.inputLine.equals("deadline")) {
                throw new IndexOutOfBoundsException();
            }
            addDeadline();

        } else if (inputCommand.contains("even")) {
            if (Duke.inputLine.equals("event")) {
                throw new IndexOutOfBoundsException();
            }
            addEvent();

        } else {
            throw new DukeException();
        }

        // informs user that task has been added to list
        Ui.printWhenCommand(listOfTasks.get(sizeOfList - 1),
                " Got it. I've added this task:", "  ", " tasks in the list.");
    }


    /**
     * Adds in new Event to the array
     */
    public static void addEvent() {
        String description;
        int indexOfEvent = Duke.inputLine.indexOf("/at") + 4;
        int descriptionStart = 6;
        int descriptionEnd = Duke.inputLine.indexOf(" /at");

        description = Duke.inputLine.substring(descriptionStart, descriptionEnd);
        String at = Duke.inputLine.substring(indexOfEvent);
        Event newEvent = new Event(description, at);
        listOfTasks.add(newEvent);
        sizeOfList++;
    }


    /**
     * Adds in new Deadline to the array
     */
    public static void addDeadline() {
        String description;
        int indexOfDeadline = Duke.inputLine.indexOf("/by") + 4;
        int descriptionStart = 9;
        int descriptionEnd = Duke.inputLine.indexOf(" /by");

        description = Duke.inputLine.substring(descriptionStart, descriptionEnd);
        String by = Duke.inputLine.substring(indexOfDeadline);
        Deadline newDeadline = new Deadline(description, by);
        listOfTasks.add(newDeadline);
        sizeOfList++;
    }


    /**
     * Adds in new Todo to array
     */
    public static void addToDo() {
        String description = Duke.inputLine.substring(5);
        Todo newToDo = new Todo(description);
        listOfTasks.add(newToDo);
        sizeOfList++;
    }

    /**
     * Prints acknowledgment that the specified task is done,
     * sets the task in the array to be done
     *
     * @throws DukeException if index is not given
     */
    public static void indicateTaskAsDone() throws DukeException {
        String[] commandArray = Duke.inputLine.split(" ");
        if (commandArray.length < 2) {
            throw new IndexOutOfBoundsException();
        }
        if (commandArray.length > 2) {
            throw new DukeException();
        }
        int index = Integer.parseInt(commandArray[1]) - 1; // obtain index to set as done
        if (index >= sizeOfList) {
            throw new DukeException();
        }
        listOfTasks.get(index).markAsDone(); // mark as done
        Ui.printWhenMarked(index);
    }
}
