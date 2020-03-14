package duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Parser {
    public static String readCommand() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        System.out.println("Type something");
        str = br.readLine().toLowerCase();
        return str.trim();
    }
}
