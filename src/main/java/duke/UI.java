package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Prompts input commands from users and displays exception messages.
 */

public class UI {

    public static void printIntroMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\t____________________________________________________________\n"
                + "\tHello! I'm Duke\n" +
                "\tWhat can I do for you?\n"
                + "\t____________________________________________________________\n");
    }

    public static void printListMessage(ArrayList<Task> taskList) {
        System.out.println("\t____________________________________________________________\n"
                + "\t Here are the tasks in your list:");
        int i = 0;
        for (Task t : taskList) {
            System.out.println("\t " + (i + 1) + ". " + t.toString());
            i++;
        }
        System.out.println("\t____________________________________________________________\n");
    }

    public static void printDoneMessage(Task t) {
        System.out.println("\t____________________________________________________________\n"
                + "\t Nice! I've marked this task as done:\n"
                + "\t\t" + t.toString() + "\n"
                + "\t____________________________________________________________\n");
    }

    public static void printFoundMessage() {
        System.out.println("\t____________________________________________________________\n"
                + "\t Here are the matching tasks in your list:");
    }

    public static void printFoundTask(Task t, int findCount){
        System.out.println("\t " + findCount + ". " + t.toString());
    }

    public static void printNotFoundMessage() {
        System.out.println("\t____________________________________________________________\n"
                + "\t ☹ OOPS!!! There are no matching tasks in your list.\n"
                + "\t____________________________________________________________\n");
    }

    public static void printDeleteMessage(Task t, ArrayList<Task> taskList) {
        System.out.println("\t____________________________________________________________\n"
                + "\t Noted. I've removed this task:\n"
                + "\t\t" + t.toString() + "\n"
                + "\t Now you have " + taskList.size() + " tasks in the list.\n"
                + "\t____________________________________________________________\n");
    }

    public static void printAddTaskMessage(Task t, ArrayList<Task> taskList) {
        System.out.println("\t____________________________________________________________\n"
                + "\t Got it. I've added this task:\n"
                + "\t   " + t.toString() + "\n"
                + "\t Now you have " + taskList.size() + " tasks in the list.\n"
                + "\t____________________________________________________________\n");
    }

    public static void printGoodbyeMessage() {
        System.out.println("\t____________________________________________________________\n"
                + "\t Bye. Hope to see you again soon!\n"
                + "\t____________________________________________________________\n");
    }

    public static void printEmptyTodoExceptionMessage() {
        System.out.println("\t____________________________________________________________\n"
                + "\t ☹ OOPS!!! The description of a todo must contain a task name in this format:\n"
                + "\t todo <task name>\n"
                + "\t____________________________________________________________\n");
    }

    public static void printEmptyDoneExceptionMessage() {
        System.out.println("\t____________________________________________________________\n"
                + "\t☹ OOPS!!! The description of a done must contain a task number in this format:\n"
                + "\t done <task number>\n"
                + "\t____________________________________________________________\n");
    }

    public static void printEmptyDeleteExceptionMessage() {
        System.out.println("\t____________________________________________________________\n"
                + "\t☹ OOPS!!! The description of a delete must contain a task number in this format:\n"
                + "\t delete <task number>\n"
                + "\t____________________________________________________________\n");
    }

    public static void printEmptyFindExceptionMessage() {
        System.out.println("\t____________________________________________________________\n"
                + "\t☹ OOPS!!! The description of a find must contain a word in this format:\n"
                + "\t find <word>\n"
                + "\t____________________________________________________________\n");
    }

    public static void printEmptyDeadlineExceptionMessage() {
        System.out.println("\t____________________________________________________________\n"
                + "\t ☹ OOPS!!! The description of a deadline must contain a task name and a date in this format:\n"
                + "\t deadline <task name> /by <date>\n"
                + "\t____________________________________________________________\n");
    }

    public static void printEmptyEventExceptionMessage() {
        System.out.println("\t____________________________________________________________\n"
                + "\t ☹ OOPS!!! The description of an event must contain a task name and a location in this format:\n"
                + "\t event <task name> /at <location>\n"
                + "\t____________________________________________________________\n");
    }

    public static void printUnknownWordExceptionMessage() {
        System.out.println("\t____________________________________________________________\n"
                + "\t ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        printHelpMessage();

    }

    public static void printHelpMessage() {
        System.out.println("\t You can try the following commands:\n"
                + "\t\t list\n"
                + "\t\t todo <task name>\n"
                + "\t\t deadline <task name> /by <date>\n"
                + "\t\t event <task name> /at <location>\n"
                + "\t\t done <task number>\n"
                + "\t\t find <word>\n"
                + "\t\t delete <task number>\n"
                + "\t\t bye\n"
                + "\t____________________________________________________________\n");
    }

    public static void printIndexOutOfBoundsExceptionMessage() {
        System.out.println("\t____________________________________________________________\n"
                + "\t ☹ OOPS!!! The index you have entered is out of bounds.\n"
                + "\t____________________________________________________________\n");
    }

    public static void printEmptyTaskListExceptionMessage() {
        System.out.println("\t____________________________________________________________\n"
                + "\t ☹ OOPS!!! Your task list is empty!\n"
                + "\t You can try entering a task using the following commands:\n"
                + "\t\t todo <task name>\n"
                + "\t\t deadline <task name> /by <date>\n"
                + "\t\t event <task name> /at <location>\n"
                + "\t____________________________________________________________\n");
    }

    public static void printIOExceptionMessage() {
        System.out.println("\t____________________________________________________________\n"
                + "\t ☹ OOPS!!! File is not found!\n"
                + "\t____________________________________________________________\n");
    }

}