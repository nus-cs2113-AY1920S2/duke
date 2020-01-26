import java.util.Scanner;
public class Duke {
    public static String printItemDescription(Task item){
        return "[" + item.getStatusIcon() + "] " + item.description;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___| ";
        System.out.println(logo + " says hello :)");
        System.out.println("\nWhat can i do for you?");
        Scanner myObj = new Scanner(System.in);
        String cmd;
        Task[] toAdd = new Task[100];
        int counter = 0;
        do {
            cmd = myObj.nextLine();
            if (!cmd.equals("bye")) {
                if (cmd.equals("list")) {
                    for (int i = 0; i < counter; i++) {
                        System.out.println(i + 1 + ". " + printItemDescription(toAdd[i]));
                    }
                } else if (cmd.substring(0,4).equals("done")) {
                    int index = Integer.parseInt(cmd.substring(cmd.length() - 1)) - 1;
                    toAdd[index].setDone();
                    System.out.println("Nice! I've marked this task as done: " + printItemDescription(toAdd[index]));
                }else{
                    Task t = new Task(cmd);
                    toAdd[counter] = t;
                    counter++;
                    System.out.println("added: " + cmd);
                }
            } else {
                System.out.println("Bye bye! Talk to me again soon!");
            }
        } while (!cmd.equals("bye"));
    }
}
