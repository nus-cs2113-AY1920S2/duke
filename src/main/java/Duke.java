import exceptions.DukeException;

/**
 * <h1>Duke</h1>
 * The main running class. Duke is a task management program,
 * allowing users to add/delete/find tasks.
 */
public class Duke {
    private static Storage storage;
    private static Parser parser;
    private static UI ui;
    private static TaskList taskList;

    /**
     * Initialises the relevant functions for Duke to run
     * Starts up the Duke UI and Parser, loads preexisting list (if any) from file
     */
    public static void init() {
        ui = new UI(); //prints greeting
        storage = new Storage();
        try {
            taskList = new TaskList(storage.loadDuke());
        } catch (DukeException e) {
            System.out.println(e);
        }
        parser = new Parser(taskList);
    }

    /**
     * Starts running the program.
     */
    public static void runDuke() {
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

    /**
     * Main method to run
     */
    public static void main(String[] args) {
        init();
        runDuke();
    }
}

