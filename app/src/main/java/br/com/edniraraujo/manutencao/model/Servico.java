package br.com.edniraraujo.manutencao.model;

public class Servico {
    private int id;
    private String nome;
    private Orientado orientado;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Orientado getOrientado() {
        return orientado;
    }
    public void setOrientado(Orientado orientado) {
        this.orientado = orientado;
    }


}
