import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "Shannon";
        System.out.println("Hello from " + logo);

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Shannon" + System.lineSeparator() + " What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String inputCommand = scanner.nextLine();
        Task[] listOfTasks = new Task[100];
        int sizeOfList = 0;
        while (inputCommand.equals("bye") == false) {

            if (inputCommand.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < sizeOfList; i++) {
                    int k = i + 1;
                    System.out.println(" " + k + ".[" + listOfTasks[i].getStatusIcon() + "] " + listOfTasks[i].description);
                }
                System.out.println("____________________________________________________________");

            } else if (inputCommand.contains("done ") == true) {
                System.out.println("____________________________________________________________");
                System.out.println(" Nice! I've marked this task as done:");
                String indexInString = inputCommand.substring(5);
                int index = Integer.parseInt(indexInString) - 1;
                listOfTasks[index].markAsDone();
                System.out.println("   [" + listOfTasks[index].getStatusIcon() + "] " + listOfTasks[index].description);
                System.out.println("____________________________________________________________");

            } else {
                Task newTask = new Task(inputCommand);
                listOfTasks[sizeOfList] = newTask;
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
