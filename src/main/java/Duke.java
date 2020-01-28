import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String BotName = "E.D.I.T.H.";
        String UserName = "USER";
        String lineDivider = "____________________________________________________________\n";
        System.out.println(lineDivider + " Hello! I'm " + BotName +
                "\n What can I do for you?\n" + lineDivider);
        System.out.print("USER\n  ");
        Scanner sc = new Scanner(System.in);
        String UserCommand = sc.nextLine();
        String ExitCommand = "Bye";
        while (!UserCommand.equals(ExitCommand)){
            System.out.println(lineDivider + BotName + "\n  " + UserCommand + "\n" + lineDivider);
            System.out.print("USER\n  ");
            UserCommand = sc.nextLine();
        }
        System.out.println(lineDivider + " Bye. Hope to see you again soon!\n" + lineDivider);
    }
}
