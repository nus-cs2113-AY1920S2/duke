import Features.Deadline;
import Features.Event;
import Features.Task;
import Features.Todo;
import Exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Contain the list of tasks added by user.
 * Contain methods that edits the list of tasks and to add specific type of task depending on keyword by user.
 */
public class TaskList {
    private ArrayList<Task> userList;

    public TaskList(ArrayList<Task> savedList) {
        this.userList = savedList;
    }

    public ArrayList<Task> getUserList() {
        return this.userList;
    }
    /**
     * Returns a new To-do task object.
     * @param userInputInArray Sentence containing the keyword and description of the to-do that user had typed into CLI.
     * @return A new <code>Todo</code> object containing the description.
     * @throws DukeException If user types in a blank description.
     */
    public static Todo newTodo(ArrayList<String> userInputInArray) throws DukeException {
        String task = String.join(" ", userInputInArray);
        if (task.length() == 0) { // if user inputs nothing after to-do
            throw new DukeException("Format error, please follow: todo <task>");
        }
        return new Todo (task);
    }
    /**
     * Returns a new Event task object.
     * @param userInputInArray Sentence containing the keyword and description of the event that user had typed into CLI.
     * @return A new <code>Event</code> object containing the description and datetime(if any).
     * @throws DukeException If user did not follow the format required. For eg, missing "/at" keyword.
     */
    public static Event newEvent(ArrayList<String> userInputInArray) throws DukeException {
       int atIndex = userInputInArray.indexOf("/at");
        if (atIndex == -1) {
            throw new DukeException("Format error, please follow: event <task> /at <datetime>." +
                    "\nFeature: you can use <datetime> as \"yyyy-mm-dd hr:min\" and we can format it for you!");
        }
        ArrayList<String> filterByArr = Parser.descriptionFilter(atIndex, userInputInArray);
        String description = filterByArr.get(0);
        String at = filterByArr.get(1);
        ArrayList<String> userInputDelimit = Parser.convertStringToArr(at, " ", 2);
        LocalDate myDate;
        String myTime = "";
        try {
            myDate = LocalDate.parse(userInputDelimit.get(0));
            myTime = userInputDelimit.get(1);

        } catch (DateTimeParseException e) {
            return new Event(description, String.join(" ", userInputDelimit));
        } catch (IndexOutOfBoundsException e) {
            myDate = LocalDate.parse(userInputDelimit.get(0));
            return new Event(description, myDate, myTime);
        }
        return new Event(description, myDate, myTime);
    }

    /** Returns a new Deadline task object.
     * @param userInputInArray Sentence containing the keyword and description of the deadline that user had typed into CLI.
     * @return A new <code>Deadline</code> object containing the description and datetime(if any).
     * @throws DukeException If user did not follow the format required. For eg, missing "/by" keyword.
     */
    public static Deadline newDeadline(ArrayList<String> userInputInArray) throws DukeException {
        int byIndex = userInputInArray.indexOf("/by");
        if (byIndex == -1) {
            throw new DukeException("Format error, please follow: deadline <task> /by <datetime>." +
                    "\nFeature: you can use <datetime> as \"yyyy-mm-dd hr:min\" and we can format it for you!");
        }
        ArrayList<String> filterByArr = Parser.descriptionFilter(byIndex, userInputInArray);
        String description = filterByArr.get(0);
        String by = filterByArr.get(1);
        ArrayList<String> userInputDelimit = Parser.convertStringToArr(by, " ", 2);
        LocalDate myDate;
        String myTime = "";
        try {
            myDate = LocalDate.parse(userInputDelimit.get(0));
            myTime = userInputDelimit.get(1);
        } catch (DateTimeParseException e) {
            return new Deadline(description, String.join(" ", userInputDelimit));
        } catch (IndexOutOfBoundsException e) {
            myDate = LocalDate.parse(userInputDelimit.get(0));
            return new Deadline(description, myDate, myTime);
        }
        return new Deadline(description, myDate, myTime);
    }

    /**
     * Adds one of the three task: <code> Todo Event Deadline</code> .
     * @param userInput Sentence that user typed into CLI.
     * @param taskType Type of task in <code>String</code>.
     */
    public void addCommand(String userInput, String taskType) {
        UI.printLines();
        ArrayList<String> userInputInArr = Parser.convertStringToArr(userInput, " ");
        userInputInArr.remove(0);
        try {
            switch (taskType) {
                case "todo":
                    userList.add(newTodo(userInputInArr));
                    break;
                case "event":
                    userList.add(newEvent(userInputInArr));
                    break;
                case "deadline":
                    userList.add(newDeadline(userInputInArr));
                    break;
            }
            UI.printAddedInfo(this.userList);
        } catch (DukeException e) {
            System.out.println("OOPS!! " + e);
        }
        UI.printLines();
    }

    /**
     * Deletes a specific task via index.
     * @param userInput Sentence that user typed into CLI.
     * @throws DukeException If user inputs an invalid task index. For eg, an out of bound index.
     */
    public void deleteCommand(String userInput) throws DukeException {
        UI.printLines();
        String[] words = userInput.split(" ");
        if (words.length != 2) {
            throw new DukeException("Format error, please follow: \"delete <task number>\"");
        }
        try {
            Integer deleteIndex = Integer.parseInt((words[1]));
            deleteIndex--;    // decrement doneIndex from one-based indexing to zero-based indexing to access ArrayList using it
            System.out.println("Noted, I've removed this task:\n" + userList.get(deleteIndex));
            userList.remove(userList.get(deleteIndex));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!! Task number " + words[1] + " is not within your list. type \"list\" for more info.");
        } catch (NumberFormatException e) {
            System.out.println("OOPS!! " + words[1] + " is not a valid task number");
        }
        UI.printLines();
    }

    /**
     * Print out all tasks and its corresponding <code>isDone</code> and <code>LocalDate</code> within the task list.
     */
    public void listCommand() {
        UI.printLines();
        if (userList.size() == 0) {
            System.out.println("No items added!");
        } else {
            System.out.println("Here are the tasks in your list:");
        }
        for (int i=0; i<userList.size(); i++) {
            Integer index = i+1;
            System.out.print(index + ".");
            System.out.println(userList.get(i));
        }
        UI.printLines();
    }

    /**
     * Marks a specific task via index as <code>DONE</code>
     * @param userInput Sentence that user typed into CLI.
     * @throws DukeException If user inputs an invalid task index. For eg, an out of bound index.
     */
    public void doneCommand( String userInput) throws DukeException {
        UI.printLines();
        String[] words = userInput.split(" ");
        if (words.length != 2) {
            throw new DukeException("Format error, please follow: \"done <task number>\"");
        }
        try {
            Integer doneIndex = Integer.parseInt((words[1]));
            doneIndex--;    // decrement doneIndex from one-based indexing to zero-based indexing to access ArrayList using it
            userList.get(doneIndex).setIsDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(userList.get(doneIndex));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!! Task number " + words[1] + " is not within your list. type \"list\" for more info.");
        } catch (NumberFormatException e) {
            System.out.println("OOPS!! " + words[1] + " is not a valid task number");
        }
        UI.printLines();
    }

    /**
     * finds similar word pattern in user's task list.
     * @param userInput Sentence that user typed in.
     */
    public void findCommand(String userInput) {
        UI.printLines();
        ArrayList<String> delimitBySpace = Parser.convertStringToArr(userInput, " ", 2);
        int counter = 0;
        System.out.println("Here are the matching task/s in your list:");
        for (Task elem : userList) {
            if (elem.getDescription().toLowerCase().contains(delimitBySpace.get(1).toLowerCase())) {
                counter += 1;
                System.out.println(counter + ". " + elem);
            }
        }
        if (counter == 0) {
            System.out.println("Nothing was found.");
        }
        UI.printLines();
    }
}
