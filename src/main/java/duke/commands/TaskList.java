package duke.commands;

import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Command> list = new ArrayList<Command>();


    /**
     * @param loadListDataFromDisk the constructor that specifies the container of Command type
     */
    public TaskList(ArrayList<Command> loadListDataFromDisk) {
        list = loadListDataFromDisk;
    }

    public TaskList() {
    }

    /**
     * @param c the command to be added
     */
    public void addTask(Command c) {
        list.add(c);
    }

    /**
     * @param index the index to be removed
     */
    public void removeTask(int index) {
        list.remove(index);
    }
}
