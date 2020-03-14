package duke.exceptions;

public class MissingLocationException extends Exception{

    String errorDescriptions;

    public MissingLocationException(String errorDescriptions){
        this.errorDescriptions = errorDescriptions;
    }

    public void printDescr(){
        System.out.println(errorDescriptions);
    }
}
