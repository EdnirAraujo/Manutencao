package br.com.edniraraujo.manutencao.dao;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import java.util.LinkedList;
        import java.util.List;
        import br.com.edniraraujo.manutencao.model.Orientado;
        import br.com.edniraraujo.manutencao.model.Servico;
public class ServicoDAO {
    private SQLiteDatabase db;
    private DBOpenHelper banco;
    public ServicoDAO(Context context) {
        banco = new DBOpenHelper(context);
    }
    private static final String TABELA_SERVICO = "servico";
    private static final String COLUNA_ID = "id";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_ORIENTADO_ID = "orientado_id";

    public String add(Servico servico){
        long resultado;
        SQLiteDatabase db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUNA_NOME, servico.getNome());
        values.put(COLUNA_ORIENTADO_ID, servico.getOrientado().getId());
        resultado = db.insert(TABELA_SERVICO,
                null,
                values);
        db.close();
        if(resultado == -1) {
            return "Erro ao inserir registro";
        } else {
            return "Registro inserido com sucesso";
        }
    }
    public List<Servico> getAll() {
        List<Servico> servicos = new LinkedList<>();
        String rawQuery = "SELECT t.*, c.nome FROM " +
                ServicoDAO.TABELA_SERVICO + " t INNER JOIN " +
                OrientadoDAO.TABELA_ORIENTADOS
                + " c ON t." + ServicoDAO.COLUNA_ID + " = c." +
                OrientadoDAO.COLUNA_ID +
                " ORDER BY " + ServicoDAO.COLUNA_NOME + " ASC";
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(rawQuery, null);
        Servico servico = null;
        if (cursor.moveToFirst()) {
            do {
                servico = new Servico();
                servico.setId(cursor.getInt(0));
                servico.setNome(cursor.getString(2));
                servico.setOrientado(new Orientado(cursor.getInt(1),
                        cursor.getString(3)));
                servicos.add(servico);
            } while (cursor.moveToNext());
        }
        return servicos;
    }
}
