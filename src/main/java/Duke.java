import java.util.Scanner;
import java.util.Vector;

public class Duke {

    public static class Task {
        protected String task;
        protected boolean isDone;

        public Task(String task) {
            this.task = task;
            this.isDone = false;
        }

        public String getTaskStatus() {
            String icon = isDone ? "\u2713" : "\u2718";
            return ("[" + icon + "] " + task);
        }
    }

    private static Vector<Task> list = new Vector<>();

    private void printList() {
        System.out.println("Sure! Lumi shall print out your list!");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i+1 + ". " + list.get(i).getTaskStatus());
        }
        System.out.println();
    }

    private void addToList(String input) {
        list.add(new Task(input));
        System.out.println("Alright, Lumi has added: " + input + "!\n");
    }

    private boolean isValidDoneInput(String input) {
        String[] words = input.split(" ");
        // Checks if input contains two words
        if (words.length != 2) {
            return false;
        }
        // Checks if first word is done
        if (!words[0].toLowerCase().equals("done")) {
            return false;
        }
        // Checks if second word is a valid list number
        return words[1].matches("\\d+") && Integer.parseInt(words[1]) <= list.size();
    }

    private void doTask(String input) {
        String[] words = input.split(" ");
        int listNumber = Integer.parseInt(words[1]);
        list.get(listNumber-1).isDone = true;
        System.out.println("Well done! Lumi marks this task as completed!\n" +
                list.get(listNumber-1).getTaskStatus() + "\n");
    }

    private void readInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.toLowerCase().equals("bye")) {
            return;
        } else if (input.toLowerCase().equals("list")) {
            printList();
        } else {
            if (isValidDoneInput(input)) {
                doTask(input);
            } else {
                addToList(input);
            }
        }
        readInput();
    }

    private void runChat() {
        System.out.println("Initializing LumiChat v0.0.1.4...\n\n" +
                "LumiChat is now ready.\n\n " +
                "Hey, I'm Lumi!\n How may I assist you today?\n");

        readInput();

        System.out.println("Bye! See you again soon!");
    }

    public static void main(String[] args) {
        String logo =
                "  __       _______  _______  ________  _______  _______  _______  ________\n" +
                " |\\_\\     |\\___\\__\\|\\ __\\__\\|\\ ______\\|\\______\\|\\___\\__\\|\\______\\|\\ ______\\\n" +
                " | | |    | |  |  || |  |  | \\|__   _|| |  ___|| |  |  || |     | \\|__   _|\n" +
                " | | |    | |  |  || |  |  |   | | |  | | |    | |  |  || |  |  |   | | |\n" +
                " | | |    | |  |  || |     |   | | |  | | |    | |     || |     |   | | |\n" +
                " | | |__  | |  |  || | | | | __| | |_ | | |___ | |  |  || |     |   | | |\n" +
                " | | |__\\ | |     || | | | ||\\__\\| |_\\| | |___\\| |  |  || |  |  |   | | |\n" +
                "  \\|_____| \\|_____| \\|_|_|_| \\|______| \\|_____| \\|__|__| \\|__|__|    \\|_|\n";
        System.out.println("Welcome to\n" + logo);

        Duke chatBot = new Duke();
        chatBot.runChat();
    }
}
