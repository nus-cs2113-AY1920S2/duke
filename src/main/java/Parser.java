import exceptions.DukeException;
import tasks.Task;

import java.util.ArrayList;

/*
Deals with making sense of the user command
 */
public class Parser {
    // TODO: Check if this method will help, may be unhelpful
    protected static String[] splitString(String[] strArr, String userCmd, String splitCase){
        strArr = userCmd.split(splitCase);
        return strArr;
    }

    protected static ArrayList<Task> runParser(String userCmd, ArrayList<Task> taskArrList) throws DukeException {

        // end program
        if (userCmd.toLowerCase().equals("bye")) {
            System.out.println("Bye. Hope to see you again!");
            System.exit(0);
        }
        // List commands
        else if (userCmd.toLowerCase().equals("list")) {
            UI.listTasks(taskArrList);
        }
        // Mark task as done
        else if (userCmd.contains("done")) {
            taskArrList = TaskList.markTaskDone(userCmd, taskArrList);
        }
        // Add task type todos (normal tasks)
        else if (userCmd.contains("todo")) {
            taskArrList = TaskList.addTask(userCmd, taskArrList);
        }
        // Add task type deadline
        else if (userCmd.contains("deadline")) {
            taskArrList = TaskList.addDeadline(userCmd, taskArrList);
        }
        // Add task type events
        else if (userCmd.contains("event")) {
            taskArrList = TaskList.addEvent(userCmd, taskArrList);
        }
        // Help command
        else if (userCmd.contains("help")) {
            UI.printHelp();
        } else if (userCmd.contains("delete")) {
            taskArrList = TaskList.deleteTask(userCmd, taskArrList);
        } else {
            System.out.println("Wrong syntax!");
            UI.printHelp();
        }
        return taskArrList;
    }
}
