package de.fhws.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mInputView;

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.explicit:
                final Intent intent = new Intent(this, SecondActivity.class);
                intent.putExtra("userinput", mInputView.getText().toString());
                startActivity(intent);
                break;
            case R.id.implicit:
                final Intent implicit = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(implicit);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInputView = (EditText) findViewById(R.id.editText);

        final Button explicitButton = (Button) findViewById(R.id.explicit);
        final Button implicitButton = (Button) findViewById(R.id.implicit);
        explicitButton.setOnClickListener(this);
        implicitButton.setOnClickListener(this);
    }
}
