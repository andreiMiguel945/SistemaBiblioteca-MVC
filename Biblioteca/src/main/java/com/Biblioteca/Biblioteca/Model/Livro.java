package com.Biblioteca.Biblioteca.Model;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private String data_de_lancamento;
    private Categoria categoria;

    public Livro(int id, String titulo, String autor, String data_de_lancamento, Categoria categoria) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.data_de_lancamento = data_de_lancamento;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public String getData_de_lancamento() {
        return data_de_lancamento;
    }
    public void setData_de_lancamento(String data_de_lancamento) {
        this.data_de_lancamento = data_de_lancamento;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
