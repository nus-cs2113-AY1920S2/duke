import java.util.Scanner;

public class Duke {

    private TaskManager manager = new TaskManager();

    public void exit() {
        System.out.println("------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("------------------------------------");
        System.out.println();
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        String command;
        while(in.hasNextLine()) {
            command = in.nextLine();
            if(command.equals("bye")) {
                return;
            } else if (command.equals("list")){
                manager.listTask();
            } else if (command.startsWith("done")){
                manager.markTask(Integer.parseInt(command.substring(5)));
            } else {
                manager.addTask(command);
            }
        }
    }

    public void greet(){
        System.out.println("------------------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("------------------------------------");
        System.out.println();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Welcome to\n" + logo);
        Duke duke = new Duke();
        duke.greet();
        duke.run();
        duke.exit();
    }
}
