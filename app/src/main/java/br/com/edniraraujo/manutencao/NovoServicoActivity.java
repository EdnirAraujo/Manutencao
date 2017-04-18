package br.com.edniraraujo.manutencao;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.List;
import br.com.edniraraujo.manutencao.dao.ServicoDAO;
import br.com.edniraraujo.manutencao.dao.OrientadoDAO;
import br.com.edniraraujo.manutencao.model.Servico;
import br.com.edniraraujo.manutencao.model.Orientado;

public class NovoServicoActivity extends AppCompatActivity {
    public final static int CODE_NOVO_SERVICO = 1002;
    private TextInputLayout tilNomeServico;
    private Spinner spOrientado;
    private List<Orientado> orientados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_servico);
        tilNomeServico = (TextInputLayout) findViewById(R.id.tilNomeServico);
        spOrientado = (Spinner)findViewById(R.id.spOrientado);
        OrientadoDAO orientadoDAO = new OrientadoDAO(this);
        orientados = orientadoDAO.getAll();
        ArrayAdapter<Orientado> adapter = new ArrayAdapter<Orientado>(getApplicationContext(),R.layout.orientado_spinner_item, orientados);
        adapter.setDropDownViewResource(R.layout.orientado_spinner_item);
        spOrientado.setAdapter(adapter);
    }
    public void cadastrar(View v) {
        ServicoDAO servicoDAO = new ServicoDAO(this);
        Servico servico = new Servico();
        servico.setNome(tilNomeServico.getEditText().getText().toString());
        servico.setOrientado((Orientado)spOrientado.getSelectedItem());
        servicoDAO.add(servico);
        retornaParaTelaAnterior();
    }
    public void retornaParaTelaAnterior() {
        Intent intentMessage=new Intent();
        setResult(CODE_NOVO_SERVICO, intentMessage);
        finish();
    }

}
