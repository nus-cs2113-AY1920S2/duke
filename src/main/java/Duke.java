import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "***John***";
        Task[] tasks = new Task[100];
        String userInput = "";
        int count = 0;

        displayWelcome(logo);

        Scanner in = new Scanner(System.in);

        while (true) {
            userInput = in.nextLine();
            if (userInput.equals("bye")){
                break;
            }
            else if (userInput.equals("list")){
                listTasks(tasks, count);
            }
            else if (userInput.startsWith("done")){
                markTaskAsDone(tasks, userInput);
            }
            else if (userInput.startsWith("todo")){
                count = addTodoTask(userInput, tasks, count);
            }
            else if (userInput.startsWith("deadline")){
                count = addDeadlineTask(userInput, tasks, count);
            }
            else if (userInput.startsWith("event")){
                count = addEventTask(userInput, tasks, count);
            }
            else {
                count = addGeneralTask(userInput, tasks, count);
            }
        }
        System.out.println("Bye. Hope to see you soon!");
    }

    private static void displayWelcome(String logo) {
        System.out.println("Hey! I am " + logo);
        System.out.println("What would you like to do?");
    }

    private static int addGeneralTask(String userInput, Task[] tasks, int count) {
        tasks[count] = new Todo(userInput);
        count++;
        printNewTask(tasks, count);
        return count;
    }

    private static int addTodoTask(String userInput, Task[] tasks, int count) {
        String todoTask = userInput.substring(5);
        tasks[count] = new Todo(todoTask);
        count++;
        printNewTask(tasks, count);
        return count;
    }

    private static int addDeadlineTask(String userInput, Task[] tasks, int count) {
        String[] details = userInput.split("/by ");
        String deadlineTask = details[0].substring(9);
        String date = details[1];
        tasks[count] = new Deadline(deadlineTask, date);
        count++;
        printNewTask(tasks, count);
        return count;
    }

    private static int addEventTask(String userInput, Task[] tasks, int count) {
        String[] details = userInput.split("/at ");
        String deadlineTask = details[0].substring(6);
        String dateAndTime = details[1];
        tasks[count] = new Event(deadlineTask, dateAndTime);
        count++;
        printNewTask(tasks, count);
        return count;
    }

    private static void markTaskAsDone(Task[] tasks, String userInput) {
        int itemNumber = Integer.parseInt((userInput.substring(5)));
        tasks[itemNumber-1].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks[itemNumber-1]);
    }

    private static void printNewTask(Task[] tasks, int count) {
        System.out.println("New task added: ");
        System.out.println(" " + tasks[count-1]);
        System.out.println("Now you have " + count + " tasks in the list.");
    }

    private static void listTasks(Task[] tasks, int count) {
        System.out.println("Here are your tasks:");
        for (int j = 0; j<count; j++) {
            System.out.println(j+1 + ". " + tasks[j]);
        }
    }
}
