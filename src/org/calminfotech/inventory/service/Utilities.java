/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.calminfotech.inventory.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.calminfotech.inventory.utils.Text;
import org.springframework.stereotype.Service;


/**
 *
 * @author megaweb
 */
@Service
public class Utilities {

    protected final Log logger = LogFactory.getLog(getClass());

   
    public boolean isInteger(String qty1) {
        //throw new UnsupportedOperationException("Not yet implemented");
        try {
            Integer.parseInt(qty1);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }

    public String getCode() {
        // throw new UnsupportedOperationException("Not yet implemented");

        StringBuffer appno = null;

        int cntrl = 0;
        int min = 0;
        int cnt = 0;
        int max = 0, indx = 0, i = 0;

        char[] num = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        char[] letter = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        char[] sletter = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        char[] symbols = {'$', '@'};

        char[] current_array = null;

        /*start to loop here*/
        boolean generate = true;
        //int no_of_password = 1;
        appno = new StringBuffer();
        // appno.append("APP");
        appno.append("");

        while (generate) {

            for (i = 0; i <= 6; i++) {


                switch (i) {

                    case 0:
                        max = 25;
                        current_array = letter;
                        break;
                    case 1:
                        max = 25;
                        current_array = letter;
                        break;
                    case 2:
                        max = 1;
                        current_array = symbols;
                        break;
                    case 3:
                        max = 9;
                        current_array = num;
                        break;
                    case 4:
                        max = 9;
                        current_array = num;
                        break;
                    case 5:
                        max = 25;
                        current_array = sletter;
                        break;
                }
                indx = min + (int) (Math.random() * max);
                appno.append(String.valueOf(current_array[indx]));
            }


            for (i = 0; i <= 2; i++) {

                switch (i) {
                    case 0:
                        max = 3;
                        current_array = letter;
                        break;

                    case 1:
                        max = 25;
                        current_array = sletter;
                        break;

                    case 2:
                        max = 3;
                        current_array = sletter;
                        break;

                }
                indx = min + (int) (Math.random() * max);
                appno.append(String.valueOf(current_array[indx]));
            }
            for (i = 0; i <= 1; i++) {

                switch (i) {
                    case 0:
                        max = 9;
                        current_array = num;
                        break;
                    case 1:
                        max = 9;
                        current_array = num;
                        break;
                    case 2:
                        max = 9;
                        current_array = num;
                        break;
                }
                indx = min + (int) (Math.random() * max);
                appno.append(String.valueOf(current_array[indx]));
            }
            for (i = 0; i <= 2; i++) {

                switch (i) {
                    case 0:
                        max = 9;
                        current_array = num;
                        break;
                    case 1:
                        max = 25;
                        current_array = letter;
                        break;
                }
                indx = min + (int) (Math.random() * max);
                appno.append(String.valueOf(current_array[indx]));
            }
            cntrl++;
            // if (cntrl > 50) {
            generate = false;
            // }
        }
        return appno.toString();

    }

 
    public String getUriPath(String uri) {
        //throw new UnsupportedOperationException("Not yet implemented");
        if (uri != null && uri.contains("?")) {
            uri = uri.substring(0, uri.indexOf('?'));
        }
        return uri;
    }

    public String implode(Collection col, char delimiter) {
        //throw new UnsupportedOperationException("Not yet implemented");
        //  int size = list.size();
        //  int i = 0;
        //  String currItem=null;
        Iterator<String> it = col.iterator();
        StringBuilder build = new StringBuilder();
        while (it.hasNext()) {
            build.append("'");
            build.append(it.next());
            build.append("'");
            build.append(delimiter);
            //if (it.hasNext()) {
            //    build.append(delimiter);
            //}
        }
        int len = build.length();
        if (build.charAt(len - 1) == delimiter) {
            build.deleteCharAt(len - 1);
        }
        return build.toString();
    }

    public String implode(char delimiter, Collection col) {
        //throw new UnsupportedOperationException("Not yet implemented");
        //  int size = list.size();
        //  int i = 0;
        //  String currItem=null;
        Iterator<String> it = col.iterator();
        StringBuilder build = new StringBuilder();
        while (it.hasNext()) {
            build.append(it.next());
            build.append(delimiter);
            //if (it.hasNext()) {
            //    build.append(delimiter);
            //}
        }
        int len = build.length();
        if (build.charAt(len - 1) == delimiter) {
            build.deleteCharAt(len - 1);
        }
        return build.toString();
    }

    public String getMonthTitle(String mnthCode) {

        if (mnthCode != null) {
            if (mnthCode.equals("01")) {
                return "January";
            } else if (mnthCode.equals("02")) {
                return "February";
            } else if (mnthCode.equals("03")) {
                return "March";
            } else if (mnthCode.equals("04")) {
                return "April";
            } else if (mnthCode.equals("05")) {
                return "May";
            } else if (mnthCode.equals("06")) {
                return "June";
            } else if (mnthCode.equals("07")) {
                return "July";
            } else if (mnthCode.equals("08")) {
                return "August";
            } else if (mnthCode.equals("09")) {
                return "September";
            } else if (mnthCode.equals("10")) {
                return "October";
            } else if (mnthCode.equals("11")) {
                return "November";
            } else if (mnthCode.equals("12")) {
                return "December";
            } else {
                return "";
            }
        }
        return "";

    }

    public Map getMonths() {

        Map<String, String> months = new LinkedHashMap();
        months.put("01", "January");
        months.put("02", "February");
        months.put("03", "March");
        months.put("04", "April");
        months.put("05", "May");
        months.put("06", "June");
        months.put("07", "July");
        months.put("08", "August");
        months.put("09", "September");
        months.put("10", "October");
        months.put("11", "November");
        months.put("12", "December");
        return months;
    }

    public String getDayRange(int wk, String month, String year) {
        String dateFrom = null;
        String dateTo = null;

        switch (wk) {
            case 1:
                dateFrom = "01";
                dateTo = "07";
                break;
            case 2:
                dateFrom = "08";
                dateTo = "14";
                break;
            case 3:
                dateFrom = "15";
                dateTo = "21";
                break;
            case 4:
                dateFrom = "22";
                dateTo = this.getMonthNumDays(month, year);

                break;
            default:
                dateFrom = "01";
                dateTo = this.getMonthNumDays(month, year);

                break;
        }
        return dateFrom + "/" + dateTo;
    }

    public String getMonthNumDays(String month, String year) {
        String numDays = null;
        try {
            if (month.equals("09") || month.equals("04") || month.equals("06") || month.equals("11")) {
                numDays = "30";
            } else if (month.equals("02")) {
                if ((Integer.parseInt(year)) % 4 == 0) {
                    numDays = "29";
                } else {
                    numDays = "28";
                }
            } else {
                numDays = "31";
            }
        } catch (NumberFormatException e) {
        }
        return numDays;
    }

    public Map getDaysMap(int dayFrom, int dateTo, String month, String year) {
        //throw new UnsupportedOperationException("Not yet implemented");

        Map daysMap = new LinkedHashMap();
        String prt = year + "-" + month + "-";
        String date = null;
        for (int i = dayFrom; i <= dateTo; i++) {
            if (String.valueOf(i).length() > 1) {
                date = prt + i;
            } else {
                date = prt + "0" + i;
            }
            daysMap.put(date, date);
        }
        return daysMap;
    }

    public Map getDaysMap2(int dateFrom, int dateTo, String month, String year) {
        //throw new UnsupportedOperationException("Not yet implemented");


        Map daysMap = new LinkedHashMap();
        String prt = "/" + month + "/" + year;
        String date = null;
        for (int i = dateFrom; i <= dateTo; i++) {
            if (String.valueOf(i).length() > 1) {
                date = i + prt;
            } else {
                date = "0" + i + prt;
            }
            daysMap.put(date, date);
        }
        return daysMap;
    }

    public Map getQuarterMap() {

        Map quarterMap = new LinkedHashMap();
        quarterMap.put("1", "Quarter 1");
        quarterMap.put("2", "Quarter 2");
        quarterMap.put("3", "Quarter 3");
        quarterMap.put("4", "Quarter 4");
        return quarterMap;
    }

    public int getWeek(int dateIntVal) {
        int week = 0;
        if (dateIntVal >= 1 && dateIntVal < 8) {
            week = 1;
        } else if (dateIntVal >= 8 && dateIntVal < 15) {
            week = 2;
        } else if (dateIntVal >= 15 && dateIntVal < 22) {
            week = 3;
        } else {
            week = 4;
        }
        return week;
    }

    public Map getWeekMap(String week) {

        Map map = null;
        if (week != null) {
            map = new LinkedHashMap();
            if (week.equals(Text.ALL)) {
                map.put("1", "Week 1");
                map.put("2", "Week 2");
                map.put("3", "Week 3");
                map.put("4", "Week 4");
            } else {
                map.put(week, "Week " + week);
            }
        }
        return map;
    }

    public Map fillMap(Map map, Collection<Object> cols, int sortAction) {
        //throw new UnsupportedOperationException("Not yet implemented");

        // Collection<String> daysList = this.getDaysList();
        Map newMap = null;
        if (sortAction == 1 || sortAction == 2) {
            newMap = new LinkedHashMap();
        }
        String currdata = null;
        if (cols != null && map != null) {
            for (Object obj : cols) {
                currdata = (String) obj;
                if (!map.containsKey(currdata)) {
                    if (sortAction == 1) {
                        newMap.put(currdata, null);
                    } else if (sortAction == 2) {//we put the zero month element first
                        newMap.put(currdata, null);
                    } else {
                        map.put(currdata, null);
                    }
                } else {
                    if (sortAction == 1) {
                        newMap.put(currdata, map.remove(currdata));
                    }
                }
            }
        }

        if (sortAction == 1) {
            return newMap;
        } else if (sortAction == 2) {//we append the resulset map to end of new map
            if (map != null) {
                newMap.putAll(map);
            }
            return newMap;
        } else {
            return map;
        }

    }

    public Map<String,String> getMonthsQuarterMap(String quarter) {

        Map<String,String> months = getMonths();
        if (quarter == null || (quarter != null && quarter.equals("***"))) {
            return months;
        }
        Map<String,String> quarterMap = new LinkedHashMap();
        int quarterIntVal;
        try {
            quarterIntVal = Integer.parseInt(quarter);
        } catch (NumberFormatException e) {
            quarterIntVal = 1;
        }
        int rangeBegin = (quarterIntVal - 1) * 3;
        int rangeEnd = rangeBegin + 2;

        Collection<String> keys = months.keySet();
        int indx = 0;
        for (String key : keys) {
            if (indx >= rangeBegin && indx <= rangeEnd) {
                quarterMap.put(key, months.get(key));
            }
            indx++;
        }
        return quarterMap;
    }

  
    public List reverse(List list) {
        Object[] arry = null;

        if (list != null) {
            arry = list.toArray(new Object[0]);
            int size = arry.length;
            // list.clear();
            for (int i = size - 1, x = 0; i >= 0; i--) {
                list.set(x++, arry[i]);
            }
        }
        return list;
    }
    
    
     public List getDays(String week,String quarter,String year) {
        // throw new UnsupportedOperationException("Not yet implemented");
        Map map = getMonthsQuarterMap(quarter);
        //for each of the months returned we get days range for week query,using day range
        String dateFrom = null;
        String dateTo = null;
        String dayRange = null;
        String[] details = null;
        List days = new ArrayList();
        try {
            if (map != null) {
                Set<String> mnths = map.keySet();
                for (String mnth : mnths) {
                    dayRange =getDayRange(Integer.parseInt(week), mnth, year);
                    if (dayRange != null && dayRange.contains("/")) {
                        details = dayRange.split("/");
                        if (details != null && details.length > 1) {
                            dateFrom = details[0];
                            dateTo = details[1];
                            days.addAll(getDaysMap(Integer.parseInt(dateFrom), Integer.parseInt(dateTo), mnth, year).values());
                        }
                    }
                }
            }
        } catch (NumberFormatException e) {
        }

        return days;
    }

    public boolean isAlertSystemDate(Map<String, Object> config) {
        //throw new UnsupportedOperationException("Not yet implemented");
        if (config != null && config.containsKey("ALERT_SYSTEM_CUR_DATE")) {
            String alertSysDate = (String) config.get("ALERT_SYSTEM_CUR_DATE");
            if (alertSysDate.equals("1")) {
                return true;
            }
        }
        return false;
    }
}
