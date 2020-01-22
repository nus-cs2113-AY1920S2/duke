import java.util.ArrayList;
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

        ArrayList<Todo> todos = new ArrayList<Todo>();


        while (!cmd.equals("bye")) {
            lineBreak();
            if (cmd.equals("list")) {
                for (int k = 1; k<=todos.size(); ++k) {
                    System.out.println("\t" + k + ". " + todos.get(k-1).listMe());
                }
            }
            else if (cmd.contains("done")){
                int option = Integer.parseInt(cmd.substring(cmd.length()-1));
                todos.get(option-1).setDone();
            }
            else {
                System.out.println("\tadded: "+cmd);
                todos.add(new Todo(cmd));
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
