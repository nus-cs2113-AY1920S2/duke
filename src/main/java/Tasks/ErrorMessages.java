package Tasks;

public class ErrorMessages {
    public ErrorMessages() {}

    public void doneErrorMessage() {
        System.out.println(" done: marks task to be completed");
        System.out.println(" Parameters: done {TASK INDEX}");
        System.out.println(" Example: done 1");
    }

    public void toDoErrorMessage() {
        System.out.println(" todo: Adds todo items");
        System.out.println(" Parameters: todo {DESCRIPTION}");
        System.out.println(" Example: todo read book");
    }

    public void eventErrorMessage() {
        System.out.println(" event: Adds event tasks");
        System.out.println(" Parameters: event {DESCRIPTION} /at {LOCATION/TIME}");
        System.out.println(" Example: event project meeting /at Mon 2-4pm");
    }

    public void deadlineErrorMessage() {
        System.out.println(" deadline: Adds deadline tasks");
        System.out.println(" Parameters: deadline {DESCRIPTION} /by {DATE/TIME}");
        System.out.println(" Example: deadline return book /by Sunday");
        System.out.println(" Disclaimer: Description field can be left empty");
    }

    public void deleteErrorMessage() {
        System.out.println(" delete: deletes task from stored list");
        System.out.println(" Parameters: delete [TASK NUMBER]");
        System.out.println(" Example: delete 1");
    }

    public void findErrorMessage() {
        System.out.println(" find: Searches through the tasks for matching queries");
        System.out.println(" Parameters: find [query]");
        System.out.println(" Example: find books");
    }
}
