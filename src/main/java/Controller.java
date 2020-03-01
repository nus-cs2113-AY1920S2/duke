import Exceptions.EmptyStringException;
import Tasks.*;

import java.io.Serializable;

public class Controller {
    Storage storage = new Storage();
    Parser parser = new Parser();

    public Serializable readInput(String userInput, TaskList taskListArrayList) {
        String command = parser.getCommand(userInput);
        String description = "";
        if (parser.hasDescription(userInput)){
            description = parser.getDescription(userInput);
        }

        switch (command) {
        case "list":
            if (taskListArrayList.isEmpty()){
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
                    throw new EmptyStringException();
                }
                ToDo t = new ToDo(userInput);
                taskListArrayList.addTask(t);
                return Ui.addTaskMessage(userInput, taskListArrayList, t);
            } catch (EmptyStringException e) {
                return Ui.EMPTY_STRING;
            }
        case "deadline":
            try {
                userInput = userInput.substring(9).trim();
                if (userInput.equals("")) {
                    throw new EmptyStringException();
                }
                Deadline d = new Deadline(userInput);
                taskListArrayList.addTask(d);
                return Ui.addTaskMessage(userInput, taskListArrayList, d);

            } catch (EmptyStringException e) {
                return Ui.EMPTY_STRING;
            }
        case "event":
            try {
                userInput = userInput.substring(6).trim();
                if (userInput.equals("")) {
                    throw new EmptyStringException();
                }
                Events e = new Events(userInput);
                taskListArrayList.addTask(e);
                return Ui.addTaskMessage(userInput, taskListArrayList, e);
            } catch (EmptyStringException e) {
                return Ui.EMPTY_STRING;
            }
        case "help":
            return Ui.HELP;
        case "bye":
            return null;
        default:
            return Ui.DEFAULT;


        }

    }

}
