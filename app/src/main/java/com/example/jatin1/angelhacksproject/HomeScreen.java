package com.example.jatin1.angelhacksproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;



public class HomeScreen extends ActionBarActivity {
    String ctry;
    String cty;
    Button destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Spinner dropdown = (Spinner) findViewById(R.id.spinner1);
        final ArrayList<String> countries = readFileFromAssets("files/countries.txt");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countries);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ctry = countries.get(position);
                final ArrayList<String> cities = findCities(ctry);
                Spinner dropdown2 = (Spinner) findViewById(R.id.spinner2);
                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(HomeScreen.this, android.R.layout.simple_spinner_item, cities);
                dropdown2.setAdapter(adapter2);
                dropdown2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        cty = cities.get(position);
                        destination = (Button) findViewById(R.id.destination);
                        destination.setText("Your Destination:" + "\n " + ctry + ", " + cty);
                        destination.setOnClickListener((new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(getApplicationContext(), Day.class));
                            }
                        }));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        // your code here
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public ArrayList<String> findCities(String country) {
        ArrayList<String> cities = readFileFromAssets("files/cities.txt");
        ArrayList<String> countries = readFileFromAssets("files/countries.txt");
        ArrayList<String> selectedCities = new ArrayList<String>();
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).equalsIgnoreCase(country)) {
                for (int j = i; j < cities.size(); j++) {
                    if (!(countries.contains(cities.get(j)))) {
                        selectedCities.add(cities.get(j));
                    }
                }
            }
        }
        return selectedCities;
    }
    private ArrayList<String> readFileFromAssets(String fileName) {
        ArrayList<String> countries = new ArrayList<String>();
        StringBuilder returnString = new StringBuilder();
        InputStream fIn = null;
        InputStreamReader isr = null;
        BufferedReader input = null;
        try {
            fIn = getResources().getAssets()
                    .open(fileName, Context.MODE_WORLD_READABLE);
            isr = new InputStreamReader(fIn);
            input = new BufferedReader(isr);
            String line = "";
            while ((line = input.readLine()) != null) {
                countries.add(line);
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                if (isr != null)
                    isr.close();
                if (fIn != null)
                    fIn.close();
                if (input != null)
                    input.close();
            } catch (Exception e2) {
                e2.getMessage();
            }
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