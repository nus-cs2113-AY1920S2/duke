package command;

import Exceptions.DukeException;
import Tasks.Deadline;
import Tasks.Events;
import Tasks.TaskList;
import Tasks.ToDo;

import java.io.Serializable;

public class Controller {
    Storage storage = new Storage();
    Parser parser = new Parser();


    /**
     * Method that reads the user's input to decide what the user wants to do with it.
     * @param userInput : scanned user input.
     * @param taskListArrayList : ArrayList that stores all the user's tasks.
     * @return : returns the updated ArrayList.
     */
    public Serializable readInput(String userInput, TaskList taskListArrayList) {
        String command = parser.getCommand(userInput);

        switch (command) {
        case "list":
            if (taskListArrayList.isEmpty()) {
                return Ui.EMPTY_LIST;
            }
            return taskListArrayList.printList();
        case "done":
            try {
                int indexCompleted = Integer.parseInt(userInput.substring(5));
                taskListArrayList.getTask(indexCompleted - 1).markAsDone();
                return Ui.LINE_SEPARATOR + "\nNice! I've marked this task as done:"
                        + taskListArrayList.getTask(indexCompleted - 1).toString() + "\n" + Ui.LINE_SEPARATOR;
            } catch (NumberFormatException e) {
                return Ui.NO_NUMBER;
            } catch (IndexOutOfBoundsException e) {
                return Ui.OUT_OF_INDEX;
            }
        case "delete":
            try {
                int indexToDelete = Integer.parseInt(userInput.substring(7));
                taskListArrayList.removeTask(indexToDelete - 1);
                return Ui.deleteTaskMessage(userInput, taskListArrayList);
            } catch (NumberFormatException e) {
                return Ui.NO_NUMBER;
            } catch (IndexOutOfBoundsException e) {
                return Ui.OUT_OF_INDEX;
            }
        case "todo":
            try {
                userInput = userInput.substring(5).trim();
                if (userInput.equals("")) {
                    throw new DukeException(Ui.EMPTY_STRING);
                }
                ToDo t = new ToDo(userInput);
                taskListArrayList.addTask(t);
                return Ui.addTaskMessage(userInput, taskListArrayList, t);
            } catch (DukeException | StringIndexOutOfBoundsException e) {
                return Ui.EMPTY_STRING;
            }
        case "deadline":
            try {
                userInput = userInput.substring(9).trim();
                if (userInput.equals("")) {
                    throw new DukeException(Ui.EMPTY_STRING);
                }
                Deadline d = new Deadline(userInput);
                taskListArrayList.addTask(d);
                return Ui.addTaskMessage(userInput, taskListArrayList, d);

            } catch (DukeException | StringIndexOutOfBoundsException e) {
                return Ui.EMPTY_STRING;
            }
        case "event":
            try {
                userInput = userInput.substring(6).trim();
                if (userInput.equals("")) {
                    throw new DukeException(Ui.EMPTY_STRING);
                }
                Events e = new Events(userInput);
                taskListArrayList.addTask(e);
                return Ui.addTaskMessage(userInput, taskListArrayList, e);
            } catch (DukeException | StringIndexOutOfBoundsException e) {
                return Ui.EMPTY_STRING;
            }
        case "clear":
            taskListArrayList.clearList();
            return Ui.CLEAR_LIST;
        case "find":
            userInput = userInput.substring(5).trim();
            return taskListArrayList.find(userInput);
        case "help":
            return Ui.HELP;
        case "bye":
            return null;
        default:
            return Ui.DEFAULT;


        }

    }

}
