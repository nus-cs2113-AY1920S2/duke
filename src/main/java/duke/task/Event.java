package duke.task;

public class Event extends Task {
    String timeSlot;

    public Event(String[] commands) {
        super(commands[0].trim());
        timeSlot = commands[1].split(" ",2)[1];
    }

    public Event(String command, boolean status, String timeSlot) {
        super(command, status);
        this.timeSlot = timeSlot;
    }

    public String textToFile() {
        String text = "E | 0 | " + name + " | " + timeSlot + System.lineSeparator();
        if(isDone) text.replace("| 0 |", "| 1 |");
        return  text;
    }

    public String print() {
        String str = "[E]";
        if(isDone) {
            str += "[✓]";
        } else {
            str += "[✗]";
        }
        return str = str + " " + name + " (at: " + timeSlot + ")";
    }
}
