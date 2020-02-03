import java.util.ArrayList;

public class TaskManager {
    protected ArrayList<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void executeUserInput(String userInput) {
        String command;
        if (userInput.contains(" ")) {
            command = userInput.substring(0, userInput.indexOf(" "));
        } else {
            command = userInput;
        }

        if (!validateInput(command, userInput)) {
            Ui.printPretty("Sorry, couldn't recognize this input format");
            return;
        }

        if (command.equals("list")) {
            listTasks();
        } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
            Task newTask = getTaskFromUserInput(command, userInput);
            if (newTask == null) {
                Ui.printPretty("Sorry, couldn't recognize this input format");
            } else {
                addTask(newTask);
            }
        } else {
            Ui.printPretty("Sorry, couldn't recognize this input format");
        }
    }

    protected boolean validateInput(String command, String userInput) {
        if (command.equals("todo")) {
            return ToDo.validateUserInput(userInput);
        } else if (command.equals("deadline")) {
            return Deadline.validateUserInput(userInput);
        } else if (command.equals("event")) {
            return Event.validateUserInput(userInput);
        } else if (command.equals("list")) {
            return true; // If command is list then anything after doesn't affect functionality
        }

        return false; // Unrecognized command
    }

    protected Task getTaskFromUserInput(String taskType, String userInput) {
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

    protected void addTask(Task newTask) {
        tasks.add(newTask);

        String message = "Added this task:" + System.lineSeparator() + newTask.toString() + System.lineSeparator() +
                "Now you have " + tasks.size() + " tasks in the list";
        Ui.printPretty(message);
    }

    protected void listTasks() {
        String message = "These are your tasks:" + System.lineSeparator();
        for (int i = 0; i < tasks.size(); i++) {
            String lineEnd = i == tasks.size() - 1 ? "" : System.lineSeparator(); // Do this so no extra newline
            message += (String.format("%d. %s", i + 1, tasks.get(i).toString()) + lineEnd);
        }

        Ui.printPretty(message);
    }
}
