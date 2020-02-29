import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import common.Messages;

import static common.Messages.START_MESSAGE;
import static common.Messages.FIRST_EXIT_MESSAGE;
import static common.Messages.SECOND_EXIT_MESSAGE;
import static common.Messages.EMPTY_COMMAND_ERROR_MESSAGE;
import static common.Messages.USER_INPUT_ARROWHEAD_DISPLAY;

/**
 * This class is the UI that allows the user to interact with Duke. It contains methods to take in user input and display messages.
 */
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

    /**
     * This method produces the introductory message on program startup.
     */
    public void sayIntro(){
        out.println(messageContainer.addCurlyBorders(START_MESSAGE));
    }

    /**
     * This method produces the goodbye message before program terminates.
     */
    public void sayGoodbye(){
        out.println(messageContainer.addCurlyBorders(FIRST_EXIT_MESSAGE));
        out.println(SECOND_EXIT_MESSAGE);
    }

    /**
     * This method obtains input from the user.
     * <p></p>
     * <p>It runs a loop that continues running if the user provides an empty/whitespace command.
     * It only breaks when the user provides a non-empty command</p>
     * @return the command provided by the user as a String.
     */
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

    /**
     * This methods prints out the message input, enclosed within underscored borders, for user to view.
     * @param message the message to be printed
     */
    public void displayMessage(String message) {
        out.println(messageContainer.addUnderscoreBorders(message));
    }

    private boolean isInputEmpty(String rawInput) {
        return rawInput.trim().isEmpty();
    }

}
