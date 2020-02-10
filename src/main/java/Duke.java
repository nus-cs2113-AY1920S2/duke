import java.util.Scanner;

public class Duke {
    private  static Task [] tasks = new Task[100];
    private  static int size = 0;

    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        String userName = GreetingsAndFunctions(myScanner); //Greetings and list of Functions

        boolean flag = true; //Boolean flag for while loop
        while(flag == true){
            String function = myScanner.next();
            String line = myScanner.nextLine();

            switch (function) {
                case "todo":
                    addtask(new Todo(line));
                    printAddedTask("New To-Do Task added:");
                    break;

                case "deadline":
                    String [] description = line.split("/");
                    String [] deadLine = description[1].split("by ");
                    addtask(new Deadline(description[0], deadLine[1]));
                    printAddedTask("New Task with deadline added:");
                    break;

                case "event":
                    String [] eventName = line.split("/");
                    String [] event = eventName[1].split("at ");
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
                        System.out.println(i+1 + "." + tasks[i]);
                    }
                    System.out.println("____________________________________________________________");
                    break;

                case "done":
                    String l = line.replace(" ","");
                    int taskNumber = Integer.parseInt(l);
                    tasks[taskNumber-1].markAsDone(tasks[taskNumber-1]);
                    System.out.println("____________________________________________________________");
                    System.out.println("Great job! I've marked this task as done in your planner:");
                    System.out.println(tasks[taskNumber-1]);
                    System.out.println("____________________________________________________________");
                    break;

                default:
                    System.out.println("Please key in a valid function"); //loop till valid function entered
                    break;
            }
        }
    }

    // method for printing the last type of task entered
    public static void printAddedTask(String s) {
        System.out.println("____________________________________________________________");
        System.out.println(s);
        System.out.println(tasks[size - 1]);                     //size-1 to get index value of current task
        System.out.println("Total number of tasks in the list:  " + size);
        System.out.println("____________________________________________________________");
    }

    // method for greeting and displaying of all available functions
    public static String GreetingsAndFunctions(Scanner myScanner) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hey there! My name is Duke and i will be your personal assistant.");
        System.out.println("First of all, how may i address you?");
        System.out.println("Please enter your username: ");
        String userName = myScanner.nextLine();
        System.out.println("Hi "+ userName + "!" + " What can i do for you?");
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
    public static void addtask(Task description){
        tasks[size++] = description;
    }
}