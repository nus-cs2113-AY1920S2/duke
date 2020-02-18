import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class Duke {
    public static void main(String[] args) {
        welcomeMessage();

        try {
            File newFile = new File("./src/main/java/data/duke.txt");
            if (newFile.createNewFile()) {
                System.out.println("File created: " + newFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<Task>();
        Boolean exitProgram = false;

        while (!exitProgram) {
            String input = sc.nextLine();
            String[] inputArray = input.split(" ", 2);

            System.out.println("____________________________________________________________");

            try {
                FileWriter fileWriter = new FileWriter("./src/main/java/data/duke.txt");
                if (input.equals("list")) {
                    viewList(tasks);
                } else if (inputArray[0].equals("todo")) {
                    if (inputArray.length < 2) throw new ToDoEmptyException();
                    addToDo(tasks, inputArray[1]);
                } else if (inputArray[0].equals("deadline")) {
                    addDeadline(tasks, inputArray[1]);
                } else if (inputArray[0].equals("event")) {
                    addEvent(tasks, inputArray[1]);
                } else if (inputArray[0].equals("done")) {
                    markDone(tasks, inputArray[1]);
                } else if (inputArray[0].equals("delete")) {
                    deleteTask(tasks, inputArray[1]);
                } else if (input.equals("bye")) {
                    exitMessage();
                    exitProgram = true;
                } else {
                    throw new InvalidCommandException();
                }
                int counter = 0;
                for (Task task : tasks) {
                    counter++;
                    fileWriter.write(counter + ". " + task);
                }
                fileWriter.close();
            } catch (ToDoEmptyException e) {
                System.out.println("OOPS!!! The description of a todo cannot be empty");
            } catch (InvalidCommandException e) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :(");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            System.out.println("____________________________________________________________");

        }

    }

    private static void deleteTask(List<Task> tasks, String s) {
        int taskIndex = Integer.parseInt(s) - 1;
        Task deleteTask = tasks.get(taskIndex);
        tasks.remove(deleteTask);
        System.out.println("Noted. I've removed this task:");
        System.out.println(deleteTask);
        System.out.format("Now you have %d tasks in the list.\n", tasks.size());
    }

    private static void markDone(List<Task> tasks, String s) {
        int taskIndex = Integer.parseInt(s) - 1;
        Task doneTask = tasks.get(taskIndex);
        doneTask.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(doneTask);
    }

    private static void exitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void addEvent(List<Task> tasks, String s) {
        String[] eventInfo = s.split("/at ", 2);
        Event newEvent = new Event(eventInfo[0], eventInfo[1]);
        tasks.add(newEvent);
        System.out.println("Got it. I've added this task:");
        System.out.println(newEvent);
        System.out.format("Now you have %d tasks in the list.\n", tasks.size());
    }

    private static void addDeadline(List<Task> tasks, String s) {
        String[] deadlineInfo = s.split("/by ", 2);
        Deadline newDeadline = new Deadline(deadlineInfo[0], deadlineInfo[1]);
        tasks.add(newDeadline);
        System.out.println("Got it. I've added this task:");
        System.out.println(newDeadline);
        System.out.format("Now you have %d tasks in the list.\n", tasks.size());
    }

    private static void addToDo(List<Task> tasks, String description) {
        ToDo newToDo = new ToDo(description);
        tasks.add(newToDo);
        System.out.println("Got it. I've added this task:");
        System.out.println(newToDo);
        System.out.format("Now you have %d tasks in the list.\n", tasks.size());
    }

    private static void viewList(List<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        int counter = 0;
        for (Task task : tasks) {
            counter++;
            System.out.println(counter + ". " + task);
        }
    }

    private static void welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }
}
