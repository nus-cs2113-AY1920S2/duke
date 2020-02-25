package duke;

public class Ui implements Logo {
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String DEADLINE_SYMBOL = " /by ";
    public static final String EVENT_SYMBOL = " /at ";
    public static final String SPACE_SYMBOL = " ";

    public Ui() {
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    public final static void exit() {
        System.out.println("  Bye. Hope to see you again soon!");
        printBreak();
    }

    public final static void printBreak() {
        System.out.println("  ____________________________________________________________");
    }

    public final static void printListTitle() {
        System.out.println("  Here are the tasks in your list:");
    }

    public final static void addTaskSuccesssful() {
        System.out.println("  Got it. I've added this task:");
    }

    public final static void markDoneSuccessful() {
        System.out.println("  Nice! I've marked this task as done:");
    }

    public static void deleteSuccessful() {
        System.out.println("  Noted. I've removed this task:");
    }
}
