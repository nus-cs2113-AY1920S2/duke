package Command;

import Duke.Task;
import Exceptions.NoParameterException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindCommand extends Command {
    private String searchItem;

    public FindCommand(String searchItem) {
        this.searchItem = searchItem;
    }

    @Override
    public void execute() {
        try {
            ArrayList<Task> searchTasks = tasks.getTaskList();
            ArrayList<Task> foundTasks = new ArrayList<>();
            // Create a pattern to be searched
            String regex = "\\b" + searchItem + "\\b";
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            for (Task task : searchTasks) {
                Matcher matchQuery = pattern.matcher(task.getDescription());
                if (matchQuery.find()) {
                    foundTasks.add(task);
                }
            }
            if (!foundTasks.isEmpty()) {
                int taskIndexCounter = 1;
                System.out.println("Here are the matching tasks in your list:");
                for (Task task : foundTasks) {
                    task.printListDetails(taskIndexCounter);
                }
            } else {
                System.out.println("No match found!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please input a valid keyword!");
        }
    }
}
