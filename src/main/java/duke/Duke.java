package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke implements Logo {
    public static void main(String[] args) throws DukeException, IOException {

        Data data = new Data();

        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        Scanner input = new Scanner(System.in);
        String cmd = input.nextLine();


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
                System.out.println("  Nice! I've marked this task as done:");
                System.out.println("  " + "[✓] " + data.getDescription(option-1));

            } else {
                try {
                    Data.newTask(cmd);
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
        data.saveToFile();
    }

    public final static void lineBreak() {
        System.out.println("  ____________________________________________________________");
    }
}

// forgot to do branching and merging for exceptions