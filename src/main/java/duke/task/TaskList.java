package duke.task;

import duke.DukeException;
import duke.ui.TextUi;
import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;
    private TextUi ui = new TextUi();

    private static final String DIVIDER = "===================================================";
    private static final String HAPPY_FACE = "(＾▽＾)";
    private static final String SAD_FACE = "(╥_╥)";

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> Tasks) {
        this.tasks = Tasks;
    }

    public void findTasks(String keyword) {
        ArrayList<Task> relatedTasks = new ArrayList<>();
        for (int i = 1; i <= tasks.size(); i++) {
            if (tasks.get(i-1).getDescription().contains(keyword)) {
                relatedTasks.add(tasks.get(i-1));
            }
        }
        TextUi.printSearchInformation(relatedTasks, keyword);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void addToDo(String description) {
        tasks.add(new ToDo(description));
    }

    public void addEvent(String description, String at) {
        tasks.add(new Event(description, at));
    }

    public void addDeadline(String description, String by) {
        tasks.add(new Deadline(description, by));
    }


    public int getSize() {
        return (tasks.size() - 1);
    }

    public void deleteTask(String index) {
        int removeTask = Integer.parseInt(index) - 1;
        String taskInformation = String.valueOf(tasks.get(removeTask));
        System.out.println(String.format("%50s", "Noted. I've removed this task: " + HAPPY_FACE));
        System.out.println(String.format("%50s", taskInformation));
        tasks.remove(removeTask);
        System.out.println(String.format("%50s", "Now you have " + tasks.size() + " tasks in the list."));
        System.out.println(DIVIDER);
    }

    public void addEvent(String description) {
        try {
            String[] event;
            event = description.split("/at", 2);
            event[0] = event[0].trim();
            if (!event[0].isEmpty()) {
                String time = event[1].trim();
                if (time.isEmpty()) {
                    TextUi.printIncompleteInformation();
                }
                tasks.add(new Event(event[0], time));
                TextUi.printEvent(tasks);
            } else {
                //catch empty string
                throw new DukeException();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(String.format("%50s", "Oops! Please include /at follows by the date " + SAD_FACE));
            System.out.println(DIVIDER);
        } catch (DukeException e) {
            TextUi.printIncompleteInformation();
        }
    }

    public void addDeadline(String description) {
        try {
            String[] deadline;
            deadline = description.split("/by", 2);
            deadline[0] = deadline[0].trim();
            if (!deadline[0].isEmpty()) {
                String time = deadline[1].trim();
                if (time.isEmpty()) {
                    TextUi.printIncompleteInformation();
                }
                tasks.add(new Deadline(deadline[0], time));
                TextUi.printDeadline(tasks);
            } else {
                //catch empty string
                throw new DukeException();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(String.format("%50s", "Oops! Please include /by follows by the date " + SAD_FACE));
            System.out.println(DIVIDER);
        } catch (DukeException e) {
            TextUi.printIncompleteInformation();
        }
    }

    public void addToDo(String[] parseInput) {
        try {
            if (!parseInput[1].isEmpty()) {
                tasks.add(new ToDo(parseInput[1]));
                System.out.println(String.format("%50s", "Got it. I've added this task:"));
                System.out.println(String.format("%50s", tasks.get(tasks.size() - 1)));
                System.out.println(String.format("\n%50s", tasks.size() + " tasks in the list " + SAD_FACE));
                System.out.println(DIVIDER);
            } else {
                throw new DukeException();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            TextUi.printIncompleteInformation();
        } catch (DukeException e) {
            System.out.println(String.format("%50s", "Please include task description. " + SAD_FACE));
            System.out.println(DIVIDER);
        }
    }

    public void list() {
        if (tasks.size() == 0) {
            TextUi.printEmptyList();
            return;
        }
        TextUi.printTasks(tasks);
    }

    public ArrayList<Task> getTaskArray() {
        return tasks;
    }
}



