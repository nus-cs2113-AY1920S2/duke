
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________\n" +
                        " Hello! I'm Duke\n" +
                        " What can I do for you?\n" +
                "____________________________________________________________");

        String[] list = new String[100];
        int index = 0;

        Scanner scanner = new Scanner(System.in);
        String inputString = "";
        while(!inputString.equals("bye bye")) {
            inputString = scanner.nextLine();
            if(inputString.equals("list")){
                for(int i = 0; i < index; i++) {
                    System.out.print(i+1);
                    System.out.println(". " + list[i]);
                }
            }
            else {
                list[index] = inputString;
                index++;
                System.out.println("  added: " + inputString + "\n" +
                        "____________________________________________________________");
            }

        }
        System.out.println(" Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }
}
