import java.util.Scanner;

public class Duke {

    public static final String COMMAND_CREATE_TODO_TASK = "todo";
    public static final String COMMAND_CREATE_DEADLINE_TASK = "deadline";
    public static final String COMMAND_CREATE_EVENT_TASK = "event";
    public static final String COMMAND_DISPLAY_LIST = "list";
    public static final String COMMAND_END_PROGRAM = "bye";
    public static final String COMMAND_MARK_AS_DONE = "done";
    public static Task[] tasks = new Task[100];
    public static int taskCount = 0, pendingTaskCount = 0;

    public static void main(String[] args) {

        String command, detailsOfTask;
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        loop:  while(true) {
            Scanner input = new Scanner(System.in);
            command = input.next();

            switch(command) {
            case COMMAND_CREATE_DEADLINE_TASK:
                detailsOfTask = input.nextLine();
                if(detailsOfTask == null || detailsOfTask.isEmpty()) {
                    System.out.println("Details of the task cannot be left Empty!");
                } else {
                    createTask(new DeadlineTask(detailsOfTask, taskCount));
                    tasks[taskCount].printCreateMessage(pendingTaskCount);

                }
                break;
            case COMMAND_CREATE_EVENT_TASK:
                detailsOfTask = input.nextLine();
                if(detailsOfTask == null || detailsOfTask.isEmpty()) {
                    System.out.println("Details of the task cannot be left Empty!");
                } else {
                    createTask(new EventTask(detailsOfTask, taskCount));
                    tasks[taskCount].printCreateMessage(pendingTaskCount);
                }
                break;
            case COMMAND_CREATE_TODO_TASK:
                detailsOfTask = input.nextLine();
                if(detailsOfTask == null || detailsOfTask.isEmpty()) {
                    System.out.println("Details of the task cannot be left Empty!");
                } else {
                    createTask(new TodoTask(detailsOfTask, taskCount));
                    tasks[taskCount].printCreateMessage(pendingTaskCount);
                }
                break;
            case COMMAND_DISPLAY_LIST:
                printTaskList(tasks, taskCount, pendingTaskCount);
                break;
            case COMMAND_END_PROGRAM:
                System.out.println("Bye. Hope to see you again soon!");
                break loop;
            case COMMAND_MARK_AS_DONE:
                String number = input.next();
                int doneTaskNumber = Integer.parseInt(number);
                if (tasks[doneTaskNumber].isDone == false) {
                    --pendingTaskCount;
                    tasks[doneTaskNumber].markTaskAsDone(pendingTaskCount);
                } else {
                    System.out.println(tasks[doneTaskNumber].taskName + " is already done!");
                }
                break;
            default:
                System.out.println("I don't understand what you are say! Please enter a valid command!");
                break;
            }
        }
    }

    public static void printTaskList(Task[] tasks, int taskCount, int pendingTaskCount) {
        System.out.println("Here is a list of all your tasks:");
        for (int i = 1; i <= taskCount; ++i) {
            tasks[i].printListMessage();
        }
        System.out.println("Total number of incomplete tasks: " + pendingTaskCount);
    }

    public static void createTask(Task t) {
        ++taskCount;
        ++pendingTaskCount;
        System.out.println(taskCount);
        tasks[taskCount] = t;
    }
}

