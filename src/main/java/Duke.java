import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    public static void main(String[] args) {
        String BotName = "E.D.I.T.H.";
        String UserName = "USER";
        String lineDivider = "____________________________________________________________\n";
        System.out.println(lineDivider + " Hello! I'm " + BotName +
                "\n What can I do for you?\n" + lineDivider);

        Scanner sc = new Scanner(System.in);
        String UserCommand;
        TaskManager Shop = new TaskManager();
        boolean run = true;
        while (run) {
            System.out.print("USER\n  ");
            UserCommand = sc.nextLine();
            switch (UserCommand) {

                case "List": {
                    System.out.println(lineDivider + "\nHere are the tasks in your list:");
                    Shop.getTask();
                    System.out.println(lineDivider);
                    break;
                }

                case "Done": {
                    System.out.println("Item to be marked? ");
                    String cmd = sc.nextLine();
                    int index = Integer.parseInt(cmd);
                    Shop.markTask(index - 1);
                    System.out.println("\nNice! I've marked this task as done:  \n  [YES]  " + Shop.getTaskItem(index - 1) + "\n" + lineDivider);
                    break;
                }

                case "Add": {
                    System.out.println("Item to be Added? ");
                    String itemName = sc.nextLine();
                    Shop.addTask(itemName);
                    System.out.println(lineDivider + BotName + "\n  Added: " + itemName + "\n" + lineDivider);
                    break;
                }

                case "Check": {
                    System.out.println("Item to be Checked? ");
                    String itemName = sc.nextLine();
                    System.out.println(lineDivider + BotName + "\n Checked:" + itemName + "\n" + lineDivider);
                    System.out.print("USER\n  ");
                    break;
                }

                case "Bye": {
                    run = false;
                    break;
                }

                default: {
                    System.out.println(lineDivider + BotName + "\n  Echoed: " + UserCommand + "\n" + lineDivider);
                    break;
                }
            }

        }
        System.out.println(lineDivider + " Bye. Hope to see you again soon!\n" + lineDivider);
    }


}
