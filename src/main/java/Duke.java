import java.util.ArrayList;
import exceptions.DukeException;
import tasks.Task;

public class Duke {
    private static ArrayList<Task> taskArrList = new ArrayList<>();

    private static Storage storage;
    private static Parser parser;
    private static UI ui;
//    TODO private static TaskList taskList;
//    TODO convert taskArrList to TaskList

    public static void init() throws DukeException {
        ui = new UI(); //prints greeting
        storage = new Storage();
        try {
            taskArrList = storage.loadDuke();
            //taskList = storage.loadDuke();
        } catch (DukeException e) {
            System.out.println(e);
        }
        parser = new Parser();
    }

    public static void runDuke() throws DukeException {
        boolean continueRun = true;
        String userCmd = "";
        try {
            while (continueRun) {
                userCmd = UI.getUserCommand();
                //immediate exit if userCmd has 'bye'
                taskArrList = parser.runParser(userCmd, taskArrList);
                storage.saveDuke(taskArrList);
            }
        }
        catch (DukeException e){
            System.out.println(e +"\nPlease try again");
        }
    }

    public static void main(String[] args) throws DukeException {
        init();
        runDuke();
    }
}

