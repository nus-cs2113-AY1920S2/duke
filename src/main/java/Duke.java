import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("    ____________________________________________________________"+"\n"+
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");
        String command;
        do{
            command = sc.next();
            System.out.println("    ____________________________________________________________");
            if(command.equals("bye")){
                System.out.println("     Bye. Hope to see you again soon!");
            }
            else
            {
                System.out.println("     "+command);
            }
            System.out.println("    ____________________________________________________________\n");
        }while(!command.equals("bye"));
    }
}
