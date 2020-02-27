import exceptions.DukeException;

/**
 * <h1>Parser</h1>
 * Deals with making sense of the user command,
 * directs the program to execute the corresponding TaskList command
 */
public class Parser {

    private static TaskList currTasks;

    public Parser(TaskList taskList) {
        currTasks = taskList;
    }

    //[event, food, fair, /at, Mon, 2-4pm]
    //[deadline, buy, food, /by, Sunday]
    //TODO: For further abstraction, related to TaskList further abstraction. To remove if abstraction is not done
    protected static String[] splitString(String userCmd){
        String[] strArr = userCmd.split(" ");
        return strArr;
    }

    /**
     * Assesses the given user command and decides what TaskList function to execute
     * @param userCmd the given user command
     * @throws DukeException from TaskList exceptions
     */
    protected static void runParser(String userCmd) throws DukeException {
        // end program
        if (userCmd.toLowerCase().equals("bye")) {
            System.out.println("Bye. Hope to see you again!");
            System.exit(0);
        }
        // List commands
        else if (userCmd.toLowerCase().equals("list")) {
            currTasks.listTasks();
        }
        // Mark task as done
        else if (userCmd.contains("done")) {
            currTasks.markTaskDone(userCmd);
        }
        // Add task type todos (normal tasks)
        else if (userCmd.contains("todo")) {
            currTasks.addTask(userCmd);
        }
        // Add task type deadline
        else if (userCmd.contains("deadline")) {
            currTasks.addDeadline(userCmd);
        }
        // Add task type events
        else if (userCmd.contains("event")) {
            currTasks.addEvent(userCmd);
        }
        // Help command
        else if (userCmd.contains("help")) {
            UI.printHelp();
        }
        else if (userCmd.contains("delete")) {
            currTasks.deleteTask(userCmd);
        } else {
            System.out.println("Wrong syntax!");
            UI.printHelp();
        }
    }
}
