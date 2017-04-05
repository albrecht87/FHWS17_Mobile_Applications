package de.fhws.helloworld.recyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Random;

import de.fhws.helloworld.R;

import static de.fhws.helloworld.FloatingActionActivity.lorem;

public class RecyclerviewActivity extends AppCompatActivity {

    private static final String TAG = RecyclerviewActivity.class.getSimpleName();

    private Adapter mAdapter;

    private android.os.Handler mHandler;

    private LinearLayoutManager mLayoutManager;

    private ProgressBar mProgressBar;

    private RecyclerView mRecyclerView;

    public void showProgressBar(final boolean show) {
        runOnUiThread(new Runnable() {
            public void run() {
                mProgressBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ArrayList mockData = createMockData();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        mHandler = new Handler();

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new Adapter(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(final int position) {
                Log.d(TAG, "onItemClick: " + position);
            }
        }, mockData, new Adapter.OnBottomReachListener() {
            @Override
            public void onBottom(final int postition) {
                loadMoreData(mockData, postition - 1, 20);
            }
        });
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

    private void insertNewData(final int startIndex, final int numberOfNews, final ArrayList list) {
        for (int i = startIndex; i < (startIndex + numberOfNews); i++) {
            list.add("News with Title " + i);
        }
    }

    private void loadMoreData(final ArrayList list, final int startIndex, final int numberOfNews) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                showProgressBar(true);
                waitSomeTime(1000);
                insertNewData(startIndex, numberOfNews, list);
                notifyAdapter();
                showProgressBar(false);

            }
        }).start();
    }

    private void notifyAdapter() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mAdapter.notifyDataSetChanged();
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void waitSomeTime(final int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
