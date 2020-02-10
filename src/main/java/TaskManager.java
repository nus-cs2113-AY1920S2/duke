import java.util.ArrayList;

public class TaskManager {
    protected ArrayList<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void executeUserInput(String userInput) {
        String command;
        if (userInput.length() == 0) {
            return;
        } else if (userInput.contains(" ")) {
            command = userInput.substring(0, userInput.indexOf(" "));
        } else {
            command = userInput;
        }

        if (command.equals("list")) {
            listTasks();
        } else {
            try {
               Task newTask = getTaskFromUserInput(command, userInput);
               addTask(newTask);
            } catch (BadTaskFormatException e){
                Ui.printPretty(e.getMessage());
            } catch (InvalidTaskException e) {
                Ui.printPretty("That is not a valid command!");
            }
        }
    }

    protected Task getTaskFromUserInput (String taskType, String userInput) throws InvalidTaskException,
            BadTaskFormatException {
        switch(taskType) {
        case "todo":
            return new ToDo(userInput);
        case "deadline":
            return new Deadline(userInput);
        case "event":
            return new Event(userInput);
        default:
            throw new InvalidTaskException();
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
