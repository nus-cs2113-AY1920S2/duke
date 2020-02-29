package duke;

import duke.excpetions.DukeException;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Storage is a class that deals with loading tasks from the file and saving tasks in the file.
 * This means the task list can be saved to the disk and the software can always keep track of it.
 * The storage corresponds to a relative file path where the tasks' information stored.
 */
public class Storage {
    String filePath;

    public Storage(String filePath){
        this.filePath = filePath;
    }

    /**
     * Rewrite the file with current task list when the user gives an exit command.
     *This keeps the file always updated.
     *
     * @param usersTasks The current task list which is going to be saved into the file
     * @throws IOException If the file can not be found or some other problems appears when trying to access the file
     */
    public static void writeFile(TaskList usersTasks) throws IOException {
        File filePath = new File("data");
        if(!filePath.exists()){
            filePath.mkdir();
        }
        File file = new File("data/tasksList.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter fw = new FileWriter("data/tasksList.txt");
        for (Task task: usersTasks.getTasks()){
            if (task!=null){
                fw.write(task.toString());
                fw.write("\n");
            }
        }
        fw.close();
    }

    /**
     * Loads the tasks from the file with given path.
     * This rebuilds the task list to the state before the users last logged out
     *
     * @return A task list that the users saved last time
     * @throws DukeException If the records given is not the correct format of task records.
     */
    public ArrayList<Task> load() throws DukeException{
        try{
            ArrayList<Task> tasks = new ArrayList<Task>();
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            String nextLine=br.readLine();
            while(nextLine != null){
                loadSingleTask(nextLine,tasks);
                nextLine=br.readLine();
            }
            br.close();
            fr.close();
            return tasks;
        }catch(FileNotFoundException e){
            throw new DukeException();
        }catch(IOException e){
            throw new DukeException();
        }
    }

    /**
     * This is a helper method of load.
     * This method loads a single task to the task list according one line in the file.
     *
     * @param nextLine The records of a single task in the file.
     * @param tasks The task list the new task is added into.
     * @throws DukeException If the records given is not the correct format of task records.
     */
    private void loadSingleTask(String nextLine,ArrayList<Task> tasks) throws DukeException {
        char taskType =nextLine.charAt(1);
        char status = nextLine.charAt(5);
        String description;
        switch (taskType) {
        case 'T':
            description = nextLine.substring(9);
            ToDo todo = new ToDo(description);
            if(status=='\u2713'){
                todo.markAsDone();
            }
            tasks.add(todo);
            break;
        case 'D':
            description = nextLine.substring(9,nextLine.indexOf(" ("));
            String byInString = nextLine.substring(nextLine.indexOf("by")+4,nextLine.indexOf(")"));
            LocalDateTime by =LocalDateTime.parse(byInString,
                    DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM,FormatStyle.MEDIUM));
            Deadline deadline = new Deadline(description,by);
            tasks.add(deadline);
            break;
        case 'E':
            description = nextLine.substring(9,nextLine.indexOf(" ("));
            String atInString = nextLine.substring(nextLine.indexOf("at")+4,nextLine.indexOf(")"));
            LocalDateTime at =LocalDateTime.parse(atInString,
                    DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM,FormatStyle.MEDIUM));
            Event event = new Event(description,at);
            tasks.add(event);
            break;
        default:
            throw new DukeException();
        }
    }

}
