/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ac.utils;


import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.company.ac.exceptions.DateFormatException;

/**
 *
 * @author Administrator
 */
public class DateManager {

    public final static int DB_FORMAT = 1;
    public final static int READABLE_FORMAT = 2;
    public final static int READABLE_ONLY_NUM_FORMAT = 3;
    public final static String dd_h_MMM_h_yyyy = "2-6-"; // dd-MMM-yyyy READABLE_FORMAT
    public final static String dd_h_mm_h_yyyy = "2-5-"; // dd-mm-yyyy READABLE_ONLY_NUM_FORMAT
    public final static String mm_s_dd_s_yyyy = "2/5/"; // mm/dd/yyyy DB_FORMAT
    public final static String yyyy_h_mm_h_dd = "4-7-"; // yyyy-mm-dd DB_FORMAT
    public final static String dd_d_mm_d_yyyy = "2.5.";//mm.dd.yyyy
    public final static String dd_sp_mm_sp_yyyy = "2 5 "; //mm dd yyyy
    public int pattern = 1;
    public final static int TIME_FORMAT_24HR = 2;
    public final static int TIME_FORMAT_12HR = 1;
    Hashtable<String, String> patterns = new Hashtable<>();
    private final SimpleDateFormat sdf = new SimpleDateFormat();
    private final SimpleDateFormat dbf = new SimpleDateFormat("MM/dd/yyyy");
    private final GregorianCalendar calendar = new GregorianCalendar();	// This is used only for todays date
    private GregorianCalendar dateObj = new GregorianCalendar();
    private final GregorianCalendar now = new GregorianCalendar();
    /**
     *0 - Jan
     *11- Dec
     */
    String[] longMonthNames = {
        "Janaury", "February", "March", "April", "May",
        "June", "July", "August", "September", "October",
        "November", "December"
    };
    //0->Jan,1->Feb,2-Mar,...
    String[] shortMonthNames = {
        "Jan", "Feb", "Mar", "Apr", "May", "Jun",
        "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    };
    // 1-Sunday, 2- Monday, ..
    String[] longDayNames = {
        "", "Sunday", "Monday", "Tuesday",
        "Wednesday", "Thursday", "Friday", "Saturday"
    };
    String[] shortDayNames = {
        "", "Sun", "Mon", "Tues",
        "Wed", "Thur", "Fri", "Sat"
    };

    public DateManager() {
        patterns.put(dd_h_MMM_h_yyyy, "" + 1);
        patterns.put("1-5-", "" + 1);

        patterns.put(mm_s_dd_s_yyyy, "" + 2);
        patterns.put("2/4/", "" + 2);
        patterns.put("1/4/", "" + 2);
        patterns.put("1/3/", "" + 2);

        patterns.put(dd_d_mm_d_yyyy, "" + 5);
        patterns.put("2.4.", "" + 5);
        patterns.put("1.4.", "" + 5);
        patterns.put("1.3.", "" + 5);

        patterns.put(dd_sp_mm_sp_yyyy, "" + 6);
        patterns.put("2 4 ", "" + 6);
        patterns.put("1 4 ", "" + 6);
        patterns.put("1 3 ", "" + 6);

        patterns.put(dd_h_mm_h_yyyy, "" + 4);
        patterns.put("2-4-", "" + 4);
        patterns.put("1-4-", "" + 4);
        patterns.put("1-3-", "" + 4);

        patterns.put(yyyy_h_mm_h_dd, "" + 3);
        patterns.put("4-6-", "" + 3);
    }

    /**
     * @author - Vivekanand Pandhare
     * Works only for HTML pages
     * @return The date in readale format Ex: 12<sub>th</sub> September 2006
     */
    public String getExtendedTodaysDate() {
        int m = 0, d = 0;
        m = calendar.get(Calendar.MONTH);
        String mm = longMonthNames[m];
        d = calendar.get(Calendar.DATE);
        String dd = "";
        int x = d % 20;
        if (d > 20 && x > 10) {
            x = x - 10;
        }
        switch (x) {
            case 1:
                dd = d + "<sup>st</sup>";
                break;
            case 2:
                dd = d + "<sup>nd</sup>";
                break;
            case 3:
                dd = d + "<sup>rd</sup>";
                break;
            case 0:
            default:
                dd = d + "<sup>th</sup>";
        }
        return longDayNames[calendar.get(Calendar.DAY_OF_WEEK)] + ", " + dd + " " + mm + " " + calendar.get(Calendar.YEAR);
    }

    /**
     * @author - Vivekanand Pandhare
     * Works only for HTML pages
     * @return The date in readale format Ex: 12<sub>th</sub> September 2006
     */
    public String getExtendedTodaysDate(String date) throws DateFormatException {
        GregorianCalendar gcal = getCalendarObj(date);
        int m = 0, d = 0;
        m = gcal.get(Calendar.MONTH);
        String mm = longMonthNames[m];
        d = gcal.get(Calendar.DATE);
        String dd = "";
        int x = d % 20;
        if (d > 20 && x > 10) {
            x = x - 10;
        }
        switch (x) {
            case 1:
                dd = d + "<sup>st</sup>";
                break;
            case 2:
                dd = d + "<sup>nd</sup>";
                break;
            case 3:
                dd = d + "<sup>rd</sup>";
                break;
            case 0:
            default:
                dd = d + "<sup>th</sup>";
        }
        gcal = null;
        return longDayNames[now.get(Calendar.DAY_OF_WEEK)] + ", " + dd + " " + mm + " " + now.get(Calendar.YEAR);
    }

    public String[] getLongMonths() {
        return longMonthNames;
    }

    /**
     * @author - Vivekanand Pandhare
     * Works proper for HTML pages only
     * @return dynamically shows the current time with seconds ex. 10:45:33 PM
     */
    public String getTimer(String delimiter) {        
        sdf.applyPattern("hh" + delimiter + "mm" + delimiter + "ss");
        int AM_PM = calendar.get(Calendar.AM_PM);
        return sdf.format(calendar.getTime()) + ((AM_PM == 0) ? " AM" : " PM");
    }

    public String getCurrentTime() {
        //sdf=new SimpleDateFormat("HH:mm:ss");
        sdf.applyPattern("HH:mm:ss");
        return sdf.format(calendar.getTime());
    }

    public String todaysDate() {
        //sdf=new SimpleDateFormat("MMM dd, yyyy");
        sdf.applyPattern("MMM dd, yyyy");
        return sdf.format(calendar.getTime());
    }

    /**
     * @author Vivekanand Pandhare
     * @return current date in yyyy-mm-dd [MySQL DB Format] format (Ex: 2009-02-21)
     */
    public String currentDate() {
        //sdf=new SimpleDateFormat("yyyy-MM-dd");
        sdf.applyPattern("yyyy-MM-dd");
        return sdf.format(calendar.getTime());
    }

    /**
     * @author Vivekanand Pandhare
     * @param date ex: 04/17/2007
     * @param delimiter: "/"
     * @return day name ex: Monday
     */
    public String getDay(String date) throws DateFormatException {
        GregorianCalendar gcal = getCalendarObj(date);
        return longDayNames[gcal.get(Calendar.DAY_OF_WEEK)];
    }

    public String getDay(int index) {
        return longDayNames[index];
    }

    public String[] getDays() {
        return longDayNames;
    }

    public String[] getShortDays() {
        return shortDayNames;
    }

    public GregorianCalendar getCalendarObj(String date) throws DateFormatException {
        try {
			now.setTime(sdf.parse(date));
		} catch (ParseException e) {
			throw new DateFormatException();
		}
		//date = format(date);//System.err.println("Date :"+date);
        StringTokenizer token = new StringTokenizer(date, "/");
        if (token.hasMoreTokens()) {
            int mm = Integer.parseInt("" + token.nextToken()) - 1;
            int dd = Integer.parseInt("" + token.nextToken());
            int yy = Integer.parseInt("" + token.nextToken());
            now.set(yy, mm, dd);
        }
        token = null;
        return now;
    }

    public GregorianCalendar getCalendarObj(String date, int hours, int minutes) {
        try {
            date = format(date);
        } catch (DateFormatException e) {
            e.printStackTrace();
            return calendar;
        }
        StringTokenizer token = new StringTokenizer(date, "/");
        //GregorianCalendar calObj = null;
        if (token.hasMoreTokens()) {
            int mm = Integer.parseInt("" + token.nextToken()) - 1;
            int dd = Integer.parseInt("" + token.nextToken());
            int yy = Integer.parseInt("" + token.nextToken());
            //calObj = new GregorianCalendar(yy, mm, dd, hours, minutes);
            now.set(yy, mm, dd);
        }
        return now;
        //return calObj;
    }

    /**
     * @author Vivekanand Pandhare
     * This method compares a given date string with todays date
     * @param date - the date string in any valid format
     * @return true if given date is after the todays date
     */
    public boolean compareWithToday(String date) throws DateFormatException {
        GregorianCalendar gcal = getCalendarObj(date);
        return calendar.before(gcal);
    }

    /**
     * @author Vivekanand Pandhare
     * This method compares a given date string with todays date
     * @param date - the date string in any valid format
     * @return 1 if given date d1 is after the date d2
     * <pre>
     * <code>d1 = d2 return  0</code>
     * <code>d1 < d2 return -1</code>
     * <code>d1 > d2 return  1</code>
     * </pre>
     */
    public int compareDates(String d1, String d2) throws DateFormatException {
        //return getCalendarObj(d1).compareTo(getCalendarObj(d2));
        try {
            d1 = format(d1);
        } catch (Exception e) {
        }
        try {
            d2 = format(d2);
        } catch (Exception e) {
        }
        if (d1.equals(d2)) {
            return 0;
        }
        return (getCalendarObj(d1).after(getCalendarObj(d2))) ? 1 : -1;
    }

    /**
     * @author Vivekanand Pandhare
     * @param date - pass the date in yyyy-mm-dd or mm/dd/yyyy format Ex:2005-02-09 or 06/21/2007
     * @return date string in readable format Ex: 12-sep-1996
     */
    public String getReadableDate(String date) throws DateFormatException {
        if (date == null || date.trim().equals("") || date.trim().equals("null")) {
            return "";
        }
        sdf.applyPattern("dd-MMM-yyyy");
        date = sdf.format(getCalendarObj(date).getTime());
        return date;
    }

    public String format(String date, String pattern) throws DateFormatException {
        sdf.applyPattern(pattern);
        return sdf.format(getCalendarObj(date).getTime());
    }

    /**
     * @author Vivekanand Pandhare
     * @param date format Ex: 05-DEC-2005
     * @return date in mm/dd/yyyy format (Ex: 9/25/2005)
     */
    public String getDBDate(String date) {
        try {
            date = format(date);
        } catch (DateFormatException e) {
            date = "";
            e.printStackTrace();
        }
        return date;
    }

    /**
     * @author Vivekanand Pandhare
     * @param date format Ex: 05-DEC-2005
     * @return date in yyyy-mm-dd format (Ex: 2009-06-26)
     */
    public String getDBDateMySQL(String date) throws DateFormatException {
        return format(date, "yyyy-MM-dd");
    }

    /**
     * @author - Vijay Pawar
     * @param startDate
     * @param endDate
     * @param day ex. Monday
     * @return no. of day occurance of given day(ex. monday) between two given dates)
     */
    public int dayOccurance(String startDate, String endDate, String dayName) throws DateFormatException {
        long t1 = getCalendarObj(startDate).getTimeInMillis();
        long t2 = getCalendarObj(endDate).getTimeInMillis();

        t1 = t1 / (24 * 60 * 60 * 1000L);
        t2 = t2 / (24 * 60 * 60 * 1000L);
        int count = 0;
        int d = getDayIndex(dayName);
        d = (d + 1) % 7;
        for (long i = t1; i <= t2; i++) {
            if (i % 7 == d) {
                count++;
            }
        }
        return count;
    }

    /**
     * @param date - Date String
     * @param noOfDays - Integer value passed as Sting
     * @return - New date obtained after adding noOfDays
     */
    public String newDate(String date, String noOfDays) throws DateFormatException {
        int days = Integer.parseInt(noOfDays);
        GregorianCalendar gcal = getCalendarObj(date);
        gcal.add(Calendar.DATE, days);        
        sdf.applyPattern("dd-MMM-yyyy");
        date = sdf.format(now.getTime());
        return date;
    }

    /**
     * @param date - Date String
     * @param noOfDays - Integer Value
     * @return - New date obtained after adding noOfDays
     */
    public String newDate(String date, int noOfDays) throws DateFormatException {
        GregorianCalendar gcal = getCalendarObj(date);
        gcal.add(Calendar.DATE, noOfDays);
        sdf.applyPattern("dd-MMM-yyyy");
        date = sdf.format(gcal.getTime());
        return date;
    }

    public java.util.Date newDate(java.util.Date date, int days) {
        GregorianCalendar gcal = new GregorianCalendar();
        gcal.setTime(date);
        gcal.add(Calendar.DATE, days);
        return gcal.getTime();
    }

    public String getPreviousDate(String date, int days) throws DateFormatException {
        long ms = getCalendarObj(date).getTimeInMillis();
        ms -= days * 24 * 60 * 60 * 1000L;
        GregorianCalendar gcal = new GregorianCalendar();
        gcal.setTimeInMillis(ms);        
        sdf.applyPattern("dd-MMM-yyyy");
        date = sdf.format(gcal.getTime());
        return date;
    }

    public String getHistoryDate(int years){
        GregorianCalendar gcal = new GregorianCalendar();
        gcal.roll(Calendar.YEAR, -years);
        sdf.applyPattern("yyyy-MM-dd");
        return sdf.format(gcal.getTime());
    }

    /**
     * This method is used to get the no_of_days between two given dates
     * @param d1 - Start Date
     * @param d2 - End Date
     * @return - no of days between d1 and d2
     */
    public String getNoofDays(String d1, String d2) {
        try {
            long start = getCalendarObj(d1).getTimeInMillis() / (24 * 60 * 60 * 1000);
            long end = getCalendarObj(d2).getTimeInMillis() / (24 * 60 * 60 * 1000);
            return "" + ((start < end) ? end - start + 1 : start - end + 1);
        } catch (Exception e) {
            System.err.println("CMSDateFormats(getNoofDays(d1,d2)):" + e);
        }
        return null;
    }

    /**
     * @param d1
     * @param d2
     * @return
     */
    public int getNoofDays(java.util.Date d1, java.util.Date d2) {
        GregorianCalendar date1 = new GregorianCalendar();
        GregorianCalendar date2 = new GregorianCalendar();
        date1.setTime(d1);
        date2.setTime(d2);
        long start = date1.getTimeInMillis() / (24 * 60 * 60 * 1000);
        long end = date2.getTimeInMillis() / (24 * 60 * 60 * 1000);
        return (start < end) ? (int) (end - start + 1) : (int) (start - end + 1);

    }

    /**
     * @author - Vivekanand Pandhare
     * @param j
     * @return no. of days in given year
     */
    public int getDaysInYear(int j) {
        GregorianCalendar now = new GregorianCalendar(j, Calendar.JANUARY, 1);
        if (now.isLeapYear(j)) {
            return 366;
        } else {
            return 365;
        }
    }

    /**
     * @author - Vivekanand Pandhare
     * @param i - month index Ex: 1 for Jan, 2 for Feb ...
     * @param j - year Ex: 2007
     * @return no. of Days for given month and year
     */
    public int getDaysInMonth(int i, int j) {
        GregorianCalendar now = new GregorianCalendar(j, i - 1, 1);
        return now.getActualMaximum(Calendar.DATE);
    }

    /**
     * @author Vivekanand Pandhare
     * @param date
     * @return
     */
    public String ageSince(String date) throws DateFormatException {
        if ("null".equals(date + "") || "".equals(date)) {
            return "";
        }
        GregorianCalendar fromDate = getCalendarObj(date);
        String age = "";
        int cm = calendar.get(Calendar.MONTH);
        int pm = fromDate.get(Calendar.MONTH);
        int yearsPassed = calendar.get(Calendar.YEAR) - fromDate.get(Calendar.YEAR);
        if (yearsPassed > 1) {
            yearsPassed--;
        }
        if (cm < pm) {
            cm += 12;
        }
        int monthDiff = cm - pm;
        if (monthDiff == 0) {
            if (calendar.get(Calendar.DAY_OF_MONTH) <= fromDate.get(Calendar.DAY_OF_MONTH)) {
                yearsPassed++;
            }
        } else if (monthDiff == 12) {
            yearsPassed++;
        }
        age = "" + yearsPassed;
        return age;
    }

    /** Returns current date and time in Gregorian miliseconds
     * @return
     */
    public String getElapsedTime(String date, int hours, int minutes) {
        GregorianCalendar now = getCalendarObj(date, hours, minutes);
        return "" + now.getTimeInMillis();
    }

    /** Returns current date and time in Gregorian miliseconds
     * @return
     */
    public String getElapsedTime() {
        GregorianCalendar now = new GregorianCalendar();
        return "" + now.getTimeInMillis();
    }

    /** Returns current date and time in Gregorian miliseconds
     * @return
     */
    public String getElapsedTime(String date) throws DateFormatException {
        GregorianCalendar now = getCalendarObj(date);
        return "" + now.getTimeInMillis();
    }

    /** Returns Gregorian miliseconds in dd-MMM-yyyy hh:mm:ss format
     * @param millis
     * @return
     */
    public String readMilliSeconds(String millis, String format) {
        GregorianCalendar gcal = new GregorianCalendar();
        long ms = 0;
        try {
            ms = Long.parseLong(millis);
        } catch (Exception e) {
            System.err.println("Error:" + e);
        }
        gcal.setTimeInMillis(ms);
        //sdf=new SimpleDateFormat(format);
        sdf.applyPattern(format);
        return sdf.format(now.getTime());
    }

    /**
     * @param long
     *            milli
     * @return String
     */
    public String convertMilliToHours(long milli) {
        DecimalFormat dc = new DecimalFormat("00");
        long hours = milli / (1000 * 60);
        long minutes = hours % 60;
        hours /= 60;
        return dc.format(hours) + ":" + dc.format(minutes);

    }

    /**
     * @author Vivekanand Pandhare
     * @param time format: HH:mm:ss Ex:14:00:00
     * @return time String in format hh:mm:am/pm Ex: 2:00 PM
     * @throws ParseException
     */
    public String formatTime(String time) {
        DateFormat dateFormatter = DateFormat.getTimeInstance(DateFormat.SHORT);
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        try {
            time = dateFormatter.format(df.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * @author Vivekanand Pandhare
     * @param time format: HH:mm:ss Ex:14:00:00
     * @return time String in format hh:mm:am/pm Ex: 2:00 PM
     * @throws ParseException
     */
    public String formatTime(String time, String timePattern) {
        DateFormat dateFormatter = DateFormat.getTimeInstance(DateFormat.MEDIUM);
        SimpleDateFormat df = new SimpleDateFormat(timePattern);
        try {
            time = dateFormatter.format(df.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    public String getMonthFullName(String date) throws DateFormatException {
        setDate(date);
        int m = readMonth();
        return longMonthNames[m - 1];
    }

    public StringBuffer welcomeUser(String name) {
        int hours = calendar.get(Calendar.HOUR);
        int amPm = calendar.get(Calendar.AM_PM);
        StringBuffer str = new StringBuffer("<font color='white'>Good ");
        switch (amPm) {
            case 0:
                str.append("Morning! ");
                break;
            case 1:
                if (hours < 4) {
                    str.append("Afternoon! ");
                } else {
                    str.append("Evening! ");
                }
        }
        str.append(name + "</font>");
        return str;
    }

    /**
     * @param date1
     * @param date2
     * @return month count between two dates.
     */
    public int getMonthCount(String date1, String date2) throws DateFormatException {
        GregorianCalendar d1 = getCalendarObj(date1);
        GregorianCalendar d2 = getCalendarObj(date2);
        if (d1 == null || d2 == null) {
            return 0;
        }
        int y1 = d1.get(Calendar.YEAR);
        int y2 = d2.get(Calendar.YEAR);
        int m1 = d1.get(Calendar.MONTH);
        int m2 = d2.get(Calendar.MONTH);
        int s1 = y1 * 12 + m1;
        int s2 = y2 * 12 + m2;
        return (s1 > s2) ? s1 - s2 : s2 - s1;
    }

    public String getShortMonthName(int month) {
        return shortMonthNames[month];
    }

    public String getZonalTime(String timeZone) {
        Calendar now = new GregorianCalendar(TimeZone.getTimeZone(timeZone));
        int hr = now.get(Calendar.HOUR);
        int mn = now.get(Calendar.MINUTE);
        int sc = now.get(Calendar.SECOND);
        //String ampm=(now.get(Calendar.AM_PM)==Calendar.AM)?"am":"pm";
        return formatTime(hr + ":" + mn + ":" + sc, "hh:mm:ss");
    }
//===============FOR SETTING A FIXED DATE========================//

    /**
     * This method is used to set a fixed date. After setting a date you can use
     * readMonth(), readDay(), readYear() to get the integer value for month, day, year respectively
     * @param date - date passed in a valid format
     */
    public void setDate(String date) throws DateFormatException {
        dateObj = getCalendarObj(date);
        if (dateObj == null) {
            dateObj = calendar;
        }
    }

    public int readMonth() {
        return dateObj.get(Calendar.MONTH) + 1;
    }

    public int readDay() {
        return dateObj.get(Calendar.DATE);
    }

    public int readYear() {
        return dateObj.get(Calendar.YEAR);
    }

    public int readDayIndex() {
        return dateObj.get(Calendar.DAY_OF_WEEK);
    }

    public void addMonth(int noOfMonth) {
        dateObj.add(Calendar.MONTH, noOfMonth);
    }

    public String getDate(String format) throws DateFormatException {
        return format(readDay() + "-" + readMonth() + "-" + readYear(), format);
    }
//================================================================//

//-----------------------------------------------------------------------------------//
/*								FORMATTING METHODS									 */
//------------------------------------------------------------------------------------//
    private void pattern(String str) throws DateFormatException {        
        try {
            Pattern userPattern = Pattern.compile("[-/:. ]");
            Matcher matcher = userPattern.matcher(str);
            String foundPattern = "";
            while (matcher.find()) {
                foundPattern += matcher.start() + matcher.group();
            }
            this.pattern = Integer.parseInt("" + patterns.get(foundPattern));
        } catch (Exception e) {
            throw new DateFormatException();
        }
    }

    /**
     * @param str - the date string Ex.: 19-jan-2008 or 01/19/2008 or 2008-01-19
     * @return str - the date string in mysql format i.e. 01/19/2008
     * @throws DateFormatException
     */
    private String format(String str) throws DateFormatException {
        pattern(str);
        switch (this.pattern) {
            case 1:
                return formatReadable(str, READABLE_FORMAT);
            case 2:
                return formatDBWithSlash(str);
            case 3:
                return formatDBWithHipen(str);
            case 4:
                return formatReadable(str, READABLE_ONLY_NUM_FORMAT);
            case 5:
                return formatDBWithDot(str);
            case 6:
                return formatDBWithSpace(str);
        }
        return str;
    }

    /**
     * @param str - 12-Jun-2008
     * @return date in formatted form i.e. 06/12/2008
     */
    private String formatReadable(String str, int format) throws DateFormatException {
        StringTokenizer token = new StringTokenizer(str, "-");
        try {
            if (token.hasMoreTokens()) {
                int dd = Integer.parseInt("" + token.nextToken());
                int mm = 0;
                if (format == READABLE_FORMAT) {
                    mm = getMonthIndex("" + token.nextToken());
                } else {
                    mm = Integer.parseInt("" + token.nextToken()) - 1;
                }
                int yy = Integer.parseInt("" + token.nextToken());
                now.set(yy, mm, dd);
            }
            token = null;
        } catch (Exception e) {
            throw new DateFormatException();
        }
        return dbf.format(now.getTime());
    }

    /**
     * @param str - 01/30/2008
     * @return str - the date string in DB2 format i.e. 06/12/2008
     * @throws DateFormatException
     */
    private String formatDBWithSlash(String str) throws DateFormatException {
        StringTokenizer token = new StringTokenizer(str, "/");
        try {
            if (token.hasMoreTokens()) {
                int mm = Integer.parseInt("" + token.nextToken()) - 1;
                int dd = Integer.parseInt("" + token.nextToken());
                int yy = Integer.parseInt("" + token.nextToken());
                now.set(yy, mm, dd);
            }
        } catch (Exception e) {
            throw new DateFormatException();
        }
        return dbf.format(now.getTime());
    }

    /**
     * @param str - 01.30.2008
     * @return str - the date string in DB2 format i.e. 06/12/2008
     * @throws DateFormatException
     */
    private String formatDBWithDot(String str) throws DateFormatException {
        StringTokenizer token = new StringTokenizer(str, ".");
        try {
            if (token.hasMoreTokens()) {
                int dd = Integer.parseInt("" + token.nextToken());
                int mm = Integer.parseInt("" + token.nextToken()) - 1;
                int yy = Integer.parseInt("" + token.nextToken());
                now.set(yy, mm, dd);
            }
        } catch (Exception e) {
            throw new DateFormatException();
        }
        return dbf.format(now.getTime());
    }

    /**
     * @param str - 01 30 2008
     * @return str - the date string in DB2 format i.e. 06/12/2008
     * @throws DateFormatException
     */
    private String formatDBWithSpace(String str) throws DateFormatException {
        StringTokenizer token = new StringTokenizer(str, " ");
        try {
            if (token.hasMoreTokens()) {
                int dd = Integer.parseInt("" + token.nextToken());
                int mm = Integer.parseInt("" + token.nextToken()) - 1;
                int yy = Integer.parseInt("" + token.nextToken());
                now.set(yy, mm, dd);
            }
        } catch (Exception e) {
            throw new DateFormatException();
        }
        return dbf.format(now.getTime());
    }

    /**
     * @param str - 2008-02-21
     * @return formatted date i.e. 02/21/2008
     */
    private String formatDBWithHipen(String str) throws DateFormatException {
        StringTokenizer token = new StringTokenizer(str, "-");
        try {
            if (token.hasMoreTokens()) {
                int yy = Integer.parseInt("" + token.nextToken());
                int mm = Integer.parseInt("" + token.nextToken()) - 1;
                int dd = Integer.parseInt("" + token.nextToken());
                now.set(yy, mm, dd);
            }
        } catch (Exception e) {
            throw new DateFormatException();
        }
        return dbf.format(now.getTime());
    }

    public boolean isValidDateFormat(String date) {
        try {
            if ("INVALID".equalsIgnoreCase(format(date))) {
                return false;
            }
        } catch (DateFormatException e) {
            e.printStackTrace();
        }
        return false;
    }

//---------------------------------------------------------------------------------------------------//
	/*									END OF FORMATING METHODS								*/
//---------------------------------------------------------------------------------------------------//
    private int getMonthIndex(String shortMonth) throws DateFormatException {
        for (int i = 0; i < shortMonthNames.length; i++) {
            if (shortMonthNames[i].equalsIgnoreCase(shortMonth)) {
                return i;
            }
        }
        throw new DateFormatException();
    }

    /**
     * @param longDayName - Day Name Ex.: Monday
     * @return Index of given day Ex.: For Sunday return value is 1
     */
    private int getDayIndex(String longDayName) {
        for (int i = 0; i < longDayNames.length; i++) {
            if (longDayNames[i].equalsIgnoreCase(longDayName)) {
                return i;
            }
        }
        return -1;
    }


    /*---------------------------------------------------------------------
     *				                MAIN METHOD
     *---------------------------------------------------------------------*/
    public static void main(String[] args) throws IOException, DateFormatException {
        DateManager formats = new DateManager();
        formats.test();
//        System.out.println(formats.getHistoryDate(29));
//        System.out.println(formats.getDBDateMySQL("12-jan-2007"));
//        formats.setDate("22-5-2009");
//        formats.addMonth(1);
//        System.out.println("Japan :"+formats.getZonalTime("GMT"));
//        System.out.println("Hong Kong:"+formats.getZonalTime("IST"));
//        System.out.println("India :"+formats.getZonalTime("EST"));
    }
    /*---------------------------------------------------------------------
     * 								END OF MAIN
     *---------------------------------------------------------------------*/

    public void test() throws DateFormatException {
        System.err.println("getExtendedTodaysDate	:"+getExtendedTodaysDate());
        System.err.println("getTimer 				:"+getTimer(":"));
        System.err.println("getDay 	(15-May-2008)	:"+getDay("5/15/2008"));
        System.err.println("compareWithToday		:"+compareWithToday("11-10-2008"));
        System.err.println("readableDate 28-10-2008 :"+getReadableDate("28-10-2008"));
        System.err.println("readableDate 2008-10-28 :"+getReadableDate("2008-10-28"));
        System.err.println("readableDate 10/28/2008 :"+getReadableDate("10/28/2008"));
        System.err.println("dayOccurance , Sunday 	:"+dayOccurance("2013-1-1","2-jAn-2014", "Wednesday"));
        System.err.println("dayOccurance , Sunday 	:"+dayOccurance("08/31/2008","28-09-2008", "Wednesday"));
        System.err.println("compareDates 	 	:"+compareDates("02/12/2008","12-Feb-2008"));
        System.err.println("newDate 		:"+newDate("01-jan-2014", "455"));
        System.err.println("getPreviousDate		:"+getPreviousDate("02-May-2010", 455));
        System.err.println("ageSince		:"+ageSince("11-sep-1980"));
        System.err.println("formatTime		:"+formatTime("17:24:23"));
        System.err.println("getDaysInYear(2008)		:"+getDaysInYear(2008));
        System.err.println("getDaysInYear(2009)		:"+getDaysInYear(2009));
        System.err.println("getDaysInMonth(2,2011)		:"+getDaysInMonth(2,2011));
        System.err.println("getDaysInMonth(2,2020)		:"+getDaysInMonth(2,2020));
        System.err.println("format(12/21/2006,yyyy/MMM dd)		:" + format("23-6-2007", "yyyy/MMM dd"));
        System.err.println("dayOccurance , Sunday 	:"+dayOccurance("01-jan-2008","28-jan-2008", "Monday"));
        System.err.println("getNoOfDays 	:"+getNoofDays(new java.util.Date(2007,3,21),new java.util.Date(2007,3,21)));
        System.err.println("welcomeUser 	:"+welcomeUser("Vivek"));
        System.err.println("D1(11-JAN-2009)=D2(11-JAN-2009) :"+compareDates("11-JAN-2009", "11-JAN-2009"));
        System.err.println("D1(11-JAN-2009)=D2(01/11/2009) :"+compareDates("11-JAN-2009", "01/11/2009"));
        System.err.println("D1(11-FEB-2009)>D2(11-01-2009) :"+compareDates("11-FEB-2009", "11-01-2009"));
        System.err.println("D1(01/11/2009)>D2(01/11/2009) :"+compareDates("01/11/2009", "01/11/2009"));
        System.err.println("Current Date: "+currentDate());
        System.err.println("Long Date(17 09 2009): "+getExtendedTodaysDate(currentDate()));
        System.err.println("getTimer 				:"+getTimer("."));
      }
}
