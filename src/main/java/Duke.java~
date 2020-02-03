import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static ArrayList<Task> list = new ArrayList<>();
    
    public static String makeDone(String line) {
        String msg = "";
        // sets a specified task as done
        int index = Integer.valueOf(line.split(" ")[1]);
        if (index > list.size()) {
            msg += "No such task!";
        } else if (list.get(index - 1).isDone) {
            msg += "Task is already completed!";
        } else {
            list.get(index - 1).markAsDone();
            msg += "Nice! I've marked this task as done: " + '\n';
            msg += "    " + list.get(index - 1).toString();
        }
        return msg;
    }
    
    public static String addTodo(String line) {
        String msg = "";
        Task todo = new ToDo(line.substring(5));
        list.add(todo);
        msg += "Got it. I've added this task: " + '\n';
        msg += "    " + todo.toString() + '\n';
        msg += "  Now you have " + list.size() + " task(s) in the list.";
        return msg;
    }
    
    public static String addDeadline(String line) {
        String msg = "";
        int ind1 = line.indexOf('/');
        Task deadline = new Deadline(line.substring(9, ind1 - 1), line.substring(ind1 + 4));
        list.add(deadline);
        msg += "Got it. I've added this task: " + '\n';
        msg += "    " + deadline.toString() + '\n';
        msg += "  Now you have " + list.size() + " task(s) in the list.";
        return msg;
    }
    
    public static String addEvent(String line) {
        String msg = "";
        int ind1 = line.indexOf('/');
        Task event = new Event(line.substring(6, ind1 - 1), line.substring(ind1 + 4));
        list.add(event);
        msg += "Got it. I've added this task: " + '\n';
        msg += "    " + event.toString() + '\n';
        msg += "  Now you have " + list.size() + " task(s) in the list.";
        return msg;
    }
    
    public static String list() {
        String msg = "";
        // accesses the list
        if (list.size() == 0) {
            msg += "list is empty";
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
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = "**Rick**";
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println("  Hello! I'm " + name);
        System.out.println("  What can I do for you?");
        System.out.println(line + '\n');
        while (true) {
            String str = sc.nextLine();
            String msg = "  ";
            if (str.equals("bye")) {
                // exits the program
                break;
            } else if (str.equals("list")) {
                msg += list();
            } else if (str.split(" ")[0].equals("done")) {
                msg += makeDone(str);
            } else if (str.split(" ")[0].equals("todo")) {
                msg += addTodo(str);
            } else if (str.split(" ")[0].equals("deadline")) {
                msg += addDeadline(str);
            } else if (str.split(" ")[0].equals("event")) {
                msg += addEvent(str);
            } else {
                msg += "Unknown command";
            }
            System.out.println(line);
            System.out.println(msg);
            System.out.println(line + '\n');
        }
        sc.close();
        System.out.println(line);
        System.out.println("  Bye. Hope to see you again soon!");
        System.out.println(line);
        System.exit(0);
    }
}
