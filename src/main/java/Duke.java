import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        String name = "**Rick**";
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println("  Hello! I'm " + name);
        System.out.println("  What can I do for you?");
        System.out.println(line + '\n');
        while (true) {
            String str = sc.nextLine();
            String msg = "  ";
            // to do: split each command into diff methods so as not to 
            // clutter up the main method
            if (str.equals("bye")) {
                // exits the program
                break;
            } else if (str.equals("list")) {
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
            } else if (str.split(" ")[0].equals("done")) {
                // sets a specified task as done
                int index = Integer.valueOf(str.split(" ")[1]);
                if (index > list.size()) {
                    msg += "No such task!";
                } else if (list.get(index - 1).isDone) {
                    msg += "Task is already completed!";
                } else {
                    list.get(index - 1).markAsDone();
                    msg += "Nice! I've marked this task as done: " + '\n';
                    msg += "    " + list.get(index - 1).toString();
                }
            } else if (str.split(" ")[0].equals("todo")) {
            	Task todo = new ToDo(str.substring(5));
            	list.add(todo);
            	msg += "Got it. I've added this task: " + '\n';
            	msg += "    " + todo.toString() + '\n';
            	msg += "  Now you have " + list.size() + " task(s) in the list.";
            } else if (str.split(" ")[0].equals("deadline")) {
            	int ind1 = str.indexOf('/');
            	Task deadline = new Deadline(str.substring(9, ind1 - 1), str.substring(ind1 + 4));
            	list.add(deadline);
            	msg += "Got it. I've added this task: " + '\n';
            	msg += "    " + deadline.toString() + '\n';
            	msg += "  Now you have " + list.size() + " task(s) in the list.";
            } else if (str.split(" ")[0].equals("event")) {
            	int ind1 = str.indexOf('/');
            	Task event = new Event(str.substring(6, ind1 - 1), str.substring(ind1 + 4));
            	list.add(event);
            	msg += "Got it. I've added this task: " + '\n';
            	msg += "    " + event.toString() + '\n';
            	msg += "  Now you have " + list.size() + " task(s) in the list.";
            }
            else {
                // adds a task to the list
                Task task = new Task(str);
                list.add(task);
                msg += "added: " + str;
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
