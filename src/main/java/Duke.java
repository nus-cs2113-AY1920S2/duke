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
        String[] listOfItems = new String[100];
        int sizeOfList = 0;
        while (inputCommand.equals("bye") == false) {

            if (inputCommand.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < sizeOfList; i++) {
                    int k = i + 1;
                    System.out.println(" " + k + ". " + listOfItems[i]);
                }
                System.out.println("____________________________________________________________");

            } else {
                listOfItems[sizeOfList] = inputCommand;
                System.out.println("____________________________________________________________");
                System.out.println(" added: " + inputCommand);
                System.out.println("____________________________________________________________");
                sizeOfList++;

            }

            inputCommand = scanner.nextLine();
        }

        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
