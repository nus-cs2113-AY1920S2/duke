package duke;
/**
 * Task item class of the Duke project
 * Extends Task class contains a field name
 */
public class ToDos extends  Task{
    private  String name;

    public  ToDos(String name){
        super(name);
        this.name = name;

    }

    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }


    @Override
    public String showSearch() {
        return "[T]" + super.toString() ;
    }
}

