package com.example.dart;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ArrayList<User> users = new ArrayList<>();

    static int maxSets = 3;
    static int maxLegs = 3;
    static int maxScore = 501;

    EditText name;
    ListView currentPlayers;
    Button resume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentPlayers = findViewById(R.id.currentPlayersList);
        resume = findViewById(R.id.resume);
        updateList(users);
    }

    public void onAdd(View view) {
        name = findViewById(R.id.newPlayer);
        if(!name.getText().toString().isEmpty()) {
            users.add(new User(name.getText().toString(), 0, 0, maxScore, new ArrayList<Integer>()));
            updateList(users);
            name.setText("");
        }
    }

    public void onStart(View view) {
        if(users.size()>0) {
            for (User u : users) {
                u.setAvgList(new ArrayList<Integer>());
                u.setSets(0);
                u.setLegs(0);
                u.setScore(maxScore);
            }
            resume.setVisibility(1);
            Intent i = new Intent(view.getContext(), GameActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(getApplicationContext(),"Add user", Toast.LENGTH_LONG).show();
        }
    }

    public void onResume(View view) {
        Intent i = new Intent(view.getContext(), GameActivity.class);
        startActivity(i);
    }

    private void updateList(ArrayList<User> list) {
        ArrayList<String> names = new ArrayList<>();
        for (User u: list) {
            names.add(u.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.row_main, R.id.currentPlayerElement, names);
        currentPlayers.setAdapter(adapter);
    }

    public void onSettings(View view) {
        Intent i = new Intent(view.getContext(), SettingsActivity.class);
        startActivity(i);
    }
}