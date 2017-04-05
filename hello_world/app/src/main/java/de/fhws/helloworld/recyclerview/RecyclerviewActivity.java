package de.fhws.helloworld.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.Random;

import de.fhws.helloworld.R;

import static de.fhws.helloworld.FloatingActionActivity.lorem;

public class RecyclerviewActivity extends AppCompatActivity {

    private Adapter mAdapter;

    private LinearLayoutManager mLayoutManager;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList mockData = createMockData();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new Adapter(mockData);
        mRecyclerView.setAdapter(mAdapter);
    }

    private ArrayList createMockData() {
        String[] wordlist = lorem.split(" ");
        ArrayList arrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int random = new Random().nextInt(wordlist.length);
            arrayList.add(i, wordlist[random]);
        }
        return arrayList;
    }

}
