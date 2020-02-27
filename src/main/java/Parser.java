import exceptions.DukeException;
import tasks.Task;

import java.util.ArrayList;
import java.util.Arrays;

/*
Deals with making sense of the user command
 */
public class Parser {

    private static TaskList currTasks;

    public Parser(TaskList taskList) {
        currTasks = taskList;
    }

    //[event, food, fair, /at, Mon, 2-4pm]
    //[deadline, buy, food, /by, Sunday]
    protected static String[] splitString(String userCmd){
        String[] strArr = userCmd.split(" ");
        return strArr; //todo remove
    }

    protected static void runParser(String userCmd) throws DukeException {
        System.out.println(Arrays.toString(splitString(userCmd)) );

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
        }
        else if (userCmd.contains("find")) {
            currTasks.findTask(userCmd);
        }else {
            System.out.println("Wrong syntax!");
            UI.printHelp();
        }
    }
}
