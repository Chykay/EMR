/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.calminfotech.inventory.domains;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lala
 */
public class CustomDate {

    private String date;

    public CustomDate(CustomDate date) {
        this.date = date.toString();
    }

    public CustomDate() {
    }

    /* public String getDate(String format) {
     //throw new UnsupportedOperationException("Not yet implemented");

      
     java.util.Date dte = new java.util.Date();
     String details[] = dte.toString().split(" ");
       
     date = details[5] + "-" + getMonth(details[1]) + "-" + details[2];
 
     if(days_ago==0){            
     return formatDate(format);
     }
     return formatDate(format);
     }*/
    public String formatDateForInsert(String data, String format) {


        if (data != null) {
            try {
                String[] dte = data.split("/");
                return dte[2] + "-" + dte[1] + "-" + dte[0];
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }

        return "";

    }

    public String getDateForInsert(int days_ago) {
        //throw new UnsupportedOperationException("Not yet implemented");

        java.util.Date dte = new java.util.Date();
        String details[] = dte.toString().split(" ");

        return details[5] + "-" + getMonth(details[1]) + "-" + details[2] + " " + details[3];
    }

    private String getMonth(String mnth) {

        List<String> months = new ArrayList<String>();
        months.add("");
        months.add("Jan");
        months.add("Feb");
        months.add("Mar");
        months.add("Apr");
        months.add("May");
        months.add("Jun");
        months.add("Jul");
        months.add("Aug");
        months.add("Sep");
        months.add("Oct");
        months.add("Nov");
        months.add("Dec");

        if (String.valueOf(months.indexOf(mnth)).length() == 1) {
            return "0" + String.valueOf(months.indexOf(mnth));
        } else {
            return String.valueOf(months.indexOf(mnth));
        }

    }

    public String formatDate(String date, String format) {

        String[] details = date.split(" ");
        String[] dte = details[0].split("-");

        String mm = dte[1];
        String yyyy = dte[0];
        String dd = dte[2];

        if (format.equals("dd/mm/yyyy")) {
            return dd + "/" + mm + "/" + yyyy;
        } else if (format.equals("yyyy-mm-dd")) {
            return yyyy + "-" + mm + "-" + dd;
        }

        return date;
    }

     public boolean validateDate(String date, String format) {

        String[] details = null;
        int mth = 0;
        String yr = null;
        int dy = 0;
        String delim = null;

        if (date == null) {
            return false;
        }
        String pattern = null;
        SimpleDateFormat formatter = null;
        switch (getFormat(format)) {
            case 1:
                pattern = "dd/MM/yyyy";
                formatter = new SimpleDateFormat(pattern);

                details = date.split("/");
                try {
                    formatter.parse(date);

                    mth = Integer.parseInt(details[1]);
                    yr = details[2];
                    Integer.parseInt(yr);
                    dy = Integer.parseInt(details[0]);
                } catch (NumberFormatException nfe) {
                    return false;
                } catch (ParseException e) {
                    return false;
                }
                break;


            case 2:
                pattern = "yyyy-mm-dd";
                formatter = new SimpleDateFormat(pattern);

                details = date.split("-");
                try {
                    formatter.parse(date);
                    mth = Integer.parseInt(details[1]);
                    yr = details[0];
                    Integer.parseInt(yr);
                    dy = Integer.parseInt(details[2]);
                } catch (NumberFormatException nfe) {
                    return false;
                } catch (ParseException e) {
                    return false;
                }
                break;
        }
        if (yr.length() != 4) {
            return false;
        }
        if (mth < 1 || mth > 12) {
            return false;
        }

        if (!isValidDayValue(yr, mth, dy)) {
            return false;
        }
        return true;
    }

    private int getFormat(String format) {

        if (format.equals("dd/mm/yyyy")) {
            return 1;
        } else if (format.equals("yyyy-mm-dd")) {
            return 2;
        }
        return 0;


    }

    private boolean isValidDayValue(String yr, int mth, int dy) {
        // throw new UnsupportedOperationException("Not yet implemented");

        int minDayValue = 1;
        int maxDayValue = 0;

        switch (mth) {

            case 2: //case february
                maxDayValue = 28;
                int rem = Integer.parseInt(yr) % 4;
                if (rem == 0) {
                    maxDayValue = 29;
                }
                break;

            case 4:
            case 6:
            case 9:
            case 11://case april,september,november,june

                maxDayValue = 30;

                break;

            default:
                maxDayValue = 31;
                break;

        }

        if (dy >= minDayValue && dy <= maxDayValue) {
            return true;
        }

        return false;


    }
}
