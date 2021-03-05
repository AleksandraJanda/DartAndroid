package com.example.dart;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private int position = 0;
    private ArrayList<Integer> equationList = new ArrayList<>();
    ListView scoreboard;
    EditText points;
    Switch doubleNumber,tripleNumber;
    Button newGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
//        hideSystemUI();
        scoreboard = findViewById(R.id.scoreboard);
        points = findViewById(R.id.points);
        doubleNumber = findViewById(R.id.doubleNumber);
        tripleNumber = findViewById(R.id.tripleNumber);
        newGame = findViewById(R.id.newGame);

        MainActivity.users.get(0).setSelected(true);

        updateList(MainActivity.users);
    }


    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    public void onNewGame(View view) {
        newGame.setVisibility(View.INVISIBLE);
        resetSets();
        resetLegs();
        resetScore();
        updateList(MainActivity.users);
    }

    public void onCount(View view) {
        if(!points.getText().toString().isEmpty()) {
            for (int i = 0; i < MainActivity.users.size(); i++) {
                if (MainActivity.users.get(i).isSelected()) {
                    position = i;
                    MainActivity.users.get(i).setScore(
                            MainActivity.users.get(i).getScore()
                                    - Integer.parseInt(points.getText().toString()));
                    MainActivity.users.get(i).setSelected(false);
                    List<Integer> currentList = MainActivity.users.get(i).getAvgList();
                    currentList.add(Integer.parseInt(points.getText().toString()));
                    MainActivity.users.get(i).setAvgList(currentList);
                    if(MainActivity.users.get(i).getScore() <= 0) {
                        legsDone(i);
                        resetScore();
                    }
                    if(MainActivity.users.get(i).getLegs() == MainActivity.maxLegs) {
                        setsDone(i);
                        resetLegs();
                    }
                    if(MainActivity.users.get(i).getSets() == MainActivity.maxSets) {
                        win(MainActivity.users.get(i).getName());
                    }
                }
            }
            position++;
            if (position == MainActivity.users.size()) {
                position = 0;
            }

            MainActivity.users.get(position).setSelected(true);
            resetEquation();
            updateList(MainActivity.users);
        }
    }

    public void addNumber(View view) {
        Button b = (Button) view;
        int number = Integer.parseInt(b.getText().toString());
        if(doubleNumber.isChecked()){
            number *= 2;
        } else if(tripleNumber.isChecked()) {
            number *= 3;
        }
        equationList.add(number);
        points.setText(String.valueOf(calculateEquation()));
    }

    public void onDouble(View view) {
        tripleNumber.setChecked(false);
    }

    public void onTriple(View view) {
        doubleNumber.setChecked(false);
    }

    public void undoNumber(View view) {
        removeLastNumber();
        points.setText(String.valueOf(calculateEquation()));
    }

    public void resetEquation(View view) {
        resetEquation();
    }

    private void updateList(ArrayList<User> list) {
        ScoreboardItemAdapter adapter = new ScoreboardItemAdapter(list, getApplicationContext());
        scoreboard.setAdapter(adapter);
    }

    private void legsDone(int i) {
        MainActivity.users.get(i).setLegs(MainActivity.users.get(i).getLegs()+1);
    }

    private void setsDone(int i) {
        MainActivity.users.get(i).setSets(MainActivity.users.get(i).getSets()+1);
    }

    private void win(String name) {
        newGame.setVisibility(View.VISIBLE);
        Toast.makeText(getApplicationContext(),"Winner: " + name, Toast.LENGTH_LONG).show();
    }

    private void resetScore() {
        for(User u: MainActivity.users) {
            u.setScore(MainActivity.maxScore);
        }
    }

    private void resetLegs() {
        for(User u: MainActivity.users) {
            u.setLegs(0);
        }
    }

    private void resetSets() {
        for(User u: MainActivity.users) {
            u.setSets(0);
        }
    }

    private int calculateEquation() {
        int result = 0;
        for(Integer i: equationList) {
            result += i;
        }
        return result;
    }

    private void removeLastNumber() {
        if(equationList.size()>0) {
            equationList.remove(equationList.size() - 1);
        }

    }

    private void resetEquation() {
        points.setText("0");
        equationList = new ArrayList<>();
    }

    public void checkPoints(View view) {
        List<Integer> currentList = MainActivity.users.get(position).getAvgList();
        int p70 = 0;
        int p100 = 0;
        int p140 = 0;
        int p180 = 0;
        if(currentList.size()>0) {
            for (int x : currentList) {
                if (x >= 70 && x < 100) {
                    p70++;
                } else if (x >= 100 && x < 140) {
                    p100++;
                }
                if (x >= 140 && x < 180) {
                    p140++;
                }
                if (x == 180) {
                    p180++;
                }
            }
        }
        Toast.makeText(getApplicationContext(),
                "70: " + p70 + ", " +
                        "100: " + p100 + ", " +
                        "140: " + p140 + ", " +
                        "180: " + p180,
                Toast.LENGTH_LONG).show();
    }
}