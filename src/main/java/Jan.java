import java.util.ArrayList;
import Exceptions.MissingDescriptionException;
import Exceptions.UnknownCommandException;
import TaskObjects.Task;
import TaskObjects.Todo;
import TaskObjects.Deadline;
import TaskObjects.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;

public class Jan {

    public static final String DIVIDER = "____________________________________________________________";
    public static ArrayList<Task> Tasks = new ArrayList<>();
    public static final String FILEPATH = "data/TaskList.txt"; //final save

    public static void executeInstruction(String command, String commandDescription) throws MissingDescriptionException,
            UnknownCommandException{
        System.out.println(DIVIDER);
        switch(command) {
        case "list":
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < Tasks.size(); i++) {
                System.out.println( i + 1 + ". " + Tasks.get(i));
            }
            break;
        case "done":
            if (commandDescription.isEmpty()) {
                throw new MissingDescriptionException();
            }
            int taskNum = Integer.parseInt(commandDescription) - 1;
            if (existTask(taskNum)) {
                System.out.println("Nice! I've marked this task as done: ");
                Tasks.get(taskNum).setDone(true);
                System.out.println(Tasks.get(taskNum));
            }else{
                System.out.println("Jan cannot find this task");
            }
            break;
        case "todo":
            if (commandDescription.isEmpty()) {
                throw new MissingDescriptionException();
            }
            addTaskToArrayList(command, commandDescription,"");
            printSuccessfulAddTaskMessage();
            printCurrentTaskCount();
            try {
                addToFile(Tasks.get(Tasks.size() - 1).stringToSave());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
            break;
        case "deadline":
            if (commandDescription.isEmpty()) {
                throw new MissingDescriptionException();
            }
            addTaskToArrayList(command, commandDescription, "/by");
            printSuccessfulAddTaskMessage();
            printCurrentTaskCount();
            try {
                addToFile(Tasks.get(Tasks.size() - 1).stringToSave());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
            break;
        case "event":
            if (commandDescription.isEmpty()) {
                throw new MissingDescriptionException();
            }
            if (addTaskToArrayList(command, commandDescription,"/at")) {
                printSuccessfulAddTaskMessage();
                printCurrentTaskCount();
                try {
                    addToFile(Tasks.get(Tasks.size() - 1).stringToSave());
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            }
            break;
        case "delete":
            if (commandDescription.isEmpty()) {
                throw new MissingDescriptionException();
            }
            taskNum = Integer.parseInt(commandDescription) - 1;
            if (existTask(taskNum)) {
                Task tempTask = Tasks.get(taskNum);
                Tasks.remove(taskNum);
                System.out.println("Noted. I've removed this task: ");
                System.out.println(tempTask);
                printCurrentTaskCount();
            }else {
                System.out.println("Jan cannot find this task");
            }
            try {
                saveToFile();
            } catch (IOException e) {
                System.out.println("Something went wrong");
            }
            break;
        default:
            throw new UnknownCommandException();
        }
        System.out.println(DIVIDER);
    }

    private static void saveToFile() throws IOException {
        File file = new File(FILEPATH);
        file.createNewFile();
        FileWriter fw = new FileWriter(FILEPATH);
        for (Task task : Tasks) {
            fw.write(task.stringToSave() + "\n");
        }
        fw.close();
    }

    private static void addToFile(String textToAdd) throws IOException {
        File file = new File(FILEPATH);
        file.createNewFile();
        FileWriter fw = new FileWriter(FILEPATH,true);
        fw.write(textToAdd + "\n");
        fw.close();
    }

    private static void getFileContents() throws FileNotFoundException {
        File f = new File(FILEPATH); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String stringInput = s.nextLine();
            parseTaskFromFile(stringInput);
        }
    }

    private static void parseTaskFromFile(String savedString){
        String[] taskDetails = savedString.split("[|]");
        switch (taskDetails[0]) {
        case "T":
            addTaskToArrayList("todo", taskDetails[1],"");
            break;
        case "E":
            addTaskToArrayList("event",taskDetails[1] + "/at" +taskDetails[2],"/at");
            break;
        case "D":
            addTaskToArrayList("event",taskDetails[1] + "/at" +taskDetails[2],"/by");
            break;
        }
    }

    private static boolean existTask(int taskNum){
        return (taskNum < Tasks.size()) && (taskNum > 0);
    }

    private static boolean addTaskToArrayList(String command, String commandDescription, String divider) {
        String[] taskDetails = commandDescription.split(divider);
        switch(command) {
        case "todo":
            Todo todo = new Todo(commandDescription);
            Tasks.add(todo);
            return true;
        case "deadline":
            try {
                Deadline deadline = new Deadline(taskDetails[0], taskDetails[1]);
                Tasks.add(deadline);
            } catch (IndexOutOfBoundsException e) {
                printIncorrectCommandMessage();
                return false;
            }
            return true;
        case "event":
            try {
                Event event = new Event(taskDetails[0], taskDetails[1]);
                Tasks.add(event);
            } catch (IndexOutOfBoundsException e) {
                printIncorrectCommandMessage();
                return false;
            }
            return true;
        }
        return false;
    }

    private static void printSuccessfulAddTaskMessage() {
        Task task = Tasks.get(Tasks.size() - 1);
        System.out.println("Got it. I've added this task:\n " + task);
    }

    private static void printCurrentTaskCount() {
        if (Tasks.size() < 2){
            System.out.println("Now you have " + Tasks.size() + " task in the list");
        } else {
            System.out.println("Now you have " + Tasks.size() + " tasks in the list");
        }
    }

    private static void printIncorrectCommandMessage() {
        System.out.println("Invalid request due to missing details or incorrect format. type \"help\" to find out more");
    }

    private static void printUnknownCommandMessage() {
        System.out.println("Try using the following commands:\n"
                + "list                             -- to get a list of all the existing tasks\n"
                + "done [item number]               -- mark task as completed\n"
                + "todo [item]                      -- add new todo task\n"
                + "deadline [item] /by [date][time] -- add new deadline task\n"
                + "event [item] /at [date][time]    -- add new event task");
        System.out.println(DIVIDER);
    }

    private static void printGreetingMessage(String logo) {
        System.out.println(logo);
        System.out.println(DIVIDER);
        System.out.println("Hello! I'm Jan\n" + " What can I do for you?");
        System.out.println(DIVIDER);
    }

    private static void printGoodbyeMessage(){
        System.out.println(DIVIDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    public static void main(String[] args) {

        String logo = " ________     _____     _____     __\n"
                + "|_____   |  /  ___  \\  |     \\   |  | \n"
                + "      |  | |  /   \\  | |  |\\  \\  |  |\n"
                + " __   |  | |  |___|  | |  | \\  \\ |  |\n"
                + "|  |__|  | |   ___   | |  |  \\  \\|  |\n"
                + "|________/ |__|   |__| |__|   \\_____|\n";
        printGreetingMessage(logo);
        try {
            getFileContents();
        } catch (FileNotFoundException e) {
            //do nothing
        }

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            String[] requests = line.split(" ", 2);
            try {
                if (requests.length > 1) {
                    executeInstruction(requests[0], requests[1]);
                } else {
                    executeInstruction(requests[0], "");
                }
            }catch (MissingDescriptionException e) {
                printIncorrectCommandMessage();
                System.out.println(DIVIDER);
            } catch (UnknownCommandException e) {
                printUnknownCommandMessage();
                System.out.println(DIVIDER);
            }
            line = in.nextLine();
        }
        printGoodbyeMessage();
    }
}
