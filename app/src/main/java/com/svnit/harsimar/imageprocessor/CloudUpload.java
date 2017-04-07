package com.svnit.harsimar.imageprocessor;

import android.graphics.Bitmap;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class CloudUpload extends AppCompatActivity {

    Bitmap image=null;
    private double latitude;
    private double longitude;
    private String label;

    private ImageView imageView;
    private EditText labelText;
    private EditText gpsText;
    private Button uploadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_upload);
        Bundle activitySentData= getIntent().getExtras();

        image=(Bitmap) activitySentData.getParcelable(predictions_adapter.IMAGE_KEY);
        latitude=activitySentData.getDouble(predictions_adapter.LAT_KEY);
        longitude=activitySentData.getDouble(predictions_adapter.LON_KEY);
        label=activitySentData.getString(predictions_adapter.LABEL_KEY);

        imageView=(ImageView)findViewById(R.id.imageView);
        labelText=(EditText)findViewById(R.id.label_et);
        gpsText=(EditText)findViewById(R.id.gps_et);
        uploadBtn=(Button)findViewById(R.id.fireabaseUpload_button);

        labelText.setText(label);
        



    }
}
