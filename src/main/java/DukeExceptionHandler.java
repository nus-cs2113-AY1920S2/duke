public class DukeExceptionHandler {
    public static void isBlank(String description) throws BlankStringException {
        if (description.isBlank()) {
            throw new BlankStringException();
        }
    }

    public static void containsBy(String descriptionAndDate) throws DeadlineDateException {
        if (!descriptionAndDate.contains(" /by ")) {
            throw new DeadlineDateException();
        }
    }
}
