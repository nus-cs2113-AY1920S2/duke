import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String BORDER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ";
    private static final String SPACE = "  ";
    private static final List<String> tasksLists = new ArrayList<>(100);

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
                return;
            }
            if(input.equals("list")){
                for (int i = 0; i < tasksLists.size(); i++ ){
                    printMessage((i+1) + ". " + tasksLists.get(i));
                }
                printBorder();
            }
            else{
                tasksLists.add(input);
                printMessage("added: " + input);
                printBorder();
            }
        }
    }

    public static void main(String[] args) {
        greetUser();
        replyUser();
    }
}
