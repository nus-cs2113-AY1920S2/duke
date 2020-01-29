import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String line;
        Scanner in = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>();

        System.out.println("Hello! I'm Isabella" + System.lineSeparator() + "What can I do for you?");
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.startsWith("done")) {
                int index = Integer.parseInt(line.substring(5)) - 1;
                taskList.get(index).markAsDone();
                System.out.println("Nice! I've marked this task as done: " + System.lineSeparator() + "[" + taskList.get(index).checkIfDone() + "] " + taskList.get(index).action);
            } else if (line.equals("list")) {
                if (taskList.size() == 0) {
                    System.out.println("There is nothing on the list.");
                } else {
                    System.out.println("Here are the tasks in your list: ");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println(i+1 + ". [" + taskList.get(i).checkIfDone() + "] " + taskList.get(i).action);
                    }
                }
            } else {
                Task t = new Task(line);
                taskList.add(t);
                System.out.println("added: " + line);
            }
                line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}
