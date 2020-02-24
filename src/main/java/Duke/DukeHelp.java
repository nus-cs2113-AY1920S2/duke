package Duke;

/**
 * Represents the help messages Duke.Duke displays
 */
public class DukeHelp {

    public DukeHelp() {}

    public void printHelp() {
        System.out.println(" Here are the commands:");
        System.out.println("   1. todo: Creates a todo activity [Syntax: todo {description}]");
        System.out.println("   2. deadline: Creates a deadline activity [Syntax: deadline {description}" +
                "/by {deadline}]");
        System.out.println("   3. event: Creates an event activity [Syntax: event {description} /at {time/date}]");
        System.out.println("   4. done: Mark event as done [Syntax: done {index of task}]");
        System.out.println("   5. delete: removes task from list [Syntax: delete {index of task}]");
        System.out.println("   6. list: displays list of all tasks [Syntax: list]");
        System.out.println("   7. find: find the task list with description matching keywords " +
                "[Syntax: find {keyword}]");
    }
}