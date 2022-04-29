package edu.niu.z1885782.daysearch;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class Item
{
    private String title;
    private String link;
    private int days;

    public Item(String newTitle, String newLink, int newDays)
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

    public void setDays(int newDays){days = newDays;}

    public String getTitle()
    {
        return title;
    }

    public String getLink()
    {
        return link;
    }

    public int getDays(){return days;}

    /**
     * Get a diff between two dates
     *
     * @param oldDate the old date
     * @param newDate the new date
     * @return the diff value, in the days
     */
    public static long getDateDiff(SimpleDateFormat format, String oldDate, String newDate) {
        try {
            return TimeUnit.DAYS.convert(format.parse(newDate).getTime() - format.parse(oldDate).getTime(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int calcDays(String date){

        return (int) getDateDiff(new SimpleDateFormat("dd/MM/yyyy"), "29/05/2017", "31/05/2017");

    }

    public String toString()
    {
        return title + "; " + link;
    }
}