package duke;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import duke.exceptions.EmptyListException;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int sizeOfList() {
        return tasks.size();
    }

    public ArrayList<Task> getTaskArray() {
        return this.tasks;
    }

    public void todoTask(String taskToAdd) {
        Todo todo = new Todo(taskToAdd);
        tasks.add(todo);
        System.out.println("Got it. I've added this task:" + System.lineSeparator()
                + tasks.get(tasks.size() - 1) + System.lineSeparator()
                + "You now have " + tasks.size() + " task(s) in the list.");
    }

    public void eventTask(String eventToAdd, String at){
        Event event = new Event(eventToAdd, at);
        tasks.add(event);
        System.out.println("Got it. I've added this task:" + System.lineSeparator()
                + tasks.get(tasks.size() - 1) + System.lineSeparator()
                + "You now have " + tasks.size() + " task(s) in the list.");
    }

    public void deadlineTask(String deadlineToAdd, String by) {
        Deadline deadline = new Deadline(deadlineToAdd, by);
        tasks.add(deadline);
        System.out.println("Got it. I've added this task:" + System.lineSeparator()
                + tasks.get(tasks.size() - 1) + System.lineSeparator()
                + "You now have " + tasks.size() + " task(s) in the list.");
    }

    public void listCommand() throws EmptyListException {
        int sizeOfList = tasks.size();
        if (sizeOfList != 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < sizeOfList ; i += 1) {
                System.out.println(i + 1 + ". " + tasks.get(i));
            }
        } else {
            throw new EmptyListException("No tasks in the list.");
        }
    }

    public void deleteCommand(int index){
        System.out.println("Got it! I've removed this task: ");
        System.out.println(tasks.get(index - 1));
        tasks.remove(index - 1);
        System.out.println( "You now have " + tasks.size() + " task(s) in the list.");
    }

    public void doneCommand(int index){
        tasks.get(index - 1).updateIsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(tasks.get(index - 1));
    }

}
