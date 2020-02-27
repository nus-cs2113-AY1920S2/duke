package Duke.Asset;

import Duke.Tasks.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * This is the class that stores the list of tasks in an ArrayList.
 * This class also saves and loads the ArrayList into data.txt
 */
public class Storage {

    private File inFile;

    public Storage(Ui ui) throws IOException, FileNotFoundException {
                ui.promptUser("Creating new data file for Nini");
                String dir= "data";
                File file = new File(dir);
                boolean bool = file.mkdir();
                dir+="/duke.txt";
                File file2 = new File(dir);
                if(bool && file2.createNewFile()){
                    ui.promptUser("Directory created successfully.\n Data for Nini is stored at:\n" +
                            file2.getAbsolutePath());
                    this.inFile = new File (dir);
                }else{
                    throw new FileNotFoundException("Sorry could not create specified directory, refer to User guide "
                            + "on missing file error");
                }
    }
    public Storage(String filePath) throws FileNotFoundException {
        this.inFile= new File(filePath);
    }

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
    public void saveFile(ArrayList<Task> l1) throws FileNotFoundException {
            PrintWriter outText= new PrintWriter(this.inFile);
            for(int i=0; i< l1.size(); i++){
                outText.println(l1.get(i).toFile());
            }
            outText.close();
    }
}
