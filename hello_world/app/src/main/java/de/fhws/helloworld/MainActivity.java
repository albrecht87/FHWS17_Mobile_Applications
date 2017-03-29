package de.fhws.helloworld;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int EXPLICIT_INTENT = 1;

    private static final int IMAGE_INTENT = 2;

    private ImageView mImageView;

    private EditText mInputView;

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.explicit:
                final Intent intent = new Intent(this, SecondActivity.class);
                intent.putExtra("userinput", mInputView.getText().toString());
                startActivityForResult(intent, EXPLICIT_INTENT);
                break;
            case R.id.implicit:
                final Intent implicit = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(implicit, IMAGE_INTENT);
                break;
        }
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode,
            final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case EXPLICIT_INTENT:
                if (resultCode == RESULT_OK) {
                    mInputView.setText(
                            data.getExtras()
                                    .getString(SecondActivity.RESULT_DATA, "no return value"));
                }
                break;
            case IMAGE_INTENT:
                if (resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    mImageView.setImageBitmap(imageBitmap);
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInputView = (EditText) findViewById(R.id.editText);
        mImageView = (ImageView) findViewById(R.id.imageView);

        final Button explicitButton = (Button) findViewById(R.id.explicit);
        final Button implicitButton = (Button) findViewById(R.id.implicit);
        explicitButton.setOnClickListener(this);
        implicitButton.setOnClickListener(this);
    }
}
