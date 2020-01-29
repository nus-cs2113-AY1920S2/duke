import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner sc = new Scanner(System.in);
        String input;
        List<Task> tasks = new ArrayList<Task>();


        do {
            input = sc.nextLine();
            String[] inputArray = input.split(" ");
            System.out.println("____________________________________________________________");
            if (inputArray[0].equals("list")) {
                int counter = 0;
                System.out.println("Here are the tasks in your list:");
                for (Task task : tasks) {
                    counter++;
                    System.out.println(counter + ".[" + task.getStatusIcon() + "] " + task);
                }
            } else if (inputArray[0].equals("done")) {
                Task doneTask = tasks.get(Integer.parseInt(inputArray[1]) - 1);
                doneTask.isDone = true;
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + doneTask.getStatusIcon() + "] " + doneTask);
            } else {
                Task dummy = new Task(input);
                tasks.add(dummy);
                System.out.println("added: " + dummy);
            }
            System.out.println("____________________________________________________________");
        } while (!input.equals("bye"));


        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

    }
}
