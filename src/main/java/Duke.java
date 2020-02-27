import java.util.ArrayList;
import exceptions.DukeException;
import tasks.Task;

public class Duke {
    private static Storage storage;
    private static Parser parser;
    private static UI ui;
    private static TaskList taskList;
//    TODO convert taskArrList to TaskList

    public static void init() throws DukeException {
        ui = new UI(); //prints greeting
        storage = new Storage();
        try {
//            taskArrList = storage.loadDuke();
            taskList = new TaskList(storage.loadDuke());
        } catch (DukeException e) {
            System.out.println(e);
        }
//        parser = new Parser();
        parser = new Parser(taskList);
    }

    public static void runDuke() throws DukeException {
        boolean continueRun = true;
        String userCmd = "";
        try {
            while (continueRun) {
                userCmd = UI.getUserCommand();
                //immediate exit if userCmd has 'bye'
                parser.runParser(userCmd);
                storage.saveDuke(taskList);
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

