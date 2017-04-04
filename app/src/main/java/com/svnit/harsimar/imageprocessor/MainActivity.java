package com.svnit.harsimar.imageprocessor;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.api.request.ClarifaiRequest;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.input.image.ClarifaiImage;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;
import clarifai2.exception.ClarifaiException;
import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ClarifaiClient client = new ClarifaiBuilder("TWLwx0Svio0V5WKuRZ1HejSVyYZtYTu8MydOT3yI", "1Lc3sttcMfHXzUcgZe3HVxkyWLHo77C3H6LmqxNs")
                .client(new OkHttpClient()).buildSync();

        Thread predictionsThread =new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ClarifaiClient client = new ClarifaiBuilder("TWLwx0Svio0V5WKuRZ1HejSVyYZtYTu8MydOT3yI", "1Lc3sttcMfHXzUcgZe3HVxkyWLHo77C3H6LmqxNs")
                            .client(new OkHttpClient()).buildSync();

                    final List<ClarifaiOutput<Concept>>
                            predictionResults = client.getDefaultModels().generalModel() // You can also do Clarifai.getModelByID("id") to get custom models
                            .predict()
                            .withInputs(
                                    ClarifaiInput.forImage(ClarifaiImage.of("https://samples.clarifai.com/metro-north.jpg"))
                            ).executeSync().get();


                    Log.d("harsimarSingh", predictionResults.toString());

                    client.getDefaultModels().generalModel().predict()
                            .withInputs(
                                    ClarifaiInput.forImage(ClarifaiImage.of("https://samples.clarifai.com/metro-north.jpg"))
                            )
                            .executeSync();
                }catch (Exception e){e.printStackTrace();};

            }
        });
        predictionsThread.start();
    }
}