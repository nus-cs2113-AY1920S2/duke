import Features.Deadline;
import Features.Event;
import Features.Task;
import Features.Todo;
import Exception.DukeException;

import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {
    private ArrayList<Task> userList;
    private String lines = "____________________________________________________________\n";

    public TaskList(ArrayList<Task> savedList) {
       this.userList = savedList;
    }
    public TaskList() {
        this.userList = new ArrayList<>();
    }
    public ArrayList<Task> getUserList() {
        return this.userList;
    }
    public static Todo newTodo(ArrayList<String> userInputDetails) throws DukeException {
        String task = String.join(" ", userInputDetails);
        if (task.length() == 0) { // if user inputs nothing after to-do
            throw new DukeException("Format error, please follow: todo <task>");
        }
        return new Todo (task);
    }
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
    public void findCommand(String userInput) {
        System.out.print(lines);
        ArrayList<String> userFindDelimitBySpace = new ArrayList<>(Arrays.asList(userInput.split(" ", 2)));
        int counter = 0;
        System.out.println("Here are the matching task/s in your list:");
        for (Task elem : userList) {
            if (elem.getDescription().toLowerCase().contains(userFindDelimitBySpace.get(1).toLowerCase())) {
                counter += 1;
                System.out.println(counter + ". " + elem);
            }
        }
        if (counter == 0) {
            System.out.println("Nothing was found.");
        }
        System.out.print(lines);
    }
}
