package com.example.caelum.agenda;

import android.widget.EditText;
import android.widget.RatingBar;

/**
 * Created by android5243 on 12/09/15.
 */
public class FormularioHelper {

    private EditText nome;
    private EditText telefone;
    private EditText site;
    private RatingBar nota;
    private EditText endereco;
    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity){
        this.aluno = new Aluno();
        this.nome = (EditText) activity.findViewById(R.id.formulario_nome);
        this.telefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        this.site = (EditText) activity.findViewById(R.id.formulario_site);
        this.nota = (RatingBar) activity.findViewById(R.id.formulario_nota);
        this.endereco = (EditText) activity.findViewById(R.id.formulario_endereco);

    }
    public Aluno pegaAlunoDoFormulario(){
        aluno.setEndereco(endereco.getText().toString());
        aluno.setNome(nome.getText().toString());
        aluno.setSite(site.getText().toString());
        aluno.setTelefone(telefone.getText().toString());
        aluno.setNota(Double.valueOf(nota.getProgress()));
        return aluno;
    }
}
