import java.util.Scanner;
import java.io.*;

public class Ui {

    protected static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
     //   System.out.println("Hello from\n" + logo);

    protected static final String DIVIDER = "————————————————————————————————————————-———————";


    protected Scanner in;
    protected PrintStream out;

    public Ui(){
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    public void showWelcome() {
        out.println(DIVIDER + "\nHello I'm\n" + LOGO
                + "\nWhat can I do for you?\n" + DIVIDER);
    }

    public void showGoodbye() {
        out.println(DIVIDER + "\nBye. Hope to see you again soon!\n"
                + DIVIDER);
    }

    public String readCommand () {
        out.println("Enter command: ");
        String line = in.nextLine();
        return line;
    }

    public void showLine () {
        out.println(DIVIDER);
    }

    public void showError(String message) {
        out.println("Something went wrong: " + message);
    }

}
