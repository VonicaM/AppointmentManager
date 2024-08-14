package software2assignment.utilities;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * DateTime is used to manage the getting the date and time information that
 * will be used in the application.
 * 
 * @author Michael Vonica michaelvonica@gmail.com
 */
public class DateTime {
    
    /**
     * Determines and returns the local time for the user.
     * 
     * @return current local time.
     */
    public static java.sql.Timestamp getTimeStamp() {
        ZoneId zoneid = ZoneId.of("UTC");
        LocalDateTime localDateTime = LocalDateTime.now(zoneid);
        java.sql.Timestamp timeStamp = Timestamp.valueOf(localDateTime);
        return timeStamp;
    }
    
    /**
     * Determines and returns the local date for the user.
     * 
     * @return current local date.
     */
    public static java.sql.Date getDate() {
        java.sql.Date date = java.sql.Date.valueOf(LocalDate.now());
        return date;
    }
}
