package duke;

import duke.excpetions.DukeException;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Storage {
    String filePath;

    public Storage(String filePath){
        this.filePath = filePath;
    }

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

    private void loadSingleTask(String nextLine,ArrayList<Task> tasks) throws DukeException {
        char taskType =nextLine.charAt(1);
        char status = nextLine.charAt(5);
        String description;
        switch (taskType) {
        case 'T':
            description = nextLine.substring(9);
            ToDo todo = new ToDo(description);
            if(status=='\u2713'){
                todo.setDone();
            }
            tasks.add(todo);
            break;
        case 'D':
            description = nextLine.substring(9,nextLine.indexOf(" ("));
            String by = nextLine.substring(nextLine.indexOf("by")+4,nextLine.indexOf(")"));
            Deadline deadline = new Deadline(description,by);
            tasks.add(deadline);
            break;
        case 'E':
            description = nextLine.substring(9,nextLine.indexOf(" ("));
            String at = nextLine.substring(nextLine.indexOf("at")+4,nextLine.indexOf(")"));
            Event event = new Event(description,at);
            tasks.add(event);
            break;
        default:
            throw new DukeException();
        }
    }

}
