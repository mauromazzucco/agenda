package com.example.caelum.agenda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import java.io.File;

/**
 * Created by android5243 on 12/09/15.
 */
public class FormularioHelper {
    private ImageView foto;
    private Button fotoButton;
    private EditText nome;
    private EditText telefone;
    private EditText site;
    private RatingBar nota;
    private EditText endereco;
    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity){
        this.foto = (ImageView) activity.findViewById(R.id.formulario_foto);
        this.fotoButton = (Button) activity.findViewById(R.id.formulario_foto_button);
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
        aluno.setCaminhoFoto((String) foto.getTag());
        return aluno;
    }

    public void preencheFormulario(Aluno aluno)
    {   Uri localFoto = Uri.fromFile(new File(aluno.getCaminhoFoto()));
        this.foto.setImageURI(localFoto);
        this.aluno = aluno;
        this.nome.setText(aluno.getNome());
        this.telefone.setText(aluno.getTelefone());
        this.site.setText(aluno.getSite());
        this.nota.setProgress(aluno.getNota().intValue());
        this.endereco.setText(aluno.getEndereco());

    }

    public ImageView getFoto() {
        return foto;
    }

    public void setFoto(ImageView foto) {
        this.foto = foto;
    }

    public Button getFotoButton() {
        return fotoButton;
    }

    public void setFotoButton(Button fotoButton) {
        this.fotoButton = fotoButton;
    }

    public void carregaImagem(String localArquivoFoto){
        Bitmap imagemFoto = BitmapFactory.decodeFile(localArquivoFoto);
        Bitmap imagemFotoReduzida = Bitmap.createScaledBitmap(imagemFoto, imagemFoto.getWidth(), 300, true);
        foto.setImageBitmap(imagemFotoReduzida);
        foto.setTag(localArquivoFoto);
        foto.setScaleType(ImageView.ScaleType.FIT_XY);
    }
}


