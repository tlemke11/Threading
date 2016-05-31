package com.example.tyler.threading;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createList(View v){
        //Create the list file
        //http://developer.android.com/training/basics/data-storage/files.html

        File file = new File(this.getFilesDir(), "numbers.txt");

        try {
            //https://examples.javacodegeeks.com/core-java/io/bufferedoutputstream/java-bufferedoutputstream-example/
            BufferedOutputStream bufOutput = new BufferedOutputStream( new FileOutputStream(this.getFilesDir() + "numbers.txt"));
            for(int i = 1; i < 11; i++){
                bufOutput.write(Integer.toString(i).getBytes());
                bufOutput.write("\n".getBytes());
                //bufOutput.write("Fine".getBytes());
                Thread.sleep(250);
            }
            bufOutput.close();

        }

        catch (Exception ex){
            System.out.println(ex);
        }
    }

    public void loadList(View v) {
        try {
            BufferedReader readFile = new BufferedReader(new FileReader(this.getFilesDir() + "numbers.txt"));
            List<String> list = new ArrayList<String>();
            for (int i = 1; i < 11; i++) {
                list.add(readFile.readLine());
                Thread.sleep(250);
            }

            //now use the arrady adapter
            //https://developer.android.com/reference/android/widget/ArrayAdapter.html
            //or much better
            //https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
            //Tricky problem with array adapter -http://stackoverflow.com/questions/12437196/you-must-supply-a-resource-id-for-a-textview
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
            ListView lv = (ListView) findViewById(R.id.listView);
            //System.out.println(list);
            if (adapter != null) {
                lv.setAdapter(adapter);
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void clearList(View v){
        try {
            adapter.clear();
        }

        catch (Exception ex){
            System.out.println(ex);
        }
    }

}
