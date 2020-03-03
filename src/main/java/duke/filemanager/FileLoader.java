package duke.filemanager;

import duke.parser.Parser;
import duke.printer.Printer;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

import java.io.File;
import java.util.List;
import java.util.Scanner;

/**
 * Loads from the save file into storage
 */
public class FileLoader {
    public static final int TASK_TYPE_INDEX = 0;
    public static final int DESCRIPTION_INDEX = 2;
    public static final int IS_DONE_INDEX = 1;
    public static final int DEADLINE_INDEX = 3;
    public static final int EVENT_AT_INDEX = 3;

    /**
     * Loads and store from the save file to the storage
     *
     * @param myTasks destination, stores from f into myTasks
     * @param f source, reads from f and stores into myTasks.
     */
    public static void loadFile(Storage myTasks, File f) {
        try  (Scanner s = new Scanner(f)){

            while (s.hasNext()) {
                String sentence = s.nextLine();
                List<String> parsedSentence = Parser.parseFile(sentence);

                String taskType = parsedSentence.get(TASK_TYPE_INDEX);
                String description = parsedSentence.get(DESCRIPTION_INDEX);
                int isDone = Integer.parseInt(parsedSentence.get(IS_DONE_INDEX));

                switch (taskType) {
                case "T":
                    ToDo toDoTask = new ToDo(isDone, description);
                    myTasks.storeTasks(toDoTask);
                    continue;

                case "D":
                    String dateline = parsedSentence.get(DEADLINE_INDEX);
                    Deadline deadlineTask = new Deadline(isDone, description, dateline);
                    myTasks.storeTasks(deadlineTask);
                    continue;

                case "E":
                    String EventAt = parsedSentence.get(EVENT_AT_INDEX);
                    Event eventTask = new Event(isDone, description, EventAt);
                    myTasks.storeTasks(eventTask);
                    continue;

                default:
                    throw new Exception();
                }
            }

        } catch (Exception e) {
            Printer.printFileCorrupted();
            System.exit(1);
        }
    }
}
