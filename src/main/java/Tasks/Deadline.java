package Tasks;

public class Deadline extends Task {

    public Deadline(String description) {
        super(description.substring(0, description.indexOf("/")) + "("
                + description.substring(description.indexOf("/") + 1) + ")");
    }

    @Override
    public String toString() {

        return "[D][" + getStatusIcon() + "] " + description;
    }
}
