package duke.task;

public class Deadline extends Task {
    String deadline;

    public Deadline(String[] commands) {
        super(commands[0].trim());
        deadline = commands[1].split(" ",2)[1];
    }

    public Deadline(String command, boolean status, String deadline) {
        super(command, status);
        this.deadline = deadline;
    }

    public String textToFile() {
        String text = "D | 0 | " + name + " | " + deadline;
        if(isDone) text.replace("| 0 |", "| 1 |");
        return  text;
    }

    public String print() {
        String str = "[D]";
        if(isDone) {
            str += "[✓]";
        } else {
            str += "[✗]";
        }
        return str = str + " " + name + " (by: " + deadline + ")";

    }
}
