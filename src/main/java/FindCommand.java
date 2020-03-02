public class FindCommand extends Command {
    public FindCommand(String command) {
        super(command);
        execute(command);
    }

    public void execute(String command) {
        textUi.printFindMessage(command);
        String keyword = command.substring(5);
        boolean isFound = false;
        for (Task task : Duke.tasks) {
            if (task.getTaskName().contains(keyword)) {
                System.out.println(task.getTaskId() + ". " + task.toString());
                isFound = true;
            }
        }
        if (!isFound) {
            System.out.println("Sorry there are no matches");
        }

    }
}
