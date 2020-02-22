import Features.Deadline;
import Features.Event;
import Features.Task;
import Features.Todo;
import Exception.DukeException;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Contain the list of tasks added by user.
 * Contain methods that edits the list of tasks and to add specific type of task depending on keyword by user.
 */
public class TaskList {
    private ArrayList<Task> userList;
    private String lines = "____________________________________________________________\n";

    public TaskList(ArrayList<Task> savedList) {
       this.userList = savedList;
    }

    public ArrayList<Task> getUserList() {
        return this.userList;
    }

    /**
     * Returns a new To-do task object.
     * @param userInputDetails Sentence containing the keyword and description of the to-do that user had typed into CLI.
     * @return A new <code>Todo</code> object containing the description.
     * @throws DukeException If user types in a blank description.
     */
    public static Todo newTodo(ArrayList<String> userInputDetails) throws DukeException {
        String task = String.join(" ", userInputDetails);
        if (task.length() == 0) { // if user inputs nothing after todo
            throw new DukeException("Format error, please follow: todo <task>");
        }
        return new Todo (task);
    }

    /**
     * Returns a new Event task object.
     * @param userInputDetails Sentence containing the keyword and description of the event that user had typed into CLI.
     * @return A new <code>Event</code> object containing the description and datetime(if any).
     * @throws DukeException If user did not follow the format required. For eg, missing "/at" keyword.
     */
    public static Event newEvent(ArrayList<String> userInputDetails) throws DukeException {
        String description = "";
        String at = "";
        int atIndex = -1;
        atIndex = userInputDetails.indexOf("/at");
        if (atIndex == -1) {
            throw new DukeException("Format error, please follow: event <task> /at <time>");
        }
        for (int i=0; i<atIndex; i++) {
            description += userInputDetails.get(i);
            description += " ";
        }
        description = description.substring(0, description.length()-1);
        for (int i=atIndex+1; i < userInputDetails.size(); i++) {
            at += userInputDetails.get(i);
            at += " ";
        }
        at = at.substring(0, at.length()-1);
        return new Event(description, at);
    }

     /** Returns a new Deadline task object.
     * @param userInputDetails Sentence containing the keyword and description of the deadline that user had typed into CLI.
     * @return A new <code>Deadline</code> object containing the description and datetime(if any).
     * @throws DukeException If user did not follow the format required. For eg, missing "/by" keyword.
     */
    public static Deadline newDeadline(ArrayList<String> userInputDetails) throws DukeException {
        String description = "";
        String by = "";
        int byIndex = -1;
        byIndex = userInputDetails.indexOf("/by");
        if (byIndex == -1) {
            throw new DukeException("Format error, please follow: deadline <task> /by <time>");
        }
        for (int i=0; i<byIndex; i++) {
            description += userInputDetails.get(i);
            description += " ";
        }
        description = description.substring(0, description.length()-1);
        for (int i=byIndex+1; i < userInputDetails.size(); i++) {
            by += userInputDetails.get(i);
            by += " ";
        }
        by = by.substring(0, by.length()-1);
        return new Deadline(description, by);
    }

    /**
     * Adds one of the three task: <code> Todo Event Deadline</code> .
     * @param userInput Sentence that user typed into CLI.
     * @param taskType Type of task in <code>String</code>.
     */
    public void addCommand(String userInput, String taskType) {
        System.out.print(lines);
        ArrayList<String> userInputDetails = new ArrayList<String>(Arrays.asList(userInput.split(" ")));
        userInputDetails.remove(0);
        try {
            switch (taskType) {
                case "todo":
                    userList.add(newTodo(userInputDetails));
                    break;
                case "event":
                    userList.add(newEvent(userInputDetails));
                    break;
                case "deadline":
                    userList.add(newDeadline(userInputDetails));
                    break;
            }
            UI.printAddedInfo(this.userList);
        } catch (DukeException e) {
            System.out.println("OOPS!! " + e);
        }
        System.out.print(lines);
    }

    /**
     * Deletes a specific task via index.
     * @param userInput Sentence that user typed into CLI.
     * @throws DukeException If user inputs an invalid task index. For eg, an out of bound index.
     */
    public void deleteCommand(String userInput) throws DukeException {
        System.out.print(lines);
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
        System.out.print(lines);
    }

    /**
     * Print out all tasks and its corresponding <code>isDone</code> and <code>LocalDate</code> within the task list.
     */
    public void listCommand() {
        System.out.print(lines);
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
        System.out.print(lines);
    }

    /**
     * Marks a specific task via index as <code>DONE</code>
     * @param userInput Sentence that user typed into CLI.
     * @throws DukeException If user inputs an invalid task index. For eg, an out of bound index.
     */
    public void doneCommand( String userInput) throws DukeException {
        System.out.print(lines);
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
        System.out.print(lines);
    }

}
