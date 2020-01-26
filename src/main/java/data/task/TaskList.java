package data.task;

import data.task.Task;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskList implements Iterable<Task> {

    private final List<Task> internalList = new ArrayList<>();

    /**
     * Constructs empty person list.
     */
    public TaskList() {}

    /**
     * Checks if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Task toCheck) {
        for (Task p : internalList) {
            if (p.isSamePerson(toCheck)) {
                return true;
            }
        }
        return false;
    }


    public List<Task> getInternalList() {
        return internalList;
    }

    /**
     * Adds a person to the list.
     */
    public void add(Task toAdd)  {
        internalList.add(toAdd);
    }

    /**
     * Clears all persons in list.
     */
    public void clear() {
        internalList.clear();
    }

    @Override
    public Iterator<Task> iterator() {
        return internalList.iterator();
    }

}
