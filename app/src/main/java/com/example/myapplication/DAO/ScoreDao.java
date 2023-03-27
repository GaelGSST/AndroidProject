package com.example.myapplication.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.example.myapplication.model.entities.Scores;

public class ScoreDao extends BaseDao<Scores> {
    public ScoreDao(DataBaseHelper helper) {
        super(helper);
    }

    public static String TABLE_NAME = "Scores";
    public static String COLUMN_PSEUDO = "Pseudo";
    public static String COLUMN_SCORE = "Score";

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected void putValues(ContentValues values, Scores entity) {
        values.put(COLUMN_PSEUDO, entity.getPseudo());
        values.put(COLUMN_SCORE, entity.getScore());
    }

    @Override
    protected Scores getEntity(Cursor cursor) {
        Integer indexPremierElement = cursor.getColumnIndex(COLUMN_PSEUDO);
        Integer indexDeuxiemeElement = cursor.getColumnIndex(COLUMN_SCORE);
        Log.d("JGER", ""+cursor.getCount());

        Scores monScore = new Scores();

        monScore.setPseudo(cursor.getString(indexPremierElement));
        monScore.setScore(cursor.getInt(indexDeuxiemeElement));

        return monScore;
    }
}
