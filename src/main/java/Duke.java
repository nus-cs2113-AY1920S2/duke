public class Duke {
    public static final String END_STRING = "bye";

    public static void main(String[] args) {
        Ui.initializeUi();
        Ui.greet();

        TaskManager myTaskManager = new TaskManager();
        String userInput = Ui.getNextLine();

        while (!userInput.equals(END_STRING)) {
            myTaskManager.executeUserInput(userInput);
            userInput = Ui.getNextLine();
        }

        Ui.sayGoodbye();
    }
}
