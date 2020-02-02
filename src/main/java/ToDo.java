public class ToDo extends Task{
    private static int toDoNum = 0;

    public ToDo(String taskName){
        super(taskName);
        toDoNum++;
        this.taskType = "[T]";
    }

    public static int getToDoNum(){
        return toDoNum;
    }

}
