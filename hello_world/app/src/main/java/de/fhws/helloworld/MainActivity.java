package de.fhws.helloworld;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int mCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.myTextView);
        final Button button = (Button) findViewById(R.id.myButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                mCount++;
                textView.setText(getString(R.string.text_counter, mCount));
            }
        });
    }
}
