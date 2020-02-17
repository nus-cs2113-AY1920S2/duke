import Features.Deadline;
import Features.Event;
import Features.Task;
import Features.Todo;
import Exception.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private static String lines = "____________________________________________________________\n";
    private static Scanner in = new Scanner(System.in);
    private static ArrayList<Task> userList = new ArrayList<>();

    public static void loadFromFile(File f) throws IOException, DukeException {

        if (f.exists()) {
            try {
                userList = taskFromFile(f);
            } catch (DukeException e){
                System.out.println("OOPS! " + e);
            }
        } else {
            f.createNewFile();
        }
    }
    public static void saveToFile(File f) throws IOException, DukeException {
        FileWriter fw = new FileWriter(f);
        for (Task elem : userList) {
            String toSave = elem.getType() + " , " + elem.getIsDone() + " , " + elem.getDescription();
            if (!elem.getType().equals("Todo")) {
                toSave = toSave + " , " + elem.getTimeToComplete();
            }
            fw.write(toSave + "\n");
        }
        fw.close();
    }
    public static ArrayList<Task> taskFromFile(File f) throws FileNotFoundException, DukeException {
        Scanner s = new Scanner(f);
        ArrayList<Task> savedUserList = new ArrayList<>();
        String currTaskString = ""; //Follows format: TASK | 1 | DESCRIPTION | TIME
        while (s.hasNext()) {
            currTaskString = s.nextLine();
            ArrayList<String> savedString = new ArrayList<String>(Arrays.asList(currTaskString.split(" , ")));
            ArrayList<String> savedDescription = new ArrayList<String>();
            savedDescription.add(savedString.get(2));
            Task taskToLoad = null;
            switch(savedString.get(0)) {
                case "Todo":
                    taskToLoad = newTodo(savedDescription);
                    break;
                case "Deadline":
                    savedDescription.add("/by");
                    savedDescription.add(savedString.get(3));
                    taskToLoad = newDeadline(savedDescription);
                    break;
                case "Event":
                    savedDescription.add("/at");
                    savedDescription.add(savedString.get(3));
                    taskToLoad = newEvent(savedDescription);
                    break;
                default:
                    throw new DukeException("File is corrupted. Unable to find task name.");
            }
            taskToLoad.setIsDone(Boolean.parseBoolean(savedString.get(1)));
            savedUserList.add(taskToLoad);
        }
        return savedUserList;
    }
    private static void listCommand() {
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

    private static void doneCommand( String userInput) throws DukeException {
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

    private static Todo newTodo(ArrayList<String> userInputDetails) throws DukeException {
        String task = String.join(" ", userInputDetails);
        if (task.length() == 0) { // if user inputs nothing after todo
            throw new DukeException("Format error, please follow: todo <task>");
        }
        return new Todo (task);
    }
    private static Event newEvent(ArrayList<String> userInputDetails) throws DukeException {
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

    private static Deadline newDeadline(ArrayList<String> userInputDetails) throws DukeException {
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

    private static void addCommand(String userInput, String taskType) {
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
            printAddedInfo();
        } catch (DukeException e) {
            System.out.println("OOPS!! " + e);
        }
        System.out.print(lines);
    }

    private static void deleteCommand(String userInput) throws DukeException {
        System.out.print(lines);
        String[] words = userInput.split(" ");
        if (words.length != 2) {
            throw new DukeException("Format error, please follow: \"delete <task number>\"");
        }
        try {
            Integer deleteIndex = Integer.parseInt((words[1]));
            deleteIndex--;    // decrement doneIndex from one-based indexing to zero-based indexing to access ArrayList using it
            System.out.println("Noted, I've removed this task :\n" + userList.get(deleteIndex));
            userList.remove(userList.get(deleteIndex));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!! Task number " + words[1] + " is not within your list. type \"list\" for more info.");
        } catch (NumberFormatException e) {
            System.out.println("OOPS!! " + words[1] + " is not a valid task number");
        }
        System.out.print(lines);
    }

    private static void printAddedInfo() {
        System.out.println("Got it . I've added this task:");
        System.out.print("  ");
        System.out.println(userList.get(userList.size()-1));
        System.out.println("Now you have " + userList.size() + " tasks in the list.");
    }

    //Main bot control
    private static void botResponse(String keyword, String userInput) throws DukeException {
        switch (keyword) {
            case "list":
                listCommand();
                break;
            case "delete":
                deleteCommand(userInput);
                break;
            case "done":
                doneCommand(userInput);
                break;
            case "todo":
                addCommand(userInput, "todo");
                break;
            case "event":
                addCommand(userInput, "event");
                break;
            case "deadline":
                addCommand(userInput, "deadline");
                break;
            default:
                throw new DukeException("Unknown command.");
        }
    }
    public static void main(String[] args) throws IOException, DukeException {
        java.util.Properties properties = System.getProperties();

        // to print all the keys in the properties map <for testing>
        //properties.list(System.out);

        // get Operating System home directory(PLATFORM INDEPENDENT METHOD)
        String home = properties.get("user.home").toString();
        // get Operating System separator
        String separator = properties.get("file.separator").toString();
        // your directory name
        String directoryName = "duke";
        // your file name
        String fileName = "savedTasks.txt";
        File dir = new File(home+separator+directoryName);
        File f = new File (dir, fileName);

        String toPrint = lines + "Hello! I'm Duke\n" + "What can I do for you?\n" + lines;
        System.out.print(toPrint);
        loadFromFile(f);
        String userInput = in.nextLine();
        ArrayList<String> words = new ArrayList<String>(Arrays.asList(userInput.split(" ")));
        String keyword = words.get(0);

        while (!keyword.equals("bye")) {          // if user command is not bye, go inside loop
            try {
                botResponse(keyword, userInput);
            } catch (DukeException e) {
                System.out.print(lines);
                System.out.println("OOPS!! " + e);
                System.out.print(lines);
            } finally {
                userInput = in.nextLine();
                words = new ArrayList<String>(Arrays.asList(userInput.split(" ")));
                keyword = words.get(0);
            }
        }
        if (!userList.equals(taskFromFile(f)))
            saveToFile(f);
        System.out.print(lines + "Bye. Hope to see you again soon!\n" + lines);
    }
}
