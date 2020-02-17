package parser;

import data.TaskManager;
import data.task.DeadlineTask;
import data.task.EventTask;
import data.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Encode task information into a data file.
 */
public class EncodeToFile {

    /**
     * Encodes all the {@code TaskManager} in the {@code taskManager} into a list of readable string presentation
     * for storage.
     */
    public static List<String> encodeAllTasks(TaskManager taskManager) {
        final List<String> encodedTasks = new ArrayList<>();
        taskManager.getTaskList().forEach(person -> encodedTasks.add(encodeTaskToString(person)));
        return encodedTasks;
    }

    /**
     * Encodes the {@code task} into a readable string representation.
     */
    private static String encodeTaskToString (Task task){
        StringBuilder encodedTaskBuilder = new StringBuilder();
        encodedTaskBuilder = getStringBuilder(task, encodedTaskBuilder);
        //encode start/end time (optional)
        switch (task.getTaskType()){
        case 'D':
            encodedTaskBuilder = encodeDeadlineToStting((DeadlineTask) task, encodedTaskBuilder);
            break;
        case 'E':
            encodedTaskBuilder = encodeEventToString((EventTask) task, encodedTaskBuilder);
            break;
        default:
        case 'T':
            break;
        }
        return encodedTaskBuilder.toString();
    }

    /**
     * Parses user input into command for execution.
     *
     * @param task
     * @param encodedTaskBuilder
     * @return encodedTaskBuilder
     */
    private static StringBuilder encodeEventToString(EventTask task, StringBuilder encodedTaskBuilder) {
        encodedTaskBuilder = encodedTaskBuilder.append("|");
        EventTask eTask = task;
        encodedTaskBuilder = encodedTaskBuilder.append(eTask.getTaskStartTime());
        encodedTaskBuilder = encodedTaskBuilder.append(eTask.getTaskEndTime());
        return encodedTaskBuilder;
    }

    /**
     * Parses user input into command for execution.
     *
     * @param task
     * @param encodedTaskBuilder
     * @return encodedTaskBuilder
     */
    private static StringBuilder encodeDeadlineToStting(DeadlineTask task, StringBuilder encodedTaskBuilder) {
        encodedTaskBuilder = encodedTaskBuilder.append("|");
        //up-casting
        DeadlineTask dTask = task;
        encodedTaskBuilder = encodedTaskBuilder.append(dTask.getTaskDeadline());
        return encodedTaskBuilder;
    }

    /**
     * Parses user input into command for execution.
     *
     * @param task
     * @param encodedTaskBuilder
     * @return encodedTaskBuilder
     */
    private static StringBuilder getStringBuilder(Task task, StringBuilder encodedTaskBuilder) {
        encodedTaskBuilder = encodedTaskBuilder.append(String.valueOf(task.getTaskType()));
        encodedTaskBuilder = encodedTaskBuilder.append("|");
        encodedTaskBuilder = encodedTaskBuilder.append(String.valueOf(task.getChar()));
        encodedTaskBuilder = encodedTaskBuilder.append("|");
        encodedTaskBuilder = encodedTaskBuilder.append(task.getTaskDescription());
        return encodedTaskBuilder;
    }

}
