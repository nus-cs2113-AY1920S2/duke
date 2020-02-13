public class Deadline extends Task {
    public Deadline(String command) {
        super("[D][✗] "
                + command.replaceFirst("deadline\\s","").replaceFirst("/by","(by:").trim()
                + ")");
    }
}
