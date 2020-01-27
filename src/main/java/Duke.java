import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\t____________________________________________________________\n"
                + "\tHello! I'm Duke\n" +
                "\tWhat can I do for you?\n"
                + "\t____________________________________________________________\n");


        Task[] taskList = new Task[100];
        int taskCount = 0;
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!line.equals("bye")){
            if(line.equals("list")){
                System.out.println("\t____________________________________________________________");
                for(int i=0; i<taskCount; ++i){
                    Task t = taskList[i];
                    System.out.println("\t" + i + ".[" + t.getStatusIcon() + "] " + t.description);
                }
                System.out.println("\t____________________________________________________________\n");
            }else if(line.startsWith("done")){
                int taskNum = Integer.parseInt(line.substring(5,6));
                Task t=taskList[taskNum];
                t.markAsDone();
                System.out.println("\t____________________________________________________________\n"
                +"\t Nice! I've marked this task as done:\n"
                +"\t" + "  [" + t.getStatusIcon() + "] " + t.description + "\n"
                +"\t____________________________________________________________\n");
            }else{
                Task t = new Task(line);
                taskList[taskCount] = t;
                taskCount++;
                System.out.println("\t____________________________________________________________\n"
                        + "\t added: " + line + "\n"
                        + "\t____________________________________________________________\n");
            }
            line = in.nextLine();
        }
        System.out.println("\t____________________________________________________________\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t____________________________________________________________\n");

    }
}
