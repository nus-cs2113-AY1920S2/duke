/**
 * Stores instructions to print frequently displayed messages.
 */

public class TextUi {

    //private boolean isUserDone;

    /**
     * Constructor for TextUi.
     */
    public TextUi() {
        //this.isUserDone = false;
    }

    public void printWelcomeMessage() {
        System.out.println(Messages.WELCOME_MESSAGE);
    }

    public void printExitMessage() {
        System.out.println(Messages.EXIT_MESSAGE);
    }

    public void printIndexOutOfBoundsExceptionMessage(Exception exception) {
        System.out.println("You have given the wrong number of arguments or the wrong set of arguments\n" +
                "Please try again or input \"bye\" to exit" + exception.getMessage());
    }

    public void printIllegalArgumentExceptionMessage(Exception exception) {
        System.out.println("Sorry I don't understand that command.\n" + Messages.LIST_OF_COMMANDS +
                exception.getMessage());
    }

    public void printTodoMessage(Task task) {
        System.out.println("Got it. I've added this task:\n  " + task.toString()
                + String.format("\nNow you have %d tasks in the list.", Task.getTotalNumOfTasks()));
    }

    public void printDeadlineMessage(Task task) {
        System.out.println("Got it. I've added this task:\n  " + task.toString()
                + String.format("\nNow you have %d tasks in the list.", Task.getTotalNumOfTasks()));
    }

    public void printEventMessage(Task task) {
        System.out.println("Got it. I've added this task:\n  " + task.toString()
                + String.format("\nNow you have %d tasks in the list.", Task.getTotalNumOfTasks()));
    }

    public void printDeleteMessage(Task task, int numOfTasksLeft) {
        System.out.println("I have removed this task:\n" + task.toString() +
                String.format("\nYou only have %d tasks left to do. 加油!", numOfTasksLeft));
    }
}
