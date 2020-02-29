package duke.commands;

import duke.commands.Command;

import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Command> list = new ArrayList<Command>();

    public TaskList(ArrayList<Command> loadListDataFromDisk) {
        list = loadListDataFromDisk;
    }

    public TaskList() {
    }

    public void doneTask(int index) {

    }

    public void addTask(Command c) {
        list.add(c);
    }

    public void removeTask(int index) {
        list.remove(index);
    }
}
