package taskManager;
import java.util.Scanner;
import exceptionHandler.inputValidation;
public class Duke {
    private static int MAXIMUM_TASKS = 100;

    public static void printTaskList(Task[] taskList, int counter){
        for (int i = 0; i < counter; i++) {
            System.out.println(i + 1 + ". " + taskList[i].printObject());
        }
    }

    public static void markTaskAsDone(Task[] taskList, String cmd){
        int index = Integer.parseInt(cmd.substring(cmd.length() - 1)) - 1;
        taskList[index].setDone();
        System.out.println("Nice! I've marked this task as done: " + taskList[index].printObject());
    }

    public static Todo createToDo(String cmd, int counter) {
        Todo item = new Todo(cmd.substring(5));
        System.out.println("Got it. I've added this task: " + item.printObject());
        System.out.println("Now you have " + (counter+1) +" tasks in the list.");
        return item;
    }

    public static Event createEvent(String cmd, int counter) {
        int indexOfAt = cmd.indexOf("/at");
        String eventDescription = cmd.substring(6, indexOfAt-1);
        String eventDate = cmd.substring(indexOfAt+4);
        Event item = new Event(eventDescription, eventDate);
        System.out.println("Got it. I've added this task: " + item.printObject());
        System.out.println("Now you have " + (counter+1) + " tasks in the list.");
        return item;
    }

    public static Deadline createDeadline(String cmd, int counter) {
        int indexOfBy = cmd.indexOf("/by");
        String deadlineDesc = cmd.substring(9, indexOfBy-1);
        String deadlineDate = cmd.substring(indexOfBy+4);
        Deadline item = new Deadline(deadlineDesc, deadlineDate);
        System.out.println("Got it. I've added this task: " + item.printObject());
        System.out.println("Now you have "+ (counter+1) +" tasks in the list.");
        return item;
    }

    private static String cmdTypeIdentifier(String cmd, int taskCount) {
        inputValidation exception = new inputValidation();
        if (cmd.equalsIgnoreCase("list")){
            return "LIST";
        } else if (cmd.substring(0,4).equalsIgnoreCase("done")) {
            if (exception.validatingDone(cmd, taskCount)) {
                return "DONE";
            }
        } else if (cmd.substring(0,4).equalsIgnoreCase("todo")) {
            if (exception.validatingTodo(cmd)) {
                return "TODO";
            }
        } else if (cmd.substring(0,5).equalsIgnoreCase("event")) {
            if (exception.validatingEventDeadline(cmd)) {
                return "EVENT";
            }
        } else if (cmd.substring(0,8).equalsIgnoreCase("deadline")) {
            if (exception.validatingEventDeadline(cmd)) {
                return "DEADLINE";
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___| ";
        System.out.println(logo + " says hello :)");
        System.out.println("\nWhat can i do for you?");
        Task[] taskList = new Task[MAXIMUM_TASKS];
        int counter = 0;
        Boolean instr = false;
        Scanner myObj = new Scanner(System.in);
        String cmd;
        cmd = myObj.nextLine();
        while (!cmd.equalsIgnoreCase("bye")) {
            String cmdType = cmdTypeIdentifier(cmd, counter);
            if (cmdType != null) {
                switch (cmdType) {
                case "LIST":
                    printTaskList(taskList, counter);
                    break;
                case "DONE":
                    markTaskAsDone(taskList, cmd);
                    break;
                case "TODO":
                    taskList[counter] = createToDo(cmd, counter);
                    counter++;
                    break;
                case "EVENT":
                    taskList[counter] = createEvent(cmd, counter);
                    counter++;
                    break;
                case "DEADLINE":
                    taskList[counter] = createDeadline(cmd, counter);
                    counter++;
                    break;
                default:
                    System.out.printf("error :(");
                    break;
                }
            }
            cmd = myObj.nextLine();
        }
        System.out.println("Bye bye! Talk to me again soon!");
    }
}
