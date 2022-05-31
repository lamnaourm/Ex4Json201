package com.example.ex3json201;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBPers extends SQLiteOpenHelper {

    public static String DBNAME = "personnes.db";
    public static String TBNAME = "personne";
    public static String COL1 = "nom";
    public static String COL2 = "prenom";
    public static String COL3 = "genre";
    public static String COL4 = "age";

    public MyDBPers(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table " + TBNAME + "(" + COL1 + " text primary key," + COL2 + " text," + COL3 + " text," + COL4 + " integer)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "drop table " + TBNAME;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public static long insert_personne(SQLiteDatabase sqLiteDatabase, Personne p){
        ContentValues ct = new ContentValues();
        ct.put(COL1,p.getNom());
        ct.put(COL2,p.getPrenom());
        ct.put(COL3,p.getGenre());
        ct.put(COL4,p.getAge());
        return sqLiteDatabase.insert(TBNAME,null,ct);
    }
}
