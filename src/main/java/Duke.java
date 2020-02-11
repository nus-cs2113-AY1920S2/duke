import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\t____________________________________________________________\n"
                + "\tHello! I'm Duke\n" +
                "\tWhat can I do for you?\n"
                + "\t____________________________________________________________\n");


        Task[] taskList = new Task[100];
        int taskCount = 0;

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        while (!line.equals("bye")){
            String[] words = line.split(" ",2); // split the first word from the rest of the sentence using space as the delimiter
            try {
                taskCount = handleCommand(taskList, taskCount, line, words);
            } catch (EmptyDoneException e) {
                printEmptyDoneExceptionMessage();
            } catch (UnknownWordException e) {
                printUnknownWordExceptionMessage();
            } catch (EmptyTodoException e) {
                printEmptyTodoExceptionMessage();
            } catch (EmptyDeadlineException e) {
                printEmptyDeadlineExceptionMessage();
            } catch (EmptyEventException e) {
                printEmptyEventExceptionMessage();
            }
            line = in.nextLine();
        }
        printGoodbyeMessage();
    }

    private static int handleCommand(Task[] taskList, int taskCount, String line, String[] words) throws EmptyDoneException, UnknownWordException, EmptyTodoException, EmptyDeadlineException, EmptyEventException {
        if (line.equals("list")) {
            printListMessage(taskList, taskCount);
        } else if (words[0].equals("done")) {
            if (words.length < 2){
                throw new EmptyDoneException();
            }
            int taskNum = Integer.parseInt(words[1]);
            Task t=taskList[taskNum-1];
            t.markAsDone();
            printDoneMessage(t);
        } else if (words[0].equals("todo") || words[0].equals("deadline") || words[0].equals("event")){
            String taskType = words[0];
            Task t = new Task(line);
            switch (taskType){
                case "todo":
                    if (words.length < 2){
                        throw new EmptyTodoException();
                    }
                    t = new Todo(words[1]);
                    break;
                case "deadline":
                    if (words.length < 3){
                        throw new EmptyDeadlineException();
                    }
                    String[] deadlineWords = words[1].split(" /by ", 2); // split the deadline task from the by string using /by as the delimiter
                    t = new Deadline(deadlineWords[0], deadlineWords[1]);
                    break;
                case "event":
                    if (words.length < 3){
                        throw new EmptyEventException();
                    }
                    String[] eventWords = words[1].split(" /at ", 2); // split the event task from the by string using /at as the delimiter
                    t = new Event(eventWords[0], eventWords[1]);
                    break;
            }
            taskList[taskCount] = t;
            taskCount++;
            printAddTaskMessage(taskCount, t);
        } else {
            throw new UnknownWordException();
        }
        return taskCount;
    }

    private static void printListMessage(Task[] taskList, int taskCount) {
        System.out.println("\t____________________________________________________________\n"
                +"\t Here are the tasks in your list:");
        for(int i=0; i<taskCount; ++i){
            Task t = taskList[i];
            System.out.println("\t " + (i+1) + ". " + t.toString());
        }
        System.out.println("\t____________________________________________________________\n");
    }

    private static void printDoneMessage(Task t) {
        System.out.println("\t____________________________________________________________\n"
                +"\t Nice! I've marked this task as done:\n"
                +"\t" + t.toString() + "\n"
                +"\t____________________________________________________________\n");
    }

    private static void printAddTaskMessage(int taskCount, Task t) {
        System.out.println("\t____________________________________________________________\n"
                + "\t Got it. I've added this task:\n"
                + "\t   " + t.toString() + "\n"
                + "\t Now you have " + taskCount +" tasks in the list.\n"
                + "\t____________________________________________________________\n");
    }

    private static void printGoodbyeMessage() {
        System.out.println("\t____________________________________________________________\n"
                + "\t Bye. Hope to see you again soon!\n"
                + "\t____________________________________________________________\n");
    }

    private static void printEmptyTodoExceptionMessage() {
        System.out.println("\t____________________________________________________________\n"
                + "\t ☹ OOPS!!! The description of a todo must contain a task name in this format:\n"
                + "\t todo <task name>\n"
                + "\t____________________________________________________________\n");
    }

    private static void printEmptyDoneExceptionMessage() {
        System.out.println("\t____________________________________________________________\n"
                + "\t☹ OOPS!!! The description of a done must contain a task number in this format:\n"
                + "\t done <task number>\n"
                + "\t____________________________________________________________\n");
    }

    private static void printEmptyDeadlineExceptionMessage() {
        System.out.println("\t____________________________________________________________\n"
                + "\t ☹ OOPS!!! The description of a deadline must contain a task name and a date in this format:\n"
                + "\t deadline <task name> /by <date>\n"
                + "\t____________________________________________________________\n");
    }

    private static void printEmptyEventExceptionMessage() {
        System.out.println("\t____________________________________________________________\n"
                + "\t ☹ OOPS!!! The description of an event must contain a task name and a location in this format:\n"
                + "\t event <task name> /at <location>\n"
                + "\t____________________________________________________________\n");
    }

    private static void printUnknownWordExceptionMessage() {
        System.out.println("\t____________________________________________________________\n"
                + "\t ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + "\t____________________________________________________________\n");
    }
}
