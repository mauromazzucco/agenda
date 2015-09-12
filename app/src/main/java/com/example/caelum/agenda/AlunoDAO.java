package com.example.caelum.agenda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android5243 on 12/09/15.
 */
public class AlunoDAO extends SQLiteOpenHelper {
    private static final int VERSAO = 1;
    private static final String TABELA = "Alunos";
    private static final String DATABASE = "CadastroCaelum";


    public AlunoDAO(Context context){
        super(context, DATABASE, null, VERSAO);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String ddl = "CREATE TABLE " + TABELA
                    + "(id INTEGER PRIMARY KEY, "
                    + "nome TEXT NOT NULL,"
                    + "telefone TEXT,"
                    + "endereco TEXT,"
                    + "site TEXT,"
                    + "nota REAL);";
        db.execSQL(ddl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXITS " + TABELA;
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(Aluno aluno){
        ContentValues cv = new ContentValues();
        cv.put("nome", aluno.getNome());
        cv.put("telefone", aluno.getTelefone());
        cv.put("site", aluno.getSite());
        cv.put("endereco", aluno.getEndereco());
        cv.put("nota", aluno.getNota());
        getWritableDatabase().insert(TABELA, null, cv);
    }

    public List<Aluno> getLista(){
        SQLiteDatabase db = getReadableDatabase();
        List<Aluno> listaAlunos = new ArrayList();
        Cursor c = db.rawQuery("SELECT * FROM " + TABELA + ";", null);
        while(c.moveToNext()){
            Aluno aluno = new Aluno();
            aluno.setId(c.getLong(c.getColumnIndex("id")));
            aluno.setNome(c.getString(c.getColumnIndex("nome")));
            aluno.setEndereco(c.getString(c.getColumnIndex("endereco")));
            aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
            aluno.setSite(c.getString(c.getColumnIndex("site")));
            aluno.setNota(c.getDouble(c.getColumnIndex("nota")));
            listaAlunos.add(aluno);
        }
        return listaAlunos;
    }
}
