package ui;

import common.Messages;
import data.task.DeadlineTask;
import data.task.EventTask;
import data.task.Task;
import data.task.TodoTask;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.util.InputMismatchException;
import java.util.Scanner;

import static common.Messages.MESSAGE_LS;
import static common.Messages.MESSAGE_RS;
import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;


public class TextUi {

    public static final int DISPLAYED_INDEX_OFFSET = 1;
    public static final Ansi.Color SYSTEM_COLOR_MESSAGE = BLUE;
    public static final Ansi.Color SYSTEM_COLOR_RESPONSE = YELLOW;
    public static final Ansi.Color SYSTEM_COLOR_DIVIDER = BLACK;
    public static final Ansi.Color SYSTEM_COLOR_LOGO = MAGENTA;
    public static final Ansi.Color SYSTEM_COLOR_ALERT = RED;


    //display welcome message
    public static void showWelcomeMessage (){
        AnsiConsole.systemInstall();
        printDivider();
        System.out.print(String.format(Messages.respondFormat,
                ansi().bold().fg(SYSTEM_COLOR_MESSAGE).a(Messages.MESSAGE_WELCOME_1).reset()) );
        System.out.print(String.format(Messages.respondFormat,
                ansi().bold().fg(SYSTEM_COLOR_MESSAGE).a(Messages.MESSAGE_WELCOME_2).reset()) );
        printDivider();
        AnsiConsole.systemUninstall();
    }

    //display farewell message
    public static void showFarewellMessage (){
        AnsiConsole.systemInstall();
        printDivider();
        System.out.println( ansi().fg(SYSTEM_COLOR_MESSAGE).a(Messages.MESSAGE_FAREWELL).reset() );
        printDivider();
        AnsiConsole.systemUninstall();
    }

    //echo function, display user's input
    public static void showResult(String text) {
        printDivider();
        printMessage(CYAN, text);
        printDivider();
    }

    public static void displayLogo() {
        AnsiConsole.systemInstall();
        ansi().reset();
        System.out.print(ansi().bold().fg(SYSTEM_COLOR_LOGO).a(Messages.LOGO).reset());
        printDivider();
        AnsiConsole.systemUninstall();
    }

    public static int getUserChoice() {
        printDivider();
        printMessage(SYSTEM_COLOR_RESPONSE, Messages.MESSAGE_ASK_TO_CHOOSE_UI);
        printDivider();
        int userChoice;
        try {
            Scanner sc = new Scanner(System.in);
            userChoice = sc.nextInt();
            return userChoice;
        } catch (InputMismatchException ex) {
            getUserChoice();
            return -1;
        }
    }

    public static void askForReInput() {
        printDivider();
        printAlert();
        printMessage(SYSTEM_COLOR_MESSAGE, Messages.MESSAGE_INVALID_USER_CHOICE);
    }

    public static void acknowledgementUserChoice(int userChoice){
        switch (userChoice){
        case 1:
            printMessage(SYSTEM_COLOR_RESPONSE, Messages.MESSAGE_THANKS_FOR_GUI);
            break;
        case 2:
            printMessage(SYSTEM_COLOR_RESPONSE, Messages.MESSAGE_THANKS_FOR_CLI);
            break;
        }
    }

    /**
     * print a divider
     */
    public static void printDivider(){
        System.out.println( ansi().bold().fg(SYSTEM_COLOR_DIVIDER).a(Messages.MESSAGE_SPLITTER).reset() );
    }

    public static void alertToAddDuplicateTask(Task toCheck){
        printAlert();
        printMessage(SYSTEM_COLOR_MESSAGE,
                String.format(Messages.MESSAGE_DUPLICATE_TASK_ALERT, toCheck.getTaskIndex()));
    }

    public static void printDuplicateTaskNotAdded(){
        printMessage(SYSTEM_COLOR_MESSAGE, Messages.MESSAGE_DUPLICATE_TASK_NOT_ADDED);
    }

    /**
     * clear the screen
     */
    public static void clearScreen(){
        AnsiConsole.systemInstall();
        System.out.println(ansi().eraseScreen());
        AnsiConsole.systemUninstall();
    }

    public static void printAlert(){
        AnsiConsole.systemInstall();
        ansi().reset();
        System.out.print(String.format(Messages.respondFormat,
                ansi().bold().fg(SYSTEM_COLOR_ALERT).a(Messages.MESSAGE_ALERT).reset()) );
        AnsiConsole.systemUninstall();
    }

    public static void printMessage(Ansi.Color color, String message){
        AnsiConsole.systemInstall();
        ansi().reset();
        System.out.print(String.format(Messages.respondFormat,
                ansi().bold().fg(color).a(message).reset()));
        AnsiConsole.systemUninstall();
    }

    public static void printLS(){
        AnsiConsole.systemInstall();
        ansi().reset();
        System.out.print(MESSAGE_LS); //black
        AnsiConsole.systemUninstall();
    }

    public static void printRS(){
        AnsiConsole.systemInstall();
        ansi().reset();
        System.out.println(MESSAGE_RS); //black
        AnsiConsole.systemUninstall();
    }

    public static void printTask(Ansi.Color color, String message){
        AnsiConsole.systemInstall();
        ansi().reset();
        printLS();
        System.out.print(
                ansi().bold().fg(color).a(message).reset());
        printRS();
        AnsiConsole.systemUninstall();
    }

    /**
     * print todo-type task
     * @param todoTask
     * @param index
     * @return
     */
    public static void printTodoTask(TodoTask todoTask, int index){
        printTask(SYSTEM_COLOR_RESPONSE,String.format(Messages.MESSAGE_LIST_RESPOND_Format, String.format(
                Messages.MESSAGE_TODO_LIST,
                index,
                todoTask.getTaskIndex(),
                todoTask.getTaskType(),
                todoTask.getChar(),
                todoTask.getTaskDescription()))) ;
    }

    /**
     * print deadline-type task
     * @param deadlineTask
     * @param index
     * @return
     */
    public static void printDeadlineTask(DeadlineTask deadlineTask, int index){
        printTask(SYSTEM_COLOR_RESPONSE,String.format(Messages.MESSAGE_LIST_RESPOND_Format, String.format(
                Messages.MESSAGE_DEADLINE_LIST,
                index,
                deadlineTask.getTaskIndex(),
                deadlineTask.getTaskType(),
                deadlineTask.getChar(),
                deadlineTask.getTaskDescription(),
                deadlineTask.getTaskDeadline())));
    }

    /**
     * print event-type task
     * @param eventTask
     * @param index
     * @return
     */
    public static void printEventTask(EventTask eventTask, int index){
        printTask(SYSTEM_COLOR_RESPONSE,String.format(Messages.MESSAGE_LIST_RESPOND_Format, String.format(
                Messages.MESSAGE_EVENT_LIST,
                index,
                eventTask.getTaskIndex(),
                eventTask.getTaskType(),
                eventTask.getChar(),
                eventTask.getTaskDescription(),
                eventTask.getTaskStartTime())));
    }
}
