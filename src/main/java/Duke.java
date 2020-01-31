import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static Task getTheTask(ArrayList<Task> List, int j)
    {
        return List.get(j);
    }

    public static void addTask(String description, ArrayList<Task> List)
    {
        Task t = new Task(description);
        List.add(t);
    }

    public static void updateCommand(Task task, ArrayList<Task> List, int count)
    {
        List.set(count,task);
    }

    public static void main(String[] args) {
        System.out.println("Hello ! I'm Duke\n");
        System.out.println("What can I do for you ? \n");

        String command;
        ArrayList<Task> list = new ArrayList<>();
        Scanner in = new Scanner (System.in);
        String option_1 = "bye";
        String option_2 = "list";
        String option_3 = "done";

        while (true)
        {
            // cannot use in.nextLine() since it will treat (done (taskNumber)) as one string
            // thus will not lead us to the done statement
            command = in.next();

            if (command.equals(option_1))
            {
                System.out.println("------------------------");
                System.out.println("Bye, see you again soon!");
                System.out.println("------------------------");
                System.exit(0);
            }
            else if (command.equals(option_2))
            {
                System.out.println("------------------------");
                int count = 1;
                System.out.println(" Here are the tasks in your list: ");
                for (Task t : list)
                {
                    System.out.print(count);
                    System.out.print(".[");
                    System.out.print(t.getStatusIcon());
                    System.out.print("]");
                    System.out.println(t.description);
                    count++;
                }
                System.out.println("------------------------");
            }
            else if (command.equals(option_3))
            {
                int taskNumber = in.nextInt();
                Task t = getTheTask(list,taskNumber-1);
                t.markAsDone();
                updateCommand(t,list,taskNumber-1);
                System.out.println("------------------------------------------");
                System.out.print("Nice! I've marked this task as done: [");
                System.out.print(t.getStatusIcon());
                System.out.print("]");
                System.out.println(t.description);
                System.out.println("------------------------------------------");
            }
            else
            {
                // if there is no "done", "bye", "list" in the string
                String splitter = in.nextLine();
                // concat() method returns a String with the value of the String passed into the method,
                // appended to the end of the String, used to invoke this method.
                command = command.concat(splitter);
                addTask(command,list);
                Task newAddCommand = getTheTask(list, list.size()-1);
                System.out.println("-------------------------------------");
                System.out.print("Successfully added: ");
                System.out.println(newAddCommand.description);
                System.out.println("--------------------------------------");
            }
        }

        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/

    }
}
