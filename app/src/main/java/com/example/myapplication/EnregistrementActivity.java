package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DAO.ScoreBaseHelper;
import com.example.myapplication.DAO.ScoreDao;
import com.example.myapplication.model.entities.Scores;
import com.google.android.material.color.utilities.Score;
import com.google.android.material.textfield.TextInputEditText;

public class EnregistrementActivity extends AppCompatActivity {

    private ScoreDao scoreDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scoreDao = new ScoreDao(new ScoreBaseHelper(this, "BDD", 1));

        setContentView(R.layout.activity_enregistrement);

        // Récupération du score
        Intent intent = getIntent();
        String VALUE_SCORE = intent.getStringExtra("SCORE");

        // Affichage du score
        TextView scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("Votre score est de : " + VALUE_SCORE);

        // Bouton quitter
        Button btnQuitter = findViewById(R.id.btnQuitter);
        btnQuitter.setOnClickListener(view -> {
            Intent secondIntent = new Intent(this, MainActivity.class);
            startActivity(secondIntent);
        });

        // Bouton pour s'enregistrer
        Button btnRegister = findViewById(R.id.btnRegister);
        TextInputEditText saisiePseudo = findViewById(R.id.inputPseudo);

        btnRegister.setOnClickListener(view -> {
            if(String.valueOf(saisiePseudo.getText()).isEmpty()){
                Toast.makeText(this, getString(R.string.INPUTPSEUDOVIDE), Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, getString(R.string.SCORECREE), Toast.LENGTH_LONG).show();

                Scores monScore = new Scores();
                monScore.setPseudo(String.valueOf(saisiePseudo.getText()));
                monScore.setScore(Integer.parseInt(VALUE_SCORE));
                scoreDao.create(monScore);

                // Ajout dans la base de données
                Intent intentRes = new Intent(this, MainActivity.class);
                startActivity(intentRes);
            }
        });
    }
}