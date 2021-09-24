package br.pucminas.projetosoftware.aluguelcarro.model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Cliente {
    private String id;
    private String nome;
    private String cpf;
    private String rg;
    private String email;
    private String endereco;
    private String profissao;
    private String entidadeEmpregadora;
    private ArrayList<Double> rendimentosAuferidos;

    // All Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getEntidadeEmpregadora() {
        return entidadeEmpregadora;
    }

    public void setEntidadeEmpregadora(String entidadeEmpregadora) {
        this.entidadeEmpregadora = entidadeEmpregadora;
    }

    public ArrayList<Double> getRendimentosAuferidos() {
        return rendimentosAuferidos;
    }

    public void setRendimentosAuferidos(ArrayList<Double> rendimentosAuferidos) {
        this.rendimentosAuferidos = rendimentosAuferidos;
    }

    public Cliente(String id, String nome, String cpf, String rg, String email, String endereco, String profissao,
            String entidadeEmpregadora, ArrayList<Double> rendimentosAuferidos) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.email = email;
        this.endereco = endereco;
        this.profissao = profissao;
        this.entidadeEmpregadora = entidadeEmpregadora;
        this.rendimentosAuferidos = rendimentosAuferidos;
    }

    public static Cliente loadFromTextFile(String fileName) throws IOException {
        // reads the entire file contents
        String content = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);

        // splits the content by the new line character
        String[] lines = content.split("\n");

        // Converts the last line into a list of double values
        ArrayList<Double> rendimentosAuferidos = new ArrayList<Double>();
        String[] rendimentos = lines[8].split(";");
        for (String rendimento : rendimentos) {
            rendimentosAuferidos.add(Double.parseDouble(rendimento));
        }

        // Extract all fields into local variables
        String id = lines[0];
        String nome = lines[1];
        String cpf = lines[2];
        String rg = lines[3];
        String email = lines[4];
        String endereco = lines[5];
        String profissao = lines[6];
        String entidadeEmpregadora = lines[7];

        // creates a new instance of the class with all the fields
        return new Cliente(id, nome, cpf, rg, email, endereco, profissao, entidadeEmpregadora, rendimentosAuferidos);

    }

}
