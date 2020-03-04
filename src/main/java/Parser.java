import java.util.ArrayList;

public class Parser {
    String input;
    public Parser(String input){
        this.input = input;
    }

    public void readFile(ArrayList<Task> tasks){
        String[] words = this.input.split("\\|");
        TaskType taskType = TaskType.valueOf(words[0]);
        boolean isDone = parseIsDone(words[1]);
        String taskDetails = words[2];
        String taskDate;
        String taskTime;
        switch (taskType) {
        case T:
            tasks.add(new TodoTask(taskType, isDone, taskDetails));
            break;
        case E:
            taskDate = words[3];
            taskTime = words[4];
            tasks.add(new EventTask(taskType, isDone, taskDetails, taskDate, taskTime));
            break;
        case D:
            taskDate = words[3];
            taskTime = words[4];
            tasks.add(new DeadlineTask(taskType, isDone, taskDetails, taskDate, taskTime));
            break;
        default:
            System.out.println("There is an error in the save file, please restart Duke");
            break;
        }
    }
    public void userInput(TaskType taskType, ArrayList<Task> tasks) {

        boolean isDone = false;
        String taskDetails;
        String[] dateAndTime;
        String taskDate;
        String taskTime;
        int slashPosition;
        Task task;

        switch (taskType) {
        case T:
            taskDetails = this.input.trim();
            tasks.add(new TodoTask(taskType, isDone, taskDetails));
            task = new TodoTask(taskType, isDone, taskDetails);
            task.printCreateMessage();
            break;
        case E:
            slashPosition = this.input.indexOf("/");
            taskDetails = this.input.substring(0, slashPosition).trim();
            dateAndTime = this.input.substring(slashPosition+3).trim().split(" ");
            taskDate = dateAndTime[0];
            taskTime = dateAndTime[1];
            tasks.add(new EventTask(taskType, isDone, taskDetails, taskDate, taskTime));
            task = new EventTask(taskType, isDone, taskDetails, taskDate, taskTime);
            task.printCreateMessage();
            break;
        case D:
            slashPosition = this.input.indexOf("/");
            taskDetails = this.input.substring(0, slashPosition).trim();
            dateAndTime = this.input.substring(slashPosition+3).trim().split(" ");
            taskDate = dateAndTime[0];
            taskTime = dateAndTime[1];
            tasks.add(new DeadlineTask(taskType, isDone, taskDetails, taskDate, taskTime));
            task = new DeadlineTask(taskType, isDone, taskDetails, taskDate, taskTime);
            task.printCreateMessage();
            break;
        default:
            System.out.println("An error has occurred, please check your input");
            break;
        }
    }

    public void writeToFile(ArrayList<Task> tasks) {

        for (Task task: tasks) {
            String taskType = task.taskType.name();
            String isDone;
            String line;
            if (task.isDone) {
                isDone = "Y";
            } else {
                isDone = "N";
            }
            switch (taskType) {
            case "T":
                line = taskType + "|" + isDone + "|" + task.taskDetails;
                break;
            case "E":
            case "D":
                line = taskType + "|" + isDone + "|" + task.taskDetails + "|" + task.taskDate + "|" + task.taskTime;
                break;
            default:
                line = null;
                System.out.println("Error in writing file, please restart Duke");
                break;
            }
            this.input += line + System.lineSeparator();
        }
    }

    public boolean parseIsDone(String input){
        boolean isDone;
        if (input.equals("Y")) {
            isDone = true;
        } else {
            isDone = false;
        }
        return isDone;
    }
}
