import java.util.Scanner;

public class Duke {
    public static void printList(String[] list) {
        for(int i = 0; i < list.length; i ++) {
            if(list[i] == null) {
                break;
            }
            System.out.println("     " + (i + 1) + ". " + list[i]);
        }
    }

    public static void main(String[] args) {
        String[] list = new String[100];
        String greeting = "    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     It seems like you are needing some help.\n"
                + "    ____________________________________________________________\n";
        int listCount = 0;
        System.out.println(greeting);
        while(true) {
            Scanner command = new Scanner(System.in);
            String echoCommand = command.nextLine();
            System.out.println("    ____________________________________________________________");
            if(echoCommand.equals("bye")) {
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________\n");
                break;
            }
            else if(echoCommand.equals("list")) {
                printList(list);
            }
            else {
                System.out.println("     added: " + echoCommand);
                list[listCount ++] = echoCommand;
            }
            System.out.println("    ____________________________________________________________\n");
        }
    }
}
