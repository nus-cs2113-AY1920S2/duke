package Tasks;

public class Deadline extends Task {

    public Deadline(String description) {
//        description = description.substring(0, description.indexOf("/")) +
//                description.substring(description.indexOf("/"), description.length()) + ")";
        super(description.substring(0, description.indexOf("/")) + "(" +
                description.substring(description.indexOf("/")+1, description.length()) + ")");
    }

    @Override
    public String toString(){

        return "[D][" + getStatusIcon() + "] " + description;
    }
}
