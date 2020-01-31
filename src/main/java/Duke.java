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
        String[] ls = new String[100]; 
        int items = 0; 

        do {
            Scanner in = new Scanner(System.in); 
            line = in.nextLine();
            if (line.equals("list")) { 
                for (int i = 0; i < items; i++) { 
                    System.out.println((i+1) + ". " +  ls[i]); 
                }
            } else { 
            System.out.println(lines);  
            System.out.println("added: " + line); 
            ls[items] = line;
            items++; 
        }
        System.out.println(lines); 
        } while (!line.equals("bye"));

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(lines);  

    }
}
