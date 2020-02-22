package TaskList;

import Exceptions.EmptyListException;
import Task.Task;
import Task.Todo;
import Task.Deadline;
import Task.Events;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void printTaskList() throws EmptyListException {
        if (taskList.isEmpty()) {
            throw new EmptyListException("The list seems to be empty!");
        } else {
            System.out.println("Here are the tasks in your list:");
            int index = 1;
            for (Task task : taskList) {
                System.out.println(String.format("%d. %s", index++, task.toString()));
            }
        }
    }

    public void markTaskAsDone(int itemIndex) throws IndexOutOfBoundsException {
        int listIndex = itemIndex + 1;
        try {
            taskList.get(itemIndex).markAsDone();
            System.out.println(taskList.get(itemIndex).getDoneResponseMessage(listIndex));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You don't have that many items in the list. Please try a number within range!");
        }
    }

    public void deleteTask(int itemIndex) throws IndexOutOfBoundsException {
        try{
            String taskDetails = taskList.get(itemIndex).toString();
            taskList.remove(itemIndex);
            printSuccessfulDeletion(taskDetails);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You don't have that many items in the list. Please try a number within range!");
        }
    }

    public void printSuccessfulDeletion(String taskDetails) {
        Task.reduceNumberOfTaskInList();
        System.out.println(String.format("Removed the task:\n    %s", taskDetails));
        System.out.println(String.format("Now you have %d tasks in the list!",
                Task.getNumberOfTasksInList()));
    }

    public void addNewToDo(String description) {
        Todo todo = new Todo(description);
        taskList.add(todo);
        printSuccessfulAddMessage(todo.toString());
    }

    public void addNewDeadline(String description, String date) {
        Deadline deadline = new Deadline(description, date);
        taskList.add(deadline);
        printSuccessfulAddMessage(deadline.toString());
    }

    public void addNewEvent(String description, String date) {
        Events event = new Events(description, date);
        taskList.add(event);
        printSuccessfulAddMessage(event.toString());
    }

    public void printSuccessfulAddMessage(String taskDetails) {
        System.out.println(String.format("Added the task:\n    %s", taskDetails));
        System.out.println(String.format("Now you have %d tasks in the list!",
                Task.getNumberOfTasksInList()));
    }

    public void findTask(String keyword) {
        int index = 1;
        for (Task task : taskList) {
            if (task.toString().contains(keyword)) {
                if (index == 1) {
                    System.out.println("Here are the matching tasks in your list:");
                }
                System.out.println(String.format("%d. %s", index++, task.toString()));
            }
        }
        if (index == 1) {
            System.out.println("There are no matching results");
        }
    }
}
