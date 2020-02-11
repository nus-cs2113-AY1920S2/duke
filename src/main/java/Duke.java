import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> list = new ArrayList<>();
    private final static String name = "*Rick*";
    private final static String line = "____________________________________________________________";
    
    public static String makeDone(String line) throws DukeException {
        String[] cmds = line.split(" ");
        if (cmds.length == 1) {
            throw new DukeException("Oops!! Please specify a task.");
        }
        String msg = "";
        // sets a specified task as done
        int index;
        try { 
            index = Integer.valueOf(cmds[1]);
        } catch (Exception exception) {
            throw new DukeException("Oops!! Wrong format.");
        }
        if (index < 1) {
            throw new DukeException("Oops!! Invalid task.");
        } else if (index > list.size()) {
            throw new DukeException("Oops!! No such task.");
        } else if (list.get(index - 1).isDone) {
            throw new DukeException("Oops!! Task is already completed.");
        } else {
            list.get(index - 1).markAsDone();
            msg += "Nice! I've marked this task as done: " + '\n';
            msg += "    " + list.get(index - 1).toString();
        }
        return msg;
    }
    
    public static String addTodo(String line) throws DukeException {
        String[] cmds = line.split(" ");
        if (cmds.length == 1) {
            throw new DukeException("Oops!! The description of a todo cannt be empty.");
        }
        String msg = "";
        String description = line.substring(5);
        Task todo = new ToDo(description);
        list.add(todo);
        msg = outputMessage(todo);
        return msg;
    }
    
    public static String addDeadline(String line) throws DukeException {
        String[] cmds = line.split(" ");
        if (cmds.length == 1) {
            throw new DukeException("Oops!! The description of a deadline cannt be empty.");
        }
        String msg = "";
        try {
            int index = line.indexOf('/');
            String description = line.substring(9, index - 1);
            String time = line.substring(index + 4);
            Task deadline = new Deadline(description, time);
            list.add(deadline);
            msg = outputMessage(deadline);
        } catch (StringIndexOutOfBoundsException exception) {
            throw new DukeException("Oops!! Wrong format.");
        }
        return msg;
    }
    
    public static String addEvent(String line) throws DukeException {
        String[] cmds = line.split(" ");
        if (cmds.length == 1) {
            throw new DukeException("Oops!! The description of an event cannt be empty.");
        }
        String msg = "";
        try {
            int index = line.indexOf('/');
            String description = line.substring(6, index - 1);
            String time = line.substring(index + 4);
            Task event = new Event(description, time);
            list.add(event);
            msg = outputMessage(event);
        } catch (StringIndexOutOfBoundsException exception) {
            throw new DukeException("Oops!! Wrong format.");
        }
        return msg;
    }
    
    public static String outputMessage(Task task) {
        String msg = "Got it. I've added this task: " + '\n';
        msg += "    " + task.toString() + '\n';
        msg += "  Now you have " + list.size() + " task(s) in the list.";
        return msg;
    }
    
    public static String list() throws DukeException {
        String msg = "";
        // accesses the list
        if (list.size() == 0) {
            throw new DukeException("Oops!! List is empty.");
        } else {
            msg += "Here are the tasks in your list:" + '\n';
            int counter = 1;
            for (Task s : list) {
                msg += "    " + counter + ". " + s.toString();
                counter++;
                msg += '\n';
            }
        }
        return msg;
    }
    
    public static void goodbye() {
        System.out.println(line);
        System.out.println("  Bye. Hope to see you again soon!");
        System.out.println(line);
        System.exit(0);
    }
    
    public static void greet() {
        System.out.println(line);
        System.out.println("  Hello! I'm " + name);
        System.out.println("  What can I do for you?");
        System.out.println(line + '\n');
    }
    
    public static void main(String[] args) throws DukeException {
        Scanner sc = new Scanner(System.in);
        greet();
        OuterLoop: while (true) {
            String str = sc.nextLine();
            String cmd = str.split(" ")[0];
            String msg = "  ";
            try {
                switch (cmd) {
                    case "bye":
                        // exits the program
                        break OuterLoop;
                    case "list":
                        msg += list();
                        break;
                    case "done":
                        msg += makeDone(str);
                        break;
                    case "todo":
                        msg += addTodo(str);
                        break;
                    case "deadline":
                        msg += addDeadline(str);
                        break;
                    case "event":
                        msg += addEvent(str);
                        break;
                    default:
                        throw new DukeException("Oops!! Unknown Command.");
                }
            } catch (DukeException exception) {
                msg += exception.getMessage();
            }
            System.out.println(line);
            System.out.println(msg);
            System.out.println(line + '\n');
        }
        sc.close();
        goodbye();
    }
}
