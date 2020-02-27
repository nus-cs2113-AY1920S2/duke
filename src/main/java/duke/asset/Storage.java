package duke.asset;

import duke.tasks.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * This is the class that stores the list of tasks in an ArrayList.<br>
 * This class also saves and loads the ArrayList into data.txt.<br>
 */
public class Storage {

    private File inFile;
    /**
     * This constructor is called only when data folder is missing.<br>
     * @param ui This is the input. Allows for the software to inform<br>
     *           Users on missing data folder and that the software is<br>
     *           creating one.<br>
     * @throws IOException if data directory is missing.<br>
     * @throws FileNotFoundException if duke.txt is missing.<br>
     */
    public Storage(Ui ui) throws IOException, FileNotFoundException {
                ui.promptUser("Creating new data file for Nini");
                String dir= "data";
                File file = new File(dir);
                boolean bool = file.mkdir();
                dir+="/duke.txt";
                File file2 = new File(dir);
                if(bool && file2.createNewFile()){
                    ui.promptUser("Directory created successfully.\nData for Nini is stored at:\n" +
                            file2.getAbsolutePath());
                    this.inFile = new File (dir);
                }else{
                    throw new FileNotFoundException("Sorry could not create specified directory, refer to User guide "
                            + "on missing file error");
                }
    }

    /**
     * This constructor creates a new Storage class.<br>
     *
     * @param filePath This is the path of the data file (duke.txt) required for Duke.<br>
     * @throws FileNotFoundException if data file (duke.txt) does not exist.<br>
     */
    public Storage(String filePath) throws FileNotFoundException {
        this.inFile= new File(filePath);
    }
    /**
     * This method loads data onto the software.<br>
     *
     * @return The method returns an ArrayList of Tasks.<br>
     * @throws FileNotFoundException if duke.txt does not exist.<br>
     */
    public ArrayList<Task> loadFile() throws FileNotFoundException {
        Scanner in = new Scanner(this.inFile);
        ArrayList<Task> l1= new ArrayList<>();
        while (in.hasNextLine()) {
            String[] temp = in.nextLine().split("~");
            initList(temp, l1);
        }
        in.close();
        return l1;
    }
    /**
     * This method initialises the ArrayList before using it.<br>
     *
     * @param temp This is the data from the text file that has<br>
     *             been split into arrays of Strings.<br>
     * @param l1 This is the ArrayList of Tasks that is used by the software.<br>
     */
    public static void initList(String[] temp, ArrayList<Task> l1){
        switch(temp[0]){
            case "T" :
                Todo todo = new Todo(temp[2]);
                if(temp[1].equals("Y")){
                    todo.done();
                }
                l1.add(todo);
                break;
            case "D" :
                Deadline deadline = new Deadline(temp[3]);
                deadline.setBy(temp[1]);
                if(temp[2].equals("Y")){
                    deadline.done();
                }
                l1.add(deadline);
                break;
            case "E" :
                Event event = new Event(temp[3]);
                event.setAt(temp[1]);
                if(temp[2].equals("Y")){
                    event.done();
                }
                l1.add(event);
                break;
            default:
                break;
        }
    }
    /**
     * This method saves the data entered by the User into a text file.<br>
     *
     * @param l1 This is the ArrayList of Tasks that is used by the software.<br>
     * @throws FileNotFoundException if duke.txt does not exist.<br>
     */
    public void saveFile(ArrayList<Task> l1) throws FileNotFoundException {
            PrintWriter outText= new PrintWriter(this.inFile);
            for(int i=0; i< l1.size(); i++){
                outText.println(l1.get(i).toFile());
            }
            outText.close();
    }
}
