package com.example.myapplication.DAO;

import android.content.Context;

public class ScoreBaseHelper extends DataBaseHelper{
    public ScoreBaseHelper(Context context, String dataBaseName, int dataBaseVersion) {
        super(context, dataBaseName, dataBaseVersion);
    }

    @Override
    protected String getCreationSql() {
        return "CREATE TABLE IF NOT EXISTS " + ScoreDao.TABLE_NAME + " (" +
                " id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ScoreDao.COLUMN_PSEUDO + " VARCHAR(255) NOT NULL, " +
                ScoreDao.COLUMN_SCORE + " INT NOT NULL " +
                ")";
    }

    @Override
    protected String getDeleteSql() {
        return "DROP TABLE IF EXISTS " + ScoreDao.TABLE_NAME;
    }
}
