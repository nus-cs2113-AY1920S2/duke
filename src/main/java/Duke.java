import java.util.Scanner;

/**
 * Main logic behind the CLI
 * A Duke object contains UI, Parser, TaskList, Storage, DukeExceptions, DukeHelp object
 */
public class Duke {

    private UI userInterface;
    private Parser parser;
    private TaskList tasks;
    private Storage storage;
    private DukeExceptions dukeExceptions;
    private DukeHelp dukeHelp;

    public Duke() {
        userInterface = new UI();
        parser = new Parser();
        tasks = new TaskList();
        storage = new Storage();
        dukeExceptions = new DukeExceptions();
        dukeHelp = new DukeHelp();
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        userInterface.printGreetingMessage();
        tasks.list();
        userInterface.printLine();
        while (true) {
            String input = sc.nextLine().trim();
            parser.updateInput(input);
            userInterface.printLine();
            if (parser.isBye()) {
                userInterface.printLeavingMessage();
                userInterface.printLine();
                break;
            } else if (parser.isList()) {
                tasks.list();
            } else if (parser.isDone()) {
                tasks.getDoneExceptions(input);
            } else if (parser.isToDo()) {
                tasks.getToDoExceptions (input);
            } else if (parser.isEvent()) {
                tasks.getEventExceptions(input);
            } else if (parser.isDeadline()) {
                tasks.getDeadlineExceptions(input);
            } else if (parser.isDelete()) {
                tasks.getDeleteExceptions(input);
            } else if (parser.isFind()){
                tasks.getFindExceptions(input);
            } else if (parser.isHelp()) {
              dukeHelp.printHelp();
            } else {
                dukeExceptions.printInvalidInput();
                dukeHelp.printHelp();
            }
            userInterface.printLine();
        }
        sc.close();
    }
    
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke dukeFeatures = new Duke();
        dukeFeatures.run();
    }
}
