package team.fjut.cf.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author axiang [2020/4/16]
 */
public class DateUtils {
    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }
}
