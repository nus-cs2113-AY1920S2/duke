import java.util.Scanner;

public class Duke {
    public static final int CAPACITY = 100;

    public static void main(String[] args) {
        printWelcomeMessage();

        Task[] tasks = new Task[CAPACITY];
        int counter = 0;

        while(true) {
            String line;
            Scanner input = new Scanner(System.in);
            line = input.nextLine();

            if (line.equalsIgnoreCase("bye")) {
                printByeMessage();
                break;
            } else if (line.equalsIgnoreCase("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < counter; i++){
                    System.out.println(i+1 + ". " + tasks[i].toString());
                }
                System.out.println("____________________________________________________________");
            } else if (line.startsWith("todo")) {
                line = line.replaceAll("todo ", "");
                Task t = new Todo(line);
                tasks[counter] = t;
                printAcknowledgement(tasks[counter], counter);
                counter++;
            } else if (line.startsWith("deadline")) {
                String[] words = line.split("/");
                String description = words[0].substring(9);
                String by = words[1].substring(3);
                Task d = new Deadline(description, by);
                tasks[counter] = d;
                printAcknowledgement(tasks[counter], counter);
                counter++;
            } else if (line.startsWith("event")) {
                String[] words = line.split("/");
                String description = words[0].substring(6);
                String at = words[1].substring(3);
                Task e = new Event(description, at);
                tasks[counter] = e;
                printAcknowledgement(tasks[counter], counter);
                counter++;
            } else if (line.startsWith("done")) {
                String strNumber = line.substring(5);
                int number = Integer.parseInt(strNumber);
                tasks[number-1].markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done: " + tasks[number-1].description);
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("error");
            }
        }
    }

    private static void printAcknowledgement(Task task, int counter) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task: ");
        System.out.println(task.toString());
        System.out.println("Now you have " + (counter + 1) + " tasks in your list.");
        System.out.println("____________________________________________________________");
    }

    private static void printByeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    private static void printWelcomeMessage() {
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
    }
}
