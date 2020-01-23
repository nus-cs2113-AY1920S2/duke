import java.util.Scanner;

public class Duke {
    public static Task[] instruction(String dukeCommand, Task[] Tasks){
        System.out.println("____________________________________________________________");
        if (dukeCommand.equals("greet")) {
            System.out.println("Hello! I'm Duke\n" +
                    " What can I do for you?");
        }else if (dukeCommand.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }else if (dukeCommand.equals("list")) {
            //print out everything in the list
            int count = 1;
            for (Task task: Tasks) {
                if (count <= Task.getTotalTask()) {
                    System.out.println(count + ": " + task.getDescription());
                    count++;
                } else {
                    break;
                }
            }
        }
        else{
            System.out.println("added: " + dukeCommand + "\n");
            Task newTask = new Task(dukeCommand);
            Tasks[Task.getTotalTask() - 1] = newTask;
        }
        /*else{
            System.out.println("____________________________________________________________");
            System.out.println("Duke cannot understand your command.\n");
            System.out.println("____________________________________________________________");
        }*/
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
        instruction("greet",Tasks);
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            instruction(line, Tasks);
            line = in.nextLine();
        }
        instruction("bye",Tasks);

    }
}
