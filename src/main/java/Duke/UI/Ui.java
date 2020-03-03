package Duke.UI;

import java.util.Scanner;
import java.io.*;

public class Ui {
    protected Scanner in;
    public static PrintStream out;

    public Ui(){
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    public void showWelcome() {
        out.println(Messages.WELCOME_MESSAGE);
    }

    public void showGoodbye() {
        out.println(Messages.GOODBYE_MESSAGE);
    }

    public String readCommand () {
        out.println(Messages.MESSAGE_ENTER_COMMAND);
        String line = in.nextLine();
        return line;
    }

    public void showLine () {
        out.println(Messages.DIVIDER);
    }

    public void showError(String errorMessage) {
        out.println(Messages.MESSAGE_UNKNOWN_ERROR + errorMessage);
    }

    public void showLoadingError(String errorMessage) {
        out.println(Messages.MESSAGE_LOAD_ERROR + errorMessage);
    }

}
