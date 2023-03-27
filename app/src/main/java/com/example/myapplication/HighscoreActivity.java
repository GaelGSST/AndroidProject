package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.DAO.ScoreBaseHelper;
import com.example.myapplication.DAO.ScoreDao;
import com.example.myapplication.model.entities.Scores;

import java.util.List;

public class HighscoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        TextView textViewResultat = findViewById(R.id.textViewHighScore);

        ScoreDao scoreDao = new ScoreDao(new ScoreBaseHelper(this, "BDD", 1));

        //Scores monScore = scoreDao.lastOrNull();
        String affichageFin = "";

        List<Scores> listScore = scoreDao.lastThreeBestScores();

        for(Scores score : listScore){
            affichageFin += score.getPseudo() + " | " + score.getScore() + "\n";
        }

        textViewResultat.setText(affichageFin);

    }
}