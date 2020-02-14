package taskManager;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;
import exceptionHandler.inputValidation;
public class Duke {
    private static int MAXIMUM_TASKS = 100;

    public static void printTaskList(ArrayList<Task> taskList){
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + ". " + taskList.get(i).printObject());
        }
    }

    public static void markTaskAsDone(ArrayList<Task> taskList, String cmd){
        int index = Integer.parseInt(cmd.substring(cmd.length() - 1)) - 1;
        taskList.get(index).setDone();
        System.out.println("Nice! I've marked this task as done: " + taskList.get(index).printObject());
    }

    public static Todo createToDo(ArrayList<Task> taskList, String cmd) {
        Todo item = new Todo(cmd.substring(5));
        System.out.println("Got it. I've added this task: " + item.printObject());
        System.out.println("Now you have " + (taskList.size()+1) +" tasks in the list.");
        return item;
    }

    public static Event createEvent(ArrayList<Task> taskList, String cmd) {
        int indexOfAt = cmd.indexOf("/at");
        String eventDescription = cmd.substring(6, indexOfAt-1);
        String eventDate = cmd.substring(indexOfAt+4);
        Event item = new Event(eventDescription, eventDate);
        System.out.println("Got it. I've added this task: " + item.printObject());
        System.out.println("Now you have " + (taskList.size()+1) + " tasks in the list.");
        return item;
    }

    public static Deadline createDeadline(ArrayList<Task> taskList, String cmd) {
        int indexOfBy = cmd.indexOf("/by");
        String deadlineDesc = cmd.substring(9, indexOfBy-1);
        String deadlineDate = cmd.substring(indexOfBy+4);
        Deadline item = new Deadline(deadlineDesc, deadlineDate);
        System.out.println("Got it. I've added this task: " + item.printObject());
        System.out.println("Now you have "+ (taskList.size()+1) +" tasks in the list.");
        return item;
    }

    public static int findObjectToRemove(ArrayList<Task> taskList, String cmd) {
        int indexOfObject = Integer.parseInt(cmd.substring(7)) - 1;
        System.out.println("Noted. I've removed this task: " + taskList.get(indexOfObject).printObject());
        System.out.println("Now you have "+ (taskList.size()-1) +" tasks in the list.");
        return indexOfObject;
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
        } else if (cmd.substring(0,6).equalsIgnoreCase("delete")) {
            return "DELETE";
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
        ArrayList<Task> taskList = new ArrayList<>();
        int counter = 0;
        Boolean instr = false;
        Scanner myObj = new Scanner(System.in);
        String cmd;
        cmd = myObj.nextLine();
        while (!cmd.equalsIgnoreCase("bye")) {
            String cmdType = cmdTypeIdentifier(cmd, taskList.size());
            if (cmdType != null) {
                switch (cmdType) {
                case "LIST":
                    printTaskList(taskList);
                    break;
                case "DONE":
                    markTaskAsDone(taskList, cmd);
                    break;
                case "TODO":
                    taskList.add(createToDo(taskList, cmd));
                    break;
                case "EVENT":
                    taskList.add(createEvent(taskList, cmd));
                    break;
                case "DEADLINE":
                    taskList.add(createDeadline(taskList, cmd));
                    break;
                case "DELETE":
                    taskList.remove(findObjectToRemove(taskList, cmd));
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
