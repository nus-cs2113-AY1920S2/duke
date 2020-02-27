package Command;

import Exceptions.MissingParameterException;
import Exceptions.NoParameterException;
import Storage.Storage;
import Task.TaskList;
import UI.Ui;

public class AddCommand extends Command {

    private static final char TASK_TODO = 'T';
    private static final char TASK_EVENT = 'E';
    private static final char TASK_DEADLINE = 'D';

    String userInput;
    int wordArrayLength;
    char taskType;

    public AddCommand(String userCommand, int wordArrayLength, char taskType) {
        this.userInput = userCommand;
        this.wordArrayLength  = wordArrayLength;
        this.taskType = taskType;
    }


    @Override
    public void execute() {
        try {
            switch (taskType) {
            case TASK_DEADLINE:
                tasks.addDeadline(userInput, wordArrayLength);

                break;
            case TASK_EVENT:
                tasks.addEvent(userInput, wordArrayLength);
                break;
            case TASK_TODO:
                tasks.addTodo(userInput, wordArrayLength);
                break;
            default:
                System.out.println("Keyword not recognised!\n");
            }

        } catch (NoParameterException e) {
            System.out.println("Missing parameters");
        } catch (MissingParameterException e) {
            System.out.println("Missing Date or Time field");
        }
    }
}
