package edu.niu.z1885782.daysearch;

public class Item
{
    private String title;
    private String link;
    private int days;

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

    public void setDays(String newDays){

    }

    public String getTitle()
    {
        return title;
    }

    public String getLink()
    {
        return link;
    }

    public int getDays(){return days;}

    public String toString()
    {
        return title + "; " + link;
    }
}