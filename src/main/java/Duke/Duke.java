package Duke;

import Exceptions.NoParameterException;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

    /*
    List of exceptions handled:

    1. General commands
        a. No recognised command given
        b. No follow up parameters in command

    2. Done command
        a. Out of range
        b. Not integer

     */


public class Duke {

    public static final int LENGTH_DEADLINE = 9;
    public static final int LENGTH_EVENT = 6;
    public static final int LENGTH_TODO = 5;
    public static final int SIZE_DONE_COMMAND = 2;

    public static void getDateTime() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println(formattedDate);
    }

    public static void completeTask(ArrayList<Task> tasks, int taskIndex) {
        taskIndex--; // index starts from 0, unlike listing number
        if ( (taskIndex < tasks.size()) || (taskIndex > 0)) { // check if out of bounce
            Task currentTask = tasks.get(taskIndex);
            if (currentTask.getStatus()) { // check if already completed
                System.out.println("Duke.Task already completed!\n");
            } else {
                currentTask.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println( "["+ currentTask.getTaskType() + "][" + currentTask.getStatusIcon() + "] " + currentTask.getDescription() + "\n");
            }
        } else {
            System.out.println("Error: No such index in use\n");
        }
    }

    public static void printList(ArrayList<Task> tasks) {
        int count = 1;
        System.out.println("Listing tasks below:");

        if (tasks.isEmpty()) {
            System.out.println("No tasks at the moment!");
        } else {
            for (Task currentTask : tasks) {
                currentTask.printListDetails(count);
                count++;
            }
        }
        System.out.println("");
    }

    public static void printHelp() {
        System.out.println("Commands: ");
        System.out.println("List: lists all recorded tasks \nusage: list\n");
        System.out.println("Done: mark task as completed \nusage: done <task number>\n");
        System.out.println("Todo: Tasks without date/time \nUsage: todo <task> \n(Avoid using other keywords as the first word)\n");
        System.out.println("Duke.Event: Duke.Event including date/time \nUsage: event <task> /<date> \n(Avoid using other keywords as the first word)\n");
        System.out.println("Duke.Deadline: Tasks including date/time \nUsage: deadline <task> /<date> \n(Avoid using other keywords as the first word)\n");
        System.out.println("");
    }

    private static void printWelcomeMessage() {
        String logo =   "  ,--,       ,---,   .--.--.       ,---,\n"
                + ",--.'|    ,`--.' |  /  /    '.    '  .' \\\n"
                + "|  | :    |   :  : |  :  /`. /   /  ;    '.\n"
                + ":  : '    :   |  ' ;  |  |--`   :  :       \\\n"
                + "|  ' |    |   :  | |  :  ;_     :  |   /\\   \\\n"
                + "'  | |    '   '  ;  \\  \\    `.  |  :  ' ;.   :\n"
                + "|  | :    |   |  |   `----.   \\ |  |  ;/  \\   \\\n"
                + "'  : |__  '   :  ;   __ \\  \\  | '  :  | \\  \\ ,'\n"
                + "|  | '.'| |   |  '  /  /`--'  / |  |  '  '--'\n"
                + ";  :    ; '   :  | '--'.     /  |  :  :\n"
                + "|  ,   /  ;   |.'    `--'---'   |  | ,'\n"
                + "---`-'   '---'                 `--''\n";

        System.out.println("\n" + logo + "\nYour Lifestyle Scheduling Assistant\n");
        System.out.println("type \"help\" for list of commands");
        System.out.println("____________________________________________________________\n");
        System.out.println("Everyday is a sunny day!");
        /*
        System.out.println("____________________________________________________________\n\nCurrent time: ");
        getDateTime(); // due to testing purposes, will fail when comparing timestamps
        */
        System.out.println("____________________________________________________________");
    }

    public static void addDeadline(ArrayList<Task> tasks, String taskDescription, int taskCounter, int wordLength) throws NoParameterException {

            if (wordLength <= 1) { // empty parameter
                taskCounter--;
                throw new NoParameterException();
            }

            try {
                String itemName = taskDescription.substring(LENGTH_DEADLINE);
                String[] words = itemName.split("/");
                for (int i = 0; i < words.length; i++) {
                    if (words[i].isBlank()){
                        throw new NoParameterException();
                    }
                }
                System.out.println("Len: " + words.length);
                Task newTask = new Deadline(words[0].trim(), words[1].trim());
                tasks.add(newTask);
                newTask.printAddDetails(taskCounter);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please input the date using the specified format");
                taskCounter--;
            }




    }

    public static void addEvent(ArrayList<Task> tasks, String taskDescription, int taskCounter) {
        try {
            String itemName = taskDescription.substring(LENGTH_EVENT);
            String[] words = itemName.split("/");
            Task newTask = new Event(words[0].trim(), words[1].trim());
            tasks.add(newTask);
            newTask.printAddDetails(taskCounter);
        } catch (Exception e) {
            System.out.println("Please input the date using the specified format");
            taskCounter--;
        }
    }

    public static void addTodo(ArrayList<Task> tasks, String taskDescription, int taskCounter) {
        String itemName = taskDescription.substring(LENGTH_TODO);
        Task newTask = new ToDo(itemName.trim());
        tasks.add(newTask);
        newTask.printAddDetails(taskCounter);
    }


    public static void main(String[] args) {

        printWelcomeMessage();

        ArrayList<Task> tasks = new ArrayList<Task>();
        int taskCounter = 0;

        Scanner input = new Scanner(System.in);
        String userCommand = input.nextLine();

        while (!userCommand.equals("bye")){
            String[] words = userCommand.split(" ");
            int wordLength = words.length;
            switch (words[0]) {
            case "list":
                printList(tasks);
                break;
            case "done":
                if (wordLength != SIZE_DONE_COMMAND) {
                    System.out.println("Wrong format for command \"done\"");
                    break;
                }
                try {
                    int index = Integer.parseInt(words[1]);
                    completeTask(tasks, index);
                } catch (NumberFormatException e) {
                    System.out.println("Please input a valid number\n");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Duke.Task not found, please try again");
                }
                break;
            case "help":
                printHelp();
                break;
            case "todo":
                taskCounter++;
                addTodo(tasks, userCommand, taskCounter);
                break;
            case "event":
                taskCounter++;
                addEvent(tasks, userCommand, taskCounter);
                break;
            case "deadline":
                taskCounter++;
                try {
                    addDeadline(tasks, userCommand, taskCounter, wordLength);
                } catch (NoParameterException e){
                    System.out.println("No Parameters detected!\n");
                }
                break;
            default:
                System.out.println("Please add the task type\n");
            }
            // end of current listening loop, preparing next command
            userCommand = input.nextLine();
        }
        System.out.println("LISA: Bye, hope to see you again!");
    }
}
