package duke;

public class Ui {
    public Ui() {
    }

    /**
     * Print the Welcome message and call commandList method to List out all the possible command
     * the user can execute.
     */
    public void greetUser() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm duke.Duke");
        helpCommand();
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Print out all the possible command the user can execute.
     */
    public void helpCommand() {
        System.out.println("    Here is the list of commands that are available:");
        System.out.println("+---------------------------------------------------------------+");
        System.out.println("| Index | Input            | Command                            |");
        System.out.println("|-------+------------------+------------------------------------|");
        System.out.println("| 01    | todo j           | Add a task(j) without dateline     |");
        System.out.println("|-------+------------------+------------------------------------|");
        System.out.println("| 02    | dateline j /by d | Add a task(j) with due date d      |");
        System.out.println("|-------+------------------+------------------------------------|");
        System.out.println("| 03    | event j /at d    | Add a task(j) that start at date d |");
        System.out.println("|-------+------------------+------------------------------------|");
        System.out.println("| 04    | list             | List out all the stored task       |");
        System.out.println("|-------+------------------+------------------------------------|");
        System.out.println("| 05    | done i           | Mark task i as done                |");
        System.out.println("|-------+------------------+------------------------------------|");
        System.out.println("| 06    | delete i         | Delete task(i)                     |");
        System.out.println("|-------+------------------+------------------------------------|");
        System.out.println("| 07    | find j           | Find all task with description j   |");
        System.out.println("|-------+------------------+------------------------------------|");
        System.out.println("| 08    | help             | List out all the commands          |");
        System.out.println("|-------+------------------+------------------------------------|");
        System.out.println("| 09    | bye              | Terminate the program              |");
        System.out.println("|---------------------------------------------------------------|");
    }
}
