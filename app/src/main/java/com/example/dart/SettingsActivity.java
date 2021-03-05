package com.example.dart;

import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SettingsActivity extends AppCompatActivity {

    EditText maxSets, maxScore, maxLegs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        maxScore = findViewById(R.id.maxScore);
        maxSets = findViewById(R.id.maxSets);
        maxLegs = findViewById(R.id.maxLegs);
    }

    public void changeSettings(View view) {
        MainActivity.maxSets = Integer.parseInt(maxSets.getText().toString());
        MainActivity.maxScore = Integer.parseInt(maxScore.getText().toString());
        MainActivity.maxLegs = Integer.parseInt(maxLegs.getText().toString());
        finish();
    }

}