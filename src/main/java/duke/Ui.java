package duke;

/**
 * Handles all user interface for Duke
 */
public class Ui {

    /**
     * Print the task
     * @param t task to be printed
     */
     static void printTask(types.Task t) {
        System.out.println(Duke.INDENT + "Got it. I've added this task: ");
        System.out.println(Duke.INDENT +  t.toString());
        System.out.println(Duke.INDENT + "Now you have " + TaskList.getNumTasks() + " tasks in the list.");
    }

    /**
     * The list command, which lists all tasks and statuses
     */
    static void listCommand() {
        System.out.println(Duke.INDENT +"Here are the tasks in your list:");
        for (int i = 0; i < TaskList.getNumTasks(); i++) {
            System.out.print(Duke.INDENT + (i + 1) + ".");
            System.out.println(TaskList.getTaskList()[i].toString());
        }
    }

    /**
     * The introduction command, which generates the introduction script
     */
    static void introduction() {
        TaskList.setNumTasks(0);
        System.out.println(Duke.BAR);
        System.out.println(Duke.INDENT + "Hey! I'm Jamun");
        System.out.println(Duke.INDENT + "How can I help you?");
        System.out.println(Duke.BAR);
    }

    /**
     * The goodbye command, which generates the goodbye script
     */
    static void goodbye() {
        System.out.println(Duke.BAR);
        System.out.println(Duke.INDENT + "Bye! See ya next time :)");
        System.out.println(Duke.BAR);
    }

}
