package duke.task;

/**
 * a deadline class that can be instantiated to deadline object
 */
public class Deadline extends Task {
    String deadline;

    public Deadline(String command, boolean status, String deadline) {
        super(command, status);
        this.deadline = deadline;
    }

    /**
     * generate description of a deadline task that need to be stored in the file*
     * @return a string that will be stored in the local file
     */
    public String textToFile() {
        String text = "D | 0 | ";
        if(isDone) text = "D | 1| ";
        text += name + " | " + deadline + System.lineSeparator();
        return  text;
    }

    /**
     * generate a string that display all attributes of a deadline task
     * @return a string that describes the task
     */
    public String print() {
        String str = "[D]";
        if(isDone) {
            str += "[\u2713]";
        } else {
            str += "[\u274c]";
        }
        return str = str + " " + name + " (by: " + deadline + ")";

    }
}
