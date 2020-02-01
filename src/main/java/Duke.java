import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static final int MAXIMUM_CAPACITY = 100;
    private static final Scanner SCANNER = new Scanner(System.in);
    private static Task[] tasks = new Task[MAXIMUM_CAPACITY];
    private static int taskCount = 0;

    public static void doLine(){
        String line = "_".repeat(60);
        System.out.println("\t"+line);
    }
    public static void doGreeting(){
        doLine();
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        doLine();
    }
    public static void doFarewell(){
        doLine();
        System.out.println("\tBye. Hope to see you again soon!");
        doLine();

    }
    public static void doWrongInput(){
        doLine();
        System.out.println("\tYou have entered an incorrect input. Please try again.");
        doLine();
    }
    public static void getList(){
        doLine();
        System.out.println("\tHere are the tasks in your list:");
        for(int i = 0; i< taskCount; i++){
            System.out.println("\t"+(i+1)+"."+tasks[i].toString());
        }
        doLine();
    }
    private static String getUserInput() {
        String inputLine = SCANNER.nextLine();
        while (inputLine.trim().isEmpty()) {
            inputLine = SCANNER.nextLine();
        }
        return inputLine;
    }
    private static void addTask(Task task){
        tasks[taskCount] = task;

        doLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t"+tasks[taskCount].toString());
        taskCount++;
        System.out.println("\tNow you have "+ taskCount +" tasks in the list.");
        doLine();
    }
    private static void completeTask(String taskCount){
        int taskNumber = Integer.parseInt(taskCount) -1;
        tasks[taskNumber].markAsDone();
        doLine();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t"+tasks[taskNumber].toString());
        doLine();
    }
    public static void processInput(String command){
        String[] firstProcess = new String[MAXIMUM_CAPACITY];
        firstProcess = command.split(" ", 2);
        String firstCommand = firstProcess[0];
        //System.out.println(Arrays.toString(a));
        try {
            if(firstCommand.equals("bye")){
                doFarewell();
                exitProgram();
            }else if(firstCommand.equals("list")) {
                getList();
            }else if(firstCommand.equals("deadline")){
                String[] secondProcess = firstProcess[1].split(" /by ");
                addTask(new Deadline(secondProcess[0], secondProcess[1]));
            }else if(firstCommand.equals("event")){
                String[] secondProcess = firstProcess[1].split(" /at ");
                addTask(new Event(secondProcess[0], secondProcess[1]));
            }else if(firstCommand.equals("todo")){
                System.out.println(firstProcess[1]);
                addTask(new ToDo(firstProcess[1]));
            }else if(firstCommand.equals("done")) {
                completeTask(firstProcess[1]);
            }else{
                doWrongInput();
            }
        }catch(Exception e){
            doWrongInput();
        }
    }

    private static void exitProgram() {
        System.exit(0);
    }
    public static void main(String[] args) {

        doGreeting();
        while(true){
            String userCommand = getUserInput();
            processInput(userCommand);
        }

    }
}
