import java.util.ArrayList;
import java.util.Scanner;


public class Duke {


    public static void validCommandChecker(String taskDescription, String[] validCommands) throws DukeException {
        boolean isValidTask = false;
        for (String c : validCommands) {
            String words[] = taskDescription.split(" ", 2);
            String command = words[0];
            command = command.toLowerCase();
            if (command.equals(c)) {
                isValidTask = true;
            }
        }
        if (!isValidTask) {
            throw new DukeException();
        }
    }

    public static void determineTask(String taskDescription, ArrayList<Task> taskList) throws DukeException {
        String action;
        if (taskDescription.startsWith("todo")) {
            action = taskDescription.substring(4);
            if (action.length() <= 1) {
                throw new DukeException();
            }
            action = taskDescription.substring(5);
            Task t = new Todo(action);
            taskList.add(t);
            System.out.println("Got it. I've added this task: " + System.lineSeparator() + t + System.lineSeparator() + "Now you have " + taskList.size() + " tasks in the list.");
        }

    }

    public static void main(String[] args) {

        String line;
        Scanner in = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>();
        String[] validCommands = {"todo", "deadline", "event", "done", "list", /*"bye"*/};

        System.out.println("Hello! I'm Isabella" + System.lineSeparator() + "What can I do for you?");
        line = in.nextLine();


        while (!line.equals("bye")) {

            try {
                validCommandChecker(line, validCommands);
            } catch (DukeException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            if (line.startsWith("done")) {
                int index = Integer.parseInt(line.substring(5)) - 1;
                taskList.get(index).markAsDone();
                System.out.println("Nice! I've marked this task as done: " + System.lineSeparator() + taskList.get(index));
            } else if (line.equals("list")) {
                if (taskList.size() == 0) {
                    System.out.println("There is nothing on the list.");
                } else {
                    System.out.println("Here are the tasks in your list: ");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println(i + 1 + "." + taskList.get(i));
                    }
                }
            } else if (line.startsWith("todo")) {
                /*Task t = new Todo(line.substring(5));
                taskList.add(t);
                System.out.println("Got it. I've added this task: " + System.lineSeparator() + t + System.lineSeparator() + "Now you have " + taskList.size() + " tasks in the list.");*/
                try {
                    determineTask(line, taskList);
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                }
            } else if (line.startsWith("deadline")) {
                int startOfDate = line.indexOf('/');
                Task t = new Deadline(line.substring(9, startOfDate), line.substring(startOfDate + 4));
                taskList.add(t);
                System.out.println("Got it. I've added this task: " + System.lineSeparator() + t + System.lineSeparator() + "Now you have " + taskList.size() + " tasks in the list.");
                try {
                    determineTask(line, taskList);
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
            } else if (line.startsWith("event")) {
                int startOfDate = line.indexOf('/');
                Task t = new Event(line.substring(6, startOfDate), line.substring(startOfDate + 4));
                taskList.add(t);
                System.out.println("Got it. I've added this task: " + System.lineSeparator() + t + System.lineSeparator() + "Now you have " + taskList.size() + " tasks in the list.");
            }
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}
