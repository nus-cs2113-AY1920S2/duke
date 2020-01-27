import java.util.Scanner;
public class Duke {
    public static String printItemDescription(Task item){
        return "[" + item.getStatusIcon() + "] " + item.description;
    }

    public static void printTaskList(Task[] taskList, int counter){
        for (int i = 0; i < counter; i++) {
            System.out.println(i + 1 + ". " + printItemDescription(taskList[i]));
        }
    }

    public static void markTaskAsDone(Task[] taskList, String cmd){
        int index = Integer.parseInt(cmd.substring(cmd.length() - 1)) - 1;
        taskList[index].setDone();
        System.out.println("Nice! I've marked this task as done: " + printItemDescription(taskList[index]));
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___| ";
        System.out.println(logo + " says hello :)");
        System.out.println("\nWhat can i do for you?");
        Task[] taskList = new Task[100];
        int counter = 0;
        Scanner myObj = new Scanner(System.in);
        String cmd;
        cmd = myObj.nextLine();
        while (!cmd.equals("bye")) {
            if (cmd.equals("list")) {
                printTaskList(taskList, counter);
            } else if (cmd.substring(0,4).equals("done")) {
                markTaskAsDone(taskList, cmd);
            }else{
                Task item = new Task(cmd);
                taskList[counter] = item;
                counter++;
                System.out.println("added: " + cmd);
            }
            cmd = myObj.nextLine();
        }
        System.out.println("Bye bye! Talk to me again soon!");
    }
}
