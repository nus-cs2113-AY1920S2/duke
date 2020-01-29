public class TaskManager{

    private int taskIndex = 0;
    private Task[] taskArray= new Task[100];

    public TaskManager(){}

    public String getTaskItem(int i){
        return taskArray[i].description;
    }

    public void addTask(String item){
        taskArray[taskIndex] = new Task(item);
        taskIndex++;
    }

    public void getTask(){
        for (int i = 0; i < taskIndex; i++){
            System.out.println((i+1) + ". [" + taskArray[i].getStatusIcon() + "] " + taskArray[i].description);
        }
    }

    public void markTask(int i){
        taskArray[i].markDone();
    }
}