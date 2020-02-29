package duke;

public class Ui {


    /**
     * Line that divides text
     */
    private static final String BAR = "____________________________________";

    /**
     * Print the task
     * @param t task to be printed
     */
     static void printTask(types.Task t) {
        System.out.println("    Got it. I've added this task: ");
        System.out.println("    " + t.toString());
        System.out.println("    Now you have " + TaskList.getNumTasks() + " tasks in the list.");
    }

    /**
     * The list command, which lists all tasks and statuses
     */
    static void listCommand() {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < TaskList.getNumTasks(); i++) {
            System.out.print("    " + (i + 1) + ".");
            System.out.println(TaskList.getTaskList()[i].toString());
        }
    }

    /**
     * The introduction command, which generates the introduction script
     */
    static void introduction() {
        TaskList.setNumTasks(0);
        System.out.println(BAR);
        System.out.println("    Hey! I'm Jamun");
        System.out.println("    How can I help you?");
        System.out.println(BAR);
    }

    /**
     * The goodbye command, which generates the goodbye script
     */
    static void goodbye() {
        System.out.println(BAR);
        System.out.println("    Bye! See ya next time :)");
        System.out.println(BAR);
    }

}
