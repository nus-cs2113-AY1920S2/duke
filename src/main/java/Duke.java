import java.util.Scanner;

public class Duke {

    private static Task[] tasks=new Task[100];;
    private static int taskCount = 0;

    public static void main(String[] args) {
        welcomeMessage();
        Scanner read=new Scanner(System.in);
        String command=read.nextLine();
        while(!command.equals("bye")){
            printDividingLine();
            switch(command) {
            case "list":
                listTasks();
                break;
            default:
                if(command.startsWith("done ")){
                    doneTask(command);
                }else if (command.startsWith("todo ")){
                    addToDo(command);
                }else if (command.startsWith("deadline ")){
                    addDeadline(command);
                }else if (command.startsWith("event ")){
                    addEvent(command);
                }else{
                    System.out.println("Please give the type of the task.");
                }
            }
            printDividingLine();
            System.out.println("Do you have any other commands? ");
            command=read.nextLine();
        }
        exitMessage();
    }

    private static void printNumOfTasks(){
        System.out.println("Now you have "+taskCount+" tasks in the list.");
    }

    private static void addEvent(String command){
        String description=command.substring(command.indexOf(" ")+1,command.indexOf("/"));
        String period=command.substring(command.indexOf("/at")+4);
        tasks[taskCount]=new Event(description, period);
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[taskCount]);
        taskCount++;
        printNumOfTasks();
    }

    private static void addDeadline(String command){
        String description=command.substring(command.indexOf(" ")+1,command.indexOf("/"));
        String by=command.substring(command.indexOf("/by")+4);
        tasks[taskCount]=new Deadline(description,by);
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[taskCount]);
        taskCount++;
        printNumOfTasks();
    }

    private static void addToDo(String command) {
        String description=command.substring(command.indexOf(" ")+1);
        tasks[taskCount]=new ToDo(description);
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[taskCount]);
        taskCount++;
        printNumOfTasks();
    }

    private static void doneTask(String command) {
        String[] splitCommand=command.split(" ");
        int taskIndex=Integer.parseInt(splitCommand[1])-1;
        if(taskIndex< taskCount) {
            tasks[taskIndex].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  ["+ tasks[taskIndex].getStatusIcon() + "] " + tasks[taskIndex].description);
        }else{
            System.out.println("There is no task No."+(taskIndex+1));
        }
    }

    private static void printDividingLine() {
        System.out.println("------------***------------");
    }

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for(int i=0;i<100;i++){
            if(tasks[i]==null){
                break;
            }
            System.out.println((i+1)+". "+tasks[i]);
        }
    }

    private static void exitMessage() {
        printDividingLine();
        System.out.println("Bye. Hope to see you again soon!");
        printDividingLine();
    }

    private static void welcomeMessage() {
        printDividingLine();
        System.out.println("Hello! I'm Momo");
        System.out.println("What can I do for you?");
        printDividingLine();
    }
}
