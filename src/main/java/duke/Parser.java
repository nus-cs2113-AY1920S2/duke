package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Parser {
    public static void determineCommand() throws DukeException {
        if (Duke.inputLine.substring(0, 4).equals("list")) {
            Ui.printsOutTheList(); // prints out the list

        } else if (Duke.inputLine.substring(0, 4).equals("done")) {
            indicateTaskAsDone(); // marks task in the stated index as done

        } else if (Duke.inputLine.substring(0, 6).equals("remove")) {
            removeTaskFromList(); // removes task in the stated index

        } else {
            addInNewTask();// adds a new task into the list
        }
    }

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
        if (indexToRemove >= Duke.sizeOfList) {
            throw new DukeException();
        }
        Task taskToDelete = Duke.listOfTasks.get(indexToRemove);

        Duke.listOfTasks.remove(taskToDelete);
        Duke.sizeOfList--;

        Ui.printWhenCommand(taskToDelete, " Noted. I've removed this task:", "   ", " in the list.");
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
        Ui.printWhenCommand(Duke.listOfTasks.get(Duke.sizeOfList - 1), " Got it. I've added this task:", "  ", " tasks in the list.");
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
        Duke.listOfTasks.add(newEvent);
        Duke.sizeOfList++;
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
        Duke.listOfTasks.add(newDeadline);
        Duke.sizeOfList++;
    }

    /**
     * Adds in new Todo to array
     */
    public static void addToDo() {
        String description = Duke.inputLine.substring(5);
        Todo newToDo = new Todo(description);
        Duke.listOfTasks.add(newToDo);
        Duke.sizeOfList++;
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
        int index = Integer.parseInt(commandArray[1]) - 1; // obtain index of task to set as done
        if (index >= Duke.sizeOfList) {
            throw new DukeException();
        }
        Duke.listOfTasks.get(index).markAsDone(); // mark as done
        Ui.printWhenMarked(index);
    }
}
