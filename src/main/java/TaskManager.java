import java.util.ArrayList;

public class TaskManager {
    ArrayList<Task> tasks = new ArrayList<Task>();

    public void addTask(String description){
        tasks.add(new Task(description));
        Duke.printLine();
        Duke.printWithIndentation("added: " + description );
        Duke.printLine();
    }

    public void maskTaskAsDone(int taskNumber){
        tasks.get(taskNumber).markAsDone();
        Duke.printLine();
        Duke.printWithIndentation("Nice! I've marked this task as done: ");
        Duke.printSpaces(2);
        Duke.printWithIndentation(tasks.get(taskNumber).getDescriptionWithStatus() );
        Duke.printLine();
    }

    public void listTasks(){
        Duke.printLine();
        Duke.printWithIndentation("Here are the tasks in your list:");
        for(int i = 0; i < tasks.size(); i++){
            Duke.printWithIndentation( (i+1) + ". " + tasks.get(i).getDescriptionWithStatus());
        }
        Duke.printLine();
    }

    public void printAlreadyDone(int index){
        Duke.printLine();
        Duke.printWithIndentation("Task was already set as done");
        Duke.printSpaces(2);
        Duke.printWithIndentation(tasks.get(index).getDescriptionWithStatus());
        Duke.printLine();
    }

    public boolean checkIndexValidity(int index){
        return index >= 0 && index < tasks.size();
    }

}
