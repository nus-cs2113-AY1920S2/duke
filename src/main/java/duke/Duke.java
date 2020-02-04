package duke;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.Optional;

import duke.task.*;
import duke.exception.DukeException;
import command.*;


public class Duke {
    private final TaskList taskList;
    
    private Duke(TaskList newtaskList) {
        this.taskList = newtaskList;
    }
    
    public Duke() {
        this.taskList = new TaskList();
    }
    
    public Duke executeListCommand() {
        System.out.println("\nHere are the tasks "
                + "in your list: ");
        
        this.taskList.getTasks()
                .stream()
                .forEachOrdered(System.out::println);
        
        return this;

    }
    
    public Duke executeDoneCommand(int taskId) {
        return new Duke(this.taskList.completeTask(taskId));
    }
    
    public Duke executeAddCommand(String commandType, String taskInfo, Optional<String> taskRequirement) {
        Task task;
        switch(commandType) {
        case "todo": 
            task = new ToDos(TaskList.taskIdCounter, taskInfo);
            System.out.println(task);
            break;
        case "deadline": 
            task = new Deadlines(TaskList.taskIdCounter, taskInfo, taskRequirement.get());
            
            break;
        case "event":
            task = new Events(TaskList.taskIdCounter, taskInfo, taskRequirement.get());
            System.out.println(task);
            break;
        default: 
            throw new DukeException("Invalid Tasks");
        }
        
        
        return new Duke(this.taskList.addTask(task));
    }
    
    public TaskList getTaskList() {
        return this.taskList;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*private final List<Task> taskList;
    private final List<String> taskInfoList;
    private final List<Command> commandList;

    public Duke(List<Task> taskList, List<String> taskInfoList, List<Command> commandList) {
        this.taskList = taskList;
        this.taskInfoList = taskInfoList;
        this.commandList = commandList;
    }
    
    public Duke(Duke oldDuke) {
        this.taskList = oldDuke.taskList.stream()
                .collect(Collectors.toList());
       
        this.taskInfoList = oldDuke.taskInfoList.stream()
                .collect(Collectors.toList());
        
        this.commandList = oldDuke.commandList.stream()
                .collect(Collectors.toList());
    }

    public Duke() {
        this.taskList = new ArrayList<>();w
        this.taskInfoList = new ArrayList<>();
        this.commandList = new ArrayList<>();
        
        this.commandList.add(new Add());
        this.commandList.add(new Print());
        this.commandList.add(new Run());
        this.commandList.add(new Complete());
        this.commandList.add(new Parse());
    }
    
    public List<Task> getTaskList() {
        return this.taskList;
    }
    
    public List<String> getTaskInfoList() {
        return this.taskInfoList;
    }
    
    public List<Command> getCommandList() {
        return this.commandList;
    }
    
    public Duke run(String input) {
        Run runner =  (Run) this.commandList.get(CommandInfo.RUN.index);
        Duke newDuke = runner.runCommand(input, this.taskList, this.taskInfoList, this.commandList);
        return new Duke(newDuke);
    } */
    
    
    /*
    public Duke runCommand(String input) throws DukeException {
        String[] argsArray = input.split(" ");
        String command = argsArray[0];
        
        Duke tempDuke = this;
        switch(command) {
        case "todo": 
            if (argsArray.length == 1) {
                throw new DukeException("☹ OOPS!!! The description "
                        + "of a todo cannot be empty.");
            }
            
            tempDuke = this.processToDosInput(input).getAdd().addToDos(this);
            Task.taskCounter++;
            break;
        case "deadline":
            if (argsArray.length == 1) {
                throw new DukeException("☹ OOPS!!! The description "
                        + "of a deadline cannot be empty.");
            }
            tempDuke = this.processDeadlinesInput(input).getAdd().addDeadlines(this);
            Task.taskCounter++;
            break;
        case "event":
            if (argsArray.length == 1) {
                throw new DukeException("☹ OOPS!!! The description "
                        + "of an event cannot be empty.");
            }
            tempDuke = this.processEventsInput(input).getAdd().addEvents(this);
            Task.taskCounter++;
            break;
        case "done": 
            tempDuke = this.completeTask(Integer.parseInt(argsArray[1]));
            break;
        case "list":
            print.printList(this);
            break;
        case "bye": 
            //this.printOutput("Bye. Hope to see you again soon!");
            break;
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, "
                    + "but I don't know what that means :-(");                 
        }

        return new Duke(tempDuke);
    }*/
    
    /*
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

        return new Duke(this.taskList, newTaskInfoList, this.print, this.add);
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

        return new Duke(this.taskList, newTaskInfoList, this.print, this.add);
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

        return new Duke(this.taskList, newTaskInfoList, this.print, this.add);
    } */

    /*
    public Duke addEvents() {
        String taskName = this.taskInfoList.get(TaskInfo.TASKNAME.index);
        String dateTime = this.taskInfoList.get(TaskInfo.DATETIME.index);
        Events events = new Events(Task.taskCounter, taskName, dateTime);
        this.taskList.add(events);

        List<Task> newTaskList = this.taskList.stream()
                .collect(Collectors.toList());

        this.print.printOutput(this.print.createAddTaskMessage(events));
        this.taskInfoList.clear();
        return new Duke(newTaskList, this.taskInfoList, this.print);
    }

    public Duke addDeadlines() {
        String taskName = this.taskInfoList.get(TaskInfo.TASKNAME.index);
        String dateTime = this.taskInfoList.get(TaskInfo.DATETIME.index);
        Deadlines deadlines = new Deadlines(Task.taskCounter,
                taskName, dateTime);
        this.taskList.add(deadlines);
        
        List<Task> newTaskList = this.taskList.stream()
                .collect(Collectors.toList());

        this.print.printOutput(this.print.createAddTaskMessage(deadlines));
        this.taskInfoList.clear();
        return new Duke(newTaskList, this.taskInfoList, this.print);
    }

    public Duke addToDos() {
        String taskName = this.taskInfoList.get(TaskInfo.TASKNAME.index);
        ToDos todos = new ToDos(Task.taskCounter, taskName);
        this.taskList.add(todos);

        List<Task> newTaskList = this.taskList.stream()
                .collect(Collectors.toList());

        this.print.printOutput(this.print.createAddTaskMessage(todos));
        this.taskInfoList.clear();
        return new Duke(newTaskList, this.taskInfoList, this.print);
    }
    */

    /*
    public Duke completeTask(int taskId) {
        Task task = this.taskList.get(taskId - 1)
                .makeDone();

        List<Task> newTaskList = this.taskList.stream()
                .map(i -> {
                    return (i.getTaskId() == taskId) ? i.makeDone() : i;
                })
                .collect(Collectors.toList());

        this.print.printDoneTask(task);
        return new Duke(newTaskList, this.taskInfoList, this.print, this.add);
    } */
    
    
    
    /*
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
                + "________________________"
                + "________________________");
    }
    */

    /*
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
    }*/
}
