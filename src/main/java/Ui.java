import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {

    private static String underscoredLine = "\t____________________________________________________________";
    private static String curlyLine = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void sayIntro(){
        String introMessage = curlyLine + System.lineSeparator() + "Hello! I'm Duke\n"
                + "What can I do for you?\n" + curlyLine + System.lineSeparator();

        out.println(introMessage);
    }

    public void sayGoodbye(){
        String goodbyeMessage = curlyLine + System.lineSeparator() + "Bye! Hope to see you again soon\n"
                + curlyLine + System.lineSeparator();
        String goodbyeMessage2 = "********************CONNECTION TERMINATED********************";

        out.println(goodbyeMessage);
        out.println(goodbyeMessage2);
    }

    public String getUserCommand() {
        out.print(">>>");
        String userInput = in.nextLine();

        //silently consume all empty/whitespace lines
        while (isInputEmpty(userInput)) {
            out.println(underscoredLine);
            out.println("\t\u2639 !!ERROR!! Command cannot be whitespaces.");
            out.println(underscoredLine);
            out.print(">>>");
            userInput = in.nextLine();
        }

        return userInput;
    }

    private boolean isInputEmpty(String rawInput) {
        return rawInput.trim().isEmpty();
    }

}
