import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___| ";
        System.out.println(logo + " says hello :)");
        System.out.println("\nWhat can i do for you?");
        Scanner myObj = new Scanner(System.in);
        String cmd;
        do {
            cmd = myObj.nextLine();
            if (!cmd.equals("bye")){
                System.out.println(cmd);
            } else {
                System.out.println("Bye bye! Talk to me again soon!");
            }
        } while (!cmd.equals("bye"));
    }
}
