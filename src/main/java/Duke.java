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
        	if (str.equals("bye")) {
        		break;
        	} else if (str.equals("list")) {
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
        	}
        	else {
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
