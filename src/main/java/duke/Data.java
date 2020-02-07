package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.util.ArrayList;

public class Data {
    private static String[] availableTasks = {"todo", "deadline", "event"};
    private static ArrayList<Todo> todos;

    public Data() {
        this.todos = new ArrayList<Todo>();
    }

    public static void newTask(String cmd) throws DukeException {
        if (cmd.isEmpty()) {
            throw new DukeException("Please type something.");
        }
        if (!cmd.contains(" ") && cmd.length() > 0) {
            if (cmd.equals(availableTasks[0]) || cmd.equals(availableTasks[1])
                    || cmd.equals(availableTasks[2])) {
                throw new DukeException("OOPS!!! The description of a " + cmd + " cannot be empty.");
            }
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        String taskType = cmd.substring(0, cmd.indexOf(" "));
        String description;
        switch (taskType) {
        case "todo":
            if (cmd.indexOf(" ") + 1 >= cmd.length()) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty. Follow format: todo [message]");
            }
            description = cmd.substring(cmd.indexOf(" ")+1);
            todos.add(new Todo(description));
            break;
        case "deadline":
            if ((cmd.indexOf(" ") + 1 >= cmd.length() || cmd.contains("/by") == false) && cmd.length() > 0) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty. Follow format: dead" +
                        "line [message] /by [date]");
            }
            description = cmd.substring(cmd.indexOf(" ") + 1, cmd.indexOf("/") - 1);
            String by = cmd.substring(cmd.indexOf("/") + 4);
            todos.add(new Deadline(description, by));
            break;
        case "event":
            if ((cmd.indexOf(" ") + 1 >= cmd.length() || cmd.contains("/at") == false) && cmd.length() > 0) {
                throw new DukeException("OOPS!!! The description of a event cannot be empty. Follow format: event [message] /at [location]");
            }
            description = cmd.substring(cmd.indexOf(" ")+1, cmd.indexOf("/") - 1);
            String at = cmd.substring(cmd.indexOf("/") + 4);
            todos.add(new Event(description, at));
            break;
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        System.out.println("  Got it. I've added this duke.task:");
        System.out.println("     " + todos.get(getSize() - 1));
        System.out.println("  Now you have " + getSize() + " tasks in the list.");

    }

    public static int getSize() {
        return todos.size();
    }

    public String printItem(int i) {
        return todos.get(i).toString();
    }
    public void setDone(int i) {
        todos.get(i).setDone();
    }
}
