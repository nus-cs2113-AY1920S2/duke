package duke;

import duke.exception.EmptyListException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Event;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public String taskValidator(int numTasks) {
        String totalTasks = Integer.toString(numTasks);
        if (numTasks <= 1) {
            return "You now have " + totalTasks + " task in the list";
        } else {
            return "You now have " + totalTasks + " tasks in the list";
        }
    }

    public int getListSize() {
        return tasks.size();
    }

    public ArrayList<Task> getTaskArray() {
        return this.tasks;
    }

    public void addTodo(String todoTask) {
        Todo todo = new Todo(todoTask);
        tasks.add(todo);
        System.out.println("Got it! You've added a todo task: ");
        System.out.println(todo.toString());
        System.out.println(taskValidator(tasks.size()));
    }

    public void addEvent(String eventTask, String at) {
        Event event = new Event(eventTask, at);
        tasks.add(event);
        System.out.println("Got it! You've added an event task: ");
        System.out.println(event.toString());
        System.out.println(taskValidator(tasks.size()));
    }

    public void addDeadline(String deadlineTask, String by) {
        Deadline deadline = new Deadline(deadlineTask, by);
        tasks.add(deadline);
        System.out.println("Got it! You've added a deadline task: ");
        System.out.println(deadline.toString());
        System.out.println(taskValidator(tasks.size()));
    }

    public void listTasks() throws EmptyListException {
        int sizeofArray = tasks.size();
        if (sizeofArray != 0) {
            System.out.println("Here are the tasks on your list: ");
            for (int i = 1; i < sizeofArray + 1; i++) {
                String taskNum = Integer.toString(i);
                System.out.println(taskNum + "." + tasks.get(i - 1));
            }
        } else {
            throw new EmptyListException("There are no tasks in the list! Please add some tasks!");
        }
    }

    public void deleteTask(int taskNum) {
        System.out.println("Got it! I've removed this task: ");
        System.out.println(tasks.get(taskNum - 1).toString());
        tasks.remove(taskNum - 1);
        System.out.println(taskValidator(tasks.size()));
    }

    public void markDone(int taskNum) {
        tasks.get(taskNum - 1).markAsDone();
        System.out.println("Awesome! I've marked the following task as done:");
        System.out.println(tasks.get(taskNum - 1).toString());

    }

    public void findTask(String findString) {
        ArrayList<Integer> matchList = new ArrayList<>();
        for (int i = 1; i < tasks.size() + 1; i++) {
            if (tasks.get(i-1).toString().contains(findString)) {
                matchList.add(i);
            }
        }

        if (matchList.size() > 0) {
            System.out.println("I've found the follow tasks matching your search: ");
            for (int i = 0; i < matchList.size(); i++) {
                String index = matchList.get(i).toString();
                System.out.println(index + "." + tasks.get(matchList.get(i) - 1));
            }
        } else {
            System.out.println("Sorry! I could not find any tasks matching your searches!");
        }



    }

}
