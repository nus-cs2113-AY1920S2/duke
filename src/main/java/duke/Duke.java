package duke;

import java.util.Scanner;

public class Duke implements Logo {
    public static void main(String[] args) throws DukeException {

        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        Scanner input = new Scanner(System.in);
        String cmd = input.nextLine();
        Data data = new Data();

        while (!cmd.equals("bye")) {
            lineBreak();

            if (cmd.equals("list")) {
                System.out.println("  Here are the tasks in your list:");
                for (int k = 1; k <= data.getSize(); ++k) {
                    System.out.println("    " + k + ". " + data.printItem(k-1));
                }

            } else if (cmd.contains("done")){
                int option = Integer.parseInt(cmd.substring(cmd.length()-1));
                data.setDone(option-1);

            } else {
                try {
                    data.taskCmd(cmd);
                } catch(DukeException e) {
                    System.out.println(e);
                }
            }
            
            lineBreak();
            System.out.println();
            cmd = input.nextLine();

        }
        lineBreak();
        System.out.println("  Bye. Hope to see you again soon!");
        lineBreak();
    }

    public final static void lineBreak() {
        System.out.println("  ____________________________________________________________");
    }
}

// forgot to do branching and merging for exceptions