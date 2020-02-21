package duke.storage;

import duke.exception.InvalidInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import static duke.common.Messages.WRITE_ERROR_MESSAGE;

/**
 * Loads tasks from file and saves tasks to file.
 */
public class Storage {

    private static int savedType = 0;

    private static int savedStatus = 1;

    private static int savedDescription = 2;

    private static int savedBy = 3;

    public static final String DEFAULT_FILEPATH = "duke.txt";
    File savedData = new File(DEFAULT_FILEPATH);
    Scanner loader;

    /**
     * Writes to the file whenever a task has been deleted or marked as done.
     *
     * @param textUi <code>Ui</code> object that interacts with the user.
     * @param tasks <code>TaskList</code> object that contains all the tasks.
     */
    public void saveChange(Ui textUi, TaskList tasks) {
        try {
            FileWriter textAdder = new FileWriter(DEFAULT_FILEPATH);
            int size = tasks.getSize();
            for (int i = 0; i < size; i += 1) {
                Task newVersion = tasks.getIndex(i);
                if (newVersion instanceof Todo) {
                    textAdder.write("T|" + newVersion.getStatus() + "|" + newVersion.getDescription() + "\n");
                } else if (newVersion instanceof Event) {
                    textAdder.write("E|" + newVersion.getStatus() + "|" + newVersion.getDescription() + "|"
                            + ((Event) newVersion).getDuration() + "\n");
                } else {
                    textAdder.write("D|" + newVersion.getStatus() + "|" + newVersion.getDescription() + "|"
                            + ((Deadline) newVersion).getBy() + "\n");
                }
            }
            textAdder.close();
        } catch (IOException e) {
            textUi.showMessage(WRITE_ERROR_MESSAGE);
        }

    }

    /**
     * Erases all saved tasks from file.
     *
     * @param textUi <code>Ui</code> object that interacts with the user.
     */
    public void performCleanup(Ui textUi) {
        try {
            FileWriter overrider = new FileWriter(DEFAULT_FILEPATH);
            overrider.close();
        } catch (IOException e) {
            textUi.showMessage(WRITE_ERROR_MESSAGE);
        }

    }

    /**
     * Loads tasks from file.
     *
     * @param tasks <code>TaskList</code> object that will store all the tasks.
     * @throws IOException If new file could not be created to store tasks.
     * @throws InvalidInputException If the data stored on file is invalid.
     */
    public void initialiseList (TaskList tasks) throws IOException,InvalidInputException {
        loader = new Scanner(savedData);
        if (!savedData.exists()) {
            savedData.createNewFile();
        }
        while (loader.hasNext()) {
            String command = loader.nextLine();
            String[] phrases = command.split("\\|");
            switch(phrases[savedType]) {
            case "T":
                Todo newTodo = new Todo(phrases[savedDescription]);
                if (phrases[savedStatus].equals("Complete")) {
                    newTodo.markAsDone();
                }
                tasks.add(newTodo);
                break;
            case "D":
                Deadline newDeadline = new Deadline(phrases[savedDescription],phrases[savedBy]);
                if (phrases[savedStatus].equals("Complete")) {
                    newDeadline.markAsDone();
                }
                tasks.add(newDeadline);
                break;
            case "E":
                Event newEvent = new Event(phrases[savedDescription],phrases[savedBy]);
                if (phrases[savedStatus].equals("Complete")) {
                    newEvent.markAsDone();
                }
                tasks.add(newEvent);
                break;
            default:
                throw new InvalidInputException();
            }
        }
    }

    /**
     * Writes a new to-do task to file.
     *
     * @param textUi <code>Ui</code> object that interacts with the user.
     * @param description Description of the to-do task.
     */
    public void writeTodo(Ui textUi,String description) {
        try {
            FileWriter textAdder = new FileWriter(DEFAULT_FILEPATH, true);
            textAdder.write("T|" + "Not complete" + "|" + description + "\n");
            textAdder.close();
        } catch (IOException e) {
            textUi.showMessage(WRITE_ERROR_MESSAGE);
        }
    }

    /**
     * Writes a new deadline to file.
     *
     * @param textUi <code>Ui</code> object that interacts with the user.
     * @param description Description of the deadline.
     * @param by Time by which the task has to be finished.
     */
    public void writeDeadline(Ui textUi,String description,String by) {
        try {
            FileWriter textAdder = new FileWriter(DEFAULT_FILEPATH,true);
            textAdder.write("D|" + "Not complete" + "|" + description + "|" + by + "\n");
            textAdder.close();
        } catch (IOException e) {
            textUi.showMessage(WRITE_ERROR_MESSAGE);
        }
    }

    /**
     * Writes a new event to file.
     *
     * @param textUi <code>Ui</code> object that interacts with the user.
     * @param description Description of the event.
     * @param duration Duration of the event.
     */
    public void writeEvent(Ui textUi,String description,String duration) {
        try {
            FileWriter textAdder = new FileWriter(DEFAULT_FILEPATH, true);
            textAdder.write("E|" + "Not complete" + "|" + description + "|" + duration + "\n");
            textAdder.close();
        } catch (IOException e) {
            textUi.showMessage(WRITE_ERROR_MESSAGE);
        }
    }
}
