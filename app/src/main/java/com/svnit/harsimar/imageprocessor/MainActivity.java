package com.svnit.harsimar.imageprocessor;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.api.ClarifaiResponse;
import clarifai2.api.ClarifaiUtil;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.input.image.ClarifaiImage;
import clarifai2.dto.model.ConceptModel;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;
import okhttp3.OkHttpClient;

import static android.view.View.GONE;
import static android.view.View.TEXT_ALIGNMENT_VIEW_END;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton uploadFABBtn;
    final int PICK_IMAGE = 1;
    final static String clientId ="TWLwx0Svio0V5WKuRZ1HejSVyYZtYTu8MydOT3yI";
    final static String clientSecret ="1Lc3sttcMfHXzUcgZe3HVxkyWLHo77C3H6LmqxNs";

    private final predictions_adapter adapter=new predictions_adapter();
    private ImageView imageView;
    private RecyclerView recyclerView;
    @Override
    protected void onStart() {
        super.onStart();
        recyclerView=(RecyclerView)findViewById(R.id.main_recycler_view);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
        }

        TextView hintTv=(TextView)findViewById(R.id.hint_tv);
        hintTv.setText("Please click on Action Button to continue!");


        uploadFABBtn = (FloatingActionButton) findViewById(R.id.uploadFAB);

        uploadFABBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Intent.ACTION_GET_CONTENT).setType("image/*"), PICK_IMAGE);
                Log.d("harsimarSingh","outside");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case PICK_IMAGE:
                imageView=(ImageView)findViewById(R.id.main_image_view);
                Uri uri=data.getData();
                imageView.setImageURI(uri);
                final byte[] imageBytes = getImageByte(this,data);
                if (imageBytes != null) {
                    onImagePicked(imageBytes);
                }
                Log.d("harsimarSingh","inside");
                break;
        }
    }

    private void onImagePicked(final byte[] imageBytes) {

        final ProgressDialog mProgress=new ProgressDialog(MainActivity.this);
        mProgress.setMessage("Please wait");
        mProgress.show();
        imageView=(ImageView)findViewById(R.id.main_image_view);
        final TextView hint=(TextView)findViewById(R.id.hint_tv);
        //adapter.setData(Collections.<Concept>emptyList());

        uploadFABBtn.setEnabled(false);
        ClarifaiClient client = new ClarifaiBuilder(clientId, clientSecret)
                .client(new OkHttpClient()).buildSync();

        new AsyncTask<Void,Void,List<ClarifaiOutput<Concept>>>(){

            @Override
            protected List<ClarifaiOutput<Concept>> doInBackground(Void... params) {
                Log.d("harsimarSingh","inside Async");

                ClarifaiClient client = new ClarifaiBuilder(clientId, clientSecret)
                        .client(new OkHttpClient()).buildSync();
                List<ClarifaiOutput<Concept>>
                        predictionResults = client.getDefaultModels().generalModel() // You can also do Clarifai.getModelByID("id") to get custom models
                        .predict()
                        .withInputs(
                                // ClarifaiInput.forImage(ClarifaiImage.of("https://samples.clarifai.com/demo-011.jpg"))
                                ClarifaiInput.forImage(ClarifaiImage.of(imageBytes))
                        ).executeSync().get();
                mProgress.dismiss();

                return predictionResults;
            }

            @Override
            protected void onPostExecute(List<ClarifaiOutput<Concept>> clarifaiOutputs) {
                super.onPostExecute(clarifaiOutputs);
                hint.setText("Predicted Results");
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                Log.d("harsimarSingh", clarifaiOutputs.get(0).data().toString());
                adapter.setData(clarifaiOutputs.get(0).data());
                recyclerView.setAdapter(adapter);

            }
        }.execute();
    }

    private byte[] getImageByte(Context context, Intent data) {
        InputStream inStream = null;
        Bitmap bitmap = null;
        try {
            inStream = context.getContentResolver().openInputStream(data.getData());
            bitmap = BitmapFactory.decodeStream(inStream);
            final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            return outStream.toByteArray();
        } catch (FileNotFoundException e) {
            return null;
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException ignored) {
                }
            }
            if (bitmap != null) {
                bitmap.recycle();
            }
        }

    }
}
