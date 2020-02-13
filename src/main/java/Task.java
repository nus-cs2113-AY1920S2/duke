public class Task {
    public String filteredTask;

    public Task(String task){
        this.filteredTask = task;
    }

    public void printMessage(int size) {
        System.out.println("    ____________________________________________________________\n"
                + "     Got it. I've added this task:\n       "
                + filteredTask
                + "\n     Now you have " + size + " tasks in the list."
                + "\n    ____________________________________________________________");
    }
}
