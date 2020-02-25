package duke;

import java.util.Scanner;

public class Ui {

    public static final String FORMAT_LINE = "------------------------------------";
    public static final String GOODBYE = "Bye. Hope to see you again soon!";
    public static final String LOADING_ERROR = "Errors happen when loading file";
    public static final String ADD_TASK = "Got it! I have added this task into your list:";
    public static final String DONE_TASK = "Good job! You have done this task:";
    public static final String GREETING = String.format("%s\n%s", "Hello, I'm Duke!", "What can I do for you?");
    public static final String DELETE_TASK= "Noted! I have deleted this task from the list:";
    public static final String LISTING = "Here is the list of all tasks you have:";
    public static final String HELPING = "Hello, Welcome to Duke!\n" +
            "This task management software can help you track all your tasks.\n" +
            "Here are the commands of Duke:";
    public static final String INVALID_COMMAND = "OOPS!!! This is an invalid command\n" +
            "Type 'help' to find more commands";
    public static final String INVALID_TASKNO = "# of the task is invalid\n";
    public static final String INVALID_DESCRIPTION = "The description of the task is invalid\n";
    public static final String LACK_KEYWORD = "A keyword is needed\n";
    public static final String TODO_DESCRIPTION = "[format] todo <Task Name>";
    public static final String DEADLINE_DESCRIPTION = "[format] deadline <Task Name> /by <Deadline>";
    public static final String DONE_DESCRIPTION = "[format] done <Task #>(within the range)";
    public static final String DELETE_DESCRIPTION= "[format] delete <Task #>(within the range)";
    public static final String EVENT_DESCRIPTION = "[format] event <Task Name> /at <TimeSlot>";
    public static final String LIST_DESCRIPTION = "[format] list";
    public static final String HELP_DESCRIPTION = "[format] help";
    public static final String BYE_DESCRIPTION = "[format] bye";
    public static final String FIND_DESCRIPTION = "[format] find <keyWord>";
    public static final String FINDING = "Here are the matching tasks in your list:";

    private static Scanner in = new Scanner(System.in);

    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Welcome to\n" + logo);
        printFormat(GREETING);
    }

    public String parseCommand() {
        return in.nextLine();
    }

    public void exit() {
        printFormat(GOODBYE);
    }

    public void showLoadingError() {
        printFormat(LOADING_ERROR);
    }

    public void showLoadingError(String errorDescription) {
        printFormat(errorDescription);
    }

    public void printFormat(String ...strs) {
        System.out.println(FORMAT_LINE);
        for(String str: strs) {
            System.out.println(str);
        }
        System.out.println(FORMAT_LINE);
        System.out.println();
    }

    public void printFormatLine() {
        System.out.println(FORMAT_LINE);
    }

    public void printString(String str) {
        System.out.println(str);
    }

    public void printUserGuide() {
        printFormatLine();
        printFormat(HELPING,
                "todo\nadd a todo task to your task list\n"+TODO_DESCRIPTION,
                "deadline\nadd a deadline task to your task list\n"+DEADLINE_DESCRIPTION,
                "event\nadd a event task to your task list\n"+EVENT_DESCRIPTION,
                "delete\ndelete one task in your task list\n"+DELETE_DESCRIPTION,
                "done\nmark one task as done in your task list\n"+DONE_DESCRIPTION,
                "find\nfind tasks by searching for a keyword\n" + FIND_DESCRIPTION,
                "list\nlist all tasks in your task list\n"+ LIST_DESCRIPTION,
                "help\nlist all commands and corresponding command format of Duke"+ HELP_DESCRIPTION,
                "exit\nexit from Duke\n"+ BYE_DESCRIPTION);
    }
}
