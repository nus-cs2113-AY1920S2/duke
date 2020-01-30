public class Duke {
    public static final String END_STRING = "bye";

    public static void main(String[] args) {
        Ui.initializeUi();
        Ui.greet();

        TaskManager myTaskManager = new TaskManager();
        String userInput;
        while (true) {
            userInput = Ui.getNextLine();
            if (userInput.equals(END_STRING)) {
                break;
            } else {
                myTaskManager.executeUserInput(userInput);
            }
        }

        Ui.sayGoodbye();
    }
}
