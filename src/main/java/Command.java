public class Command {
    protected String command;
    protected TextUi textUi;

    public Command(String command) {
        this.command = command;
        textUi = new TextUi();
    }
}
