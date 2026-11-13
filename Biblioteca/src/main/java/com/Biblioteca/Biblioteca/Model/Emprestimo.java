package com.Biblioteca.Biblioteca.Model;

public class Emprestimo {
    private int id;
    private String livroEmprestado;
    private Usuario Usuario;
    private String data_de_emprestimo;
    private String data_de_devolucao;

    public Emprestimo(int id, String livroEmprestado, Usuario usuario, String data_de_emprestimo, String data_de_devolucao) {
        this.id = id;
        this.livroEmprestado = livroEmprestado;
        Usuario = usuario;
        this.data_de_emprestimo = data_de_emprestimo;
        this.data_de_devolucao = data_de_devolucao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLivroEmprestado() {
        return livroEmprestado;
    }

    public void setLivroEmprestado(String livroEmprestado) {
        this.livroEmprestado = livroEmprestado;
    }

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario usuario) {
        Usuario = usuario;
    }

    public String getData_de_emprestimo() {
        return data_de_emprestimo;
    }

    public void setData_de_emprestimo(String data_de_emprestimo) {
        this.data_de_emprestimo = data_de_emprestimo;
    }

    public String getData_de_devolucao() {
        return data_de_devolucao;
    }

    public void setData_de_devolucao(String data_de_devolucao) {
        this.data_de_devolucao = data_de_devolucao;
    }
}
