package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private static final String FOUR_SPACE_INDENT = "    ";
    private static final String SIX_SPACE_INDENT = "      ";
    private static final String BORDER = FOUR_SPACE_INDENT +
            "___________________________________________________________________";
    public static final String MAKING_DIRECTORY = "Making directory: \"data\"";
    public static final String CREATING_FILE = "Creating file: \"duke.txt\"";
    public static final String LOADING_TASKS = "Loading tasks from \"data/duke.txt\", if there are any...";
    public static final String LOADING_DONE = "Loading done.";
    public static final String EXIT_MESSAGE = "Ok, see ya!";
    public static final String THANKS_RESPONSE = "The great Taskmaster Yipyap appreciates your gratitude :-)";
    public static final String NUM_FORMAT_ERROR = "Index must be an integer, like \"1\", but not \"one\".";
    public static final String SAVE_ERROR = "An IO error was encountered while saving.";
    public static final String NO_TASKS = "No tasks found.";
    public static final String HELP_RESPONSE = "Here are the list of commands:";
    public static final String[] COMMAND_LIST = new String[] {
            "\"bye\": exit program",
            "\"list\": show a list of tasks",
            "\"find <keyword>\": search for tasks using a keyword",
            "\"done <task index>\": mark a task as done",
            "\"delete <task index>\": delete a task",
            "\"todo <description>\": add a todo",
            "\"deadline <description> /by <deadline>\": add a deadline",
            "\"event <description> /at <date/time>\": add an event",
            "\"thanks\": if you're feeling thankful"};

    public String readCommand() {
        Scanner in = new Scanner(System.in);
        String input = "";
        while (input.isEmpty()) {
            input = in.nextLine();
            input = input.trim();
        }
        return input; // Input is not empty
    }

    public void greet() {
        System.out.println(BORDER);
        System.out.println(FOUR_SPACE_INDENT + "Hello, I'm Taskmaster Yipyap.");
        System.out.println(FOUR_SPACE_INDENT + "I can manage your tasks, and save them automatically!");
        System.out.println(FOUR_SPACE_INDENT + "Type \"help\" to see exactly what I can do!");
        System.out.println(BORDER);
        System.out.print("\n");
    }

    public void exit() {
        printFormattedString(EXIT_MESSAGE);
    }

    public void printAddedTask(ArrayList<Task> tasks) {
        System.out.println(BORDER);
        System.out.println(FOUR_SPACE_INDENT + "Got it. I've added this task:");
        System.out.println(SIX_SPACE_INDENT + tasks.get(tasks.size() - 1).toString());
        System.out.println(FOUR_SPACE_INDENT + "Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(BORDER);
        System.out.print("\n");
    }

    public void printDoneTask(ArrayList<Task> tasks, int taskIndex) {
        System.out.println(BORDER);
        System.out.println(FOUR_SPACE_INDENT + "Nice! I've marked this task as done:");
        System.out.println(SIX_SPACE_INDENT + tasks.get(taskIndex - 1).toString());
        System.out.println(BORDER);
        System.out.print("\n");
    }

    public void printDeletedTask(ArrayList<Task> tasks, int taskIndex) {
        System.out.println(BORDER);
        System.out.println(FOUR_SPACE_INDENT + "Noted. I've removed this task:");
        System.out.println(SIX_SPACE_INDENT + tasks.get(taskIndex - 1).toString());
        tasks.remove(taskIndex - 1);
        System.out.println(FOUR_SPACE_INDENT + "Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(BORDER);
        System.out.print("\n");
    }

    public void printFormattedString(String str) {
        System.out.println(BORDER);
        System.out.println(FOUR_SPACE_INDENT + str);
        System.out.println(BORDER);
        System.out.print("\n");
    }

    public void printLine(String str) {
        System.out.println(FOUR_SPACE_INDENT + str);
    }

    public void printTasks(ArrayList<Task> tasks) {
        int bulletNum = 1;
        System.out.println(BORDER);
        System.out.println(FOUR_SPACE_INDENT + "Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println(FOUR_SPACE_INDENT + bulletNum + "." + task.toString());
            bulletNum++;
        }
        System.out.println(BORDER);
        System.out.print("\n");
    }

    public void printFoundTasks(ArrayList<String> foundTasks) {
        System.out.println(BORDER);
        System.out.println(FOUR_SPACE_INDENT + "Here are the matching tasks in your list:");
        for (String indexedTask : foundTasks) {
            System.out.println(FOUR_SPACE_INDENT + indexedTask);
        }
        System.out.println(BORDER);
        System.out.print("\n");
    }

    public void helpUser() {
        System.out.println(BORDER);
        System.out.println(FOUR_SPACE_INDENT + HELP_RESPONSE);
        for (String command : COMMAND_LIST) {
            System.out.println(FOUR_SPACE_INDENT + command);
        }
        System.out.println(BORDER);
        System.out.print("\n");
    }
}
