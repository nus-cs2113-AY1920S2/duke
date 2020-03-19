package duke.commands;

import java.util.ArrayList;

/**
 * TaskList class that contains the list of command tasks.
 */
public class TaskList {
    public static ArrayList<Command> list = new ArrayList<Command>();


    /**
     * @param loadListDataFromDisk Constructor that specifies the container of Command type.
     */
    public TaskList(ArrayList<Command> loadListDataFromDisk) {
        list = loadListDataFromDisk;
    }

    public TaskList() {
    }

    /**
     * @param command Command to be added.
     */
    public void addTask(Command command) {
        list.add(command);
    }

    /**
     * @param index Index to be removed.
     */
    public void removeTask(int index) {
        list.remove(index);
    }
}
