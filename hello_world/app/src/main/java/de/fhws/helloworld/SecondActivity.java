package de.fhws.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class SecondActivity extends AppCompatActivity {

    public static final String RESULT_DATA = "result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        final String text = intent.getExtras().getString("userinput", "empty");
        getSupportActionBar().setTitle(text);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Intent result = new Intent();
        result.putExtra(RESULT_DATA, "GoodBye");
        setResult(RESULT_OK, result);
    }
}
