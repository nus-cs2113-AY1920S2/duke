import java.util.Scanner;
public class Duke {
    private static String[] tasks = new String[100];
    private static int taskCount = 0;
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();

        String responde;
        Scanner in = new Scanner(System.in);
        do {
            responde = in.nextLine();
            addList(responde);

        }while(!responde.equals("bye"));

    }

    public static void greet(){
        printLine();
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        printLine();
        System.out.println();
    }

    public static void addList(String line){
        printLine();
        if (line.equals("bye")){
            System.out.println("\tBye. Hope to see you again soon!");
        }
        else if (line.equals("list")){
            int currentCount = 0;
            while(currentCount < taskCount){
                System.out.println("\t" + Integer.toString(currentCount+1) + ". " + tasks[currentCount]);
                currentCount++;
            }
        }
        else{
            tasks[taskCount] = line;
            taskCount++;
            System.out.println("\tadded: " + line);
        }
        printLine();
        System.out.println();
    }

    public static void printLine(){
        System.out.println("____________________________________________________________");
    }


}

