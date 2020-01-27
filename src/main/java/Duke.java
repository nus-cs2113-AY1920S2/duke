import java.util.Scanner;

public class Duke {
    private static final String LINE = "____________________________________________________________ ";
    private static final String SPACE = "     ";

    private static void printLine(){
        System.out.println(LINE);
    }
    private static void printMessage(String message){
        System.out.println(SPACE + message);
    }

    private static void greetUser(){
        printLine();
        printMessage("Hello! I'm Duke");
        printMessage("What can I do for you?");
        printLine();
    }

    private static void replyUser(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            String input = scanner.nextLine();
            if(input.equals("bye")){
                printLine();
                printMessage("Bye. Hope to see you again soon!");
                printLine();
                return;
            }
            else{
                printLine();
                printMessage(input);
                printLine();
            }
        }
    }

    public static void main(String[] args) {
        greetUser();
        replyUser();
    }
}
