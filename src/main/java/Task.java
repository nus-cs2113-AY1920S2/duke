public class Task {

    private String name;
    private int taskNumber;

    public Task(String name, int taskNumber) {
        this.name = name;
        this.taskNumber = taskNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public String getName() {
        return name;
    }

    public void printTask() {
        System.out.println("    " + taskNumber + ". " + name);
    }

}
