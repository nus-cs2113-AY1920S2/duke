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

        String[] data = new String[100];
        Boolean[] done = new Boolean[100];
        int i = 0;

        while (!cmd.equals("bye")) {
            lineBreak();
            if (cmd.equals("list")) {
                int j = 1;
                for (int k = 0; k<i; ++k) {
                    String stat = done[k] ? "[✓] " : "[✗] ";
                    System.out.println("\t" + j + ". " + stat + data[k]);
                    j++;
                }
            }
            else if (cmd.contains("done")){
                int option = Integer.parseInt(cmd.substring(cmd.length()-1));
                done[option-1] = true;
                System.out.println("\tNice! I've marked this task as done:");
                System.out.println("\t" + "[✓] " + data[option-1]);
            }
            else {
                System.out.println("\tadded: "+cmd);
                done[i] = false;
                data[i++] = cmd;
            }
            
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
