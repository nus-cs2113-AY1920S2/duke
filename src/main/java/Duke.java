import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {

        String line;
        Scanner in = new Scanner(System.in);
        String[] storage = new String[100];
        Boolean[] actionDone = new Boolean[100];
        Arrays.fill(actionDone, Boolean.FALSE);
        int listCount = 0;

        System.out.println("Hello! I'm Isabella" + System.lineSeparator() + "What can I do for you?");
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.startsWith("done")) {
                int index = Integer.parseInt(line.substring(5)) - 1;
                actionDone[index] = true;
                System.out.println("Nice! I've marked this task as done: " + System.lineSeparator() + "[" + Task.checkIfDone(actionDone[index]) + "] " + storage[index]);
            } else if (line.equals("list")) {
                if (listCount == 0) {
                    System.out.println("There is nothing on the list.");
                } else {
                    System.out.println("Here are the tasks in your list: ");
                    for (int i = 0; i < listCount; i++) {
                        System.out.println(i+1 + ". [" + Task.checkIfDone(actionDone[i]) + "] " + storage[i]);
                    }
                }
            } else {
                storage[listCount] = line;
                System.out.println("added: " + line);
                listCount++;
            }
                line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}
