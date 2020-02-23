package Exceptions;

import java.util.Scanner;

public class resetError extends Exception {
    String errorDescriptions;

    public resetError(String errorDescriptions){
        this.errorDescriptions = errorDescriptions;
    }

    public void printDescr(){
        System.out.println(errorDescriptions);
    }

    public void manualPathEntry(){
        Scanner myScanner = new Scanner(System.in);
        String path = myScanner.nextLine();

    }
}
