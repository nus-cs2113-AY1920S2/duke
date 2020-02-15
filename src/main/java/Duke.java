import java.lang.NullPointerException;
import java.util.Scanner;
import Duke.*;
import Exceptions.*;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static int size = 0;

    public static void main(String[] args)  {

        Scanner myScanner = new Scanner(System.in);
        String userName = GreetingsAndFunctions(myScanner); //Greetings and list of Functions
        boolean flag = true; //Boolean flag for while loop
        String function = null;
        while (flag == true) {

            try {
                function = myScanner.next();
                String line = myScanner.nextLine();

                switch (function) {
                    case "todo":
                        if(line.equals("")){
                            throw new MissingDescriptionException("☹ OOPS!!! The todo description cannot be empty!!");
                        }
                        addtask(new Todo(line));
                        printAddedTask("New To-Do Duke.Duke.Task added:");
                        break;

                    case "deadline":
                        String[] description = line.split("/");
                        if(description[0].equals("")){
                            throw new MissingDescriptionException("☹ OOPS!!! The deadline description cannot be empty!!");
                        }
                        String[] deadLine = description[1].split("by ");
                        if(description[0] == null){
                            throw new ArrayIndexOutOfBoundsException();
                        }
                        addtask(new Deadline(description[0], deadLine[1]));
                        printAddedTask("New Duke.Duke.Task with deadline added:");
                        break;

                    case "event":
                        String[] eventName = line.split("/");
                        if(eventName[0].equals("")){
                            throw new MissingDescriptionException("☹ OOPS!!! The event description cannot be empty!!");
                        }
                        String[] event = eventName[1].split("at ");
                        if(event[0].equals(null)){
                            throw new ArrayIndexOutOfBoundsException();
                        }
                        addtask(new Events(eventName[0], event[1]));
                        printAddedTask("New event added:");
                        break;

                    case "bye":
                        System.out.println("Bye " + userName + "! Hope to see you again soon!");
                        System.out.println("____________________________________________________________");
                        flag = false;
                        break;

                    case "list":
                        System.out.println("____________________________________________________________");
                        System.out.println("Here are your task(s) currently in your planner:");
                        for (int i = 0; i < size; i++) {
                            System.out.println(i + 1 + "." + tasks.get(i));
                        }
                        System.out.println("____________________________________________________________");
                        break;

                    case "done":
                        String l = line.replace(" ", "");
                        if(l == ""){
                            throw new IllegalArgumentException();
                        }
                        int taskNumber = Integer.parseInt(l);
                        tasks.get(taskNumber - 1).markAsDone(tasks.get(taskNumber - 1));
                        if(tasks.get(0) == null){
//                            if(tasks[0] == null){
                                throw new NullPointerException();
                        }
                        System.out.println("____________________________________________________________");
                        System.out.println("Great job! I've marked this task as done in your planner:");
                        System.out.println(tasks.get(taskNumber - 1));
//                        System.out.println(tasks[taskNumber - 1]);
                        System.out.println("____________________________________________________________");
                        break;

                    default:
                        System.out.println("Please key in a valid function"); //loop till valid function entered
                        break;
                }
            } catch (MissingDescriptionException e) {
               e.printDescr();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("☹ OOPS! Error storing date and time!! Please try again.");
            } catch (NullPointerException e) {
                System.out.println("☹ OH NO! task number is missing!! Please try storing a task first.");
            } catch (IllegalArgumentException e) {
                System.out.println("☹ OOPS! Missing command! Please try command: done [task number] ");
            }
        }
    }

    // method for printing the last type of task entered
    public static void printAddedTask(String s) {
        System.out.println("____________________________________________________________");
        System.out.println(s);
        System.out.println(tasks.get(size - 1));                     //size-1 to get index value of current task
        System.out.println("Total number of tasks in the list:  " + size);
        System.out.println("____________________________________________________________");
    }

    // method for greeting and displaying of all available functions
    public static String GreetingsAndFunctions(Scanner myScanner) {

            String logo = " __     __ __________ __     __ _________       _________       ___        _________  __     __ \n"
                         + "|  |   |  |   ____   |  |   |  |   ___   |     |   ___   \\     /   \\      |   ___   \\|  |   |  |\n"
                         + "|  |   |  |  |    |  |  |   |  |  |   |  |     |  |   |  |    /  _  \\     |  |   |  ||  |   |  |\n"
                         + "|  |___|  |  |    |  |  |   |  |  |___|  |     |  |___|  |   /  /_\\  \\    |  |___|  ||  |___|  |\n"
                         + "|______   |  |    |  |  |   |  |   ___   |     |   ___   |  /  _____  \\   |   ___   ||______   |\n"
                         + "       |  |  |    |  |  |   |  |  |   \\  \\     |  |   |  | /  /     \\  \\  |  |   |  |       |  |\n"
                         + " ______|  |  |____|  |  |___|  |  |    \\  \\    |  |___|  |/  /       \\  \\ |  |___|  | ______|  |\n"
                         + "|_________|__________|_________|__|     \\__\\   |_________/__/         \\__\\|_________/|_________|\n";

            System.out.println(logo);
            System.out.println("____________________________________________________________");
            System.out.println("Hey there! I am AI Scheduler, YOURBABY");
            System.out.println("Please enter your username: ");
            String userName = myScanner.nextLine();
            System.out.println("Hi " + userName + "!" + " What can i do for you?");
            System.out.println(" ___________________________________________________________________________________________________________");
            System.out.println("|  Functions:  |                 Descriptions:                      |               Example:                |");
            System.out.println("|______________|____________________________________________________|_______________________________________|");
            System.out.println("|   todo       |                Create a To-do task                 | (eg. todo borrow books)               |");
            System.out.println("|   deadline   |                Create a task with a deadline       | (eg. deadline bathe /by 9PM)          |");
            System.out.println("|   event      |                Create an event task                | (eg. event Meeting /at Library, 12PM) |");
            System.out.println("|   list       |                List all the task in your planner   |                                       |");
            System.out.println("|   done       |                Mark a task as completed            |                                       |");
            System.out.println("|   bye        |                Exit Planner                        |                                       |");
            System.out.println("|______________|____________________________________________________|_______________________________________|");
            System.out.println("Please key in your command: ");
            return userName;
    }

    //method for adding adding newly created task into tasks array
    public static void addtask(Task description) {
        tasks.add(description);
        size++;
    }
}

