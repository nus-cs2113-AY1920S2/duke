import java.util.Scanner;

public class Duke {

    private static TaskManager taskManager = new TaskManager();

    public static void printSpaces(int numberOfSpaces){
        while(numberOfSpaces > 0){
            System.out.print(" ");
            numberOfSpaces--;
        }
    }

    public static void printLine(){
        printSpaces(4);
        System.out.println("____________________________________________________________");
    }

    public static void printWithIndentation(String line){
        printSpaces(5);
        System.out.println(line);
    }

    public static void welcomeMessage(){
        printLine();
        printWithIndentation("Hello! I'm Duke");
        printWithIndentation("What can I do for you?");
        printLine();
        System.out.println();
    }

    public static void byeMessage(){
        printLine();
        printWithIndentation("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printInvalidIndex(){
        printLine();
        printWithIndentation("Invalid Command (done x : x should be a valid integer index)");
        printLine();
    }

    public static void printInvalidInteger(){
        printLine();
        printWithIndentation("Invalid Command (done x : x should be an integer)");
        printLine();
    }

    private static void printEmptyLine() {
        printLine();
        printWithIndentation("You have entered a empty line, Please enter a valid command");
        printLine();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        welcomeMessage();
        String command;
        command = sc.nextLine();
        while(!command.equals("bye")){
            String[] commandSplit = command.split(" ",2);
            String commandType = commandSplit[0];
            switch(commandType) {
            case "list":
                taskManager.listTasks();
                break;
            case "":
                printEmptyLine();
                break;
            case "done":
                int taskNumber;
                try{
                    taskNumber = Integer.parseInt(commandSplit[1]);
                    taskNumber--;                           // Convert to 0-based index
                    if(taskManager.checkIndexValidity(taskNumber)){
                        if(taskManager.tasks.get(taskNumber).isDone){
                            taskManager.printAlreadyDone(taskNumber);
                        } else {
                            taskManager.maskTaskAsDone(taskNumber);
                        }
                    } else {
                        printInvalidIndex();
                    }
                }
                catch(NumberFormatException e){
                    printInvalidInteger();
                }

                break;
            default:
                taskManager.addTask(command);
                break;
            }
            System.out.println();
            command = sc.nextLine();
        }
        byeMessage();
    }
}
