import java.util.Scanner;

public class Duke {
    public static String[] instruction(String dukeCommand, String[] Actions, int actionCount){
        System.out.println("____________________________________________________________");
        if (dukeCommand.equals("greet")) {
            System.out.println("Hello! I'm Duke\n" +
                    " What can I do for you?");
        }else if (dukeCommand.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }else if (dukeCommand.equals("list")) {
            //print out everything in the list
            int count = 1;
            for (String Action: Actions) {
                if (count <= actionCount) {
                    System.out.println(count + ": " + Action);
                    count++;
                } else {
                    break;
                }
            }
        }
        else{
            System.out.println(dukeCommand + "\n");
            Actions[actionCount] = dukeCommand;
        }
        /*else{
            System.out.println("____________________________________________________________");
            System.out.println("Duke cannot understand your command.\n");
            System.out.println("____________________________________________________________");
        }*/
        System.out.println("____________________________________________________________");
        return Actions;
    }
    public static void main(String[] args) {

        int actionCount = 0;

        String[] Actions = new String[100];

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        instruction("greet",Actions,actionCount);
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            instruction(line, Actions,actionCount);
            if(!line.equals("list") && !line.equals("greet")) {
                actionCount++;
            }
            line = in.nextLine();
        }
        instruction("bye",Actions,actionCount);

    }
}
