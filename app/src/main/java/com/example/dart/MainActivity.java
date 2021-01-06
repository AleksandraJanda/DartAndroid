package com.example.dart;

import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ArrayList<User> users = new ArrayList<>();

    static int maxSets = 3;
    static int maxScore = 501;

    EditText name;
    ListView currentPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentPlayers = findViewById(R.id.currentPlayersList);
        users.add(new User("olinka"));
        users.add(new User("marcin"));
        updateList(users);
    }

    public void onAdd(View view) {
        name = findViewById(R.id.newPlayer);
        if(!name.getText().toString().isEmpty()) {
            users.add(new User(name.getText().toString(), 0, 0, maxScore));
            updateList(users);
            name.setText("");
        }
    }

    public void onStart(View view) {
        for(User u: users) {
            u.setScore(maxScore);
        }
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