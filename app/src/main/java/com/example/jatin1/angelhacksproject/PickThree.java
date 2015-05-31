package com.example.jatin1.angelhacksproject;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Context;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;



public class PickThree extends ActionBarActivity {

    int curLat = 0;
    int curLong = 0;

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

    private ArrayList<String> random(String fileName) {
        ArrayList<String> parsed = readFileFromAssets(fileName);
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> address = new ArrayList<>();
        ArrayList<String> toReturn = new ArrayList<>();
        for(String s : parsed) {
            String[] details = s.split(",");
            names.add(details[0]);
            address.add(details[1]);
        }
        Random randomGenerator = new Random();
        for (int i = 1; i <= 4; ++i) {
            int randomInt = randomGenerator.nextInt(parsed.size());
            toReturn.add(names.get(i) + "\n" + address.get(i));
        }
        return toReturn;
    }

    private ArrayList<String> pick(String fileName) {
        ArrayList<String> parsed = readFileFromAssets(fileName);
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> address = new ArrayList<>();
        ArrayList<Double> lat = new ArrayList<>();
        ArrayList<Double> longi = new ArrayList<>();
        ArrayList<Double> latNew = new ArrayList<>();
        ArrayList<Double> longiNew = new ArrayList<>();
        ArrayList<Double> distance = new ArrayList<>();
        ArrayList<String> toReturn = new ArrayList<>();
        for(String s : parsed) {
            String[] details = s.split(",");
            names.add(details[0]);
            address.add(details[1]);
            lat.add(Double.parseDouble(details[2]));
            longi.add(Double.parseDouble(details[3]));
        }
        for(int i = 0; i < lat.size(); i++) {
            latNew.set(i, lat.get(i) - curLat);
            latNew.set(i, latNew.get(i) * latNew.get(i));
        }
        for(int i = 0; i < longi.size(); i++) {
            longiNew.set(i, longi.get(i) - curLong);
            longiNew.set(i, longiNew.get(i) * longiNew.get(i));
        }
        for(int i = 0; i < longi.size(); i++) {
            distance.set(i, longiNew.get(i) + latNew.get(i));
        }

        int n = distance.size();
        double temp = 0;
        String temp1;
        for(int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (distance.get(j-1) > distance.get(j)) {
                    temp = distance.get(j-1);
                    distance.set(j - 1, distance.get(j));
                    distance.set(j, temp);
                    temp1 = names.get(j-1);
                    names.set(j - 1, names.get(j));
                    names.set(j, temp1);
                    temp1 = address.get(j-1);
                    address.set(j - 1, address.get(j));
                    address.set(j, temp1);
                }
            }
        }
        for (int i = 0; i < 3; ++i) {
            toReturn.add(names.get(i) + "\n" + address.get(i));
        }
        return toReturn;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_three);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pick_three, menu);
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
