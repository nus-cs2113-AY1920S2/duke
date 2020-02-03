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
}




