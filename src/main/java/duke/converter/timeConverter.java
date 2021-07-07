package duke.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class timeConverter {
    public static String dateTime (String dateTime) throws ParseException {
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("d/MM/yyyy HHmm");
        Date date = simpleDateFormat1.parse(dateTime);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("EEE dd MMMM yyyy hh:mm a");
        return simpleDateFormat2.format(date);
    }
}
