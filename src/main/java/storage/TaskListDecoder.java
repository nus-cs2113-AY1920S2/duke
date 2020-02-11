package storage;

import duke.task.Deadlines;
import duke.task.Events;
import duke.task.InvalidTaskArgumentException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class TaskListDecoder {
    public static int decoderTaskCounter = 0;
    
    public static TaskList decodeTaskList(List<String> lines) {
        TaskList decodedTaskList = new TaskList();       
        
        
        for (String line : lines) {
            TaskListDecoder.decoderTaskCounter++;
            Task decodedTask = decodeTask(line);
            
            decodedTaskList.addTask(decodedTask);
        }

        return decodedTaskList;
    }
    
    public static Task decodeTask(String line) {
        List<Optional<String>> list = new ArrayList<>();
        String[] taskArgs = line.split(" | ");
        
        String cleanWord = "";
        for (String word : taskArgs) {           
            if (word.equals("|")) {
              list.add(Optional.of(cleanWord));
              cleanWord = "";
              continue;
            } else {
                if (cleanWord == "") {
                    cleanWord += (word);
                } else {
                    cleanWord += (" " + word);
                }
            }
        }
        list.add(Optional.of(cleanWord));
        
        if (list.size() == 3) {
            list.add(Optional.of(""));
        }
       
         
        String taskType = list.get(0).get();
        String isDoneNumber = list.get(1).get();
        String taskName = list.get(2).get();
        Optional<String> taskRequirement = list.get(3);
        
        boolean isDone = (isDoneNumber.equals("1") ? true : false);

        
        Task task;        
        switch(taskType) {
        case "T":             
            task = new ToDos(TaskListDecoder.decoderTaskCounter, 
                    taskName, isDone);           
            break;
        case "D":
            task = new Deadlines(TaskListDecoder.decoderTaskCounter, 
                    taskName, taskRequirement.get(), isDone);
            break;
        case "E":
            task = new Events(TaskListDecoder.decoderTaskCounter, 
                    taskName, taskRequirement.get(), isDone);
            break;
        default:
            throw new InvalidTaskArgumentException("Cannot understand taskType");
        }

        return task;
        
    }
    
}
