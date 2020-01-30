import java.util.ArrayList;

public class TaskManager {
    protected ArrayList<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<Task>();
    }

    public void executeUserInput(String userInput) {
        // Get the first word in the userInput
        String command;
        if (userInput.contains(" ")) {
            command = userInput.substring(0, userInput.indexOf(" "));
        } else {
            command = userInput;
        }

        switch(command) {
        case "list":
            listTasks();
            break;
        case "todo":
            ToDo newTodo = new ToDo("this is a todo");
            addTask(newTodo);
            break;
        case "deadline":
            Deadline newDeadline = new Deadline("this is a deadline", "this is a due time");
            addTask(newDeadline);
            break;
        case "event":
            Event newEvent = new Event("this is an event", "this is an event time");
            addTask(newEvent);
            break;
        default:
            Ui.printPretty("Sorry, couldn't recognize this input format");
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
