package Ui;
import TaskList.Task;
import java.util.ArrayList;
import java.util.Scanner;

public class TextUi {
    public TextUi(){}

    public void printWelcomeMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___| ";
        System.out.println(logo + " says hello :)");
        System.out.println("\nWhat can i do for you?");
    }

    public void printExitMsg() {
        System.out.println("Bye bye! Talk to me again soon!");
    }

    public String readCommand() {
        Scanner myObj = new Scanner(System.in);
        String cmd = myObj.nextLine();
        return cmd;
    }

    public void showLoadingError() {
        System.out.println("Duke has encountered a loading error :( Let's create one!");
    }

    public void printIOError() {
        System.out.println("IO saving tasklist error occured!");
    }

    public void printFormatError() {
        System.out.println("I cannot understand the command, please check again.");
    }

    public void printTaskList(ArrayList<Task> taskList){
        if (taskList.size() == 0) {
            System.out.println("There are currently nothing to do! Add something!");
        }
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + ". " + taskList.get(i).printObject());
        }
    }
}
