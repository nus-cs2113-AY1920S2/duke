import java.util.Scanner;

public class Parser {

    private Ui ui = new Ui();

    public static final String[] VALID_COMMANDS =
            {"done", "list", "bye", "todo", "deadline", "event", "delete", "find"};

    public String getInput() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;
    }

    public  String getCommand(String line) throws InvalidCommandException {
        int dividerPosition = line.indexOf(" ");
        String command;
        if (dividerPosition != -1) {
            command = line.substring(0, dividerPosition);
        } else {
            command = line;
        }
        boolean isExists = false;
        for (String temp: VALID_COMMANDS) {
            if (temp.equals(command)) {
                isExists = true;
                break;
            }
        }
        if (isExists) {
            return command;
        } else {
            throw new InvalidCommandException();
        }
    }

    public String getTaskInformation(String line) throws IndexOutOfBoundsException {
        int dividerPosition = line.indexOf(" ");
        if (dividerPosition == -1) {
            throw new IndexOutOfBoundsException();
        } else {
            return line.substring(dividerPosition + 1);
        }
    }

    public void handleInvalidCommand() {
        ui.printLine();
        System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        ui.printLine();
    }

    public void handleIndexOutOfBounds(String command) {
        ui.printLine();
        System.out.println(" ☹ OOPS!!! The description of a " + command + " cannot be empty.");
        ui.printLine();
    }

    public Task readTaskFromFile(String line) {
        String[] splitLine = line.split(", ");
        String typeIcon = splitLine[0];
        String isDoneString = splitLine[1];
        String description = splitLine[2];
        Task task;

        if (typeIcon.equals("[E]")) {
            String timePeriod = splitLine[3];
            task = new Event(description, timePeriod);
        } else if (typeIcon.equals("[D]")) {
            String dueDate = splitLine[3];
            task = new Deadline(description, dueDate);
        } else {
            task = new Todo(description);
        }

        boolean isDone = Boolean.parseBoolean(isDoneString);
        task.setIsDone(isDone);
        return task;
    }
}
