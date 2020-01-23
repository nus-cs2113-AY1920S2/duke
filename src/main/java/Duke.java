import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Duke {
    private final List<Task> taskList;
    private final List<String> taskInfoList;

    private Duke(List<Task> taskList, List<String> taskInfoList) {
        this.taskList = taskList;
        this.taskInfoList = taskInfoList;
    }

    public Duke() {
        this.taskList = new ArrayList<>();
        this.taskInfoList = new ArrayList<>();
    }

    public Duke processEventsInput(String input) {
        String[] argsArray = input.split(" ");
        int splitIndex = 0;

        splitIndex = IntStream.range(0, argsArray.length)
                .filter(i -> (argsArray[i].equals("/at")))
                .reduce(0, (x, y) -> x + y);

        String taskName = "";
        for (int j = 1; j < splitIndex; j++) {
            if (j != 1) {
                taskName += (" " + argsArray[j]);
            } else {
                taskName += (argsArray[j]);
            }
        }

        String dateTime = "";
        for (int k = splitIndex + 1; k < argsArray.length; k++) {
            if (k > splitIndex + 1) {
                dateTime += (" " + argsArray[k]);
            } else {
                dateTime += (argsArray[k]);
            }
        }

        this.taskInfoList.add(taskName);
        this.taskInfoList.add(dateTime);
        List<String> newTaskInfoList = this.taskInfoList.stream()
                .collect(Collectors.toList());

        return new Duke(this.taskList, newTaskInfoList);
    }

    public Duke processDeadlinesInput(String input) {
        String[] argsArray = input.split(" ");
        int splitIndex = 0;

        splitIndex = IntStream.range(0, argsArray.length)
                .filter(i -> (argsArray[i].equals("/by")))
                .reduce(0, (x, y) -> x + y);

        String taskName = "";
        for (int j = 1; j < splitIndex; j++) {
            if (j != 1) {
                taskName += (" " + argsArray[j]);
            } else {
                taskName += (argsArray[j]);
            }
        }

        String dateTime = "";
        for (int k = splitIndex + 1; k < argsArray.length; k++) {
            if (k > splitIndex + 1) {
                dateTime += (" " + argsArray[k]);
            } else {
                dateTime += (argsArray[k]);
            }
        }
        this.taskInfoList.add(taskName);
        this.taskInfoList.add(dateTime);
        List<String> newTaskInfoList = this.taskInfoList.stream()
                .collect(Collectors.toList());

        return new Duke(this.taskList, newTaskInfoList);
    }

    public Duke processToDosInput(String input) {
        String[] argsArray = input.split(" ");

        String taskName = "";
        for (int i = 1; i < argsArray.length; i++) {
            if (i != 1) {
                taskName += (" " + argsArray[i]);
            } else {
                taskName += (argsArray[i]);
            }
        }

        this.taskInfoList.add(taskName);
        List<String> newTaskInfoList = this.taskInfoList.stream()
                .collect(Collectors.toList());

        return new Duke(this.taskList, newTaskInfoList);
    }

    public Duke addEvents() {
        String taskName = this.taskInfoList.get(0);
        String dateTime = this.taskInfoList.get(1);
        Events events = new Events(Task.taskCounter, taskName, dateTime);
        this.taskList.add(events);

        List<Task> newTaskList = this.taskList.stream()
                .collect(Collectors.toList());

        printOutput(createAddTaskMessage(events));
        this.taskInfoList.clear();
        return new Duke(newTaskList, this.taskInfoList);
    }

    public Duke addDeadlines() {
        String taskName = this.taskInfoList.get(0);
        String dateTime = this.taskInfoList.get(1);
        Deadlines deadlines = new Deadlines(Task.taskCounter,
                taskName, dateTime);

        this.taskList.add(deadlines);
        List<Task> newTaskList = this.taskList.stream()
                .collect(Collectors.toList());

        printOutput(createAddTaskMessage(deadlines));
        this.taskInfoList.clear();
        return new Duke(newTaskList, this.taskInfoList);
    }

    public Duke addToDos() {
        String taskName = this.taskInfoList.get(0);
        ToDos todos = new ToDos(Task.taskCounter, taskName);
        this.taskList.add(todos);

        List<Task> newTaskList = this.taskList.stream()
                .collect(Collectors.toList());

        printOutput(createAddTaskMessage(todos));
        this.taskInfoList.clear();
        return new Duke(newTaskList, this.taskInfoList);
    }

    public Duke completeTask(int taskId) {
        Task task = this.taskList.get(taskId - 1)
                .makeDone();

        List<Task> newTaskList = this.taskList.stream()
                .map(i -> {
                    return (i.getTaskId() == taskId) ? i.makeDone() : i;
                })
                .collect(Collectors.toList());

        printDoneTask(task);
        return new Duke(newTaskList, this.taskInfoList);
    }

    public String createAddTaskMessage(Task task) {
        String message = "";
        message += ("Got it. I've added this task:\n"
                + "  " 
                + task
                + "\nNow you have " + Task.taskCounter
                + " tasks in the list.\n");
        return message;
    }

    public void printDoneTask(Task task) {
        printBorder();

        String output = "\nNice! I've marked this task as done:\n"
                + ("  " 
                        + task.taskWithSymbol());
        System.out.println(output);

        printBorder();
    }

    public void printList() {
        printBorder();

        System.out.println("\nHere are the tasks "
                + "in your list: ");

        this.taskList
        .stream()
        .forEachOrdered(System.out::println);

        printBorder();
    }

    public void printOutput(String content) {
        String output = "";

        printBorder(); 
        output += ("\n" + content);	  
        System.out.println(output);

        printBorder();
    }

    public void printBorder() {
        System.out.println("_______________________"
                + "________________________");
    }

    @Override
    public String toString() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___| \n";

        logo += ("\nHello! I'm Duke!" 
                + "\nWhat can I do for you today?");
        return logo;
    }
}
