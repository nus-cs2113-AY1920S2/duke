import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    Task newTask;
    private ArrayList<Task> tasks;

    public Duke() {
        this.tasks = new ArrayList<Task>();
    }
    
    private void printLine() {
        System.out.println(" ======================================================================");
    }

    private void printGreetingMessage() {
        System.out.println("Hi! I am Duke, your next doooooorrrr friendly elf.....I mean bot");
        System.out.println("How may Dukeeeeee be of service to you today?");
        System.out.println("_______________________________________________________________________");
    }

    private void printLeavingMessage() {
        System.out.println(" Bye! Duke is now a freeeeee elf again!!!!");
    }

    private void printExceptionError(Exception exception) {
        System.out.println(" Duke cannot understand what you are trying to do!");
        System.out.println(" Error message: " + exception);
    }

    private void add(String description) {
        newTask = new Task(description);
        tasks.add(newTask);
        System.out.println(" Added: " + description);
        System.out.println(" There are currently " + tasks.size() + " item(s) queued");
    }

    private void printList() {
        int position = 1;
        for (Task text: tasks) {
            System.out.println("    " + position + ". " + text);
            position ++;
        }
    }

    private void list() {
        if (tasks.size() == 0) {
            System.out.println(" Hmph!! There are currently no items queued");
        } else {
            System.out.println(" Here are the list of tasks:");
            printList();
        }
    }

    private String getFirstWord(String line) {
        String removeTrailingSpaces = line.trim();
        String [] words = removeTrailingSpaces.split(" ");
        String firstWord = words[0].toLowerCase();
        return firstWord;
    }

    private int getIndex(String line) {
        String removeTrailingSpaces = line.trim();
        String numericalIndex = removeTrailingSpaces.substring(5,line.length()).trim();
        int index = Integer.valueOf(numericalIndex) - 1;
        return index;
    }

    private void done(int index) {
        Task completedTask = tasks.get(index);
        boolean isCompleted = completedTask.getStatus();
        if (isCompleted) {
            System.out.println(" The task(s) has already been marked as done :x");
        } else {
            completedTask.markAsDone();
            tasks.set(index, completedTask);
            System.out.println(" Duke has marked the following tasks as done:");
            System.out.println("  " + completedTask);
        }
    }

    private void getDoneException(String line) {
        try {
            int index = getIndex(line);
            done(index);
        } catch (Exception exception) {
            printExceptionError(exception);
        }
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String line = sc.nextLine();
            String firstWord = getFirstWord(line);
            boolean isBye = firstWord.equals("bye");
            boolean isList = firstWord.equals("list");
            boolean isDone = firstWord.equals("done");
            printLine();
            if (isBye) {
                printLeavingMessage();
                printLine();
                break;
            } else if (isList) {
                list();
            } else if (isDone) {
                getDoneException(line);
            } else {
                add(line);
            }
            printLine();
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
        dukeFeatures.printGreetingMessage();
        dukeFeatures.run();
    }
}
