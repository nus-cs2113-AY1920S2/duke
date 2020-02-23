import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileSaver {

    public static void makeNewDirectory() {
        File f = new File("data");
        f.mkdir();
    }

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

    public static void saveFile(File f, String description) {
        try (FileWriter fw = new FileWriter(f, true)) {
            fw.write("T | 0 | " + description + System.lineSeparator());

        } catch (FileNotFoundException e) {
            System.out.println("File not found"); //TODO move to printer class

        } catch (IOException e) {
            System.out.println("nani"); //TODO move to printer class
        }
    }

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

    public static Scanner getScanner(File f, int index, FileWriter fw) throws IOException {
        Scanner s = new Scanner(f);
        for (int i = 1; i < index; i += 1) {
            String sentence = s.nextLine();
            fw.append(sentence + System.lineSeparator());
        }
        return s;
    }

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

    public static void copyUntilEnd(FileWriter fw, Scanner s) throws IOException {
        while (s.hasNext()) {
            String inputSentence = s.nextLine();
            fw.append(inputSentence + System.lineSeparator());
        }
    }
}
