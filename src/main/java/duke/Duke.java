package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static final int CAPACITY = 100;
    public static final String BORDER = "____________________________________________________________";
    public static final int TODO = 5;
    public static final int DEADLINE = 9;
    public static final int EVENT = 6;
    public static final int DONE = 5;
    public static final int DATE = 3;
    public static final int DELETE = 7;

    public static void main(String[] args) {
        printWelcomeMessage();
        //Task[] tasks = new Task[CAPACITY];
        int taskCounter = 0;
        Scanner input = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        while(true) {
            String line;
            line = input.nextLine();
            String[] sentence = line.split(" ");
            String taskType = sentence[0].toLowerCase();
            try {
                switch(taskType) {
                case "bye":
                    printByeMessage();
                    break;
                case "list":
                    printList(tasks, taskCounter);
                    break;
                case "todo":
                    line = line.substring(TODO);
                    Task t = new Todo(line);
                    tasks.add(t);
                    //tasks[taskCounter] = t;
                    printAcknowledgement(tasks.get(taskCounter), taskCounter);
                    taskCounter++;
                    break;
                case "deadline":
                    String[] deadlineWords = line.split("/");
                    String deadlineDescription = deadlineWords[0].substring(DEADLINE);
                    String by = deadlineWords[1].substring(DATE);
                    Task d = new Deadline(deadlineDescription, by);
                    tasks.add(d);
                    //tasks[taskCounter] = d;
                    printAcknowledgement(tasks.get(taskCounter), taskCounter);
                    taskCounter++;
                    break;
                case "event":
                    String[] eventWords = line.split("/");
                    String eventDescription = eventWords[0].substring(EVENT);
                    String at = eventWords[1].substring(DATE);
                    Task e = new Event(eventDescription, at);
                    tasks.add(e);
                    //tasks[taskCounter] = e;
                    printAcknowledgement(tasks.get(taskCounter), taskCounter);
                    taskCounter++;
                    break;
                case "done":
                    String doneNumber = line.substring(DONE);
                    int doneTaskNum = Integer.parseInt(doneNumber);
                    tasks.get(doneTaskNum - 1).markAsDone();
                    System.out.println(BORDER);
                    System.out.println("Nice! I've marked this task as done: " + tasks.get(doneTaskNum - 1).description);
                    System.out.println(BORDER);
                    break;
                case "delete":
                    String deleteNumber = line.substring(DELETE);
                    int delTaskNum = Integer.parseInt(deleteNumber);
                    tasks.remove(tasks.get(delTaskNum - 1));
                    System.out.println(BORDER);
                    System.out.println("Noted! I've removed this task:  " + tasks.get(delTaskNum - 1).description);
                    System.out.println("Now you have " + (tasks.size()) + " tasks in your list!");
                    System.out.println(BORDER);
                    taskCounter--;
                    break;
                default:
                    throw new DukeException();
                }
            } catch (DukeException e) {
                System.out.println(BORDER);
                System.out.println("☹ OH NO!!! I'm sorry, but I don't know what that means! :o(");
                System.out.println(BORDER);
            } catch (NullPointerException e) {
                System.out.println(BORDER);
                System.out.println("☹ OH NO!!! There is no such task to be done! :o(");
                System.out.println(BORDER);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(BORDER);
                System.out.println("☹ OH NO!!! The description of a " + taskType + " cannot be empty! :o(");
                System.out.println(BORDER);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(BORDER);
                System.out.println("☹ OH NO!!! The description of a " + taskType + " cannot be empty! :o(");
                System.out.println(BORDER);
            }
        }
    }

    private static void printList(ArrayList<Task> tasks, int taskCounter) {
        System.out.println(BORDER);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCounter; i++){
            System.out.println(i+1 + ". " + tasks.get(i).toString());
        }
        System.out.println(BORDER);
    }

    private static void printAcknowledgement(Task task, int counter) {
        System.out.println(BORDER);
        System.out.println("Got it! I've added this task: ");
        System.out.println(task.toString());
        System.out.println("Now you have " + (counter + 1) + " tasks in your list!");
        System.out.println(BORDER);
    }

    private static void printByeMessage() {
        System.out.println(BORDER);
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(BORDER);
    }

    private static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(BORDER);
        System.out.println("Hello! I'm Duke.Duke");
        System.out.println("What can I do for you?");
        System.out.println(BORDER);
    }
}
