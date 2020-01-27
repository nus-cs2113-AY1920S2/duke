import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        System.out.println("Hey! I'm Jamun");
        System.out.println("How can I help you?");
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            System.out.println("____________________________________");
            System.out.println("    " + command);
            System.out.println("____________________________________");
            command = sc.nextLine();
        }
        System.out.println("____________________________________");
        System.out.println("    Bye! See ya next time :)");
        System.out.println("____________________________________");
    }

}

