package com.harinder.asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
     ListView mylist;
    ProgressBar progressBar;
    private  String[] texts={"harinder","pari","ankit","Sriram","Technos","Android", "ISGW","App developers","Java","php","Django","CSS","Jnode","Ruby"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_main);
        mylist= (ListView) findViewById(R.id.list);
        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        mylist.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new ArrayList<String>()));
        new myclass().execute();
    }

class myclass extends AsyncTask<Void,String,Void>
{

    ArrayAdapter<String> adapter;
    int count=0;
    @Override
    protected void onPreExecute() {
     adapter= (ArrayAdapter<String>) mylist.getAdapter();
        setProgressBarIndeterminate(false);
        setProgressBarVisibility(true);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        for(String item: texts){
        publishProgress(item);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
      adapter.add(values[0]);
        count++;
        progressBar.setProgress((int)(((double)count/texts.length)*100));
    }

    @Override
    protected void onPostExecute(Void aVoid) {
       setProgressBarVisibility(false);
        Toast.makeText(MainActivity.this, "All fields added successfully", Toast.LENGTH_LONG).show();
    }
}
}
