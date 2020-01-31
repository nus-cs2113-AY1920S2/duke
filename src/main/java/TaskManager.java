import java.util.ArrayList;

public class TaskManager {
    protected ArrayList<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<Task>();
    }

    // Something to consider in the future is to put a from string constructor in tasks classes
    // Then move validation into each class

    public void executeUserInput(String userInput) {
        // Get the first word in the userInput
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

        String description;
        switch(command) {
        case "list":
            listTasks();
            break;
        case "todo":
            description = userInput.substring(userInput.indexOf(" ") + 1);
            ToDo newTodo = new ToDo(description);
            addTask(newTodo);
            break;
        case "deadline":
            description = userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf(" /by "));
            String dueDateTime = userInput.substring(userInput.indexOf(" /by ") + 5);
            Deadline newDeadline = new Deadline(description, dueDateTime);
            addTask(newDeadline);
            break;
        case "event":
            description = userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf(" /at "));
            String startEndDateTime = userInput.substring(userInput.indexOf(" /at ") + 5);
            Event newEvent = new Event(description, startEndDateTime);
            addTask(newEvent);
            break;
        default:
            Ui.printPretty("Sorry, couldn't recognize this input format"); // Shouldn't reach here
        }
    }

    protected boolean validateInput(String command, String userInput) {
        if (!command.equals("list") && !command.equals("todo") && !command.equals("deadline") &&
                !command.equals("event")) {
            return false;
        }

        if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
            if (!userInput.contains(" ")) {
                return false;
            }
        }

        if (command.equals("deadline")) {
            if (!userInput.contains(" /by ")) {
                return false;
            }

            // Ensure there is a by time
            if (userInput.indexOf(" /by ") + 5 > userInput.length() - 1) {
                return false;
            }
        }

        if (command.equals("event")) {
            if (!userInput.contains(" /at ")) {
                return false;
            }

            if (userInput.indexOf(" /at ") + 5 > userInput.length() - 1) {
                return false;
            }
        }

        return true;
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
