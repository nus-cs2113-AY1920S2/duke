package Command;

import Exceptions.MissingParameterException;
import Exceptions.NoParameterException;

<<<<<<< HEAD
/**
 * Adds a task to the task list.
 * Processes the task based on its task type
 */
=======
import java.time.format.DateTimeParseException;

>>>>>>> master
public class AddCommand extends Command {

    private static final char TASK_TODO = 'T';
    private static final char TASK_EVENT = 'E';
    private static final char TASK_DEADLINE = 'D';

    String userInput;
    int wordArrayLength;
    char taskType;

    /**
     * Convenience constructor using raw values
     *
     * @param userCommand full user input, including command and task descriptions
     * @param wordArrayLength number of parameters supplied for command
     * @param taskType type of task to be processed
     */
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
<<<<<<< HEAD
                System.out.println("[Error][New Task]: Keyword not recognised!\n");
                System.out.println("Task types:\ntodo\nevent\ndeadline");
            }

        } catch (NoParameterException e) {
            System.out.println("[Error][New Task]: Missing parameters");
            System.out.println("Usage:\n{todo} <Details>\n{event or deadline} <Details> / <YYYY-MM-DD> HH");
=======
                System.out.println("[Error] Keyword not recognised!\n");
            }

        } catch (NoParameterException e) {
            System.out.println("[Error] Missing parameters");
        } catch (MissingParameterException | NullPointerException e) {
            System.out.println("[Error] Missing Date or Time field");
        } catch (DateTimeParseException e) {
            System.out.println("[Error] Wrong format for date\nPlease record in YYYY-DD-MM format");
        } catch (NumberFormatException e) {
            System.out.println("[Error] time should be all numbers in 24Hr format: HHMM");
>>>>>>> master
        }
    }
}
