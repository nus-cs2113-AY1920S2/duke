package commands;

import exceptions.MissingSearchQueryException;
import storage.Storage;
import tasks.Task;
import java.util.ArrayList;

/**
 * Represents all the logic used in find command
 */
public class FindCommand extends TaskList{

    protected Storage storage;
    protected ArrayList<Task> tasks;
    protected ErrorMessages errorMessages;

    public FindCommand() {
        storage = new Storage();
        tasks = new ArrayList<Task>();
        errorMessages = new ErrorMessages();
    }

    /**
     * Returns an ArrayList containing the search results
     * @param input String input by user
     * @return ArrayList of search results
     */
    public ArrayList<Task> getSearchResults(String input) {
        String descriptionLowerCase = input.toLowerCase();
        ArrayList<Task> results = new ArrayList<Task>();
        tasks = storage.loadTasks();
        for (Task task : tasks) {
            String taskDescription = task.getDescription().toLowerCase();
            if (taskDescription.contains(descriptionLowerCase)) {
                results.add(task);
            }
        }
        return results;
    }

    /**
     * Prints search results
     * @param description search query
     */
    public void printSearchResults(String description) {
        ArrayList<Task> results = getSearchResults(description);
        if (results.size() == 0) {
            System.out.println(" [WARNING: There are no tasks that matched the search queries]");
        } else {
            System.out.println(" Here are the matching tasks in your list:");
            int count = 1;
            for (Task task: results) {
                System.out.println("  " + count + ". " + task);
                count += 1;
            }
        }
    }

    /**
     * Prints search results based on query given
     * @param input String input by user
     * @throws MissingSearchQueryException if search query is missing
     */
    public void findSearchQuery(String input) throws MissingSearchQueryException {
        int lengthOfCommand = 4;
        String searchQuery = input.substring(lengthOfCommand).trim();
        if (searchQuery.length() == 0) {
            errorMessages.findErrorMessage();
            throw new MissingSearchQueryException();
        } else {
            printSearchResults(searchQuery);
        }
    }
}
