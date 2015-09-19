package com.example.caelum.agenda;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;


public class ListaAlunosActivity extends ActionBarActivity {
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        Button b = (Button) findViewById(R.id.lista_aluno_floating_button);
        lista = (ListView) findViewById(R.id.lista);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });
        registerForContextMenu(lista);
    }
    @Override
    public void onResume(){
        super.onResume();
        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos = dao.getLista();
       final  ListView lv = (ListView) findViewById(R.id.lista);
        lv.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, alunos));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                intent.putExtra("aluno", (Aluno) lv.getItemAtPosition(position));
                startActivity(intent);
            }
        });
    }

    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo info){

        MenuItem ligar = menu.add("Ligar");
        Intent intentLigar = new Intent(Intent.ACTION_CALL);
        intentLigar.setData(Uri.parse("tel:555555555" ));
        ligar.setIntent(intentLigar);
        menu.add("Enviar SMS");
        menu.add("Achar no Mapa");
        menu.add("Navegar no Site");
        final AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) info;
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                AlunoDAO alunoDAO = new AlunoDAO(ListaAlunosActivity.this);
                Aluno aluno = (Aluno) lista.getAdapter().getItem(adapterContextMenuInfo.position);
                alunoDAO.deletar(aluno);
                alunoDAO.close();
                carregaLista();
                return false;
            }

        });
    }

    private void carregaLista(){
        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> listaAluno = dao.getLista();
        dao.close();
        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, listaAluno);
        this.lista.setAdapter(adapter);
    }

}
