package Exceptions;

public class MissingDescriptionException extends Exception{

    String errorDescriptions;

    public MissingDescriptionException(String errorDescriptions){
        this.errorDescriptions = errorDescriptions;
    }

    public void printDescr(){
        System.out.println(errorDescriptions);
    }
}
