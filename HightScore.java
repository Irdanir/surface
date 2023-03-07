package ru.pavlenty.surfacegame2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class HightScore extends AppCompatActivity {
    private TextView textView, textView2, textView3, textView4;
    private SharedPreferences sharedPreferences;

    private static final String SHARE_PREF_NAME = "HIGH_SCORE";
    private static final int DEFAULT_SCORE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hight_score);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);

        sharedPreferences = getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);

        textView.setText("1. " + getHighScore(1));
        textView2.setText("2. " + getHighScore(2));
        textView3.setText("3. " + getHighScore(3));
        textView4.setText("4. " + getHighScore(4));
    }

    private int getHighScore(int rank) {
        return sharedPreferences.getInt("score" + rank, DEFAULT_SCORE);
    }
}