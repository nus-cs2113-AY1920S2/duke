import Features.Task;
import Exception.DukeException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main bot control class for users to manage their tasks.
 * It includes features like setting deadlines, events and todos.
 */
public class Duke {
    private Storage myStorage;
    private TaskList myTasks;
    private UI myUI;

    /**
     * Initialise the required class instances.
     * Loads the previous task list from file with <code>Storage</code> object.
     * @param f Location of the previously stored text file.
     */
    public Duke(File f) {
        myUI = new UI();
        myStorage = new Storage(f);
        try {
            myTasks = new TaskList(myStorage.loadFromFile());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     *
     * @param keyword First keyword that user inputs into the CLI.
     * @param userInput The whole sentence that user inputs.
     * @throws DukeException If keyword is not regcognized.
     */
    private void botResponse(String keyword, String userInput) throws DukeException {
        switch (keyword) {
            case "list": myTasks.listCommand();
                break;
            case "delete":
                myTasks.deleteCommand(userInput);
                break;
            case "done":
                myTasks.doneCommand(userInput);
                break;
            case "todo":
                myTasks.addCommand(userInput, "todo");
                break;
            case "event":
                myTasks.addCommand(userInput, "event");
                break;
            case "deadline":
                myTasks.addCommand(userInput, "deadline");
                break;
            case "find":
                myTasks.findCommand(userInput);
                break;
            default:
                throw new DukeException("Unknown command.");
        }
    }

    /**
     * Main bot loop, stops when user inputs keyword "bye".
     */
    public void run() {
        myUI.intro();
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        ArrayList<String> words = Parser.convertStringToArr(userInput, " ");
        String keyword = words.get(0);
        while (!keyword.equals("bye")) {
            try {
                botResponse(keyword, userInput);
            } catch (DukeException e) {
                UI.printLines();
                System.out.println("OOPS!! " + e);
                UI.printLines();
            } finally {
                userInput = in.nextLine();
                words = Parser.convertStringToArr(userInput, " ");
                keyword = words.get(0);
            }
        }
        try {
            ArrayList<Task> listToSave = myTasks.getUserList();
            if (!listToSave.equals(myStorage.loadFromFile())) {
                myStorage.saveToFile(listToSave);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        myUI.exit();
    }

    public static void main(String[] args) {
        java.util.Properties properties = System.getProperties();
        String home = properties.get("user.home").toString();
        // your file name
        String fileName = "savedTasks.txt";

        File dir = new File(home);
        File f = new File(dir, fileName);
        new Duke(f).run();
    }
}
