import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void printNewLine(){
        String NEW_LINE = "____________________________________________________________";
        System.out.println(NEW_LINE);
    }

    public static void main(String[] args) {
        // Create Scanner object
        Scanner myScanner = new Scanner(System.in);
        ArrayList<String> myList = new ArrayList<>();
        
        // Create Hello message
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "What can I do for you?\n");

        String userInput;
        do {
            System.out.print("USER:");
            userInput = myScanner.nextLine();
            printNewLine();

            switch(userInput){
            case "bye":
                break;
            case "list":
                for (int i = 0; i < myList.size(); i++){
                    System.out.println((i+1) + ". " + myList.get(i));
                }
                printNewLine();
                break;
            default:
                myList.add(userInput);
                System.out.println("Added: " + userInput);
                printNewLine();
            }
        }while(!userInput.equals("bye"));

        System.out.println("Exiting DUKE\n" + logo);
    }
}
