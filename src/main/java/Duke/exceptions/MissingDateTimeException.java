package duke.exceptions;

public class MissingDateTimeException extends Exception{

    String errorDescriptions;

    public MissingDateTimeException(String errorDescriptions){
        this.errorDescriptions = errorDescriptions;
    }

    public void printDescr(){
        System.out.println(errorDescriptions);
    }
}
