import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        welcomeMessage();

        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<Task>();
        Boolean exitProgram = false;

        while (!exitProgram) {
            String input = sc.nextLine();
            String[] inputArray = input.split(" ", 2);

            System.out.println("____________________________________________________________");

            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                int counter = 0;
                for (Task task : tasks) {
                    counter++;
                    System.out.println(counter + ". " + task);
                }
            } else if (inputArray[0].equals("todo")) {
                ToDo newToDo = new ToDo(inputArray[1]);
                tasks.add(newToDo);
                System.out.println("Got it. I've added this task:");
                System.out.println(newToDo);
                System.out.format("Now you have %d tasks in the list.\n", tasks.size());
            } else if (inputArray[0].equals("deadline")) {
                String[] deadlineInfo = inputArray[1].split("/by ", 2);
                Deadline newDeadline = new Deadline(deadlineInfo[0], deadlineInfo[1]);
                tasks.add(newDeadline);
                System.out.println("Got it. I've added this task:");
                System.out.println(newDeadline);
                System.out.format("Now you have %d tasks in the list.\n", tasks.size());
            } else if (inputArray[0].equals("event")) {
                String[] eventInfo = inputArray[1].split("/at ", 2);
                Event newEvent = new Event(eventInfo[0], eventInfo[1]);
                tasks.add(newEvent);
                System.out.println("Got it. I've added this task:");
                System.out.println(newEvent);
                System.out.format("Now you have %d tasks in the list.\n", tasks.size());
            } else if (inputArray[0].equals("done")) {
                Task doneTask = tasks.get(Integer.parseInt(inputArray[1]) - 1);
                doneTask.isDone = true;
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(doneTask);
            } else if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                exitProgram = true;
            } else {
                Task newTask = new Task(input);
                tasks.add(newTask);
                System.out.println("added: " + newTask);
            }

            System.out.println("____________________________________________________________");

        }

    }

    private static void welcomeMessage() {
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
