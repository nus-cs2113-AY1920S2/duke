package duke.commands;

import duke.util.Parser;
import duke.util.TaskList;
import duke.util.UI;
import duke.exceptions.IllegalDoneTaskException;
import duke.taskmanager.Task;

import java.io.IOException;
import java.util.List;

public class DoneCommand extends Command {
    public static UI ui = new UI();
    public static int taskNo;
    public static String task;

    public DoneCommand() {
        taskNo = TaskList.getSize();
    }

    /**
     * Read and print the current task list, for the
     * user to select a task from that to be mark as done.
     * @param list the current TaskList stored.
     * @return task list after it is marked as done.
     * @throws IllegalDoneTaskException when the task selected is
     *                                  not within the task list
     */
    public List<Task> execute(List<Task> list) throws IllegalDoneTaskException {
        ui.printDoneIntro();
        int indexOfTask = Integer.parseInt(ui.getStringInput());
        System.out.println(indexOfTask);
        if (indexOfTask < taskNo && indexOfTask >= 0) {
            Task task = list.get(indexOfTask);
            System.out.println(task);
            task.markAsDone();
            ui.printRespondToDoneTask(indexOfTask, list.get(indexOfTask));
            return list;
        } else {
            throw new IllegalDoneTaskException();
        }
    }

    public static void retry() throws IOException {
        ui.printExceptionInstruction();
        String doneExceptionInput = ui.getStringInput();
        if (doneExceptionInput.equals("1") || doneExceptionInput.equals("Yes")) {
            Parser.parseCommand("3");
        }
    }
}
