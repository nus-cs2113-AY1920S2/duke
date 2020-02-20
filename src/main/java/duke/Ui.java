package duke;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private static final String DUKE_LOGO =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String DIVIDER = "_________________________________________________";
    private static final String LS = System.lineSeparator();

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    public void showDivider() {
        out.println(DIVIDER);
    }

    public void showToUser(String... messages) {
        for (String message : messages) {
            out.println(message);
        }
    }

    public void showWelcomeMessage() {
        showDivider();
        showToUser("This is", DUKE_LOGO, "How can I help you today?");
        showDivider();
    }

    public void showByeMessage() {
        showToUser("Bye then");
        showDivider();
    }

    public String getUserCommand() {
        out.print("> ");
        out.flush();
        return in.nextLine().trim();
    }
}
