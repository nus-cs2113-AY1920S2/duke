import java.util.Scanner;

public class Duke {

    /**
     * List of all tasks added by the user
     */
    private static Task[] taskList;

    /**
     * Number of tasks in taskList
     */
    private static int numTasks;

    public static void main(String[] args) {
        taskList = new Task[100];
        numTasks = 0;
        System.out.println("____________________________________");
        System.out.println("    Hey! I'm Jamun");
        System.out.println("    How can I help you?");
        System.out.println("____________________________________");
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            System.out.println("____________________________________");

            String[] splitTask = command.split(" ");
            String cWord = splitTask[0];
            String task = "";
            for (int i = 1; i < splitTask.length; i++) {
                task += " " + splitTask[i];
            }

            if (command.equals("list")) {
                System.out.println("    Here are the tasks in your list:");
                for (int i = 0; i < numTasks; i++) {
                    System.out.print("    " + (i+1) + ".");
                    System.out.println(taskList[i].toString());
                }
            } else if (cWord.equals("done")) {
                String[] splitTask2 = command.split(" ");
                int taskDoneNum = Integer.parseInt(splitTask2[1]);
                if (taskDoneNum-1 >= numTasks) {
                    System.out.println("    You haven't done that many tasks yet!");
                } else {
                    taskList[taskDoneNum-1].setDone(true);
                    System.out.println("    Good work! I've marked this task as done!");
                    System.out.println(taskList[taskDoneNum-1].toString());
                }
            } else if (cWord.equals("todo")){
                Task t = new Todo(task, numTasks);
                taskList[numTasks] = t;
                numTasks++;
                System.out.println("Got it. I've added this task: ");
                System.out.println("    " + t.toString());
                System.out.println("Now you have " + numTasks + " tasks in the list.");
            } else if (cWord.equals("deadline")){
                String[] splitTask2 = task.split("/by");
                Task t = new Deadline(splitTask2[0], numTasks, splitTask2[1]);
                taskList[numTasks] = t;
                numTasks++;
                System.out.println("Got it. I've added this task: ");
                System.out.println("    " + t.toString());
                System.out.println("Now you have " + numTasks + " tasks in the list.");
            } else if (cWord.equals("event")){
                String[] splitTask2 = task.split("/at");
                Task t = new Event(splitTask2[0], numTasks, splitTask2[1]);
                taskList[numTasks] = t;
                numTasks++;
                System.out.println("Got it. I've added this task: ");
                System.out.println("    " + t.toString());
                System.out.println("Now you have " + numTasks + " tasks in the list.");
            } else {
                Task t = new Task(command, numTasks);
                taskList[numTasks] = t;
                numTasks++;
                System.out.println("    added: " + t.getName());
            }
            System.out.println("____________________________________");
            command = sc.nextLine();
        }

        System.out.println("____________________________________");
        System.out.println("    Bye! See ya next time :)");
        System.out.println("____________________________________");
    }

}

