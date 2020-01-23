import java.util.Scanner;

public class Duke {
    public static Task[] instruction(String dukeCommand, Task[] Tasks, int tasknum){
        System.out.println("____________________________________________________________");
        if (dukeCommand.equals("greet")) {
            System.out.println("Hello! I'm Duke\n" +
                    " What can I do for you?");
        }else if (dukeCommand.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }else if (dukeCommand.equals("list")) {
            //print out everything in the list
            System.out.println("Here are the tasks in your list:");
            int count = 1;
            for (Task task: Tasks) {
                if (count <= Task.getTotalTask()) {
                    System.out.println(count + ".[" + task.getStatusIcon() + "]" + task.getDescription());
                    count++;
                } else {
                    break;
                }
            }
        } else if  (dukeCommand.equals("done")) {
            tasknum--;
            System.out.println("Nice! I've marked this task as done: ");
            Tasks[tasknum].setDone();
            System.out.println(tasknum + ".[" + Tasks[tasknum].getStatusIcon() + "]" + Tasks[tasknum].getDescription());
        } else if (dukeCommand.equals("Invalid")) {
            System.out.println("Duke cannot understand your command.\n");
        }
        else{
            System.out.println("added: " + dukeCommand + "\n");
            Task newTask = new Task(dukeCommand);
            Tasks[Task.getTotalTask() - 1] = newTask;
        }
        System.out.println("____________________________________________________________");
        return Tasks;
    }
    public static void main(String[] args) {

        Task[] Tasks = new Task[100];

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        instruction("greet",Tasks,0);
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            String[] requests = line.split(" ");
            if (requests[0].equals("done") && Integer.parseInt(requests[1]) < Task.getTotalTask()) {
                instruction(requests[0], Tasks, Integer.parseInt(requests[1]));
            } else if(requests[0].equals("done") && !(Integer.parseInt(requests[1]) < Task.getTotalTask())) {
                instruction("Invalid", Tasks, 0);
            }
            else {
                instruction(line, Tasks, 0);
            }
            line = in.nextLine();
        }
        instruction("bye",Tasks,0);

    }
}
