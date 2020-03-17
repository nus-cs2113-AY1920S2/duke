package parser;

import task.*;

import java.util.ArrayList;

/**
 * ParserFile class contains parsing methods for the memory file.
 */
public class ParserFile extends Parser{

    /**
     * Constructor of ParserFile follows the Parser class.
     * @param input input from memory file
     */
    public ParserFile(String input) {
        super(input);
    }

    /**
     * Parse output from tasks ArrayList to memory file.
     * @param tasks ArrayList of tasks in Duke
     */
    public void parseOutputToFile(ArrayList<Task> tasks) {
        for (Task task: tasks) {
            String taskType = task.getTaskType().name();
            String isDone;
            String line;
            if (task.isDone()) {
                isDone = "Y";
            } else {
                isDone = "N";
            }
            switch (taskType) {
                case "T":
                    line = taskType + "|" + isDone + "|" + task.getTaskDetails();
                    break;
                case "E":
                case "D":
                    line = taskType + "|" + isDone + "|" + task.getTaskDetails() + "|" + task.getTaskDate() + "|" + task.getTaskTime();
                    break;
                default:
                    line = null;
                    System.out.println("Error in writing file, please restart Duke");
                    break;
            }
            this.input += line + System.lineSeparator();
        }
    }

    /**
     * Parse input from memory file into the tasks ArrayList.
     * @param tasks ArrayList of tasks in Duke
     */
    public void parseInputFromFile(ArrayList<Task> tasks){
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

    /**
     * Parse isDone status of task from memory file input.
     * @param input input from the memory file "Y" or "N"
     * @return boolean value of isDone for the task
     */
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
