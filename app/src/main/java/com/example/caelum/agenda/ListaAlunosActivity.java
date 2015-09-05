package com.example.caelum.agenda;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;


public class ListaAlunosActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        List<String> alunos = Arrays.asList("Ze", "Joao");
        ListView lv = (ListView) findViewById(R.id.lista);
        lv.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, alunos));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nome = (String) parent.getItemAtPosition(position);
                Log.i("Nome", nome);
            }
        });
    }


}
