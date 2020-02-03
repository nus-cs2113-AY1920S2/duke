import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Duke {
    private static final String BORDER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ";
    private static final String SPACE = "  ";
    private static final List<Task> tasksList = new ArrayList<>(100);

    private static void printBorder(){
        System.out.println(BORDER);
    }
    private static void printMessage(String message){
        System.out.println(SPACE + message);
    }

    private static void greetUser(){
        printBorder();
        printMessage("Hello! I'm Duke");
        printMessage("What can I do for you?");
        printBorder();
    }

    private static void replyUser(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            String input = scanner.nextLine();
            printBorder();
            if(input.equals("bye")) {
                printMessage("Bye. Hope to see you again soon!");
                printBorder();
                break;
            }
            else if(input.equals("list")){
                if (tasksList.size() == 0){
                    printMessage("There are no tasks in your listlis!");
                }
                else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasksList.size(); i++) {
                        printMessage((i + 1) + "." + "[" + tasksList.get(i).getStatusIcon() + "] " + tasksList.get(i).getDescription());
                    }
                }
            }
            else{
                String[] completedTask = input.split(" ");
                if (completedTask.length == 2 && completedTask[0].equals("done") && isNumeric(completedTask[1])){
                    int i = Integer.parseInt(completedTask[1]);
                    if (i > 0 && i <= tasksList.size()){
                        System.out.println("Nice! I've marked this task as done");
                        tasksList.get(i-1).markAsDone();
                        System.out.println(i + "." + "[" + "\u2713" + "] " + tasksList.get(i-1).getDescription());
                    }
                    else {
                        printMessage("Task not found!");
                    }
                }
                else{
                    Task t = new Task(input);
                    tasksList.add(t);
                    printMessage("Added: " + input);
                }
            }
            printBorder();
        }
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        greetUser();
        replyUser();
    }
}
