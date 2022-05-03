package edu.niu.z1885782.daysearch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Item
{
    private String title;
    private String link;
    private String days;

    public Item(String newTitle, String newLink, String newDays)
    {
        setTitle(newTitle);
        setLink(newLink);
        setDays(newDays);
    }

    public void setTitle(String newTitle)
    {
        title = newTitle;
    }

    public void setLink(String newLink)
    {
        link = newLink;
    }

    /****************************************************************
     *                                                              *
     * setDays sets the String that contains the full line of input *
     * from the pubDate portion of the article.                     *
     *                                                              *
     ***************************************************************/
    public void setDays(String newDays)
    {
        days = newDays;
    }

    public String getTitle()
    {
        return title;
    }

    public String getLink()
    {
        return link;
    }

    /****************************************************************
     *                                                              *
     * getDays returns the String from the pubDate portion of the   *
     * article.                                                     *
     *                                                              *
     ***************************************************************/
    public String getDays()
    {
        return days;
    }

    /****************************************************************
     *                                                              *
     * getDateDiff calculates the difference, in days, between two  *
     * dates that are passed into the function.                     *
     *                                                              *
     ***************************************************************/
    public static long getDateDiff(SimpleDateFormat format, String oldDate, String newDate) {
        try {
            return TimeUnit.DAYS.convert(format.parse(newDate).getTime() - format.parse(oldDate).getTime(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /****************************************************************
     *                                                              *
     * calcDays takes the entire line of pubDate from the article   *
     * input, parses the spaces out, grabs the needed data, converts*
     * the month into a number, then formats that date and the      *
     * current date to be passed into a separate function that      *
     * gets the exact number of days between those dates.           *
     *                                                              *
     ***************************************************************/
    public int calcDays(String date){

        //Declares new java string to be used to grab specific parts of data
        String[] dateStrings;
        dateStrings = date.split(" ");

        //Grab and convert month abbrev. to number
        String month = dateStrings[2].toLowerCase();

        //Converts the month abbreviations into the number equivalent
        switch(month) {
            case "jan":
                month = "1";
                break;

            case "feb":
                month = "2";
                break;

            case "mar":
                month = "3";
                break;

            case "apr":
                month = "4";
                break;

            case "may":
                month = "5";
                break;

            case "jun":
                month = "6";
                break;

            case "jul":
                month = "7";
                break;

            case "aug":
                month = "8";
                break;

            case "sep":
            case "sept":
                month = "9";
                break;

            case "oct":
                month = "10";
                break;

            case "nov":
                month = "11";
                break;

            case "dec":
                month = "12";
                break;
        }

        //Parse the date parts needed
        String postDate = dateStrings[1]+"/"+month+"/"+dateStrings[3];

        //Gets and formats current date
        SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
        String currentDate = sdf.format(new Date());

        //Calculates difference between the two formatted dates
        return (int) getDateDiff(new SimpleDateFormat("d/M/yyyy"), postDate, currentDate);

    }

    public String toString()
    {
        return title + "; " + link;
    }
}