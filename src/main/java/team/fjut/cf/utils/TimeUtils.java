package team.fjut.cf.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author axiang [2020/3/16]
 */
public class TimeUtils {
    /**
     * 获得当天零时零分零秒
     *
     * @return
     */
    public static Date initDateByDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }
}
