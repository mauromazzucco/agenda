package com.example.caelum.agenda;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.net.URI;


public class FormularioActivity extends ActionBarActivity {

    private FormularioHelper helper;
    private String localArquivoFoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        this.helper = new FormularioHelper(this);

        Button foto = helper.getFotoButton();
        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localArquivoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri localFoto = Uri.fromFile(new File(localArquivoFoto));

                intent.putExtra(MediaStore.EXTRA_OUTPUT, localFoto);
                startActivityForResult(intent, 123);

            }
        });
        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");
        if(aluno != null) {
            helper.preencheFormulario(aluno);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:
                Aluno aluno  = helper.pegaAlunoDoFormulario();
                AlunoDAO dao = new AlunoDAO(this);
                if(aluno.getId() != null){
                    dao.update(aluno);
                }else{
                    dao.insere(aluno);
                }
                dao.close();
                finish();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        if(requestCode == 123){
            if(resultCode == Activity.RESULT_OK){
                helper.carregaImagem(this.localArquivoFoto);
            }else{
                this.localArquivoFoto = null;
            }
        }
    }
}
