package com.svnit.harsimar.imageprocessor;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewDebug;

import com.tbruyelle.rxpermissions.RxPermissions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.api.ClarifaiResponse;
import clarifai2.api.request.ClarifaiRequest;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.input.image.ClarifaiImage;
import clarifai2.dto.model.ConceptModel;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;
import clarifai2.exception.ClarifaiException;
import clarifai2.internal.JSONArrayBuilder;
import clarifai2.internal.JSONObjectBuilder;
import okhttp3.OkHttpClient;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        if(permissionCheck!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
        }



        /*
        ClarifaiClient client = new ClarifaiBuilder("TWLwx0Svio0V5WKuRZ1HejSVyYZtYTu8MydOT3yI", "1Lc3sttcMfHXzUcgZe3HVxkyWLHo77C3H6LmqxNs")
                .client(new OkHttpClient()).buildSync();

        Thread predictionsThread =new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    ClarifaiClient client = new ClarifaiBuilder("TWLwx0Svio0V5WKuRZ1HejSVyYZtYTu8MydOT3yI", "1Lc3sttcMfHXzUcgZe3HVxkyWLHo77C3H6LmqxNs")
                            .client(new OkHttpClient()).buildSync();
                    /*
                    final ConceptModel generalModel = client.getDefaultModels().generalModel();

                    final ClarifaiResponse<List<ClarifaiOutput<Concept>>> response=

                            generalModel.predict().withInputs(
                                    ClarifaiInput.forImage(ClarifaiImage.of("https://samples.clarifai.com/demo-011.jpg"))
                            ).executeSync();

                    final List<ClarifaiOutput<Concept>> predictions = response.get();
                    Log.d("harsimarSingh", predictions.toString());

                        */
/*
                    final List<ClarifaiOutput<Concept>>
                            predictionResults = client.getDefaultModels().generalModel() // You can also do Clarifai.getModelByID("id") to get custom models
                            .predict()
                            .withInputs(
                                    ClarifaiInput.forImage(ClarifaiImage.of("https://samples.clarifai.com/demo-011.jpg"))
                            ).executeSync().get();


                    Log.d("harsimarSingh", predictionResults.get(0).data().toString());



                }catch (Exception e){e.printStackTrace();}

            }
        });
        predictionsThread.start();
  */
    }

}