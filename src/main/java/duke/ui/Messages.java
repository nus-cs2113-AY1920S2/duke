package duke.ui;

import duke.data.TaskList;

import static duke.format.TextFormatter.ANGRY_FACE;
import static duke.format.TextFormatter.HAPPY_FACE;
import static duke.format.TextFormatter.SAD_FACE;
import static duke.format.TextFormatter.SHOCK_FACE;
import static duke.format.TextFormatter.THINKING_FACE;
import static duke.format.TextFormatter.createSpaces;
import static duke.format.TextFormatter.toBold;
import static duke.format.TextFormatter.toBoldAndItalic;

public class Messages {

    private static final String TAB = createSpaces(4);
    private static final String VERSION = "v5.6.1.2";
    private static final String LOGO =
            "  __       _______  _______  ________  _______  _______  _______  ________\n" +
            " |\\_\\     |\\___\\__\\|\\ __\\__\\|\\ ______\\|\\______\\|\\___\\__\\|\\______\\|\\ ______\\\n" +
            " | | |    | |  |  || |  |  | \\|__   _|| |  ___|| |  |  || |     | \\|__   _|\n" +
            " | | |    | |  |  || |  |  |   | | |  | | |    | |  |  || |  |  |   | | |\n" +
            " | | |__  | |  |  || |     | __| | |_ | | |___ | |     || |     |   | | |\n" +
            " | | |__\\ | |     || | | | ||\\__\\| |_\\| | |___\\| |  |  || |  |  |   | | |\n" +
            "  \\|_____| \\|_____| \\|_|_|_| \\|______| \\|_____| \\|__|__| \\|__|__|    \\|_|\n";


    /* System Messages */
    public static final String WELCOME_MESSAGE = "Welcome to\n" + LOGO;

    public static final String LOAD_MESSAGE = "Initializing LumiChat " + VERSION + "...\n";

    public static final String EXIT_MESSAGE = "Aborting LumiChat program...\nLumiChat program has ended.\n";

    public static final String READY_MESSAGE =
            "LumiChat is now ready.\n" + HAPPY_FACE + "Hey I'm Lumi!\n  How may Lumi assist you today?\n";

    public static final String GOODBYE_MESSAGE = SAD_FACE + "Goodbye! Lumi will miss you!\n";


    /* Storage Messages */
    public static final String CREATE_NEW_FILE_MESSAGE = "New empty task list file created.\n";

    public static final String ABORT_CREATE_NEW_FILE_MESSAGE = "New task list is not created.\n";

    public static final String CREATE_CONFIRMATION_MESSAGE =
            "Do you want to overwrite the corrupted task list with a new " +
                    toBold("empty") + " task list?\n";

    public static final String PROMPT_VALID_CREATE_CONFIRMATION_MESSAGE =
            "Please enter either " + toBoldAndItalic("YES") + " to confirm creating a new task list or " +
                    toBoldAndItalic("NO") + " to abort the LumiChat program.\n";

    public static final String SUCCESSFUL_SAVE_MESSAGE = HAPPY_FACE + "Lumi saves your task list successfully!\n";

    public static final String UNSUCCESSFUL_SAVE_MESSAGE = SHOCK_FACE + "Lumi is not able to save your task list!\n";


    /* Add task messages */
    public static String addTaskMessage() {
        int latestTaskIndex = TaskList.size() - 1; // 0-based indexing

        return HAPPY_FACE + "Alright, Lumi has added: " + TaskList.get(latestTaskIndex).getTask() + "!\n" +
                TAB + TaskList.get(latestTaskIndex).getTaskStatus() +
                "\nYou now have " + TaskList.size() + ((TaskList.size() == 1) ? " task" : " tasks") +
                " in Lumi's list!\n";
    }


    /* Do task messages */
    public static String completeTaskMessage(int index) {
        return HAPPY_FACE + "Well done! Lumi marks this task as completed!\n" +
                TAB + TaskList.get(index).getTaskStatus() +"\n";
    }

    public static String alreadyCompletedTaskMessage(int index) {
        return ANGRY_FACE + "Hey!! Lumi already marked <" + TaskList.get(index).getTask() +
                "> as completed!\n";
    }


    /* List messages */
    public static final String LIST_MESSAGE = HAPPY_FACE + "Sure! Lumi prints your list!";


    /* Delete task messages */
    public static String DELETE_TASK_CONFIRMATION_MESSAGE(String task) {
        return THINKING_FACE + "Umm... Lumi needs you to confirm to delete this task:\n" +
                createSpaces(8) + task + "\n";
    }

    public static final String PROMPT_VALID_DELETE_CONFIRMATION_MESSAGE =
        THINKING_FACE + "Uh... Lumi needs you to enter either " +
        toBoldAndItalic("YES") + " to confirm deletion or " +
        toBoldAndItalic("NO") + " to cancel...\n";

    public static String deleteTaskMessage(String taskToDelete) {
        int listSize = TaskList.size();

        return HAPPY_FACE + "Bleep, Lumi says bye-bye to:\n" +
                TAB + taskToDelete +
                "\nYou now have " + listSize + ((listSize == 1) ? " task" : " tasks") +
                " in Lumi's list!\n";
    }

    public static final String ABORT_DELETE_MESSAGE = HAPPY_FACE + "OK, Lumi continues without deleting!\n";


    /* Find messages */
    public static final String FIND_MESSAGE = HAPPY_FACE + "Sure! Lumi searches your list...";

    public static final String NO_MATCHES_MESSAGE = THINKING_FACE + "Huh? Lumi is not able to find anything...\n";


    /* Exit messages */
    public static final String PROMPT_VALID_EXIT_CONFIRMATION_MESSAGE =
            THINKING_FACE + "Uh... Lumi needs you to enter either " +
            toBoldAndItalic("YES") + " to confirm to leave " + toBold("without") + " saving or " +
            toBoldAndItalic("NO") + " to stay...\n";

    public static final String ABORT_EXIT_MESSAGE = HAPPY_FACE + "Woohoo! Lumi continues to be with you!\n";
}
