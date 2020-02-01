import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "Shannon";
        System.out.println("Hello from " + logo);

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Duke" + System.lineSeparator() + " What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String inputCommand = scanner.nextLine();
        while (inputCommand.equals("bye") == false) {
            System.out.println("____________________________________________________________");
            System.out.println(inputCommand);
            System.out.println("____________________________________________________________");
            inputCommand = scanner.nextLine();
        }

        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
