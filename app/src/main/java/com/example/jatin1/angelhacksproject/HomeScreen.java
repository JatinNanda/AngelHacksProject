package com.example.jatin1.angelhacksproject;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class HomeScreen extends ActionBarActivity {
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Country country = new Country();

        Spinner dropdown = (Spinner)findViewById(R.id.spinner1);
        ArrayList<String> countries = getCountries();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countries);
        dropdown.setAdapter(adapter);

    }

    public ArrayList<String> getCountries() {
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(new File("/Users/advaithvenkatakrishnan/AndroidStudioProjects/AngelHacksProject/app/src/main/assets/database/countries.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("FAILURE");
            e.printStackTrace();
        }
        String fileline;
        ArrayList<String >countries = new ArrayList<String>();
        while(fileScanner.hasNext()) {
            fileline = fileScanner.nextLine();
            countries.add(fileline);
        }
        return countries;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
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