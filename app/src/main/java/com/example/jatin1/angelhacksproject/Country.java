package com.example.jatin1.angelhacksproject;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * Created by advaithvenkatakrishnan on 5/30/15.
 */
public class Country {
    private String name;
    public Country() {
    }
    public ArrayList getCountries() {
        String fileName = "countries.txt";
        BufferedReader br = null;
        ArrayList<String> countries = new ArrayList<String>();
        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader("countries.txt"));

            while ((sCurrentLine = br.readLine()) != null) {
                countries.add(sCurrentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return countries;
    }
}
