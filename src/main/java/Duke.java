import java.util.Scanner;

public class Duke {
    public static void printList(Task[] list) {
        System.out.println("     Here are the tasks in your list:");
        for(int i = 0; i < list.length; i ++) {
            if(list[i] == null) {
                break;
            }
            System.out.println("     " + (i + 1) + "[" + list[i].getStatusIcon() + "] " + list[i].description);
        }
    }

    public static void main(String[] args) {
        Task[] list = new Task[100];
        String greeting = "    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     It seems like you are needing some help.\n"
                + "    ____________________________________________________________\n";
        int listCount = 0;
        System.out.println(greeting);
        while(true) {
            Scanner command = new Scanner(System.in);
            String echoCommand = command.nextLine();
            String[] words = echoCommand.split(" ");
            System.out.println("    ____________________________________________________________");
            if(echoCommand.equals("bye")) {
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________\n");
                break;
            }
            else if(echoCommand.equals("list")) {
                printList(list);
            }
            else if(words.length == 2 && words[0].equals("done") && (int)Double.parseDouble(words[1])
                    == Double.parseDouble(words[1])) {
                System.out.println("    Nice! I've marked this task as done:");
                int index = Integer.parseInt(words[1]);
                list[index - 1].markAsDone();
                System.out.printf("      [%s] %s\n", list[index - 1].getStatusIcon(), list[index - 1].description);
            }
            else {
                System.out.println("     added: " + echoCommand);
                list[listCount ++] = new Task(echoCommand);
            }
            System.out.println("    ____________________________________________________________\n");
        }
    }
}
