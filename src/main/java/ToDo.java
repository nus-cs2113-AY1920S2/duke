public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public static boolean validateUserInput(String userInput) {
        if (!userInput.contains(" ")) {
            return false;
        } else if (userInput.indexOf(" ") + 1 > userInput.length() - 1) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }
}
