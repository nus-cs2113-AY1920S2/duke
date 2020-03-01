package duke.utility;

import java.time.DateTimeException;
import java.time.LocalDate;


public class DateFormatter {


    private String rawInput;
    private String[] dateInfo;

    private LocalDate date;
    private String time;
    private String midday;

    private boolean isValidDate = false;
    private boolean isValidTime = false;

    private final int DATE_LENGTH = 3;
    private final int DATE_INFO_MAX_LENGTH = 2;

    enum Midday {
        AM, PM
    }

    public DateFormatter (String rawInput) throws DateTimeException {
        this.rawInput = rawInput.trim();

        this.dateInfo = rawInput.split(" ");

        initDate();
        initTime();

        checkForException();
    }

    private void checkForException() throws DateTimeException {

        if (!isValidTime && !isValidDate) {
            throw new DateTimeException("Invalid time and date: [" + rawInput + "]");
        }

        if (!isValidTime && isValidDate && dateInfo.length == DATE_INFO_MAX_LENGTH) {
            throw new DateTimeException("Invalid time: [" + dateInfo[1] + "]");
        }

        if (isValidTime && !isValidDate && dateInfo.length == DATE_INFO_MAX_LENGTH) {
            throw new DateTimeException("Invalid date: [" + dateInfo[0] + "]");
        }

    }

    private void initDate () {

        try {

            String[] dateComponents = dateInfo[0].split("-");

            if (dateComponents.length != DATE_LENGTH) {
                isValidDate = false;
                return;

            } else {
                int day = Integer.parseInt(dateComponents[0]);
                int month = Integer.parseInt(dateComponents[1]);
                int year = Integer.parseInt(dateComponents[2]);

                date = LocalDate.of(year, month, day);
                isValidDate = true;
            }


        } catch (DateTimeException e) {
            isValidDate = false;
        }

    }

    private void initTime () {

        try {

            String[] timeComponents;

            // Check whether the user only entered a time
            if (dateInfo.length == 1 && !isValidDate) {
                timeComponents = dateInfo[0].trim().split(":");
            } else {
                timeComponents = dateInfo[1].trim().split(":");
            }

            int rawHour = Integer.parseInt(timeComponents[0]);
            int rawMinutes = Integer.parseInt(timeComponents[1]);

            int hour = getHour(rawHour);

            if (!(hour >= 1 && hour <= 12)) {
                isValidTime = false;
                return;
            }

            if (!(rawMinutes >= 0 && rawMinutes <= 59)) {
                isValidTime = false;
                return;
            }

            time = String.format("%02d:%02d %s", hour, rawMinutes, midday);
            isValidTime = true;

        } catch (IndexOutOfBoundsException e) {
            isValidTime = false;

        } catch (NumberFormatException e) {
            isValidTime = false;

        }

    }

    private int getHour (int rawHour) {

        if (rawHour >= 0 && rawHour <= 11) {
            midday = Midday.AM.toString().toLowerCase();

            if (rawHour == 0) {
                return 12;
            }

            return rawHour;
        } else {
            midday = Midday.PM.toString().toLowerCase();

            if (rawHour == 12) {
                return rawHour;
            }

            return rawHour - 12;
        }
    }

    public String getTime () {

        if (isValidTime) {
            return time;
        }

        return "";
    }

    public String getDate () {

        if (isValidDate) {

            int day = date.getDayOfMonth();
            int year = date.getYear();
            String month = getMonth(date.getMonth().toString());

            String date = String.format("%s %02d, %d", month, day, year);

            return date;
        }

        return "";
    }

    private String getMonth (String rawMonth) {
        String month = rawMonth.substring(0, 3).toLowerCase();
        month = month.substring(0, 1).toUpperCase() + month.substring(1);

        return month;
    }

    public boolean hasValidDate () {
        return isValidDate;
    }

    public boolean hasValidTime () {
        return isValidTime;
    }

}
