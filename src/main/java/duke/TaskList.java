package duke;

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


}
