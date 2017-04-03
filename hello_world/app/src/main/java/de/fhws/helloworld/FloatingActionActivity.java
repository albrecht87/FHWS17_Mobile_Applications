package de.fhws.helloworld;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

import de.fhws.helloworld.adapter.DataAdapter;

public class FloatingActionActivity extends AppCompatActivity {

    public static final String lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show();
            }
        });

        // Generate some mock data
        String[] wordlist= lorem.split(" ");
        ArrayList arrayList = new ArrayList<>();
        for (int i = 0 ; i< 5000; i++ ){
            int random = new Random().nextInt(wordlist.length);
            arrayList.add(i, wordlist[random]);
        }

        //Create an Adapter
        DataAdapter arrayAdapter = new DataAdapter(this, arrayList);

        //Reference Listview and set Adapter
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(arrayAdapter);


    }

}
