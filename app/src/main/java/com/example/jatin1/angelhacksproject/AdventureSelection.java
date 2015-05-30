package com.example.jatin1.angelhacksproject;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class AdventureSelection extends ActionBarActivity {
    public static java.util.ArrayList itineraryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure_selection);
        final ImageView[] choices = new ImageView[3];
        choices[1] = (ImageView) findViewById(R.id.image1);
        choices[2] = (ImageView) findViewById(R.id.image2);
        choices[3] = (ImageView) findViewById(R.id.image3);
        choices[1].setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //insert code to influence next choice
                itineraryList.add(choices[1]);
                startActivity(new Intent(getApplicationContext(), NextTransition.class));
            }
        }));
        choices[2].setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //insert code to influence next choice
                itineraryList.add(choices[2]);
                startActivity(new Intent(getApplicationContext(), NextTransition.class));
            }
        }));
        choices[3].setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //insert code to influence next choice
                itineraryList.add(choices[3]);
                startActivity(new Intent(getApplicationContext(), NextTransition.class));
            }
        }));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_adventure_selection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
