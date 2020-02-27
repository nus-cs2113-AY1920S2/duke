package src.main.java;

import src.main.java.duke.exceptions.AlreadyDoneException;
import src.main.java.duke.exceptions.InvalidDateException;
import src.main.java.duke.exceptions.InvalidDoneException;
import src.main.java.duke.task.Deadline;
import src.main.java.duke.task.Event;
import src.main.java.duke.task.Task;
import src.main.java.duke.task.Todo;

class Duke_Static_Methods {
    static void addNewEvent(Task[] taskList, String s) throws InvalidDateException {
        String[] taskDetails = s.split(" /at ", 2);
        if (taskDetails.length == 1) {
            throw new InvalidDateException();
        }
        Event event = new Event(taskDetails[0], taskDetails[1]);
        addTaskInList(event, taskList);
        printAddedTask(taskList);
    }

    static void addNewDeadline(Task[] taskList, String taskDescription) throws InvalidDateException {
        String[] taskDetails = taskDescription.split(" /by ", 2);
        if (taskDetails.length == 1) {
            throw new InvalidDateException();
        }
        Deadline deadline = new Deadline(taskDetails[0], taskDetails[1]);
        addTaskInList(deadline, taskList);
        printAddedTask(taskList);
    }

    static void printAddedTask(Task[] taskList) {
        System.out.println("  " + taskList[Task.getTotalNumberOfTask() - 1].toString());
        System.out.println("Now you have " + Task.getTotalNumberOfTask() + " tasks in the list");
    }

    static void greetUser() {
        System.out.println("Hello from Optimus Prime");
        System.out.println("What can I do for you?");
    }

    static void addTaskInList(Task taskDescription, Task[] taskList) {
        System.out.println("Got it. I've added this task");
        taskList[Task.getTotalNumberOfTask() - 1] = taskDescription;
    }

    static void markedAsDone(String numberInput, Task[] taskList) throws InvalidDoneException, AlreadyDoneException {
        int taskNumber = Integer.parseInt(numberInput) - 1;
        if (taskNumber == -1 || taskNumber >= taskList.length) {
            throw new InvalidDoneException();
        }
        if (taskList[taskNumber].getIsDone()) {
            throw new AlreadyDoneException();
        } else {
            taskList[taskNumber].completedTask();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + taskList[taskNumber].toString());
        }
    }

    static void listTasks(Task[] taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.getTotalNumberOfTask(); i++) {
            System.out.println((i + 1) + "." + taskList[i].toString());
        }
    }

    static void addNewTodo(Task[] taskList, String taskDescription) {
        Todo toDo = new Todo(taskDescription);
        addTaskInList(toDo, taskList);
        printAddedTask(taskList);
    }
}
