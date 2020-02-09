import java.util.Scanner;

public class Duke {

    public static final String FORMAT_LINE = "------------------------------------";
    public static final String GOODBYE = "Bye. Hope to see you again soon!";
    public static final String INVALID = "This is an invalid command, please type a valid command";
    public static final String GREETING = String.format("%s\n%s", "Hello, I'm Duke!", "What can I do for you?");
    private TaskManager manager = new TaskManager();

    public static void printFormat(String str) {
        System.out.println(FORMAT_LINE);
        System.out.println(str);
        System.out.println(FORMAT_LINE);
        System.out.println();
    }

    public void exitDuke() {
        printFormat(GOODBYE);
    }

    public void runDuke() {
        Scanner in = new Scanner(System.in);
        String command;
        while(in.hasNextLine()) {
            command = in.nextLine();
            String[] commands = command.split(" ", 2);
            if(commands[0].equals("bye")) {
                return;
            } else if (commands[0].equals("list")){
                manager.listTask();
            } else if (commands[0].equals("done")){
                manager.markTask(Integer.parseInt(commands[1]));
            } else if (commands[0].equals("todo")){
                manager.addTodo(commands[1]);
            } else if (commands[0].equals("deadline")) {
                manager.addDeadline(commands[1]);
            } else if (commands[0].equals("event")) {
                manager.addEvent(commands[1]);
            } else {
                printFormat(INVALID);
            }
        }
    }

    public void greet(){
        printFormat(GREETING);
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
        duke.runDuke();
        duke.exitDuke();
    }
}
