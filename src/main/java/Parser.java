public class Parser {
    protected String input;
    public Parser(){
        this.input = "";
    }
    public void setInput(String input){
        this.input = input;
    }
    public static void doLine(){
        String line = "_".repeat(60);
        System.out.println("\t"+line);
    }
    public String getFirst(){
        return this.input.split(" ", 2)[0];
    }
    public String getSecond(){
        return this.input.split(" ", 2)[1];
    }
    public String getDeadlineItem(){
        return getSecond().split(" /by ")[0];
    }
    public String getDeadlineBy(){
        return getSecond().split(" /by ")[1];
    }
    public String getEventItem(){
        return getSecond().split(" /at ")[0];
    }
    public String getEventAt(){
        return getSecond().split(" /at ")[1];
    }
    public String getToDo(){
        return getSecond();
    }
    public String getCompleteNumber(){
        return getSecond();
    }


}
