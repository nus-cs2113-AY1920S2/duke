package Duke;

import Duke.TaskTypes.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;


public class Ui {

    public Ui() {
    }

    private Scanner initializeScanner() {
        return new Scanner(System.in);
    }

    private String getUserInput(Scanner myInput) {
        return myInput.nextLine();
    }

    public void displayHello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke.Duke\nWhat can I do for you?");
    }

    public void displayGoodbye() {
        System.out.println(System.lineSeparator());
        System.out.println("Bye. Hope to see you again soon! Maybe next time");
    }

    public void displayTaskAdded(Task newTask) {
        System.out.println("Got it. I've added this task: " + newTask.toString());
    }

    public String getUserInput() {
        Scanner myInput = initializeScanner();
        return getUserInput(myInput);
    }

    public void displayOutOfRange() {
        System.out.println("Out of range");
    }

    public void displayEachTask(int i, TaskList tasks) {
        System.out.println(
                Integer.toString(i + 1) + ". " + tasks.getTaskList().get(i).toString());
    }

    public void displayTaskMarkedAsDone(TaskList tasks, int taskListNumber) {
        System.out.println(
                "Nice! I marked this as done: " + tasks.getTaskList().get(taskListNumber - 1).toString());
    }


    public void displayTaskRemoved(String removedTask, int currentNumberOfTasks) {
        System.out.println(
                "Noted. I removed this task: "
                        + System.lineSeparator()
                        + removedTask
                        + System.lineSeparator()
                        + "Now you have "
                        + currentNumberOfTasks
                        + " tasks in the list"
                        + System.lineSeparator());
    }

    public void displayPrompt() {
        System.out.println("What else do you want to do?");
    }
}
