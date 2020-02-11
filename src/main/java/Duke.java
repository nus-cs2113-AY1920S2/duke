import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static Command splitCommand(String line) {
        String word;
        String argument;
        try {
            word = line.substring(0, line.indexOf(' '));
            argument = line.substring(line.indexOf(' ') + 1);
        } catch (IndexOutOfBoundsException e) {
            word = line;
            argument = " ";
        }
        return new Command(word, argument);
    }

    public static Task getTheTask(ArrayList<Task> List, int j)
    {
        return List.get(j);
    }


    public static void updateCommand(Task task, ArrayList<Task> List, int count)
    {
        List.set(count,task);
    }

    public static void main(String[] args) {
        System.out.println("Hello ! I'm Duke\n");
        System.out.println("What can I do for you ? \n");

        String command;
        String description;
        ArrayList<Task> list = new ArrayList<>();
        Scanner in = new Scanner (System.in);
        String option_1 = "bye";
        String option_2 = "list";
        String option_3 = "done";
        String option_4 = "todo";
        String option_5 = "event";
        String option_6 = "deadline";

        while (true)
        {
            command = in.nextLine();
            final Command option = splitCommand(command);
            String choice = option.getCommandName();

            if (choice.equals(option_1)) {
                System.out.println("------------------------");
                System.out.println("Bye, see you again soon!");
                System.out.println("------------------------");
                System.exit(0);
            } else if (choice.equals(option_2)) {
                System.out.println("------------------------");
                int count = 1;
                System.out.println(" Here are the tasks in your list: ");
                for (Task t : list) {
                    System.out.print(count);
                    System.out.print(".");
                    System.out.println(t.toString());
                    count++;
                }
                System.out.println("------------------------");
            } else if (choice.equals(option_3)) {
                int taskNumber = Integer.parseInt(String.valueOf(option.getArgs()));
                Task t = getTheTask(list,taskNumber-1);
                t.markAsDone();
                updateCommand(t,list,taskNumber-1);
                System.out.println("------------------------------------------");
                System.out.print("Nice! I've marked this task as done: [");
                System.out.print(t.getStatusIcon());
                System.out.print("]");
                System.out.println(t.description);
                System.out.println("------------------------------------------");
            } else if (choice.equals(option_4)) {
                Task newTask = new ToDos(option.getArgs());
                if ( option.getArgs() == " ")
                {
                    System.out.println("-------------------------------------------");
                    System.out.println("Uh Oh! The description cannot be empty ~");
                    System.out.println("-------------------------------------------");
                } else {
                    list.add(newTask);
                    System.out.println("------------------------");
                    System.out.println("Added : ");
                    System.out.println(newTask.toString());
                    System.out.printf("You have %d tasks in your list ^^ \n" , list.size());
                }

            } else if (choice.equals(option_5)) {
                final int indexOfAtPrefix = option.getArgs().indexOf("/at");
                description = option.getArgs().substring(0, indexOfAtPrefix);
                String Time = option.getArgs().substring(indexOfAtPrefix + 3).trim();
                Task newEvent = new Events(description, Time);
                list.add(newEvent);
                System.out.println("------------------------");
                System.out.println("Added : ");
                System.out.println(newEvent.toString());
                System.out.printf("You have %d tasks in your list ^^ \n" , list.size());
                System.out.println("------------------------");

            } else if (choice.equals(option_6)) {
                final int indexOfByPrefix = option.getArgs().indexOf("/by");
                description = option.getArgs().substring(0, indexOfByPrefix);
                String Date = option.getArgs().substring(indexOfByPrefix + 3).trim();
                Task newDeadline = new Deadlines(description, Date);
                list.add(newDeadline);
                System.out.println("------------------------");
                System.out.println("Added : ");
                System.out.println(newDeadline.toString());
                System.out.printf("You have %d tasks in your list ^^ \n" , list.size());
                System.out.println("------------------------");
            } else {
                // if there is no "done", "bye", "list" in the string
                System.out.println("-------------------------------------------");
                System.out.println("Try again maybe? Choose the right option :) ");
                System.out.println("--------------------------------------------");
            }
        }

    }
}
