import Features.Task;
import Exception.DukeException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Storage myStorage;
    private TaskList myTasks;
    private UI myUI;

    public Duke(File f) {
        myUI = new UI();
        myStorage = new Storage(f);
        try {
            myTasks = new TaskList(myStorage.loadFromFile());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    //Main bot control
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

        // to print all the keys in the properties map <for testing>
        //properties.list(System.out);
        // get Operating System home directory(PLATFORM INDEPENDENT METHOD)
        String home = properties.get("user.home").toString();
        // get Operating System separator
        String separator = properties.get("file.separator").toString();
        // your directory name
        String directoryName = "duke";
        // your file name
        String fileName = "savedTasks.txt";
        File dir = new File(home + separator + directoryName);
        File f = new File(dir, fileName);
        new Duke(f).run();
    }
}
