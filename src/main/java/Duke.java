import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
    	String lines = "--------------------------------------------"; 
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?"); 
        System.out.println(lines); 
        String line; 
        do {
            Scanner in = new Scanner(System.in); 
            line = in.nextLine(); 
            System.out.println(line); 
            System.out.println(lines); 
        } while (!line.equals("bye"));

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(lines);  

    }
}
