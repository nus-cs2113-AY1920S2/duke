public class TaskFactory {
    public static Task getTaskFromUserInput(String taskType, String userInput) {
        if (userInput == null) {
            return null;
        }

        String description;
        switch(taskType) {
        case "todo":
            description = userInput.substring(userInput.indexOf(" ") + 1);
            return new ToDo(description);
        case "deadline":
            description = userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf(" /by "));
            String dueDateTime = userInput.substring(userInput.indexOf(" /by ") + 5);
            return new Deadline(description, dueDateTime);
        case "event":
            description = userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf(" /at "));
            String startEndDateTime = userInput.substring(userInput.indexOf(" /at ") + 5);
            return new Event(description, startEndDateTime);
        default:
            return null;
        }
    }
}
