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
        ArrayList<Task> myList = new ArrayList<Task>();
        
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

            String[] command = userInput.trim().split(" ");

            switch(command[0]){
            case "bye":
                break;
            case "list":
                for (int i = 0; i < myList.size(); i++){
                    String listResponseMessage = String.format("%d. %s %s", (i + 1),
                            myList.get(i).getStatusIcon(), myList.get(i).getDescription());
                    System.out.println(listResponseMessage);
                }
                break;
            case "done":
                // bounds checking
                if(command.length < 2){
                    System.out.println("Enter an index to be marked done!");
                }else if(Integer.parseInt(command[1]) > myList.size() || Integer.parseInt(command[1]) <= 0){
                    System.out.println("Item requested is out of range! Try another item.");
                }else{
                    int itemIndexRequested = Integer.parseInt(command[1]) - 1;

                    myList.get(itemIndexRequested).markAsDone();
                    String doneResponseMessage = String.format("Item [%s. %s] marked as done!",
                            command[1], myList.get(itemIndexRequested).getDescription());
                    System.out.println(doneResponseMessage);
                }
                break;
            default:
                myList.add(new Task(userInput));
                System.out.println("Added: " + userInput);
            }
            printNewLine();
        }while(!userInput.equals("bye"));

        System.out.println("Exiting DUKE\n" + logo);
        myScanner.close();
    }
}
