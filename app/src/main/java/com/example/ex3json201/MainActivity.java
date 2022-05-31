package com.example.ex3json201;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ListView ls;
    MyDBPers db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new MyDBPers(this);
        ls = findViewById(R.id.lst);
        ArrayList<Personne> pers = getPersonsFromJSON();

        ArrayList<HashMap<String, Object>> list_pers = new ArrayList<>();
        for(Personne p : pers){
            HashMap<String, Object> item = new HashMap<>();
            item.put("pnom","Nom : "+p.getNom());
            item.put("pprenom","Prenom : " + p.getPrenom());
            if(p.getGenre().equalsIgnoreCase("homme"))
                item.put("pimage",R.drawable.homme);
            else
                item.put("pimage",R.drawable.femme);
            list_pers.add(item);
        }
        String[] from= {"pnom","pprenom","pimage"};
        int[] to = {R.id.tnom, R.id.tprenom,R.id.pimage};

        SimpleAdapter ad = new SimpleAdapter(this,list_pers,R.layout.item_pers,from,to);
        ls.setAdapter(ad);


        for(Personne p : pers)
            MyDBPers.insert_personne(db.getWritableDatabase(),p);
    }

    public String loadJSONFromRaw(int resId) {
        try {
            InputStream in = getResources().openRawResource(resId);
            byte[] data = new byte[in.available()];
            in.read(data);
            in.close();
            return new String(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public ArrayList<Personne> getPersonsFromJSON() {
        ArrayList<Personne> pers = new ArrayList<>();

        try {
            JSONArray arr = new JSONArray(loadJSONFromRaw(R.raw.personnes));

            for (int i = 0; i < arr.length(); i++) {
                Personne p = new Personne();
                JSONObject ob = arr.getJSONObject(i);
                p.setNom(ob.getString("nom"));
                p.setPrenom(ob.getString("prenom"));
                p.setGenre(ob.getString("genre"));
                p.setAge(ob.getInt("age"));

                pers.add(p);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pers;
    }
}