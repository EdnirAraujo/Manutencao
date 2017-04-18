package br.com.edniraraujo.manutencao.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.LinkedList;
import java.util.List;
import br.com.edniraraujo.manutencao.model.Orientado;

public class OrientadoDAO {
    private DBOpenHelper banco;
    public OrientadoDAO(Context context) {
        banco = new DBOpenHelper(context);
    }
    public static final String TABELA_ORIENTADOS = "orientado";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_NOME = "nome";
    public List<Orientado> getAll() {
        List<Orientado> orientados = new LinkedList<>();
        String query = "SELECT * FROM " + TABELA_ORIENTADOS;
        SQLiteDatabase db = banco.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Orientado orientado = null;
        if (cursor.moveToFirst()) {
            do {
                orientado = new Orientado();
                orientado.setId(cursor.getInt(cursor.getColumnIndex(COLUNA_ID)));
                orientado.setNome(cursor.getString(cursor.getColumnIndex(COLUNA_NOME)));
                orientados.add(orientado);
            } while (cursor.moveToNext());
        }
        return orientados;
    }
    public Orientado getBy(int id) {
        SQLiteDatabase db = banco.getReadableDatabase();
        String colunas[] = { COLUNA_ID, COLUNA_NOME};
        String where = "id = " + id;
        Cursor cursor = db.query(true, TABELA_ORIENTADOS, colunas, where, null, null,
                null, null, null);
        Orientado orientado = null;
        if(cursor != null)
        {
            cursor.moveToFirst();
            orientado = new Orientado();
            orientado.setNome(cursor.getString(cursor.getColumnIndex(COLUNA_NOME)));
            orientado.setId(cursor.getInt(cursor.getColumnIndex(COLUNA_ID)));
        }
        return orientado;
    }



}
