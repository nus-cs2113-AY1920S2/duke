import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hey there! My name is Duke and i will be your personal assistant.");
        Scanner myScanner = new Scanner(System.in);
        System.out.println("First of all, how may i address you?");
        System.out.println("Please enter your username: ");
        String userName = myScanner.nextLine();
        System.out.println("Hi "+ userName + "!" + " What can i do for you?");
        System.out.println("____________________________________________________________");
        String[] inputArray = new String[100];
        int size = 0;
        while(true){
            String input = myScanner.nextLine();
            if(input.equals("bye")){
                break;
            }
            else if(input.equals("list")){
                for(int i = 0; i < size; i++){
                    System.out.println(i+1 + "." + inputArray[i] +"\n");
                }
            }
            else{
                inputArray[size++] = input;
                System.out.println("Storing " + input);
                System.out.println("Please wait for a moment...");
            }
        }
        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println("____________________________________________________________");
    }
}