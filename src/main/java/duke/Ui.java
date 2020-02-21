package duke;

public class Ui {
    /**
     * Generates Logo.
     */
    public void printLogo() {
        int rows = 8;
        for (int i=1; i<= rows ; i++) {
            for (int j = rows; j > i ; j--) {
                System.out.print("       ");
            }
            System.out.print("           **");
            for (int k = 1; k < 2*(i -1) ;k++) {
                System.out.print("HI");
            }
            if( i==1) {
                System.out.println("RORO");
            } else {
                System.out.println("RORO**");
            }
        }
        for (int i=rows-1; i>= 1 ; i--) {
            for (int j = rows; j > i ; j--) {
                System.out.print("       ");
            }
            System.out.print("**SHI");
            for (int k = 1; k < 2*(i -1) ;k++) {
                System.out.print("RO");
            }
            if( i==1)
                System.out.println(" ");
            else
                System.out.println("SHI**");
        }
    }

    public static void printStraightLine() {
        System.out.println("_________________________________________________________________________________\n");
    }

    public static void printFancyLine() {
        System.out.println("****--****--****--****--****--****--****--****--****--****--****--****--****--***\n");
    }


    public Ui(){
        printLogo();
        printFancyLine();
        System.out.println("Hello! I'm Hiroshi");
        System.out.println("Lets plan your day buddy! Things are looking good.\n");
        printFancyLine();
    }
}
