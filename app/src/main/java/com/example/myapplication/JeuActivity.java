package com.example.myapplication;

import static java.lang.Float.parseFloat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.util.Random;

public class JeuActivity extends AppCompatActivity {

    private MenuItem itemVie;
    private MenuItem itemScore;
    private MenuItem toolbarStatutNiv;
    private TextView textViewCalcul;
    private TextInputEditText responseUtilisateur;
    private String calculString = "";
    private float resultatCalcul = 0;
    private int vie = 3;
    private int score = 0;

    private int statusNiveau = 0;
    private int minNbAl = 1;
    private int maxNbAl = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);

        // View pour le calcul
        textViewCalcul = findViewById(R.id.textViewCalcul);

        // Génération du premier calcul
        generationCalcul(this.minNbAl, this.maxNbAl);
        textViewCalcul.setText(calculString);

        // Input de la réponse
        responseUtilisateur = findViewById(R.id.reponseInput);

        // Bouton valider
        Button btnValider = findViewById(R.id.btnValidation);
        btnValider.setOnClickListener(view -> {

            // Récupération du résultat de l'utilisateur
            String saisieUtilisateur = String.valueOf(responseUtilisateur.getText());

            if(!saisieUtilisateur.isEmpty()){
                if(parseFloat(saisieUtilisateur) == resultatCalcul){
                    // Remise à 0 des valeur
                    responseUtilisateur.setText("");
                    this.calculString = "";
                    this.resultatCalcul = 0;

                    // Nouveau calcul
                    generationCalcul(this.minNbAl, this.maxNbAl);
                    textViewCalcul.setText(calculString);

                    // Ajout score
                    this.score += 1;
                    this.itemScore.setTitle(getString(R.string.toolbar_score) + " " + score);
                }else{
                    // Perte de vie
                    if(this.vie > 1){
                        this.vie -= 1;
                        this.itemVie.setTitle(getString(R.string.toolbar_vie) + " " + vie);
                    }else{
                        enregistrementDefaite();
                    }
                }
            }
        });
    }

    private void enregistrementDefaite(){
        // Ouvrir l'activité d'enrgistrement
        // Passer le score dans la nouvelle activitée
        Intent intent = new Intent(this, EnregistrementActivity.class);
        intent.putExtra("SCORE", Integer.toString(this.score));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar, menu);

        // Score et Vie + Affichage de base
        this.itemVie = menu.findItem(R.id.toolbar_vie);
        this.itemScore = menu.findItem(R.id.toolbar_score);
        this.itemScore.setTitle(getString(R.string.toolbar_score) + " 0");
        this.itemVie.setTitle(getString(R.string.toolbar_vie) + " 3");

        // Bouton pour recommencer la partie
        MenuItem btnRecommencer= menu.findItem(R.id.toolbar_recommencer);
        btnRecommencer.setOnMenuItemClickListener(view -> {
            // Remise à 0 des valeur
            resetValue();

            this.itemScore.setTitle(getString(R.string.toolbar_score) + " 0");
            this.itemVie.setTitle(getString(R.string.toolbar_vie) + " 3");

            responseUtilisateur.setText("");

            // Nouveau calcul
            generationCalcul(this.minNbAl, this.maxNbAl);
            textViewCalcul.setText(calculString);

            return true;
        });

        // Bouton pour modifier la difficultée du jeu
        // 3 niveaux : Extrême(1:999) - Moyen(1:500) - Facile(1:100)
        this.toolbarStatutNiv = menu.findItem(R.id.toolbar_niveauDiff);
        this.toolbarStatutNiv.setTitle(getString(R.string.toolbar_niveau) + " " + getString(R.string.toolbar_niveauF));

        this.toolbarStatutNiv.setOnMenuItemClickListener(view -> {
            this.statusNiveau = this.statusNiveau < 2 ? this.statusNiveau+1 : 0;
            switch (this.statusNiveau){
                case 0:
                    this.maxNbAl = 100;
                    this.toolbarStatutNiv.setTitle(getString(R.string.toolbar_niveau) + " " + getString(R.string.toolbar_niveauF));
                    break;
                case 1:
                    this.maxNbAl = 500;
                    this.toolbarStatutNiv.setTitle(getString(R.string.toolbar_niveau) + " " + getString(R.string.toolbar_niveauM));
                    break;
                case 2 :
                    this.maxNbAl = 999;
                    this.toolbarStatutNiv.setTitle(getString(R.string.toolbar_niveau) + " " + getString(R.string.toolbar_niveauH));
                    break;
            }

            // Nouveau calcul
            resetValue();
            this.itemScore.setTitle(getString(R.string.toolbar_score) + " 0");
            this.itemVie.setTitle(getString(R.string.toolbar_vie) + " 3");
            responseUtilisateur.setText("");
            generationCalcul(this.minNbAl, this.maxNbAl);
            textViewCalcul.setText(calculString);
            return true;
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void generationCalcul(int min, int max){
        Random rand = new Random();
        int premierNombre = rand.nextInt((max - min) + 1) + min;
        int secondNombre = rand.nextInt((max - min) + 1) + min;

        int signe = rand.nextInt((3) + 1);

        switch (signe){
            case 0: // Cas du +
                this.calculString += Integer.toString(premierNombre) + " + " + Integer.toString(secondNombre);
                this.resultatCalcul = premierNombre + secondNombre;
                break;
            case 1: // Cas du -
                this.calculString += Integer.toString(premierNombre) + " - " + Integer.toString(secondNombre);
                this.resultatCalcul = premierNombre - secondNombre;
                break;
            case 2: // Cas du *
                this.calculString += Integer.toString(premierNombre) + " * " + Integer.toString(secondNombre);
                this.resultatCalcul = premierNombre * secondNombre;
                break;
            case 3: // Cas du /
                premierNombre = premierNombre * secondNombre;
                this.calculString += Integer.toString(premierNombre) + " / " + Integer.toString(secondNombre);
                this.resultatCalcul = premierNombre / secondNombre;
                break;
        }
    }

    private void resetValue(){
        this.calculString = "";
        this.resultatCalcul = 0;
        this.vie = 3;
        this.score = 0;
    }
}