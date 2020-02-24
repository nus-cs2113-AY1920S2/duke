import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import common.Messages;

import static common.Messages.START_MESSAGE;
import static common.Messages.FIRST_EXIT_MESSAGE;
import static common.Messages.SECOND_EXIT_MESSAGE;
import static common.Messages.EMPTY_COMMAND_ERROR_MESSAGE;
import static common.Messages.USER_INPUT_ARROWHEAD_DISPLAY;

public class Ui {

    private final Scanner in;
    private final PrintStream out;
    private final Messages messageContainer = new Messages();

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void sayIntro(){
        out.println(messageContainer.addCurlyBorders(START_MESSAGE));
    }

    public void sayGoodbye(){
        out.println(messageContainer.addCurlyBorders(FIRST_EXIT_MESSAGE));
        out.println(SECOND_EXIT_MESSAGE);
    }

    public String getUserCommand() {
        out.print(USER_INPUT_ARROWHEAD_DISPLAY);
        String userInput = in.nextLine();

        //silently consume all empty/whitespace lines
        while (isInputEmpty(userInput)) {
            displayMessage(EMPTY_COMMAND_ERROR_MESSAGE);
            out.print(USER_INPUT_ARROWHEAD_DISPLAY);
            userInput = in.nextLine();
        }

        return userInput;
    }

    public void displayMessage(String errorMessage) {
        out.println(messageContainer.addUnderscoreBorders(errorMessage));
    }

    private boolean isInputEmpty(String rawInput) {
        return rawInput.trim().isEmpty();
    }

}
