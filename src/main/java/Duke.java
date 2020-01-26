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
        String[] toAdd = new String[100];
        int counter = 0;
        do {
            cmd = myObj.nextLine();
            if (!cmd.equals("bye")){
                if (cmd.equals("list")) {
                    for (int i = 0; i < counter; i++) {
                        System.out.println(i+1 + ". " + toAdd[i]);
                    }
                } else {
                    toAdd[counter] = cmd;
                    counter++;
                    System.out.println("added: " + cmd);
                }
            } else {
                System.out.println("Bye bye! Talk to me again soon!");
            }
        } while (!cmd.equals("bye"));
    }
}
