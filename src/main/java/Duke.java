import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String input;
        List<String> texts = new ArrayList<String>();
        int counter = 0;

        do {
            input = sc.nextLine();
            if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                for (String text : texts) {
                    System.out.println(text);
                }
                System.out.println("____________________________________________________________");
            } else {
                counter++;
                texts.add(counter + ". " + input);
                System.out.println("____________________________________________________________");
                System.out.println("added: " + input);
                System.out.println("____________________________________________________________");
            }
        } while (!input.equals("bye"));


        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

    }
}
