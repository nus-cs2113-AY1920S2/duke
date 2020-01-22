import java.util.Scanner;

public class Duke {

    private static String[] list = new String[100];
    private static int sizeOfList = 0;

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

    public static void welcomeMessage(){
        printLine();
        printSpaces(5);
        System.out.println("Hello! I'm Duke");
        printSpaces(5);
        System.out.println("What can I do for you?");
        printLine();
        System.out.println();
    }

    public static void byeMessage(){
        printLine();
        printSpaces(5);
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void displayList(){
        printLine();
        for(int i = 1; i <= sizeOfList; i++){
            printSpaces(5);
            System.out.println( i + ". " + list[i-1]);
        }
        printLine();
    }

    public static void addToList(String command){
        list[sizeOfList] = command;
        printLine();
        printSpaces(5);
        System.out.println("added: " + command );
        printLine();
        sizeOfList++;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        welcomeMessage();
        String command;
        command = sc.nextLine();
        while(!command.equals("bye")){
            switch(command) {
            case "list":
                displayList();
                break;
            default:
                addToList(command);
                break;
            }
            System.out.println();
            command = sc.nextLine();
        }
        byeMessage();
    }
}
