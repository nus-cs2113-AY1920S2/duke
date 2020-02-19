import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;


public class Duke {


    public static void main(String[] args) {

        ArrayList<Task> taskList = new ArrayList<Task>();

        try {
            loadFile("data/duke.txt", taskList);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        String line;
        Scanner in = new Scanner(System.in);
        String[] validCommands = {"todo", "deadline", "event", "done", "list"};

        System.out.println("Hello! I'm Isabella" + System.lineSeparator() + "What can I do for you?");
        line = in.nextLine();

        while (checkLoop(line)) {

            String[] words =  line.split(" ", 2);
            String command = words[0].toLowerCase();

            try {
                checkValidCommand(command, validCommands);
            } catch (DukeException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                line = in.nextLine();
                continue;
            }

            if (command.equals("list")) {
                displayList(taskList);
            } else {
                determineTask(command, line, taskList);
            }

            line = in.nextLine();
        }

        try {
            saveFile("data/duke.txt", taskList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        System.out.println("Bye. Hope to see you again soon!");
    }




    //read text file to load previously saved taskList
    private static void loadFile (String filePath, ArrayList<Task> taskList) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] words =  line.split(" \\| ");
            if (words[0].equals("T")) {
                Task t = new Todo (words[2]);
                if (words[1].equals("1")) {
                    t.markAsDone();
                }
                taskList.add(t);
            } else if (words[0].equals("D")) {
                Task t = new Deadline(words[2], words[3]);
                if (words[1].equals("1")) {
                    t.markAsDone();
                }
                taskList.add(t);
            } else {
                Task t = new Event(words[2], words[3]);
                if (words[1].equals("1")) {
                    t.markAsDone();
                }
                taskList.add(t);
            }
        }
    }

    //save taskList to text file before end program
    private static void saveFile (String filePath, ArrayList<Task> taskList) throws IOException {

        //FileWriter fw = new FileWriter(filePath, true);
        String lineInTextFile;
        String taskComplete;

        for (int i = 0; i < taskList.size(); i++) {

            FileWriter fw;
            if (i == 0) {
                fw = new FileWriter(filePath);
            } else {
                fw = new FileWriter(filePath, true);
            }

            if (taskList.get(i).isDone) {
                taskComplete = "1";
            } else {
                taskComplete = "0";
            }
            lineInTextFile = taskList.get(i).taskType + " | " + taskComplete +
                    " | " + taskList.get(i).action;
            if (taskList.get(i).date != null) {
                lineInTextFile += (" | " + taskList.get(i).date + System.lineSeparator());
            } else {
                lineInTextFile += System.lineSeparator();
            }

            fw.write(lineInTextFile);
            fw.close();

        }
    }


    public static boolean checkLoop(String line) {
        String s = line.toLowerCase();
        if (s.startsWith("bye")) {
            return false;
        } else {
            return true;
        }
    }

    public static void checkValidCommand(String command, String[] validCommands) throws DukeException {
        boolean isValidTask = false;
        for (String c : validCommands) {
            if (command.equals(c)) {
                isValidTask = true;
            }
        }
        if (!isValidTask) {
            throw new DukeException();
        }
    }

    public static void checkMissingDescription(String command, String taskDescription, ArrayList<Task> taskList) throws DukeException {
        String[] arr = taskDescription.split(" ");
        String action = arr[1];
        int index = 0;
        if (command.equals("deadline")) {
            index = taskDescription.indexOf("/by ");
            if (action.startsWith("/by")) {
                index = -1;
            }
        } else if (command.equals("event")) {
            index = taskDescription.indexOf("/at ");
            if (action.startsWith("/at")) {
                index = -1;
            }
        } else if (command.equals("done")) {
            int listNumber = Integer.parseInt(action);
            if (listNumber > taskList.size()) {
                index = -1;
            }
        }
        if ((action.length() == 0) || (index == -1)) {
            throw new DukeException();
        }
    }

    public static void printEditTask(String command, Task t, ArrayList<Task> taskList) {
        if (command.equals("done")){
            System.out.println("Nice! I've marked this task as done: " + System.lineSeparator() + t);
        } else {
            System.out.println("Got it. I've added this task: " + System.lineSeparator() + t
                    + System.lineSeparator() + "Now you have " + taskList.size() + " tasks in the list.");
        }
    }

    public static void printErrorMessage(String command) {
        String commandType = "", addition = "";
        if (command.equals("todo")) {
            commandType = "a " + command;
            addition = ".";
        } else if (command.equals("deadline")){
            commandType = "a " + command;
            addition =  " and needs a date indicated by \"/by \".";
        } else if (command.equals("event")) {
            commandType = "an " + command;
            addition =  " and needs a date indicated by \"/at \".";
        } else if (command.equals("done")) {
            commandType = command;
            addition = " and has to be a number in the list.";
        }
        System.out.println("☹ OOPS!!! The description of " + commandType + " cannot be empty" + addition);
    }

    public static void displayList(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            System.out.println("There is nothing on the list.");
            return;
        }
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + "." + taskList.get(i));
        }
    }

    public static void determineTask(String command, String taskDescription, ArrayList<Task> taskList) {
        String action, date;
        try {
            checkMissingDescription(command, taskDescription, taskList);
        } catch (DukeException e) {
            printErrorMessage(command);
            return;
        } catch (NumberFormatException e) { //if parse invalid string into Integer.parseInt()
            printErrorMessage(command);
            return;
        } catch (ArrayIndexOutOfBoundsException e) { //if arr[1] doesn't exist and no word after command
            printErrorMessage(command);
            return;
        }
        if (command.equals("todo")) {
            action = taskDescription.substring(5);
            Task t = new Todo(action);
            taskList.add(t);
            printEditTask(command, t, taskList);
        } else if (command.equals("deadline")) {
            int startOfDate = taskDescription.indexOf('/');
            action = taskDescription.substring(9, startOfDate);
            date = taskDescription.substring(startOfDate + 4);
            Task t = new Deadline(action, date);
            taskList.add(t);
            printEditTask(command, t, taskList);
        } else if (command.equals("event")) {
            int startOfDate = taskDescription.indexOf('/');
            action = taskDescription.substring(6, startOfDate);
            date = taskDescription.substring(startOfDate + 4);
            Task t = new Event(action, date);
            taskList.add(t);
            printEditTask(command, t, taskList);
        } else if (command.equals("done")) {
            int listNumber = Integer.parseInt(taskDescription.substring(5));
            taskList.get(listNumber-1).markAsDone();
            printEditTask(command, taskList.get(listNumber-1), taskList);
        }
    }



}
