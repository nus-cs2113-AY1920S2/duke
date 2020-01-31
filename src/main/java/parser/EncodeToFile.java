package parser;

import data.Duke;
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
     * Encodes all the {@code Task} in the {@code duke} into a list of readable string presentation
     * for storage.
     */
    public static List<String> encodeAllTasks(Duke duke) {
        final List<String> encodedTasks = new ArrayList<>();
        duke.getTaskList().forEach(person -> encodedTasks.add(encodeTaskToString(person)));
        return encodedTasks;
    }

    /**
     * Encodes the {@code task} into a readable string representation.
     */
    private static String encodeTaskToString (Task task){
        StringBuilder encodedTaskBuilder = new StringBuilder();
        //encode task type
        encodedTaskBuilder = encodedTaskBuilder.append(String.valueOf(task.getTaskType()));
        encodedTaskBuilder = encodedTaskBuilder.append("|");
        //encode task status
        encodedTaskBuilder = encodedTaskBuilder.append(String.valueOf(task.getChar()));
        encodedTaskBuilder = encodedTaskBuilder.append("|");
        //encode task name
        encodedTaskBuilder = encodedTaskBuilder.append(task.getTaskDescription());
        //encode start/end time (optional)
        switch (task.getTaskType()){
        case 'D':
            encodedTaskBuilder = encodedTaskBuilder.append("|");
            //up-casting
            DeadlineTask dTask = (DeadlineTask) task;
            encodedTaskBuilder = encodedTaskBuilder.append(dTask.getTaskDeadline());
            break;
        case 'E':
            encodedTaskBuilder = encodedTaskBuilder.append("|");
            EventTask eTask = (EventTask) task;
            encodedTaskBuilder = encodedTaskBuilder.append(eTask.getTaskStartTime());
            encodedTaskBuilder = encodedTaskBuilder.append(eTask.getTaskEndTime());
            break;
        default:
        case 'T':
            break;
        }
        return encodedTaskBuilder.toString();
    }

}
