import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
        String name = "**Rick**";
        System.out.println("____________________________________________________________");
        System.out.println("  Hello! I'm " + name);
        System.out.println("  What can I do for you?");
        System.out.println("____________________________________________________________" + '\n');
        while (true) {
        	String str = sc.nextLine();
        	if (str.equals("bye")) {
        		break;
        	}
        	System.out.println("____________________________________________________________");
        	System.out.println(str);
        	System.out.println("____________________________________________________________" + '\n');
        }
        System.out.println("____________________________________________________________");
        System.out.println("  Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
        System.exit(0);
    }
}
