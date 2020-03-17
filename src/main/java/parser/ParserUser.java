package parser;

import task.*;

import java.util.ArrayList;

/**
 * ParserUser contains parsing methods for user inputs.
 */
public class ParserUser extends Parser {

    /**
     * Constructor of the ParserUser class follows the Parser class.
     * @param input the input from the user
     */
    public ParserUser(String input) {
        super(input);
    }

    /**
     * Parse one line of user input into Task format.
     * @param taskType taskType of the task
     * @param tasks ArrayList of all tasks in Duke
     */
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
}
