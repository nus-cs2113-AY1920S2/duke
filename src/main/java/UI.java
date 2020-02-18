/**
 * Represents the possible messages that makes up the User Interface of Duke
 * A UI object represents all the messages that makes up the User Interface of Duke
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
     *prints boundary of each command
     */
    public void printLine() {
        System.out.println(boundary);
    }

    public void printGreetingMessage() {
        System.out.println(firstGreetingMessage);
        System.out.println(secondGreetingMessage);
        System.out.println(line);
    }
    
    public void printLeavingMessage() {
        System.out.println(leavingMessage);
    }
}
