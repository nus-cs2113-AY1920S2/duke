package Command;

import Duke.Task;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Find tasks based on keyword provided
 */
public class FindCommand extends Command {
    private String searchItem;

    /**
     * Convenience constructor using raw values
     *
     * @param searchItem keyword used to search
     */
    public FindCommand(String searchItem) {
        this.searchItem = searchItem;
    }

    /**
     * Searches for keyword in all task description
     * search term is case insensitive
     */
    @Override
    public void execute() {
        try {
            ArrayList<Task> searchTasks = tasks.getTaskList();
            ArrayList<Task> foundTasks = new ArrayList<>();
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
                    task.printListDetails(taskIndexCounter++);
                }
            } else {
                System.out.println("No match found!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please input a valid keyword!");
        }
    }
}
