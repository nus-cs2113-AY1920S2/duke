import java.util.Scanner;

public class Duke {
    public static Task[] instruction(String dukeCommand, Task[] Tasks, String commandDescription){
        System.out.println("____________________________________________________________");
        switch(dukeCommand) {
        case "greet":
            System.out.println("Hello! I'm Duke\n" + " What can I do for you?");
            break;
        case "bye":
            System.out.println("Bye. Hope to see you again soon!");
            break;
        case "list":
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < Task.getTotalTask(); i++) {
                    System.out.println(Tasks[i].getTaskNum() + ". " + Tasks[i]);
            }
            break;
        case "done":
            int taskNum = Integer.parseInt(commandDescription) - 1;
            if (taskNum < Task.getTotalTask()) {
                System.out.println("Nice! I've marked this task as done: ");
                Tasks[taskNum].setDone(true);
                System.out.println(Tasks[taskNum]);
            }else{
                System.out.println("Duke cannot find this task");
            }
            break;
        case "todo":
            Todo todo = new Todo(commandDescription,"T");
            System.out.println("Got it. I've added this task:\n " + todo);
            printTotalTask();
            Tasks[Task.getTotalTask() - 1] = todo;
            break;
        case "deadline":
            String[] deadlineDetails = commandDescription.split("/by");
            Deadline deadline = new Deadline(deadlineDetails[0],deadlineDetails[1]);
            System.out.println("Got it. I've added this task:\n " + deadline);
            printTotalTask();
            Tasks[Task.getTotalTask() - 1] = deadline;
            break;
        case "event":
            String[] eventDetails = commandDescription.split("/at");
            Event event = new Event(eventDetails[0],eventDetails[1]);
            System.out.println("Got it. I've added this task:\n " + event);
            printTotalTask();
            Tasks[Task.getTotalTask() - 1] = event;
            break;
        default:
            System.out.println("Duke cannot understand your command.\n");
            break;
        }
        System.out.println("____________________________________________________________");
        return Tasks;
    }

    private static void printTotalTask() {
        if (Task.getTotalTask() < 2){
            System.out.println("Now you have " + Task.getTotalTask() + " task in the list");
        } else {
            System.out.println("Now you have " + Task.getTotalTask() + " tasks in the list");
        }
    }

    public static void main(String[] args) {

        Task[] Tasks = new Task[100];

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        instruction("greet",Tasks,"");
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            String[] requests = line.split(" ",2);
            if(requests.length > 1) {
                instruction(requests[0], Tasks,requests[1]);
            } else {
                instruction(requests[0],Tasks,"");
            }
            line = in.nextLine();
        }
        instruction("bye",Tasks,"");

    }
}
