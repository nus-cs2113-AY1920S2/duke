public class UI {
    protected String leavingMessage;
    protected String boundary;
    protected String firstGreetingMessage;
    protected String secondGreetingMessage;
    protected String line;

    public UI() {
        leavingMessage = " Bye! Duke is now a freeeeee elf again!!!!";
        boundary = " ======================================================================";
        firstGreetingMessage = " Hi! I am Duke, your next doooooorrrr friendly elf.....I mean bot";
        secondGreetingMessage = " How may Dukeeeeee help you today?";
        line = "_______________________________________________________________________";
    }
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
