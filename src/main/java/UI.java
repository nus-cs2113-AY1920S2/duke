/**
 * Represents messages displayed by Duke for its user interface
 * A UI object corresponds to greeting message, leaving message and boundary
 */
public class UI {
    
    protected String leavingMessage;
    protected String boundary;
    protected String firstGreetingMessage;
    protected String secondGreetingMessage;
    protected String line;

    public UI() {
        leavingMessage = " Bye! Duke is now a freeeeee elf again!!!!";
        boundary = "===========================================================================================";
        firstGreetingMessage = " Hi! I am Duke, your next doooooorrrr friendly elf.....I mean bot";
        secondGreetingMessage = " How may Dukeeeeee help you today?";
        line = "____________________________________________________________________________________________";
    }

    /**
     * Prints boundary for user interface
     */
    public void printLine() {
        System.out.println(boundary);
    }

    /**
     * Prints greeting message when Duke is started
     */
    public void printGreetingMessage() {
        System.out.println(firstGreetingMessage);
        System.out.println(secondGreetingMessage);
        System.out.println(line);
    }

    /**
     * Prints leaving message when Duke is terminated
     */
    public void printLeavingMessage() {
        System.out.println(leavingMessage);
    }
}
