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
        Task[] tasks = new Task[100]; 
        int items = 0; 

        do {
            Scanner in = new Scanner(System.in); 
            line = in.nextLine();
            line = line.trim();

            if (line.equals("bye")) {
                break;
            } else if (line.equals("list")) {
                System.out.println(lines);
                System.out.println("Here are the tasks in your list: "); 
                for (int i = 0; i < items; i++) { 
                    System.out.println((i+1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].description); 
                }
            } else if (line.contains("done")) { 
                int line_length = line.length(); 
                int number = line.charAt(line_length-1) - '0';
                Task doneTask = tasks[number-1];
                doneTask.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + doneTask.getStatusIcon() + "] " + doneTask.description);

                
            } else { 
            System.out.println(lines); 
            Task t = new Task(line);  
            System.out.println("added: " + line); 
            tasks[items] = t;
            items++; 
            }



        System.out.println(lines); 
        } while (!line.equals("bye"));

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(lines);  

    }
}


