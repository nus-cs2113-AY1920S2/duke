import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String line;
        Scanner in = new Scanner(System.in);
        String[] storage = new String[100];
        int listCount = 0;

        System.out.println("Hello! I'm Isabella" + System.lineSeparator() + "What can I do for you?");
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (!line.equals("list")) {
                storage[listCount] = line;
                System.out.println("added: " + line);
                listCount++;
            } else {
                if (listCount == 0) {
                    System.out.println("There is nothing on the list.");
                } else {
                    for (int i = 0; i < listCount; i++) {
                        System.out.println(i+1 + ". " + storage[i]);
                    }
                }
            }
                line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");




    }
}
