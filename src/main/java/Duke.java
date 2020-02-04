import java.util.Scanner;
import java.util.ArrayList;
import tasks.*;

public class Duke {
    private static void addTask(String userCmd, ArrayList<Task> taskArrList) {
        String[] splitCmd = userCmd.split("todo ");
        String todoStr = splitCmd[1].trim();

        Task newTask = new Task(TaskType.TODO, todoStr);
        taskArrList.add(newTask);
        System.out.println("Task added: " +todoStr);
    }

    private static void listTasks(ArrayList<Task> taskArrList) {
        if (taskArrList.size() == 0) {
            System.out.println("List is empty!");
        }

        else {
            System.out.println("Current task list: ");
            int index = 1;
            for (Task t : taskArrList) {
                System.out.println("\t" +index +". " +t.toString());
                index++;
            }

        }
    }

    private static void markTaskDone(String userCmd, ArrayList<Task> taskArrList) {
        // syntax: done 2
        String[] splitCmd = userCmd.split(" ");
        int completedTask = 0;

        // error check syntax
        try {
            completedTask = Integer.parseInt(splitCmd[1]);
        }
        catch (Exception e){
            System.out.println("Incorrect syntax! Try again");
            return;
        }

        if (completedTask > taskArrList.size() ) {
            System.out.println("Task not found!");
        }
        else {
            int completedIndex = completedTask-1;
            Task t = taskArrList.get(completedIndex);
            //todo: need to check if already completed?
            t.setDone();

            System.out.println("I've marked this task as done:");
            System.out.println("\t"+t.toString());
        }
    }

    private static void addDeadline(String userCmd, ArrayList<Task> taskArrList) {
        // Syntax: deadline return book /by Sunday
        String[] splitCmd = userCmd.split("/by ");
        String deadlineStr = splitCmd[1].trim();

        String[] splitName = splitCmd[0].split("deadline ");
        String taskStr = splitName[1].trim();

        Deadline newDeadline = new Deadline(TaskType.DEADLINE, taskStr, deadlineStr);
        taskArrList.add(newDeadline);

        System.out.println("Got it. I've added this task: ");
        System.out.println("\t" +newDeadline.toString() );
    }

    private static void addEvent(String userCmd, ArrayList<Task> taskArrList) {
        // Syntax: deadline return book /by Sunday
        String[] splitCmd = userCmd.split("/at ");
        String dateStr = splitCmd[1].trim();

        String[] splitName = splitCmd[0].split("event ");
        String eventStr = splitName[1].trim();

        TaskEvent newEvent = new TaskEvent(TaskType.EVENT, eventStr, dateStr);

        System.out.println("Got it. I've added this event: ");
        System.out.println("\t" +newEvent.toString() );
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);

        boolean continueRun = true;
        String userCmd = "";
        ArrayList<Task> taskArrList = new ArrayList<>();

        while (continueRun) {
            System.out.println("==========================");
            System.out.println("How can I help you?");
            userCmd = sc.nextLine();

            // end program
            if (userCmd.toLowerCase().equals("bye") ) {
                System.out.println("Bye. Hope to see you again!");
                continueRun = false;
                break;
            }
            // List commands -->not updated
            else if (userCmd.toLowerCase().equals("list") ) {
                listTasks(taskArrList);
            }
            // Mark task as done
            else if (userCmd.contains("done") ) {
                markTaskDone(userCmd, taskArrList);
            }
            // Add todos (normal tasks)
            else if (userCmd.contains("todo") ) {
                addTask(userCmd, taskArrList);
            }
            // Add task 'deadline'
            else if (userCmd.contains("deadline") ) {
                addDeadline(userCmd, taskArrList);
            }
            else if (userCmd.contains("event") ) {
                addEvent(userCmd, taskArrList);
            }
            else {
                String helpMsg = "Wrong syntax!\n\n";
                helpMsg += "Here is a list of things you can do: \n";
                helpMsg += "\ttodo: tasks without a date/time (syntax: todo buy food\n";
                helpMsg += "\tlist: list your current tasks (syntax: list)\n";
                helpMsg += "\tdeadline: tasks that need to be done by a date/time (syntax: deadline buy food /by Sunday\n";
                helpMsg += "\tevent: tasks that start/end by a specific time (syntax: event food fare /at Mon 2-4pm";

                System.out.println(helpMsg);
            }
        }
    }

}
