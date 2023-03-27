package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnJouer;
    private Button btnHighscore;
    private Button btnApropos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bouton jouer
        btnJouer = findViewById(R.id.btnJouer);
        btnJouer.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, JeuActivity.class);
            startActivity(intent);
        });

        // Bouton HighScore
        btnHighscore = findViewById(R.id.btnHighscore);
        btnHighscore.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, HighscoreActivity.class);
            startActivity(intent);
        });

        // Bouton A propos
        btnApropos = findViewById(R.id.btnApropos);
        btnApropos.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Apropos.class);
            startActivity(intent);
        });
    }
}