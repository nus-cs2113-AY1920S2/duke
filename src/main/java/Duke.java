
import java.util.Scanner;

public class Duke {

    private static Task[] list = new Task[100];
    private static int index = 0;

    public static void printList(Task[] list) {
        for(int i = 0; i < index; i++) {
            System.out.print(String.valueOf(i+1) + ". ");
            System.out.println(list[i]);
        }
    }

    public static void main(String[] args) {

        System.out.println("____________________________________________________________\n" +
                        " Hello! I'm Duke :)\n" +
                        " What can I do for you?\n" +
                "____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String inputString = "";
        while(!inputString.equals("bye bye")) {
            inputString = scanner.nextLine();
            //Task new_task = new Task(inputString);
            Task new_task;

            if(inputString.equals("list")) {
                printList(list);
            } else if(inputString.contains("done")) {
                int ind = Integer.parseInt((inputString.substring(5)));
                System.out.println(ind);
                list[ind-1].markAsDone();
                System.out.println(" ____________________________________________________________\n" +
                        "  Yay! You have done: " + list[ind-1].description + "\n"+
                        "____________________________________________________________\n");
            } else if (inputString.contains("todo")){
                new_task = new Todo(inputString.substring(5));
                list[index] = new_task;
                index++;
                System.out.println(" ____________________________________________________________\n" +
                        "  Ok! I've added this task. \n" +
                        "  Now you have " + String.valueOf(index) + " tasks on your list.\n " +
                        "____________________________________________________________\n");
            } else if (inputString.contains("deadline")){
                int findSeparator = inputString.indexOf('/');
                new_task = new Deadline(inputString.substring(9, findSeparator-1), inputString.substring(findSeparator+4));
                list[index] = new_task;
                index++;
                System.out.println(" ____________________________________________________________\n" +
                        "  Ok! I've added this task. \n" +
                        "  Now you have " + String.valueOf(index) + " tasks on your list.\n " +
                        "____________________________________________________________\n");
            } else if (inputString.contains("event")){
                int findSeparator = inputString.indexOf('/');
                new_task = new Event(inputString.substring(6, findSeparator-1), inputString.substring(findSeparator+4));
                list[index] = new_task;
                index++;
                System.out.println(" ____________________________________________________________\n" +
                        "  Ok! I've added this task. \n" +
                        "  Now you have " + String.valueOf(index) + " tasks on your list.\n " +
                        "____________________________________________________________\n");
            } else {
                //list[index] = new_task;
                index++;
                System.out.println("  added: " + inputString + "\n" +
                        "____________________________________________________________");
            }

        }
        System.out.println(" Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }
}
