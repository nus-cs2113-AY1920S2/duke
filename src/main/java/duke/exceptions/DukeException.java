package duke.exceptions;

import duke.ui.Ui;

public class DukeException extends Exception  {

    public static void markAsIncorrectFormat() {
        System.out.println("Your inputs can only be of the following forms: \n 1. todo {task description} \n 2." +
                " deadline {task description} /by {dedline eg. 6 PM} \n 3. event {event description} " +
                "/at {event date\\time eg. 6 PM} \n 4. delete {taskNumber} \n 5. list \n 6. done {task number}" );
        System.out.println("Try again!");
    }

    public static void markIncorrectDoneStatement(){
        System.out.println("OOPS! Please specify (eg: \"done 2\")");
        Ui.printStraightLine();
    }

    public static void markDoneStatementOutOfBounds(){
        System.out.println("That task number isn't in our task list, please try again making a valid task!");
        Ui.printStraightLine();
    }

    public static void markDoneStatementAsNonInt(){
        System.out.println("The output format should be of the format \"done 2\", not \"done + {string}\".");
        Ui.printStraightLine();
    }

    public static void markIncorrectDeleteStatement(){
        System.out.println("OOPS! Please specify (eg: delete 2)");
        Ui.printStraightLine();
    }

    public static void markDeleteStatementOutOfBounds(){
        System.out.println("That task number isn't in our task list, please try deleting with a task that exists!");
        Ui.printStraightLine();
    }

    public static void markDeleteStatementAsNonInt(){
        System.out.println("The output format should be of the format \"delete 2\", not \"delete + {string}\".");
        Ui.printStraightLine();
    }

    public static void markIncorrectListStatement(){
        System.out.println("List statement can only be of the following form \"list\".");
        Ui.printStraightLine();
    }

    public static void markIncorrectClearStatement(){
        System.out.println("List statement can only be of the following form \"clear\".");
        Ui.printStraightLine();
    }

    public static void markIncorrectFindStatement(){
        System.out.println("List statement can only be of the following form \"find {key_word}\", where \"{key_word}\" is only one word.");
        Ui.printStraightLine();
    }

}

