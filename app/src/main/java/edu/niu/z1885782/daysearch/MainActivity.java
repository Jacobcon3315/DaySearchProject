/************************************************************************
 *                                                                      *
 *  CSCI 480/524               Assignment 4                 Spring 2022 *
 *                                                                      *
 *   Project Name: Day Search                                           *
 *                                                                      *
 *     Class Name: MainActivity.java                                    *
 *                                                                      *
 *   Developer(s): Jacob Powell Z1846418, Jacob Conacher Z1885782       *
 *                                                                      *
 *       Due Date: 5 May 2022                                           *
 *                                                                      *
 *        Purpose: User can specify which articles they want to see by  *
 *        limiting the amount of days since they have been published    *
 *                                                                      *
 *        Changes: Changes were made within the MainActivity.java,      *
 *        Item.java, SAXHandler.java, and activity_main.xml files.      *
 *                                                                      *
 *        activity_main.xml: Added a button and search bar              *
 *                                                                      *
 *        MainActivity.java: Added two functions, a onClickListener for *
 *        the button, and a few lines of code int the onCreate portion  *
 *                                                                      *
 *        Item.java: Added four functions, two for setting and getting  *
 *        the new pubDate (newDays and days) variable within the class, *
 *        and the other two functions for getting and parsing the given *
 *        info from the current article, as well as calculating the day *
 *        difference between the given date and the current date.       *
 *                                                                      *
 *        SAXHandler: Added two lines of code at the bottom of the file *
 *        that get the pubDate portion of the article.                  *
 *                                                                      *
 ************************************************************************/

package edu.niu.z1885782.daysearch;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private int listDays = 0;
    private final String URL
            = "https://www.autosport.com/rss/f1/news/"; //This RSS feed is only a month old
    private ListView listView;
    private ArrayList<Item> listItems;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_view);
        ParseTask task = new ParseTask(this);
        task.execute(URL);

        //Gets the info for the button and search bar
        EditText search = (EditText) findViewById(R.id.search);
        Button searchBut = (Button) findViewById(R.id.button3);

        //Sets an onClickListener for the button to get data and call a function
        searchBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Saves the number input by the user and catches non-numbers
                try {
                    listDays = Integer.parseInt(search.getText().toString());
                }
                catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                //Calls the displayList function to display the correct amount of items
                displayList(listItems);

            }
        });

    }

    //Sets the list of items to a global variable to be used further along the line in this file
    public void setList(ArrayList<Item> items)
    {
        listItems = items;
    }

    /****************************************************************
     *                                                              *
     * displayList iterates through the entire list of articles and *
     * checks if the article was published within the amount of days*
     * the user has specified. Then it adds it to the list of items *
     * to be displayed, and displays the list.                      *
     *                                                              *
     ***************************************************************/
    public void displayList(ArrayList<Item> items)
    {
        if (items != null)
        {
            // Build ArrayList of titles to display
            ArrayList<String> titles = new ArrayList<String>();
            for (Item item : items) {
                if (item.calcDays(item.getDays()) <= listDays)
                    titles.add(item.getTitle());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, titles);
            listView.setAdapter(adapter);
            ListItemHandler lih = new ListItemHandler();
            listView.setOnItemClickListener(lih);
        }
        else
            Toast.makeText(this, "Sorry - No data found",
                    Toast.LENGTH_LONG).show();
    }

    private class ListItemHandler implements AdapterView.OnItemClickListener
    {
        public void onItemClick(AdapterView<?> parent, View view,
                                int position, long id)
        {
            Item selectedItem = listItems.get(position);
            Uri uri = Uri.parse(selectedItem.getLink());
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(browserIntent);
        }
    }
}