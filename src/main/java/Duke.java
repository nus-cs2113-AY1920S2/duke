import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "######  #     # ######  #     #\n"
                + "#     # #     # #     # #     #\n"
                + "#     # #     # #     # #     #\n"
                + "######  #     # ######  #     #\n"
                + "#       #     # #       #     #\n"
                + "#       #     # #       #     #\n"
                + "#        #####  #        #####\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("What can I do for you?");

        Scanner temp = new Scanner(System.in);
        String cmd = temp.nextLine();

        while (!cmd.equals("bye")) {
            lineBreak();
            System.out.println("\t"+cmd);
            lineBreak();
            System.out.println();
            cmd = temp.nextLine();

        }
        lineBreak();
        System.out.println("\tBye. Hope to see you again soon!");
        lineBreak();
    }

    public static void lineBreak() {
        System.out.println("\t____________________________________________________________");
    }
}
