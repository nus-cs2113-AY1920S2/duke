import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private ArrayList <Task> tasks;
    private ToDo newToDo;
    private Event newEvent;
    private Deadline newDeadline;
    private UI userInterface;
    private Parser parser;
    private DukeExceptions dukeExceptions;
    private Storage storage;
    private final int startIndex = 0;
    private final int lengthOfDeadline = 9;
    private final int lengthOfEvent = 5;
    private final int lengthOfDone = 5;
    private final int lengthOfDelete = 7;

    public Duke() {
        this.tasks = new ArrayList<Task>();
        userInterface = new UI();
        parser = new Parser();
        dukeExceptions = new DukeExceptions();
        storage = new Storage();
    }

    private void printList() {
        int position = 1;
        for (Task text: tasks) {
            System.out.println("    " + position + ". " + text);
            position ++;
        }
    }

    private void list() {
        tasks = storage.loadTasks();
        if (tasks.size() == 0) {
            dukeExceptions.printListExceptions();
        } else {
            System.out.println(" Here are the list of tasks:");
            printList();
        }
    }

    private int getIndex(String input, int position) {
        String removeTrailingSpaces = input.trim();
        String numericalIndex = removeTrailingSpaces.substring(position,input.length()).trim();
        int index = Integer.valueOf(numericalIndex) - 1;
        return index;
    }

    private void markAsDone(int index) {
        Task completedTask = tasks.get(index);
        boolean isCompleted = completedTask.getStatus();
        if (isCompleted) {
            dukeExceptions.printDoneExceptions();
        } else {
            completedTask.markAsDone();
            tasks.set(index, completedTask);
            System.out.println(" Duke has marked the following tasks as done:");
            System.out.println("  " + completedTask);
            storage.save(tasks);
        }
    }

    private void getDoneExceptions(String input) {
        try {
            int index = getIndex(input, lengthOfDone);
            markAsDone(index);
        } catch(IndexOutOfBoundsException exception) {
            dukeExceptions.printDoneIOBExceptions(input);
        } catch (NumberFormatException exception) {
            dukeExceptions.printDoneNFExceptions();
        }
    }

    private void addToDo(String input) {
        String removeTrailingSpaces = input.trim();
        String description = removeTrailingSpaces.substring(5, input.length());
        newToDo = new ToDo(description);
        tasks.add(newToDo);
        System.out.println(" Got it! I have added the following tasks: ");
        System.out.println( newToDo);
        System.out.println("There are currently " + tasks.size() + " task(s) queued");
        storage.save(tasks);
    }

    private void getToDoExceptions(String input) {
        try {
            addToDo(input);
        } catch (StringIndexOutOfBoundsException exception) {
            dukeExceptions.toDoExceptions(input);
        }
    }

    private void addEvent(String input) {
        String description = input.substring(lengthOfEvent).trim();
        int index = description.indexOf("/");
        String details = description.substring(startIndex, index).trim();
        String specifier = description.substring(index + 1, index + 3);
        String time = description.substring(index + 3, description.length()).trim();
        boolean isAt = specifier.toLowerCase().equals("at");
        if (details.isEmpty() || time.isEmpty() || !isAt) {
            String errorMessage = "Missing Event Description / Event Time / Missing parameters";
            dukeExceptions.printEventExceptions(errorMessage, input);
        } else {
            newEvent = new Event(details, time);
            tasks.add(newEvent);
            System.out.println(" New task has been added:");
            System.out.println(" " + newEvent);
            System.out.println(" There are currently " + tasks.size() + " task(s) being queued");
            storage.save(tasks);
        }
    }

    private void getEventExceptions(String input) {
        try {
            addEvent(input);
        } catch (IndexOutOfBoundsException exception) {
            String errorMessage = "Missing Event Description / Event Time & Missing parameters";
            dukeExceptions.printEventExceptions(errorMessage, input);
        }
    }

    private void addDeadline(String input) {
        String description = input.substring(lengthOfDeadline, input.length()).trim();
        int index = description.indexOf("/");
        String details = description.substring(startIndex, index);
        String day = description.substring(index + 3, description.length()).trim();
        String specifier = description.substring(index + 1, index + 3);
        boolean isBy = specifier.toLowerCase().equals("by");
        if (details.isEmpty() || day.isEmpty() || !isBy) {
            String errorMessage = "Missing Event Description / Event Time / Missing parameters";
            dukeExceptions.printDeadlineExceptions(errorMessage, input);
        } else {
            newDeadline = new Deadline(details, day);
            tasks.add(newDeadline);
            System.out.println(" Got it!I've added this task:");
            System.out.println(" " + newDeadline);
            System.out.println(" There are currently " + tasks.size() + " being queued");
            storage.save(tasks);
        }
    }

    private void getDeadlineExceptions(String input) {
        try {
            addDeadline(input);
        } catch (IndexOutOfBoundsException exception) {
            String errorMessage = "Missing Event Description / Event Time & Missing parameters";
            dukeExceptions.printDeadlineExceptions(errorMessage, input);
        }
    }

    private void deleteItems(int index) {
        Task deletedTask = tasks.get(index);
        int length = tasks.size() - 1;
        tasks.remove(deletedTask);
        System.out.println(" Got it! I've removed this task:");
        System.out.println(" " + deletedTask);
        System.out.println(" There are currently " + length + " task(s) being queued");
        storage.save(tasks);
    }

    private void getDeleteExceptions(String input) {
        try {
            int index = getIndex(input, lengthOfDelete);
            deleteItems(index);
        } catch (NumberFormatException exceptions) {
            dukeExceptions.printNFEDeleteExceptions(input);
        } catch (IndexOutOfBoundsException exception) {
            dukeExceptions.printIOBDeleteExceptions(input);
        }
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        userInterface.printGreetingMessage();
        storage.restoreArray(sc, tasks);
        while (true) {
            String input = sc.nextLine().trim();
            parser.updateInput(input);
            userInterface.printLine();
            if (parser.isBye()) {
                userInterface.printLeavingMessage();
                userInterface.printLine();
                break;
            } else if (parser.isList()) {
                list();
            } else if (parser.isDone()) {
                getDoneExceptions (input);
            } else if (parser.isToDo()) {
                getToDoExceptions (input);
            } else if (parser.isEvent()) {
                getEventExceptions(input);
            } else if (parser.isDeadline()) {
                getDeadlineExceptions(input);
            } else if (parser.isDelete()) {
                getDeleteExceptions(input);
            } else {
                System.out.println(" Duke does not understand your command! Can you repeat again?");
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
