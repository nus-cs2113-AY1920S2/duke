import java.util.Scanner;
import java.util.Arrays;

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
        String StrArray[] = new String[100];
        int StrArrayIndex = 0;
        while (!UserCommand.equals(ExitCommand)){
            if (UserCommand.equals("List")|| UserCommand.equals("list")){
                System.out.println(lineDivider + "\nHere's your list!");
                for (int i = 0; i < StrArrayIndex; i++){
                    System.out.println( (i+1) + ". " + StrArray[i]);
                }
                System.out.println(lineDivider);
            } else {
                StrArray[StrArrayIndex] = UserCommand;
                StrArrayIndex++;
                System.out.println(lineDivider + BotName + "\n  Added: " + UserCommand + "\n" + lineDivider);
                System.out.print("USER\n  ");

            }
            UserCommand = sc.nextLine();

        }
        System.out.println(lineDivider + " Bye. Hope to see you again soon!\n" + lineDivider);
    }


}
