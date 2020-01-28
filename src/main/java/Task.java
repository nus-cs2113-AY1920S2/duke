public class Task {

    public static String checkIfDone(Boolean actionDone) {
        if (actionDone) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }
}
