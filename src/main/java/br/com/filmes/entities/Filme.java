package br.com.filmes.entities;

public class Filme {
    private int id;
    private String nome;
    private String diretor;
    private int anoLancamento;
    private int avaliacao;

    public Filme() {
    }

    public Filme(int id, String nome, String diretor, int anoLancamento, int avaliacao) {
        this.id = id;
        this.nome = nome;
        this.diretor = diretor;
        this.anoLancamento = anoLancamento;
        this.avaliacao = avaliacao;
    }


//ID
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


//NOME
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }


//DIRETOR
    public String getDiretor() {
        return diretor;
    }
    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }


//ANO LANÇAMENTO
    public int getAnoLancamento() {
        return anoLancamento;
    }
    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }


//AVALIAÇÃO
    public int getAvaliacao() {
        return avaliacao;
    }
    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }
}
