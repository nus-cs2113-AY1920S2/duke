/**
<<<<<<< HEAD
 * 
=======
 * A class that is the foundation where all the Commands extends from.
>>>>>>> JavaDocs
 */

public class Command {
    protected String command;
    protected TextUi textUi;

    public Command() {
        this.command = "";
    }

    /**
     * Constructor for Command object.
     * @param command the command inputted by the user, with trailing and leading white spaces removed.
     */

    public Command(String command) {
        this.command = command;
        textUi = new TextUi();
    }
}
