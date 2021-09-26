package br.pucminas.projetosoftware.aluguelcarro.model;

public abstract class Usuario {
    protected String id;
    protected String email;
    protected String senha;

    public Usuario() {
        this.id = "";
        this.email = "";
        this.senha = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
