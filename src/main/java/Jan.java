import java.util.Scanner;

public class Jan {

    public static final int MAXIMUM_TASK = 100;

    public static void executeInstruction(String command, Task[] Tasks, String commandDescription){
        System.out.println("____________________________________________________________");
        switch(command) {
        case "greet":
            System.out.println("Hello! I'm Jan\n" + " What can I do for you?");
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
                System.out.println("Jan cannot find this task");
            }
            break;
        case "todo":
            Todo todo = new Todo(commandDescription);
            Tasks[Task.getTotalTask() - 1] = todo;
            System.out.println("Got it. I've added this task:\n " + todo);
            printCurrentTaskCount();

            break;
        case "deadline":
            String[] deadlineDetails = commandDescription.split("/by");
            Deadline deadline = new Deadline(deadlineDetails[0],deadlineDetails[1]);
            Tasks[Task.getTotalTask() - 1] = deadline;
            System.out.println("Got it. I've added this task:\n " + deadline);
            printCurrentTaskCount();
            break;
        case "event":
            String[] eventDetails = commandDescription.split("/at");
            Event event = new Event(eventDetails[0],eventDetails[1]);
            Tasks[Task.getTotalTask() - 1] = event;
            System.out.println("Got it. I've added this task:\n " + event);
            printCurrentTaskCount();
            break;
        default:
            System.out.println("Duke cannot understand your command.\n");
            break;
        }
        System.out.println("____________________________________________________________");
    }

    private static void printCurrentTaskCount() {
        if (Task.getTotalTask() < 2){
            System.out.println("Now you have " + Task.getTotalTask() + " task in the list");
        } else {
            System.out.println("Now you have " + Task.getTotalTask() + " tasks in the list");
        }
    }

    public static void main(String[] args) {

        Task[] Tasks = new Task[MAXIMUM_TASK];

        String logo = " ________     _____     _____     __\n"
                + "|_____   |  /  ___  \\  |     \\   |  | \n"
                + "      |  | |  /   \\  | |  |\\  \\  |  |\n"
                + " __   |  | |  |___|  | |  | \\  \\ |  |\n"
                + "|  |__|  | |   ___   | |  |  \\  \\|  |\n"
                + "|________/ |__|   |__| |__|   \\_____|\n";
        System.out.println(logo);
        executeInstruction("greet",Tasks,"");

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            String[] requests = line.split(" ",2);
            if(requests.length > 1) {
                executeInstruction(requests[0], Tasks,requests[1]);
            } else {
                executeInstruction(requests[0],Tasks,"");
            }
            line = in.nextLine();
        }

        executeInstruction("bye",Tasks,"");

    }
}
