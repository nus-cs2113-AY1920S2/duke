package Duke;

/** Handle User input(Ui) queries */
public class Ui {
    private static final String BORDER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ";
    private static final String INDENT = "  ";
    private static final String TASK_ERROR_MESSAGE = "Task not found";

    /**
     * Prints a String.
     *
     * @param s String to be printed.
     */
    public void printMessage(String s){
        System.out.println(INDENT + s);
    }

    /**
     * Prints a border.
     */
    public void printBorder(){
        System.out.println(BORDER);
    }

    /**
     * Prints greeting for the user.
     */
    public void greetUser() {
        printBorder();
        printMessage("Hello! I'm Duke");
        printMessage("What can I do for you?");
        printBorder();
    }

    /**
     * Lists out tasks in the list.
     *
     * @param tasks List of tasks.
     */
    public void list(TaskList tasks){
        if(tasks.getSize() == 0){
            printMessage("There are no tasks in your list.");
        }
        else{
            printMessage("Here are the tasks in your list");
            for (int i = 0; i < tasks.getSize(); i++){ //unsure of getSize()
                printMessage(i+1 + "." + tasks.getTasks().get(i).toString());
            }
        }
    }

    /**
     * Prints task to show that it is done.
     *
     * @param taskDescription String that refers to details of the task.
     */
    public void showDoneMessage(String taskDescription){
        printBorder();
        printMessage("Nice! I've marked this task as done: ");
        printMessage(taskDescription);
    }

    /**
     * Prints task to show that it is added to the task list.
     *
     * @param taskDescription String that refers to the details of the task.
     */
    public void showAddedTask(String taskDescription){
        printMessage("Got it! I've added this task");
        printMessage(taskDescription);
    }

    /**
     * Prints task to show that is is deleted from the task list.
     *
     * @param taskDescription String that refers to the details of the task.
     */
    public void showDeletedTask(String taskDescription){
        printMessage("Noted. I've removed this task: ");
        printMessage(taskDescription);
    }

    /**
     * Prints number of tasks left in the list.
     *
     * @param tasks List of tasks.
     */
    public void showTasksLeft(TaskList tasks){
        printMessage("Now you have " + tasks.getSize() + " tasks left in your list.");
    }

    public void showTaskErrorMessage(){
        printMessage(TASK_ERROR_MESSAGE);
    }

    public void exitProgram(){
        printMessage("Bye. Hope to see you again soon!");
    }

    public void showLoadingError(){

    }

}
