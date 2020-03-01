package duke.Ui;
import duke.command.CommandOption;
import duke.taskList.TaskList;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TextUi {

    private final Scanner in;
    private final PrintStream out;

    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String getUserCommand() {
        out.print("Sheena: Please enter your command ~ \n");
        String Input = in.nextLine();
        PresentMessages("Sheena: Yay! This is your input (" + Input + ") !");
        return Input;
    }

    public void welcomeMessage(String storageFile) {
        String Info = String.format(USING_STORAGE, storageFile);
        PresentMessages(WELCOME, Info);
    }

    public void goodbye() {
        PresentMessages(GOODBYE);
    }

    public void failed() {
        PresentMessages(FAILED);
    }

    /**
     * Shows message(s) to the user
     */
    public void PresentMessages(String... message) {
        for (String a : message) {
            out.println(a);
        }
    }

    public void printResult(CommandOption result) {
        PresentMessages(result.feedback);
    }


    public void storedList() {
        if (TaskList.size() <= 0) {
            System.out.println("\nSheena: Hmm nothing here.\nSheena: It's okay! Let me create a new one!");
        } else {
            System.out.println("---------------------------------------------------------");
            System.out.println("Sheena: Yay! I successfully retreat your previous list!");
            TaskList.storedTaskList();
            System.out.println("---------------------------------------------------------");
        }
    }

    public static final String GOODBYE = "Sheena: I will miss you! Hope to see you again soon!";
    public static final String FAILED = "Sheena: Erm can't run now. Gonna Exit first.";
    public static final String WELCOME = "Hello! I'm Sheena ^^ \nWhat can I do for you ?";
    public static final String USING_STORAGE = "Sheena: using storage file ( %1$s )";
}
