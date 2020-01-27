import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner read=new Scanner(System.in);
        System.out.println("------------***------------");
        System.out.println("Hello! I'm Momo");
        System.out.println("What can I do for you?");
        System.out.println("------------***------------");
        String command=read.nextLine();
        while(!command.equals("bye")){
            System.out.println("------------***------------");
            switch(command) {
                case "list":
                    System.out.println("list");
                    break;
                case "blah":
                    System.out.println("blah");
                    break;
                default:
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println("------------***------------");
            System.out.println("Do you have any other command? ");
            command=read.nextLine();
        }
        System.out.println("------------***------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("------------***------------");
    }
}
