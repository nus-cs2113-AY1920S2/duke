package duke.storage;
import duke.taskList.TaskList;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;
import java.util.*;

public class ReadTaskList {

    public static TaskList accessTaskList(List<String> saveTaskList) throws TaskList.DuplicateTaskException {
        final List<Task> readTasks = new ArrayList<>();
        for (String saveTask : saveTaskList) {
            readTasks.add(accessTask(saveTask));
        }
        return new TaskList(readTasks);
    }

    private static Task accessTask(String savedTask) {
        String[] parse = savedTask.split("\\s*\\|\\s*");
        if (parse.length == 3) {
            ToDos t = new ToDos(parse[2]);
            if (parse[1].equals("1")) {
                t.markAsDone();
            }
            return t;
        } else if (parse.length == 4) {
            if (parse[0].equals("D")) {
                Deadlines d = new Deadlines(parse[2], parse[3]);
                if (parse[1].equals("1")) {
                    d.markAsDone();
                }
                return d;
            } else {
                Events e = new Events(parse[2], parse[3]);
                if (parse[1].equals("1")) {
                    e.markAsDone();
                }
                return e;
            }
        }
        return null;
    }
}
