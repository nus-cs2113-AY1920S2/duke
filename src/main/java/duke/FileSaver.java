package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Saves after each command into save file.
 * Saves in this format:
 * [TYPE] | [DONE_STATUS] | [DESCRIPTION] | [SECONDARY PARAMETERS]
 *
 * e.g., D | 0 | submit iP | March 2nd
 */
public class FileSaver {

    /**
     * Makes a new directory in the current working directory
     */
    public static void makeNewDirectory() {
        File f = new File("data");
        f.mkdir();
    }

    /**
     * Makes a new save file after ensuring that the directory to file is created.
     *
     * @return the save file object.
     */
    public static File makeNewSaveFile() {
        makeNewDirectory();

        File f = Duke.SAVE_FILE;
        boolean isFileExist = f.exists();
        if (!isFileExist) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.print("Unable to create new file\n");
            }
        }

        return f;
    }

    /**
     * Saves file after a todo command.
     * Prints an error if the save file is not found or there is an IO error
     *
     * @param f file object to save to.
     * @param description description of the task to save.
     */
    public static void saveFile(File f, String description) {
        try (FileWriter fw = new FileWriter(f, true)) {
            fw.write("T | 0 | " + description + System.lineSeparator());

        } catch (FileNotFoundException e) {
            System.out.println("File not found"); //TODO move to printer class

        } catch (IOException e) {
            System.out.println("nani"); //TODO move to printer class
        }
    }

    /**
     * Saves file after a event or deadline command.
     * Prints an error if the save file is not found or there is an IO error
     *
     * @param command the type of command
     * @param f file object to save to
     * @param description description of the task to save
     * @param dateTime the date and time  of the task to save
     */
    public static void saveFile(String command, File f, String description, String dateTime) {
        try (FileWriter fw = new FileWriter(f, true)) {
            switch (command) {
            case "deadline":
                fw.write("D | 0 | " + description + " | " + dateTime + System.lineSeparator());
                break;
            case "event":
                fw.write("E | 0 | " + description + " | " + dateTime + System.lineSeparator());
                break;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found"); //TODO move to printer class

        } catch (IOException e) {
            System.out.println("nani"); //TODO move to printer class
        }
    }

    public static void createTempFile(File temp) {
        try {
            temp.createNewFile();
        } catch (IOException e) {
            System.out.println("nani"); //TODO
        }
    }

    /**
     * Returns scanner after moving it by the amount of lines specified by @param index.
     * As the scanner moves to the next line, it will append the contents of the line to a temp file
     *
     * @param f the file to scane
     * @param index the number of lines scanner should move
     * @param fw the temp file that scanner will append to.
     * @return scanner after moving it by amount of lines.
     * @throws IOException If file no longer exists when trying to write
     */
    public static Scanner getScanner(File f, int index, FileWriter fw) throws IOException {
        Scanner s = new Scanner(f);
        for (int i = 1; i < index; i += 1) {
            String sentence = s.nextLine();
            fw.append(sentence + System.lineSeparator());
        }
        return s;
    }

    /**
     * Updates a specific line, changing the task's [DONE_STATUS] to 1
     *
     * Copies the save file content into a temp file until the specific line
     * Then at the specific line, change the [DONE_STATUS] and append that into the line
     * Continue copying until EOF
     * Delete previous save file and rename temp file to the new save file.
     *
     * @param f the save file
     * @param index the specific line
     */
    public static void updateFile(File f, int index) {
        File temp = new File("data/temp.txt");

        createTempFile(temp);

        try (FileWriter fw = new FileWriter(temp, true)) {
            Scanner s = getScanner(f, index, fw);

            String regex = "\\d+";
            String sentence = (s.nextLine()).replaceFirst(regex, "1");
            fw.append(sentence + System.lineSeparator());

            copyUntilEnd(fw, s);
            s.close();

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            f.delete();
            temp.renameTo(f);
        }
    }

    /**
     * Deletes a specific line in the save file.
     *
     * Copies the save file content into a temp file until the specific line
     * Then at the specific line, skip the specific line.
     * Continue copying until EOF
     * Delete previous save file and rename temp file to the new save file.
     *
     * @param saveFile the save file
     * @param index the specific line
     */
    public static void deleteSpecificLine(File saveFile, int index) {
        File temp = new File("data/temp.txt");

        createTempFile(temp);

        try (FileWriter fw = new FileWriter(temp, true)) {
            Scanner s = getScanner(saveFile, index, fw);

            s.nextLine();

            copyUntilEnd(fw, s);
            s.close();

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            saveFile.delete();
            temp.renameTo(saveFile);
        }
    }

    /**
     * Copies from save file and appends it to temp file unit EOF.
     *
     * @param fw the writer to temp file
     * @param s the scanner for save file
     * @throws IOException If file no longer exists when trying to write
     */
    public static void copyUntilEnd(FileWriter fw, Scanner s) throws IOException {
        while (s.hasNext()) {
            String inputSentence = s.nextLine();
            fw.append(inputSentence + System.lineSeparator());
        }
    }
}
