import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	ArrayList<String> list = new ArrayList<>();
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
        			msg = "list is empty";
        		} else {
        			int counter = 1;
        			for (String s : list) {
        				msg += counter + ".  " + s;
        				counter++;
        				msg += '\n' + "  ";
        			}
        		}
        	} else {
        		list.add(str);
        		msg += "added: " + str;
        	}
        	System.out.println(line);
        	System.out.println(msg);
        	System.out.println(line + '\n');
        }
        System.out.println(line);
        System.out.println("  Bye. Hope to see you again soon!");
        System.out.println(line);
        System.exit(0);
    }
}
