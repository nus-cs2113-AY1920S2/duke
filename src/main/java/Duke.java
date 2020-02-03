import java.util.Scanner;
public class Duke {
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();

        String userResponde;
        Scanner in = new Scanner(System.in);
        do {
            userResponde = in.nextLine();
            dukeResponde(userResponde);

        }while(!userResponde.equals("bye"));

    }

    public static void greet(){
        printLine();
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        printLine();
        System.out.println();
    }

    public static void dukeResponde(String userResponde){
        printLine();
        if(userResponde.equals("bye")){
            System.out.println("\tBye. Hope to see you again soon!");
        }
        else if(userResponde.equals("list")) {
            listTask();
        }
        else if(userResponde.startsWith("done")) {
            markAsDone(userResponde);
        }
        else{
            addNewTask(userResponde);
        }
        printLine();
        System.out.println();
    }

    public static void printLine(){
        System.out.println("\t____________________________________________________________");
    }

    public static void listTask(){
        System.out.println("\tHere are the tasks in your list:");
        for(int i = 0; i < taskCount; i++) {
            System.out.println("\t" + (i + 1) + "." + tasks[i]);
        }
    }
    public static void markAsDone(String userResponde){
        int doneCount = Integer.parseInt(userResponde.substring(5)) - 1;
        tasks[doneCount].markAsDone();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  [\u2713] " + tasks[doneCount].getDescription());
    }

    public static void addNewTask(String userResponde){
        if(userResponde.startsWith("todo")){
            tasks[taskCount] = new Todo(userResponde.substring(5));
        }
        else if(userResponde.startsWith("deadline")){
            int dividerPosition = userResponde.indexOf(" /by");
            String taskName = userResponde.substring(9,dividerPosition);
            String deadlineTime = userResponde.substring(dividerPosition + 5);
            tasks[taskCount] = new Deadline(taskName,deadlineTime);
        }
        else if(userResponde.startsWith("event")){
            int dividerPosition = userResponde.indexOf(" /at");
            String taskName = userResponde.substring(6,dividerPosition);
            String deadlineTime = userResponde.substring(dividerPosition + 5);
            tasks[taskCount] = new Event(taskName,deadlineTime);
        }
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + tasks[taskCount]);
        System.out.println("\tNow you have " + (taskCount+1) + " tasks in the list");
        taskCount++;
    }


}

