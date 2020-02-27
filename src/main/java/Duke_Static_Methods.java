package src.main.java;

import src.main.java.duke.exceptions.AlreadyDoneException;
import src.main.java.duke.exceptions.InvalidDateException;
import src.main.java.duke.exceptions.InvalidDoneException;
import src.main.java.duke.task.Deadline;
import src.main.java.duke.task.Event;
import src.main.java.duke.task.Task;
import src.main.java.duke.task.Todo;

import java.util.ArrayList;

class Duke_Static_Methods {
    static void addNewEvent(ArrayList<Task> taskList, String s) throws InvalidDateException {
        String[] taskDetails = s.split(" /at ", 2);
        if (taskDetails.length == 1) {
            throw new InvalidDateException();
        }
        Event event = new Event(taskDetails[0], taskDetails[1]);
        addTaskInList(event, taskList);
        printAddedTask(taskList);
    }

    static void addNewDeadline(ArrayList<Task> taskList, String taskDescription) throws InvalidDateException {
        String[] taskDetails = taskDescription.split(" /by ", 2);
        if (taskDetails.length == 1) {
            throw new InvalidDateException();
        }
        Deadline deadline = new Deadline(taskDetails[0], taskDetails[1]);
        addTaskInList(deadline, taskList);
        printAddedTask(taskList);
    }

    static void printAddedTask(ArrayList<Task> taskList) {
        System.out.println("  " + taskList.get(Task.getTotalNumberOfTask() - 1).toString());
        System.out.println("Now you have " + Task.getTotalNumberOfTask() + " tasks in the list");
    }

    static void greetUser() {
        System.out.println("Hello from Optimus Prime");
        System.out.println("What can I do for you?");
    }

    static void addTaskInList(Task task, ArrayList<Task> taskList) {
        System.out.println("Got it. I've added this task");
        taskList.add(task);
    }

    static void markedAsDone(ArrayList<Task> taskList, String numberInput) throws InvalidDoneException, AlreadyDoneException {
        int taskNumber = Integer.parseInt(numberInput) - 1;
        if (taskNumber == -1 || taskNumber >= taskList.size()) {
            throw new InvalidDoneException();
        }
        if (taskList.get(taskNumber).getIsDone()) {
            throw new AlreadyDoneException();
        } else {
            taskList.get(taskNumber).completedTask();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + taskList.get(taskNumber).toString());
        }
    }

    static void listTasks(ArrayList<Task> taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.getTotalNumberOfTask(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i).toString());
        }
    }

    static void addNewTodo(ArrayList<Task> taskList, String taskDescription) {
        Todo toDo = new Todo(taskDescription);
        addTaskInList(toDo, taskList);
        printAddedTask(taskList);
    }

    static void deleteTask(ArrayList<Task> taskList, String taskNumber) {
        System.out.println("  " + taskList.get(Integer.parseInt(taskNumber) - 1).toString());
        taskList.remove(Integer.parseInt(taskNumber) - 1);
        Task.reduceTotalNumberOfTask();
        System.out.println("Now you have " + Task.getTotalNumberOfTask() + " tasks in the list");
    }
}
