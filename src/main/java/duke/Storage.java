package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringJoiner;

public class Storage {
    private static final String DEFAULT_FILE_PATH = "./dukeData.txt";
    private String filePath;

    public Storage() {
        this(DEFAULT_FILE_PATH);
    }

    public Storage(String path) {
        this.filePath = path;
    }

    public TaskList load() throws DukeException, FileNotFoundException {
        File f = new File(filePath);
        Scanner scanner = new Scanner(f);
        TaskList taskList = new TaskList();

        while (scanner.hasNextLine()) {
            String taskLine = scanner.nextLine();
            switch (taskLine.charAt(0)) {
            case 'T':
                // todo
                taskList.addTask(Todo.decodeTask(taskLine));
                break;
            case 'D':
                // deadline
                taskList.addTask(Deadline.decodeTask(taskLine));
                break;
            case 'E':
                // event
                taskList.addTask(Event.decodeTask(taskLine));
                break;
            default:
                throw new DukeException("Unknown save file format encountered"
                        + System.lineSeparator()
                        + "Exit now to manually fix and retain saved data");
            }
        }
        return taskList;
    }

    public void save(TaskList taskList) throws IOException {
        // TODO: remove this check after changing to ArrayList
        if (taskList.getNumTasks() == 0) {
            // taskList is empty, nothing to save
            return;
        }
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        for (int i = 0; i < taskList.getNumTasks(); ++i) {
            sj.add(taskList.getTasks()[i].encodeTask());
        }

        FileWriter fw = new FileWriter(filePath); // throws IOException
        fw.write(sj.toString()); // throws IOException
        fw.close();
    }
}
