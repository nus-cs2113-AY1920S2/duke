package duke.tasklist;

import duke.task.Tasks;
import duke.constants.Messages;

import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Tasks> list;

    public TaskList() {
    }

    public TaskList(ArrayList<Tasks> list) {
        this.list = list;
    }

    public int getTaskCount() {
        return list.size();
    }

    public Tasks getTaskList(int i) {
        return list.get(i);
    }

    public static void completeTask(int i) {
        list.get(i).markAsDone();
        System.out.println(Messages.divider + "\n" + Messages.MESSAGE_MARK_DONE+ "\n" + "  " + list.get(i).toString());
        System.out.println(Messages.divider);
    }
    public static void deleteTask(int i) {
        System.out.println(Messages.divider+ "\n" + Messages.MESSAGE_REMOVED_TASKS + "\n" + "  "+ list.get(i).toString());
        list.remove(i);
        System.out.println(Messages.MESSAGE_LIST_START + list.size() + Messages.MESSAGE_LIST_END);
        System.out.println(Messages.divider);
    }

    public static void addTasks(Tasks t) {
        list.add(t);
        if (list.size() == 1) {
            System.out.println(Messages.divider + "\n" + Messages.MESSAGE_ADD_TASK +"\n" +  "   " + t.toString() + "\n" + Messages.MESSAGE_LIST_START + list.size() + Messages.MESSAGE_LIST_END);
            System.out.println(Messages.divider);
        }
        else {
            System.out.println( Messages.divider + "\n" + Messages.MESSAGE_ADD_TASK+"\n"
                    + "   " + t.toString() + "\n" + Messages.MESSAGE_LIST_START + list.size() + Messages.MESSAGE_LIST_END);
            System.out.println(Messages.divider);
        }
    }
    public static void listTask () {
        System.out.println(Messages.divider);
        System.out.println(Messages.MESSAGE_LIST_TASKS);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i+1 + "." + list.get(i).toString());
        }
        System.out.println(Messages.divider);
    }

    public static void find(String match) {
        System.out.println(Messages.divider);
        System.out.println(Messages.MESSAGE_MATCHING_TASKS);
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).description.contains(match)) {
                System.out.println(count+1 + "." + list.get(i).toString());
                count++;
            }
        }
        System.out.println(Messages.divider);
    }

}

